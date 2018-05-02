package com.company;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class ExamTaker {
    public static void main(String[] args) throws IOException{
        // Get student information
        Scanner infoScan = new Scanner(System.in);
        System.out.print("Enter student name: ");
        String studentName = infoScan.nextLine();

        System.out.print("Enter student ID: ");
        String studentID = infoScan.nextLine();

        // Load an Exam file
        String examDataFile = "examData.txt";
        File examFile = new File(examDataFile);
        Scanner reader = new Scanner(examFile);
        Exam exam = new Exam(reader);

        //makes an exam to take provided by examdata.txt
        int numberOfQuestions = 0;
        while(reader.hasNextLine())
        {
            String examLine = reader.nextLine();
            if (examLine.equals("SAQuestion")) {
                SAQuestion questionSA = new SAQuestion(reader);
                SAAnswer answerSA = new SAAnswer(reader);
                questionSA.setRightAnswer(answerSA);

                exam.addQuestion(questionSA);
                numberOfQuestions++;

            } else if (examLine.equals("MCSAQuestion")) {
                MCSAQuestion questionMCSA = new MCSAQuestion(reader); //asks for name and value

                int numberOfAnswers = reader.nextInt(); //gets number of answers
                reader.nextLine(); //flush

                for (int i = 0; i < numberOfAnswers; i++) {
                    double answerValue;
                    String answerName;
                    answerValue = reader.nextDouble(); //value of answer
                    answerName = reader.nextLine(); //name of answer

                    MCSAAnswer answerMCSA = new MCSAAnswer(answerName, answerValue); // makes new answer class
                    questionMCSA.addAnswer(answerMCSA); //adds answer to question
                }

                exam.addQuestion(questionMCSA);
                numberOfQuestions++;
            } else if (examLine.equals("MCMAQuestion")) {
                MCMAQuestion questionMCMA = new MCMAQuestion(reader);
                int numberOfAnswers = reader.nextInt(); //gets number of answers
                reader.nextLine(); //flush

                for (int i = 0; i < numberOfAnswers; i++) {
                    double answerValue;
                    String answerName;
                    answerValue = reader.nextDouble(); //value of answer
                    answerName = reader.nextLine(); //name of answer

                    MCMAAnswer answerMCMA = new MCMAAnswer(answerName, answerValue); // makes new answer class
                    questionMCMA.addAnswer(answerMCMA); //adds answer to question
                }

                exam.addQuestion(questionMCMA);
                numberOfQuestions++;
            }
            else if (examLine.equals("NumQuestion"))
            {
                NumQuestion questionNum = new NumQuestion(reader);
                reader.nextLine(); //flush

                NumAnswer answerNum = new NumAnswer(reader);
                questionNum.setRightAnswer(answerNum);

                exam.addQuestion(questionNum);
                numberOfQuestions++;
            }
        }
        reader.close();

        //exam.resetAnswers(); //THIS WILL FILL ALL THE ANSWERS WITH AN INVALID ANSWER TO AVOID NULL POINTER EXCEPTIONS

        System.out.println("Exam is ready to be taken!\n");
        System.out.println("Number of questions in this exam: " + numberOfQuestions);
        exam.print();

        Scanner scan = new Scanner(System.in);
        String userInput = "";
        while (!userInput.toLowerCase().equals("finish"))
        {
            for (int i = 1; i < numberOfQuestions + 1; i++) //goes through the questions
            {
                System.out.println("Question #" + (i));
                exam.printQuestion(i);
                System.out.print("Type a command. If you need help, type 'help': ");
                userInput = scan.nextLine();

                if (userInput.toLowerCase().equals("finish"))
                {
                    break;
                }

                else if (userInput.toLowerCase().equals("help"))
                {
                    System.out.println("-------------------------------------------------------------");
                    System.out.println("When asked a question, these are the commands available:");
                    System.out.println("'help' -- prints this message");
                    System.out.println("'change' -- change answer to a question");
                    System.out.println("'skip' -- skips question, can go back later");
                    System.out.println("'finish' -- finish the exam");
                    System.out.println("'answer' -- answer the current question");
                    System.out.println("-------------------------------------------------------------");
                }
                else if (userInput.toLowerCase().equals("change"))
                {
                    System.out.print("Enter question to change answers to: ");
                    int questionToChange = scan.nextInt();
                    exam.getAnswerFromStudent(questionToChange);
                }
                else if (userInput.toLowerCase().equals("skip"))
                {

                    //does nothing so it will go on to the next question
                }
                else if (userInput.toLowerCase().equals("answer"))
                {
                    exam.getAnswerFromStudent(i);
                }
                else
                {
                    System.out.println("Invalid command. Try Again");
                    i--;
                }
            }
        }
        String studentDataFile = "studentData.txt";
        PrintWriter studentData = null;

        try {
            studentData = new PrintWriter(studentDataFile);

        } catch (FileNotFoundException error) {

            error.printStackTrace();

        }

        //Saves student info
        studentData.println(studentName);
        studentData.println(studentID);
        studentData.println();
        exam.saveStudentAnswers(studentData);
        studentData.close();
    }
}
