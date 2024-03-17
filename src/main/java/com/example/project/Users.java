package com.example.project;

import java.io.*;

public class Users extends Questions {
    private String username;
    private String password;

    public Users() { }
    public Users (String arg1, String arg2) {
        this.username = arg1;
        this.password = arg2;

        this.username = this.username.split("'")[1];
        this.password = this.password.split("'")[1];
    }

    public void writeUsersToFile() {
        try (FileWriter fw = new FileWriter("users.txt", true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {

            out.println(username + "," + password);
        } catch (IOException e) {
            System.err.print(e);
        }
    }

    public int checkForUsernames() {
        try {
            FileReader fReader = new FileReader(
                    "users.txt");
            BufferedReader bReader = new BufferedReader(fReader);
            String line = bReader.readLine();
            while(line != null)
            {
                String[] parts = line.split(",");
                if(parts[0].equals(username))
                    return 0;
                line = bReader.readLine();
            }
            bReader.close();
            fReader.close();
        } catch (IOException e) {
            System.err.print(e);
        }
        return 1;
    }

    public int checkForPasswords() {
        try {
            FileReader fReader = new FileReader(
                    "users.txt");
            BufferedReader bReader = new BufferedReader(fReader);
            String line = bReader.readLine();
            while(line != null)
            {
                String[] parts = line.split(",");
                if(parts[1].equals(password))
                    return 0;
                line = bReader.readLine();
            }
            bReader.close();
            fReader.close();
        } catch (IOException e) {
            System.err.print(e);
        }
        return 1;
    }

    public static int authentication(String[] args) {
        if(args.length < 3){
            if (args.length == 1) {
                System.out.println("{ 'status' : 'error', 'message' : 'You need to be authenticated'}");
                return 0;
            }
            else {
                String s = args[1].split("'")[0];
                if (s.equals("-u ")) {
                    System.out.println("{ 'status' : 'error', 'message' : 'You need to be authenticated'}");
                    return 0;
                }
            }
        }
        if(args.length == 3) {
            Users user = new Users(args[1], args[2]);
            if (user.checkForUsernames() == 1) { // user exists
                System.out.println("{ 'status' : 'error', 'message' : 'Login failed'}");
                return 0;
            }
            else
                if(user.checkForPasswords() == 1) {
                    System.out.println("{ 'status' : 'error', 'message' : 'Login failed'}");
                    return 0;
                }
        }
        return 1;
    }

    public static void UsersTask(String[] args) {
        switch (args.length) {
            case 3 : {
                Users user = new Users(args[1], args[2]);
                //Checking if the user already exists
                switch (user.checkForUsernames()) {
                    case 0 :
                        System.out.println("{ 'status' : 'error', 'message' : 'User already exists'}");
                        return;

                    case 1 :
                        user.writeUsersToFile();
                        System.out.println("{ 'status' : 'ok', 'message' : 'User created successfully'}");
                        return;

                }
            }
            case 1 :
                //Checking for -u parameter
                System.out.println("{ 'status' : 'error', 'message' : 'Please provide username'}");
                return;

            case 2 :
                //Checking for -p
                System.out.println("{ 'status' : 'error', 'message' : 'Please provide password'}");
        }
    }
}
