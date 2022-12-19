package classes;

public class StaticString {
	public static String addStudent = "insert into student(Name,Email) values(?,?)";
	public static String getQuestionList = "select Question,OptionA,OptionB,OptionC,OptionD,CorrectAnswer,Id from question";
	public static String saveUserAns = "insert into selectedanswer(QuestionId,Options,StudentId) values(?,?,?)";
	public static String addSummary = "insert into quizresult(IndividualRank,AttemptedQuestion,SkipQuestion,CorrectQuestion,Grade,StudentId) values (?,?,?,?,?,?)";
	public static String getRank = "select count(1) from quizresult where CorrectQuestion >= ?";
	public static String getTopTenRankers = "select IndividualRank,CorrectQuestion,StudentId,Name, Email from quizresult\r\n"
			+ "inner join student on student.Id=quizresult.StudentId\r\n"
			+ " GROUP BY StudentId ORDER BY CorrectQuestion DESC LIMIT 10;";
	public static String getRankById = "select IndividualRank,CorrectQuestion,StudentId,Name, Email from quizresult\r\n"
			+ "inner join student on student.Id=quizresult.StudentId\r\n" + "where Email=?";
	public static String getUser = "select Name,Id,Email,\r\n"
			+ "(select count(1) from quizresult where quizresult.CorrectQuestion >= (SELECT CorrectQuestion from quizresult quizresultTbl where quizresultTbl.StudentId  = student.Id))as individualRank  \r\n"
			+ "from student where Email = ?;";
}