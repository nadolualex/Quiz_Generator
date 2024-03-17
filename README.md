## Quiz Generator

The goal of this project is to implement a simple Quiz Generator application. Users of this program will authenticate themselves in the application with each system call (for simplification), except for the user creation call. Authenticated users in the system will be able to:

- Create questions (with single correct answers or multiple correct answers).
- Create quizzes based on the previously added questions.
- Respond to quizzes created by others, only once.

### Question Structure
Every question is characterized by an identifier, a text, and a list of possible answers. Each answer has an identifier, text, and a Boolean value (True/False) indicating its correctness.

### Quiz Evaluation
In the context of quizzes, questions are scored using the French style. If the chosen answer is correct, the question is scored as 1. If no answer is provided, the question is scored as 0. However, if an incorrect answer is chosen, the question is scored negatively, with a value of -1.

### General Rules of the Quiz Generator:
- No two questions can have the same text.
- No two quizzes can have the same name.
- Users can use questions created by other users while creating quizzes.
- A quiz can contain between 1 and 10 questions.
- Each question in a quiz contributes equally to the total score of the quiz.
- Negative scores for questions are proportional to the total score of the quiz.
- Total quiz score is rounded to the nearest integer.
- If the total score of a quiz is negative, it is set to 0.
- A question can have between 1 and 5 possible answers.
- Only one correct answer can be selected for questions with a single correct answer.
- Users can view their scores for each completed quiz.
- Quizzes can only be deleted by their creators.

### Implementation Details:
The implementation will be done using a Java console application. It will accept a series of command-line arguments that need to be interpreted. Complete documentation for these commands and their expected responses is provided in the document "Tema1-DocumentațieComenzi.pdf".

### Commands to be Processed:
1. Create User
2. Create Question
3. Get Question ID by Text
4. Get All Questions
5. Create Quiz
6. Get Quiz ID by Name
7. Get All Quizzes
8. Get Quiz Details by ID
9. Submit Quiz
10. Delete Quiz
11. Get My Solutions
12. Cleanup Data

### Data persistence
For data persistence, in the implementation of the Quiz Generator application, the following approach was adopted:

- **Users:** User information was stored in a file named `users.csv`. Each line in this file contains the username and password of a user, separated by a comma.
- **Questions:** Question data was stored in a file named `questions.csv`. Each line in this file represents a single question, with its unique identifier, text, and other relevant details.
- **Quizzes:** Quiz information was stored in a file named `quizzes.csv`. Each line in this file corresponds to a quiz, containing its unique identifier, name, and other necessary details.
- **Solutions:** Solutions to quizzes were stored in a file named `solutions.csv`. Each line in this file represents a solution submitted by a user for a specific quiz, including the user's identifier, quiz identifier, and the chosen answers.

This approach ensures that user data, questions, quizzes, and solutions are stored efficiently in separate files using the CSV format. Each file maintains data integrity and allows for easy retrieval and manipulation of data within the application.

---
In summary, the data persistence strategy involved storing user information, questions, quizzes, and solutions in separate CSV files, enabling efficient data management and retrieval within the Quiz Generator application.
