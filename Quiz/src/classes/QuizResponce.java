package classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class QuizResponce {
	private String rank;
	private int attemptedQue;
	private int skipQue;
	private int correctAnswer;
	private String grad;
	private String UserId;

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public int getAttemptedQue() {
		return attemptedQue;
	}

	public void setAttemptedQue(int attemptedQue) {
		this.attemptedQue = attemptedQue;
	}

	public int getSkipQue() {
		return skipQue;
	}

	public void setSkipQue(int skipQue) {
		this.skipQue = skipQue;
	}

	public int getCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(int correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	public String getGrad() {
		return grad;
	}

	public void setGrad(String grad) {
		this.grad = grad;
	}

	public String getUserId() {
		return UserId;
	}

	public void setUserId(String userId) {
		UserId = userId;
	}

	int addQuizSummary(int attemptedQuestion, int skipQuestion, int correctQuestion,String studentId) {
		int flag = 0;
		int individualRank = 0;
		try {
			System.out.println("Your Quiz Has Been Completed..! Thanks for giving the Quiz..!");
			System.out.println("Below is Your Quiz Result:");
			System.out.println("Number of Attempted Question:-" + attemptedQuestion);
			System.out.println("Number of Skip Question:-" + skipQuestion);
			System.out.println("Number of Correct Answers:-" + correctQuestion);
			String grade = null;
			if (correctQuestion > 7) {
				grade = "A";
			} else if (correctQuestion > 5 || correctQuestion < 8) {
				grade = "B";
			} else if (correctQuestion == 5) {
				grade = "C";
			} else {
				grade = "fail";
			}
			System.out.println("Based On Your Correct Answer Your Grade is: " + grade);
				
			Connection conn = DBConnection.getDbConnection();
			
			PreparedStatement preparedStatement = conn.prepareStatement(StaticString.getRank);
			preparedStatement.setInt(1, correctQuestion);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				individualRank = rs.getInt(1)+1;
			}
			System.out.println("Your rank is " + individualRank);
			PreparedStatement preparedStatementaddSummary = conn.prepareStatement(StaticString.addSummary);
			preparedStatementaddSummary.setInt(1, individualRank);
			preparedStatementaddSummary.setInt(2, attemptedQuestion);
			preparedStatementaddSummary.setInt(3, skipQuestion);
			preparedStatementaddSummary.setInt(4, correctQuestion);
			preparedStatementaddSummary.setString(5, grade);
			preparedStatementaddSummary.setString(6, studentId);
			flag = preparedStatementaddSummary.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return individualRank;
	}
}