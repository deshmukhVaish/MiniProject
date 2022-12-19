package classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class QuestionAns {
	private String questionId;
	private String option;
	private String userId;
	public String getQuestionId() {
		return questionId;
	}
	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}
	public String getOption() {
		return option;
	}
	public void setOption(String option) {
		this.option = option;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public int submitAnswerOfQuestion(Questions questions, String selectedOption,String StudentId){
		int flag=0;
		try {
			Connection conn = DBConnection.getDbConnection();
			PreparedStatement st = conn.prepareStatement(StaticString.saveUserAns);
			st.setInt(1, questions.getId());
			st.setString(2, selectedOption);
			st.setString(3, StudentId);
			flag = st.executeUpdate();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
}