package com.example.project;


import java.io.*;

public class Quiz extends Questions {
    public String name;
    public int correctAnswers;

    public void writeQuizToFile() {
        try (FileWriter fw = new FileWriter("quiz.txt", true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
             out.println(name);
        } catch (IOException e) {
            System.err.print(e);
        }
    }

    public int getQuizzID() {
        try {
            FileReader fReader = new FileReader(
                    "quiz.txt");
            BufferedReader bReader = new BufferedReader(fReader);
            String line = bReader.readLine();
            return DuplicatesGood(fReader, bReader, line, name);
        } catch (IOException e) {
            System.err.print(e);
        }
        return 0;
    }

    public void verifyQuizId(String[] args) {
        int x = 1;
        for (int i = 4; i < args.length; i++) {
            x++;
        }
        try {
            int noOfQuestions = 1;
            FileReader fReader = new FileReader(
                    "questions.txt");
            BufferedReader bReader = new BufferedReader(fReader);
            String line = bReader.readLine();
            while (line != null) {
                noOfQuestions++;
                line = bReader.readLine();
            }
            if (noOfQuestions == x) {
                System.out.println("{ 'status' : 'ok', 'message' : 'Quizz added succesfully'}");
                bReader.close();
                fReader.close();
            } else if (noOfQuestions > 10) {
                System.out.println("{ 'status' : 'error', 'message' : 'Quizz has more than 10 questions'}");

            } else {
                System.out.println("{ 'status' : 'error', 'message' : 'Question ID for question " + (x - 1) + " does not exist'}");
                bReader.close();
                fReader.close();
            }
        } catch (IOException e) {
            System.err.print(e);
        }
    }

    public void getAllQuizzes() {
        try {
            int id = 1;
            FileReader fReader = new FileReader(
                    "quiz.txt");
            BufferedReader bReader = new BufferedReader(fReader);
            String line = bReader.readLine();
            System.out.print("{ 'status' : 'ok', 'message' : '[");
            while (line != null) {
                System.out.print("{\"quizz_id\" : \"" + id + "\", \"quizz_name\" : \"" + line + "\", \"is_completed\" : \"False\"}");
                id++;
                line = bReader.readLine();
                if (line != null)
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

    public void getQuizzInfo() {
        try {
            FileReader fReader = new FileReader(
                    "quizinfo.txt");
            BufferedReader bReader = new BufferedReader(fReader);
            String line = bReader.readLine();
            System.out.print(" {'status':'ok','message':'[");
            int idx = 1;
            int ans_id = 1;
            while (line != null) {
                if (idx == 1)
                    System.out.print("{\"question-name\":\"" + line + "\"");
                else if (idx == 2)
                    System.out.print("\"question_index\":\"" + line + "\"");
                else if (idx == 3)
                    System.out.print("\"question_type\":\"" + line + "\"");
                else if (idx == 4)
                    System.out.print("\"answers\":\"[{\"answer_name\":\"" + line + "\"");
                else if (idx == 5) {
                    System.out.print("\"answer_id\":\"" + ans_id + "\"}");
                    ans_id++;
                } else if (idx == 6) {
                    System.out.print("{\"answer_name\":\"" + line + "\", ");
                    System.out.print("\"answer_id\":\"" + ans_id + "\"}]\"}");
                    ans_id++;
                    idx = 0;
                }
                idx++;
                line = bReader.readLine();
                if (line != null)
                    System.out.print(", ");
                else
                    System.out.println("]'}");
            }
            bReader.close();
            fReader.close();
        } catch (IOException e) {
            System.err.print(e);
        }
    }

    public int searchQuizId(int id) {
        try {
            FileReader fReader = new FileReader(
                    "quiz.txt");
            BufferedReader bReader = new BufferedReader(fReader);
            String line = bReader.readLine();
            int line_index = 1;
            while (line != null) {
                if (id == line_index)
                    return id;
                line_index++;
                line = bReader.readLine();
            }
            bReader.close();
            fReader.close();
            return 0;
        } catch (IOException e) {
            System.err.print(e);
        }
        return 0;
    }

    public int getNoOfQuestions() {
        try {
            int noOfQuestions = 0;
            FileReader fReader = new FileReader(
                    "questions.txt");
            BufferedReader bReader = new BufferedReader(fReader);
            String line = bReader.readLine();
            while (line != null) {
                noOfQuestions++;
                line = bReader.readLine();
            }
            bReader.close();
            fReader.close();
            return noOfQuestions;
        } catch (IOException e) {
            System.err.print(e);
        }
        return 0;
    }

    public void quizDeleteById(String[] args) {
        try {
            int id = 1;
            FileReader fReader = new FileReader(
                    "quiz.txt");
            BufferedReader bReader = new BufferedReader(fReader);
            String line = bReader.readLine();
            while (line != null) {
                if(id == Integer.parseInt(args[3].split("'")[1])){
                    try (FileWriter fw = new FileWriter("quiz.txt", true);
                         BufferedWriter bw = new BufferedWriter(fw);
                         PrintWriter out = new PrintWriter(bw)) {

                        out.println("");
                        System.out.println("{ 'status' : 'ok', 'message' : 'Quizz deleted successfully'}");
                    } catch (IOException e) {
                        System.err.print(e);
                    }
                }
                id++;
                line = bReader.readLine();
            }
            bReader.close();
            fReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int verifyCorrectquiz(String[] args) {
        try {
            int i = 4;
            FileReader fReader = new FileReader(
                    "quizCorrectAnswers.txt");
            BufferedReader bReader = new BufferedReader(fReader);
            String line = bReader.readLine();

            while(line != null) {
                if(args[i].split("'")[1].equals(line)) {
                    correctAnswers++;
                }
                i++;
                line = bReader.readLine();
            }
            bReader.close();
            fReader.close();
            if(getNoOfQuestions() == correctAnswers)
                return 100;
            else
                return 0;
        } catch (IOException e) {
            System.err.print(e);
        }
        if(getNoOfQuestions() == correctAnswers)
            return 100;
        else
            return 0;
    }

}




