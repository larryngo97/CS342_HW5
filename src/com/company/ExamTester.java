package com.company;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ExamTester {
    public static void main(String[] args){
        String examDataFile = "examData.txt";
        String studentDataFile = "studentData.txt";
        PrintWriter examData = null;
        PrintWriter studentData = null;
        try {
            examData = new PrintWriter(examDataFile);
            studentData = new PrintWriter(studentDataFile);
        } catch (FileNotFoundException error) {
            error.printStackTrace();
        }
        Scanner reader = new Scanner(System.in);
        System.out.print("Enter an exam name: ");
        Exam exam = new Exam(reader);
        reader.nextLine();
        int numberOfQuestions = 0;
        while (true) {
            System.out.print("Enter type of question: ");
            //reader.nextLine(); //accounts for the spaces
            String input = reader.nextLine();
            if (input.equals("end")) {
                break;
            }
            if (input.equals("SAQuestion")) {
                System.out.println("You chose SA");
                SAQuestion questionSA;
                SAAnswer answerSA;
                System.out.println("Enter value and name: ");
                questionSA = new SAQuestion(reader);
                answerSA = new SAAnswer(reader);
                questionSA.setRightAnswer(answerSA);
                exam.addQuestion(questionSA);
                numberOfQuestions++;
            } else if (input.equals("MCSAQuestion")) {
                System.out.println("You chose MCSAQuestion");
                MCSAQuestion questionMCSA;
                MCSAAnswer answerMCSA;
                questionMCSA = new MCSAQuestion(reader); //asks for name and value
                int numberOfAnswers = reader.nextInt(); //gets number of answers
                reader.nextLine(); //flush
                for (int i = 0; i < numberOfAnswers; i++) {
                    double answerValue;
                    String answerName;
                    answerValue = reader.nextDouble(); //value of answer
                    answerName = reader.nextLine(); //name of answer
                    answerMCSA = new MCSAAnswer(answerName, answerValue); // makes new answer class
                    questionMCSA.addAnswer(answerMCSA); //adds answer to question
                    System.out.println("Added " + answerName + " worth: " + answerValue);
                }
                exam.addQuestion(questionMCSA);
                numberOfQuestions++;
            } else if (input.equals("MCMAQuestion")) {
                System.out.println("You chose MCMAQuestion");
                MCMAQuestion questionMCMA;
                MCMAAnswer answerMCMA;
                questionMCMA = new MCMAQuestion(reader);
                int numberOfAnswers = reader.nextInt(); //gets number of answers
                reader.nextLine(); //flush
                for (int i = 0; i < numberOfAnswers; i++) {
                    double answerValue;
                    String answerName;
                    answerValue = reader.nextDouble(); //value of answer
                    answerName = reader.nextLine(); //name of answer
                    answerMCMA = new MCMAAnswer(answerName, answerValue); // makes new answer class
                    questionMCMA.addAnswer(answerMCMA); //adds answer to question
                    System.out.println("Added " + answerName + " worth: " + answerValue);
                }
                exam.addQuestion(questionMCMA);
                numberOfQuestions++;
            } else {
                System.out.println("Invalid type of question. Please try again.");
            }
        }
        exam.print(); //prints out the whole exam, including the questions
        exam.reorderQuestions(); // Randomly reorders the list
        exam.reorderMCAnswers(-1); //reorders the MC answers if it is a MC question
        System.out.println("");
        exam.save(examData);
        examData.close();
        exam.print(); //THIS IS THE REORDERED PRINTED EXAM
        for (int i = 1; i <= numberOfQuestions; i++) {
            System.out.println("Question " + (i) + ": ");
            exam.getAnswerFromStudent(i);
        }
        exam.reportQuestionValues();
        double userScore = exam.getValue();
        System.out.println("FINAL SCORE: " + userScore);
        exam.saveStudentAnswers(studentData);
        studentData.close();
    }
}