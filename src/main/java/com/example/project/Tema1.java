package com.example.project;

import java.io.*;

public class Tema1 extends Users{
	public static void main(final String[] args) {
		if (args == null) {
			System.out.print("Hello world!");
		}

		else if (args[0].equals("-create-user")) {
			UsersTask(args);
		}

		else if (args[0].equals("-create-question")) {
			QuestionsTask(args);
		}

		else if (args[0].equals("-cleanup-all")) {
			CleanupAll();
		}

		else if (args[0].equals("-get-question-id-by-text")) {
			Users.authentication(args);

			if (args.length > 3) {
				Questions q = new Questions();
				q.question = args[3].split("'")[1];
				if (q.verifyQuestions() == 0) {
					System.out.println("{ 'status' : 'error', 'message' : 'Question does not exist'}");
				} else {
					System.out.println("{ 'status' : 'ok', 'message' : '" + q.verifyQuestions() + "'}");
				}
			}
		}

		else if (args[0].equals("-get-all-questions")) {
			if (Users.authentication(args) == 1) {
				Questions q = new Questions();
				q.getAllQuestions();
			}
		}

		else if (args[0].equals("-create-quizz")) {
			if (Users.authentication(args) == 0) return;
			Quiz q = new Quiz();

			if (args.length > 3) {
				q.name = args[3].split("'")[1];
				if (!(q.getQuizzID() == 0)) {
					System.out.println("{ 'status' : 'error', 'message' : 'Quizz name already exists'}");
					return;
				}
				q.writeQuizToFile();
				q.verifyQuizId(args);
			}
		}

		else if (args[0].equals("-get-quizz-by-name")) {
			if (Users.authentication(args) == 0) return;
			Quiz q = new Quiz();
			q.name = args[3].split("'")[1];
			if (q.getQuizzID() == 0)
				System.out.println("{ 'status' : 'error', 'message' : 'Quizz does not exist'}");
			else
				System.out.println("{ 'status' : 'ok', 'message' : '" + q.getQuizzID() + "'}");
		}

		else if (args[0].equals("-get-all-quizzes")) {
			if (Users.authentication(args) == 0) return;
			Quiz q = new Quiz();
			q.getAllQuizzes();
		}

		else if (args[0].equals("-get-quizz-details-by-id")) {
			if (Users.authentication(args) == 0) return;
			Quiz q = new Quiz();
			q.getQuizzInfo();
		}

		else if (args[0].equals("-submit-quizz")) {
			if (Users.authentication(args) == 0) return;
			if (args.length < 4) {
				System.out.println("{ 'status' : 'error', 'message' : 'No quizz identifier was provided'}");
				return;
			}
			Quiz q = new Quiz();
			if (q.searchQuizId(Integer.parseInt(args[3].split("'")[1])) == 0) {
				System.out.println("{ 'status' : 'error', 'message' : 'No quiz was found'}");
				return;
			}
			System.out.println("{ 'status' : 'ok', 'message' : '" + q.verifyCorrectquiz(args) + " points'}");
		}

		else if(args[0].equals("-delete-quizz-by-id")){
			if(Users.authentication(args) == 0) return;
			Quiz q = new Quiz();

			if (args.length < 4) {
				System.out.println("{ 'status' : 'error', 'message' : 'No quizz identifier was provided'}");
				return;
			}
			if (q.searchQuizId(Integer.parseInt(args[3].split("'")[1])) == 0) {
				System.out.println("{ 'status' : 'error', 'message' : 'No quiz was found'}");
			}
			q.quizDeleteById(args);
		}

		else if(args[0].equals("-get-my-solutions"))
			Users.authentication(args);
	}
}


