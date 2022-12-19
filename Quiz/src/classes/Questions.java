package classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Questions {
	private String question;
	private String optionA;
	private String optionB;
	private String optionC;
	private String optionD;
	private String correctAns;
	private int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getOptionA() {
		return optionA;
	}
	public void setOptionA(String optionA) {
		this.optionA = optionA;
	}
	public String getOptionB() {
		return optionB;
	}
	public void setOptionB(String optionB) {
		this.optionB = optionB;
	}
	public String getOptionC() {
		return optionC;
	}
	public void setOptionC(String optionC) {
		this.optionC = optionC;
	}
	
	public String getOptionD() {
		return optionD;
	}
	public void setOptionD(String optionD) {
		this.optionD = optionD;
	}
	public String getCorrectAns() {
		return correctAns;
	}
	public void setCorrectAns(String correctAns) {
		this.correctAns = correctAns;
	}
	public List<Questions> getQuestionList(){
		List<Questions> list = new ArrayList<Questions>();
		
		try {
			Connection conn = DBConnection.getDbConnection();
			PreparedStatement st=conn.prepareCall(StaticString.getQuestionList);
			ResultSet rs=st.executeQuery();
			while(rs.next()) {
				Questions que=new Questions();
				que.setQuestion(rs.getNString("Question"));
				que.setOptionA(rs.getNString("OptionA"));
				que.setOptionB(rs.getNString("OptionB"));
				que.setOptionC(rs.getNString("OptionC"));
				que.setOptionD(rs.getNString("OptionD"));
				que.setCorrectAns(rs.getNString("CorrectAnswer"));
				que.setId(rs.getInt("Id"));
				list.add(que);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
}