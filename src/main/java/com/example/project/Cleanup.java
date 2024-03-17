package com.example.project;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Cleanup {

    public static void userCleanup(){
        try {
            PrintWriter writer = new PrintWriter("users.txt");
            writer.print("");
            writer.close();
        } catch (FileNotFoundException e) {
            System.err.print(e);
        }
    }

    public static void quizCleanup() {
        try {
            PrintWriter writer = new PrintWriter("quiz.txt");
            writer.print("");
            writer.close();
        } catch (FileNotFoundException e) {
            System.err.print(e);
        }
        try {
            PrintWriter writer = new PrintWriter("quizinfo.txt");
            writer.print("");
            writer.close();
        } catch (FileNotFoundException e) {
            System.err.print(e);
        }
        try {
            PrintWriter writer = new PrintWriter("GoodQuizCorrectAnswers.txt");
            writer.print("");
            writer.close();
        } catch (FileNotFoundException e) {
            System.err.print(e);
        }
    }

    public static void questionCleanup(){
        try {
            PrintWriter writer = new PrintWriter("questions.txt");
            writer.print("");
            writer.close();
        } catch (FileNotFoundException e) {
            System.err.print(e);
        }
        try {
            PrintWriter writer = new PrintWriter("quizCorrectAnswers.txt");
            writer.print("");
            writer.close();
        } catch (FileNotFoundException e) {
            System.err.print(e);
        }
    }

    public static void CleanupAll() {
        userCleanup();
        quizCleanup();
        questionCleanup();
        System.out.println("{ 'status' : 'ok', 'message' : 'Cleanup finished successfully'}");

    }

}
