package com.example.project;

import java.io.*;

public class Questions extends Cleanup {
    public String question;
    public String[] answers;
    public String value;
    static int id = 1;
    public static int correctId = 1;
    public int duplicateAnswers(String [] answers, int length) {
        this.answers = answers;
        for (int i = 5; i < length - 1; i += 2)
            for (int j = i + 2; j < length - 1; j += 2) {
                String[] text1 = answers[i].split("'");
                String[] text2 = answers[j].split("'");
                if (text1[1].equals(text2[1]))
                    return 0;
            }
        return 1;
    }
    public int multipleValidation(String[] args, int length) {
        int x = 0;
        for(int i = 4; i < length; i += 2) {
            value = args[i].split("'")[1];
            if (value.equals("1"))
                x++;
            if (x == 2) {
                System.out.println("{ 'status' : 'error', 'message' : 'Single correct answer question has more than one correct answer'}");
                return 0;
            }
        }
        return 1;
    }
    public void writeQuestionsToFile() {
        try (FileWriter fw = new FileWriter("questions.txt", true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {

            out.println(question);
        } catch (IOException e) {
            System.err.print(e);
        }
    }

    public int verifyQuestions() {
        try {
            FileReader fReader = new FileReader(
                    "questions.txt");
            BufferedReader bReader = new BufferedReader(fReader);
            String line = bReader.readLine();
            return DuplicatesGood(fReader, bReader, line, question);
        } catch (IOException e) {
            System.err.print(e);
        }
        return 0;
    }

    public static int DuplicatesGood(FileReader fReader, BufferedReader bReader, String line, String args) throws IOException {
        int id = 1;
        while(line != null)
        {
            String[] parts = line.split("\n");
            if(parts[0].equals(args)) {
                return id;
            }
            id++;
            line = bReader.readLine();
        }
        bReader.close();
        fReader.close();
        return 0;
    }


    public int checkFlagDescr(String[] args) {
        int x = 1, y = 1;
        for(int i = 5; i < args.length; i ++){
            if(i % 2 != 0) {
                if (!args[i].split("'")[0].equals("-answer-" + x + " ")) {
                    System.out.println("{ 'status' : 'error', 'message' : 'Answer " + x + " has no answer description'}");
                    return 0;
                }
                x++;
            }
            else {
                if (!args[i].split("'")[0].equals("-answer-" + y + "-is-correct ")) {
                    System.out.println("{ 'status' : 'error', 'message' : 'Answer " + y + " has no answer correct flag'}");
                    return 0;
                }
                y++;
            }
        }
        return 1;
    }

    public void getAllQuestions(){
        try {
            int id = 1;
            FileReader fReader = new FileReader(
                    "questions.txt");
            BufferedReader bReader = new BufferedReader(fReader);
            String line = bReader.readLine();
            System.out.print("{ 'status' : 'ok', 'message' : '[");
            while(line != null)
            {
                System.out.print("{\"question_id\" : \""+ id + "\", \"question_name\" : \"" + line + "\"}");
                id++;
                line = bReader.readLine();
                if(line != null)
                    System.out.print(", ");
                else
                    System.out.print("]'}");
            }
            bReader.close();
            fReader.close();
        } catch (IOException e) {
            System.err.print(e);
        }
    }

    public void writeAllInfoToFile(String[] args){
        int ans_id = 0;
        for (int i = 3; i < args.length - 1; i++){
            try (FileWriter fw = new FileWriter("quizinfo.txt", true);
                 BufferedWriter bw = new BufferedWriter(fw);
                 PrintWriter out = new PrintWriter(bw)) {
                if (!args[i].split("'")[0].equals("-answer-" + ans_id + "-is-correct ")) {
                    if (args[i].length() > i) {
                        out.println(args[i].split("'")[1]);
                    }
                    else {
                        out.println(args[i]);
                        ans_id++;
                        out.println(ans_id);
                    }
                    if (args[i].split("'")[0].equals("-text ")) {
                        out.println(verifyQuestions());
                    }
                }
            } catch (IOException e) {
                System.err.print(e);
            }
        }
    }

    //Checking if name already exists in file
    public void writeCorrectAnswersInFile(String[] args){
        int ans_id = 1;
        for (int i = 6; i < args.length; i+=2){
            try (FileWriter fw = new FileWriter("quizCorrectAnswers.txt", true);
                 BufferedWriter bw = new BufferedWriter(fw);
                 PrintWriter out = new PrintWriter(bw)) {

                if(args[i].split("'")[0].equals("-answer-" + ans_id + "-is-correct ")) {
                    if(Integer.parseInt(args[i].split("'")[1]) == 1) {
                        out.println(correctId);
                        ans_id++;
                    }
                    correctId++;
                }
            } catch (IOException e) {
                System.err.print(e);
            }
        }
    }

    public static void QuestionsTask(String[] args) {
        Users.authentication(args);
        if (args.length > 3) {
            Questions q = new Questions();

            //No question provided
            if (!args[3].split("'")[0].equals("-text ")) {
                System.out.println("{ 'status' : 'error', 'message' : 'No question text provided'}");
                return;
            }
            q.question = args[3].split("'")[1];

            //One or no answer provided
            switch (args.length) {
                case 5 :
                    System.out.println("{ 'status' : 'error', 'message' : 'No answer provided'}");
                    return;

                case 7 :
                    System.out.println("{ 'status' : 'error', 'message' : 'Only one answer provided'}");
                    return;

            }

            //Checking for description
            if (q.checkFlagDescr(args) == 0) {
                return;
            }

            //Checking for duplicate answers
            if (q.duplicateAnswers(args, args.length) == 0) {
                System.out.println("{ 'status' : 'error', 'message' : 'Same answer provided more than once'}");
                return;
            }

            //Checking for multiple correct answers
            if (args[4].equals("-type 'single'"))
                if (q.multipleValidation(args, args.length) == 0) {
                    return;
                }

            //Checking if the question already exists
            if (!(q.verifyQuestions() == 0)) {
                System.out.println("{ 'status' : 'error', 'message' : 'Question already exists'}");
                return;
            }
            //Writing the question to the file
            q.writeQuestionsToFile();
            q.writeAllInfoToFile(args);
            q.writeCorrectAnswersInFile(args);
            System.out.println("{ 'status' : 'ok', 'message' : 'Question added successfully'}");
        }
    }
}