package classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.x.protobuf.MysqlxPrepare.Prepare;

public class User {
	private String email;
	private String name;
	private String id;
	private String individualRank;
	private String correctQuestion;

	public User(String email, String name) {
		super();
		this.email = email;
		this.name = name;
	}

	public User() {
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIndividualRank() {
		return individualRank;
	}

	public void setIndividualRank(String individualRank) {
		this.individualRank = individualRank;
	}

	public String getCorrectQuestion() {
		return correctQuestion;
	}

	public void setCorrectQuestion(String correctQuestion) {
		this.correctQuestion = correctQuestion;
	}

	int addUser() {
		int flag = 0;
		Connection conn = null;
		try {
			conn = DBConnection.getDbConnection();
			PreparedStatement st = conn.prepareCall(StaticString.addStudent);
			st.setString(1, name.replace("\n", "").replace("\r", ""));
			st.setString(2, email.replace("\n", "").replace("\r", ""));
			flag = st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return flag;
	}

	List<User> getTopTenUserList() {
		List<User> list = new ArrayList<User>();
		try {
			Connection conn = DBConnection.getDbConnection();
			PreparedStatement pre = conn.prepareStatement(StaticString.getTopTenRankers);
			ResultSet rs = pre.executeQuery();
			System.out.print("Name" + "     ");
			System.out.print("Email" + "     ");
			System.out.print("Rank" + "      ");
			System.out.print("CorrectAnswer" + "     ");
			System.out.println("StudentId");
			int i=1;
			while (rs.next()) {
				User user1 = new User();
				user1.setName(rs.getNString("Name"));
				user1.setEmail(rs.getString("Email"));
				System.out.print(rs.getString("Name") + "        ");
				System.out.print(rs.getString("Email") + "       ");
				System.out.print(i+ "       ");
				System.out.print(rs.getString("CorrectQuestion") + "      ");
				System.out.println(rs.getString("StudentId"));
				list.add(user1);
				i++;
			}
			System.out.println(list.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	User getUserRankById(String emailId) {
		User user = new User();
		try {
			Connection conn = DBConnection.getDbConnection();
			PreparedStatement prepst = conn.prepareStatement(StaticString.getRankById);
			prepst.setString(1, emailId);
			ResultSet rs = prepst.executeQuery();
			System.out.print("Name" + "     ");
			System.out.print("Email" + "     ");
			System.out.print("Rank" + "      ");
			System.out.print("CorrectAnswer" + "     ");
			System.out.println("StudentId");
			while (rs.next()) {
				user.setName(rs.getString("Name"));
				user.setEmail(rs.getString("Email"));
				user.setId(rs.getString("StudentId"));
				user.setIndividualRank(rs.getString("IndividualRank"));
				user.setCorrectQuestion(rs.getString("CorrectQuestion"));
				System.out.print(rs.getString("Name") + "        ");
				System.out.print(rs.getString("Email") + "       ");
				System.out.print(rs.getString("IndividualRank") + "       ");
				System.out.print(rs.getString("CorrectQuestion") + "      ");
				System.out.println(rs.getString("StudentId"));
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return user;
	}

	/*
	 * User getUserDetailsById(String emailId) { User user = null; try {
	 * Connection conn = DBConnection.getDbConnection(); String sql =
	 * "SELECT Id,Name,Email FROM student GROUP BY Id\r\n" +
	 * "ORDER BY createdOn DESC\r\n" + "LIMIT 1 "; PreparedStatement prepstat =
	 * conn.prepareStatement(sql); System.out.println(sql); //
	 * prepstat.setString(1, emailId+"%"); ResultSet rs =
	 * prepstat.executeQuery(); System.out.println("90" + emailId); while
	 * (rs.next()) { System.out.println("92" + rs.getString("email")); user =
	 * new User(); user.setEmail(rs.getString("email"));
	 * user.setId(rs.getString("Id")); user.setName(rs.getString("name")); } }
	 * catch (ClassNotFoundException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } catch (SQLException e) { // TODO Auto-generated
	 * catch block e.printStackTrace(); }
	 * 
	 * return user; }
	 */

	User getUserDetailById(String emailId) {
		User user = null;
		try {
			Connection conn = DBConnection.getDbConnection();
			PreparedStatement prepst = conn.prepareStatement(StaticString.getUser);
			prepst.setString(1, emailId);
			ResultSet rs = prepst.executeQuery();
			System.out.println("181"+emailId);
			
			while (rs.next()) {
				System.out.println("183");
				user=new User();
				user.setName(rs.getString("Name"));
				user.setEmail(rs.getString("Email"));
				user.setId(rs.getString("Id"));
				user.setIndividualRank(rs.getString("individualRank"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
}