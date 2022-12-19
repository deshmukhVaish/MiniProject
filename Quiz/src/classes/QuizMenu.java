package classes;

import java.util.List;
import java.util.Scanner;

public class QuizMenu {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);

		System.out.println("Welcome To Quiz...!");
		System.out.println("Please Provide Your Details:");
		System.out.println("Enter Your Name:- ");
		String name = sc.nextLine();
		System.out.println("Enter Your Email:- ");
		String email = sc.nextLine();
		User user = new User(email, name);
		User getUser = user.getUserDetailById(email);
		boolean flag = false;
		if (getUser != null) {
			user.setId(getUser.getId());
			System.out.println("Wellcome again " + name);
			if (user.getId() != null)
				flag = true;
		} else {
			int flagAddUser = user.addUser();
			System.out.println(flagAddUser);
			if (flagAddUser > 0) {
				System.out.println(flagAddUser);
				getUser = user.getUserDetailById(email);
			}
				
			System.out.println("Welcome " + name);
			flag = true;
		}
		if (flag) {
			showMenu(sc,getUser);
		} else {

		}

		// }
	}

	public static void showMenu(Scanner sc, User getUser) {
		System.out.println("Please Enter Any Option To Procced:  ");
		System.out.println("A : Start Quiz ");
		System.out.println("B : See top 10 rankers");
		System.out.println("C : Check Rank by email");
		String menuOption = sc.nextLine();
		switch (menuOption) {
		case "A":

			if (!getUser.getIndividualRank().equalsIgnoreCase("0")) {
				System.out.println("You have allready given the Quiz your rank is " + getUser.getIndividualRank());
			} else {
				Questions question = new Questions();
				List<Questions> list = question.getQuestionList();
				int attemtedQue = list.size(), skipQues = 0, correctQues = 0;
				for (int i = 0; i < list.size(); i++) {
					System.out
							.println("Please select any one option between A,B,C or D and Type S to skip the question");
					Questions questions = list.get(i);
					System.out.println("Q. " + questions.getQuestion());
					System.out.println("A. " + questions.getOptionA());
					System.out.println("B. " + questions.getOptionB());
					System.out.println("C. " + questions.getOptionC());
					System.out.println("D. " + questions.getOptionD());

					String selectedOption = sc.nextLine();
					int flagSelectedOption = 0;
					switch (selectedOption) {
					case "A":
						flagSelectedOption = new QuestionAns().submitAnswerOfQuestion(questions, selectedOption,
								getUser.getId());
						break;
					case "B":
						flagSelectedOption = new QuestionAns().submitAnswerOfQuestion(questions, selectedOption,
								getUser.getId());
						break;
					case "C":
						flagSelectedOption = new QuestionAns().submitAnswerOfQuestion(questions, selectedOption,
								getUser.getId());
						break;
					case "D":
						flagSelectedOption = new QuestionAns().submitAnswerOfQuestion(questions, selectedOption,
								getUser.getId());
						break;
					case "S":
						skipQues++;
						attemtedQue--;
						break;
					default:
						i--;
						System.out.println("Please Enter Valid Option ");
						break;

					}
					if (selectedOption.equalsIgnoreCase(questions.getCorrectAns())) {
						correctQues++;
					}
               }
				int individualRank =new QuizResponce().addQuizSummary(attemtedQue, skipQues, correctQues, getUser.getId());
				getUser.setIndividualRank(Integer.toString(individualRank));
			}
			System.out.println("Enter C to Go back to the Quiz Menu or Q to Exit");
			String s = sc.nextLine();
			if (s.equalsIgnoreCase("c")) {
				showMenu(sc,getUser);
			}
			break;
		case "B":
			User topTenUser = new User();
			topTenUser.getTopTenUserList();
			System.out.println("Enter C to Go back to the Quiz Menu or Q to Exit");
			 s = sc.nextLine();
			if (s.equalsIgnoreCase("c")) {
				showMenu(sc,getUser);
			}
			
			break;

		case "C":
			User rank = new User();
			System.out.println("Please Enter EmailId of the Student whome you want to see the Rank:");
			String emailId = sc.nextLine();
			rank.getUserRankById(emailId);
			System.out.println("Enter C to Go back to the Quiz Menu or Q to Exit");
			 s = sc.nextLine();
			if (s.equalsIgnoreCase("c")) {
				showMenu(sc,getUser);
			}
			break;
		default:

			break;
		}

	}
}