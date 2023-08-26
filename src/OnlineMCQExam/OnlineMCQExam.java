package OnlineMCQExam;

import java.util.Scanner;

public class OnlineMCQExam {
	private String username;
	private String password;
	private boolean isLoggedIn;
	private int timeRemaning;
	private int noOfQuestion;
	private int[] userAnswer;
	private int[] correctAnswer;

	public static void main(String[] args) {
		System.out.println(
				"WELCOME to OnlineExamination - by Ninad Sonawane \nPlease Register Your Username and Password ");
		Scanner sc1 = new Scanner(System.in);
		System.out.println("Enter Your Username : ");
		String Username = sc1.nextLine();
		System.out.println("Enter Your Password : ");
		String Password = sc1.nextLine();
		OnlineMCQExam examSystem = new OnlineMCQExam(Username, Password);
		examSystem.login();
		examSystem.StartExam();

	}

	public OnlineMCQExam(String username, String password) {
		this.username = username;
		this.password = password;
		System.out.println(" Successfully You Are Registered !! ");
		this.isLoggedIn = false;
		this.timeRemaning = 10; // in minutes
		this.noOfQuestion = 10;
		this.userAnswer = new int[noOfQuestion];
		this.correctAnswer = new int[noOfQuestion];
		// initialize correct answer with random values (0 , 1)
		for (int i = 0; i < noOfQuestion; i++) {
			correctAnswer[i] = (int) Math.round(Math.random());
		}

	}

	public void login() {

		System.out.println("Log in to give Exam ");
		Scanner sc = new Scanner(System.in);
		System.out.println("Username : ");
		String inputUsername = sc.nextLine();

		System.out.println("Password : ");
		String inputPassword = sc.nextLine();

		if (inputUsername.equals(username) && inputPassword.equals(password)) {
			isLoggedIn = true;
			System.out.println("Login Successful Best Of Luck ");

		} else {
			System.out.println("Login Failed. Try Again ");

		}

	}

	public void logout() {
		isLoggedIn = false;
		System.out.println("Logout Successful. ");
	}

	public void StartExam() {
		if (!isLoggedIn) {
			System.out.println("Please Login First !!");
			return;
		}
		Scanner sc2 = new Scanner(System.in);
		System.out.println("You have " + timeRemaning + " minutes to complete the Exam. ");
		for (int i = 0; i < noOfQuestion; i++) {
			System.out.println("Question " + (i + 1) + ":");
			System.out.println("1. Option 1");
			System.out.println("2. Option 2");
			System.out.print("Your Answer (1 or 2): ");
			int answer = sc2.nextInt();
			userAnswer[i] = answer;
		}
		System.out.println(" Would you like to submit ? \n1: Yes  \n2: No ");
		int n = sc2.nextInt();
		if (n == 1) {
			submitExam();
		} else {
			// auto submit exam when times up !!
			try {
				Thread.sleep(timeRemaning * 10 * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
				submitExam();

			}
		}

	}

	private void submitExam() {
		if (!isLoggedIn) {
			System.out.println("Please Login First !!");
			return;
		}
		int score = 0;
		for (int i = 0; i < noOfQuestion; i++) {
			if (userAnswer[i] == correctAnswer[i]) {
				score++;
			}
		}
		System.out.println("Your score is " + score + " out of " + noOfQuestion + " . ");
		System.out.println("Best Of luck !!");
		logout();

	}

}
