package com.company;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.io.File;

//made by Larry Ngo | lngo4 | 677609505
public class ExamBuilder {
    public static void main(String[] args) throws IOException{
        String examDataFile = "examData.txt";
        Exam exam = null;
        File examFile = new File(examDataFile);
        Scanner reader = new Scanner(examFile); //reads file off of the exam file

        while (true) {
            System.out.println("Choose an option: ");
            System.out.println("1. Load a saved exam from a file");
            System.out.println("2. Add questions interactively");
            System.out.println("3. Remove questions interactively");
            System.out.println("4. Reorder questions and/or answers");
            System.out.println("5. Print the Exam to the screen");
            System.out.println("6. Save Exam");
            System.out.println("7. Quit");

            System.out.print("Your choice: ");
            Scanner tokenReader = new Scanner(System.in);
            int token = tokenReader.nextInt();

            switch (token) {
                case 1: //CREATES EXAM PROVIDED BY examData.txt
                    exam = new Exam(reader);
                    reader.nextLine();
                    while (reader.hasNextLine()) {
                        String input = reader.nextLine();
                        if (input.equals("SAQuestion")) {
                            SAQuestion questionSA;
                            SAAnswer answerSA;

                            questionSA = new SAQuestion(reader);
                            answerSA = new SAAnswer(reader);
                            questionSA.setRightAnswer(answerSA);
                            exam.addQuestion(questionSA);

                        } else if (input.equals("MCSAQuestion")) {
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
                            }
                            exam.addQuestion(questionMCSA);
                        } else if (input.equals("MCMAQuestion")) {
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
                            }
                            exam.addQuestion(questionMCMA);
                        }
                        else if (input.equals("NumQuestion"))
                        {
                            NumQuestion questionNum;
                            NumAnswer answerNum;

                            questionNum = new NumQuestion(reader);
                            reader.nextLine(); //flush

                            answerNum = new NumAnswer(reader);
                            questionNum.setRightAnswer(answerNum);
                            exam.addQuestion(questionNum);
                        }
                    }
                    break;
                case 2:
                    reader = new Scanner(System.in);
                    if(exam == null) //checks to see if the exam is empty
                    {
                        System.out.print("Exam was empty! Enter a name: ");
                        exam = new Exam(reader);
                    }

                    System.out.println("Question Choices: SAQuestion, MCSAQuestion, MCMAQuestion, NumQuestion");
                    System.out.print("Enter type of question: ");

                    String input = reader.nextLine();
                    if (input.equals("SAQuestion"))
                    {
                        System.out.println("You chose SA");
                        SAQuestion questionSA;
                        SAAnswer answerSA;

                        questionSA = new SAQuestion(reader);

                        System.out.print("Enter the correct answer for this question: ");
                        answerSA = new SAAnswer(reader);
                        questionSA.setRightAnswer(answerSA);
                        exam.addQuestion(questionSA);

                    }
                    else if (input.equals("MCSAQuestion"))
                    {
                        System.out.println("You chose MCSAQuestion");
                        MCSAQuestion questionMCSA;
                        MCSAAnswer answerMCSA;

                        System.out.print("Enter a value for this question: ");
                        double questionValue = reader.nextDouble();
                        reader.nextLine();

                        System.out.print("Enter a name for this question: ");
                        String questionName = reader.nextLine();

                        questionMCSA = new MCSAQuestion(questionName, questionValue); //asks for name and value

                        System.out.print("Enter number of answers for this question: ");
                        int numberOfAnswers = reader.nextInt(); //gets number of answers
                        reader.nextLine(); //flush

                        for (int i = 0; i < numberOfAnswers; i++) {
                            double answerValue;
                            String answerName;

                            System.out.println();
                            System.out.print("Enter value of answer #" + (i+1) + ": ");
                            answerValue = reader.nextDouble(); //value of answer
                            reader.nextLine(); //flush

                            System.out.print("Enter name of answer #" + (i+1) + ": ");
                            answerName = reader.nextLine(); //name of answer

                            answerMCSA = new MCSAAnswer(answerName, answerValue); // makes new answer class
                            questionMCSA.addAnswer(answerMCSA); //adds answer to question
                            System.out.println("Added " + answerName + " worth: " + answerValue);
                        }
                        exam.addQuestion(questionMCSA);
                    }
                    else if (input.equals("MCMAQuestion"))
                    {
                        System.out.println("You chose MCMAQuestion");
                        MCMAQuestion questionMCMA;
                        MCMAAnswer answerMCMA;

                        System.out.print("Enter a value for this question: ");
                        double questionValue = reader.nextDouble();
                        reader.nextLine();

                        System.out.print("Enter a name for this question: ");
                        String questionName = reader.nextLine();

                        System.out.print("Enter a base credit for this question: ");
                        double questionBaseCredit = reader.nextDouble();
                        reader.nextLine();

                        questionMCMA = new MCMAQuestion(questionName, questionValue, questionBaseCredit);

                        System.out.print("Enter number of answers for this question: ");
                        int numberOfAnswers = reader.nextInt(); //gets number of answers
                        reader.nextLine(); //flush

                        for (int i = 0; i < numberOfAnswers; i++)
                        {
                            double answerValue;
                            String answerName;

                            System.out.println();
                            System.out.print("Enter value of answer #" + (i+1) + ": ");
                            answerValue = reader.nextDouble(); //value of answer
                            reader.nextLine(); //flush

                            System.out.print("Enter name of answer #" + (i+1) + ": ");
                            answerName = reader.nextLine(); //name of answer

                            answerMCMA = new MCMAAnswer(answerName, answerValue); // makes new answer class
                            questionMCMA.addAnswer(answerMCMA); //adds answer to question
                            System.out.println("Added " + answerName + " worth: " + answerValue);
                        }
                        exam.addQuestion(questionMCMA);
                    }
                    else if (input.equals("NumQuestion"))
                    {
                        System.out.println("You chose NumQuestion");
                        NumQuestion questionNum;
                        NumAnswer answerNum;

                        questionNum = new NumQuestion(reader);
                        reader.nextLine(); //flush

                        System.out.print("What is the answer to this question?: ");
                        answerNum = new NumAnswer(reader);
                        questionNum.setRightAnswer(answerNum);
                        exam.addQuestion(questionNum);
                    }
                    else {
                        System.out.println("Invalid type of question. Please try again.");
                    }
                    break;
                case 3:
                    System.out.print("Which question do you want to remove?: ");
                    int questionNumber = reader.nextInt();
                    exam.removeQuestion(questionNumber);
                    break;
                case 4:
                    System.out.println("Choose an option. Anything else will go back: ");
                    System.out.println("1. Reorder questions ONLY");
                    System.out.println("2. Reorder answers ONLY");
                    System.out.println("3. Reorder questions AND answers");

                    int x = reader.nextInt();

                    switch(x)
                    {
                        case 1:
                            exam.reorderQuestions();
                            break;
                        case 2:
                            System.out.print("Which MCQuestion's answers should be reordered? Enter -1 for all: ");
                            exam.reorderMCAnswers(reader.nextInt());
                            break;
                        case 3:
                            exam.reorderQuestions();
                            System.out.print("Which MCQuestion's answers should be reordered? Enter -1 for all: ");
                            exam.reorderMCAnswers(reader.nextInt());
                            break;
                        default:
                            System.out.println("Going back");
                    }
                case 5:
                    String examPrintFile = "examPrint.txt";

                    System.out.println("Choose an option:");
                    System.out.println("1. Print exam to screen");
                    System.out.println("2. Print exam to a file (" + examPrintFile +")");
                    System.out.println("3. Go back");

                    Scanner print = new Scanner(System.in);
                    int printInput = print.nextInt();

                    switch(printInput)
                    {
                        case 1:
                            exam.print();
                            break;
                        case 2:
                            PrintWriter examPrintWriter = null;

                            try {
                                examPrintWriter = new PrintWriter(examPrintFile);
                            } catch (FileNotFoundException error) {
                                error.printStackTrace();
                            }

                            exam.save(examPrintWriter);
                            examPrintWriter.close();
                            break;
                        case 3:
                            System.out.println("Going back.");
                            break;
                        default:
                            System.out.println("Invalid option. Going back\n");
                    }
                    break;
                case 6:
                    PrintWriter examData = new PrintWriter(examDataFile);

                    try {
                        examData = new PrintWriter(examDataFile);
                    } catch (FileNotFoundException error) {
                        error.printStackTrace();
                    }

                    exam.save(examData);
                    examData.close();
                    break;
                case 7:
                    System.exit(1);
                    break;
                default:
                    System.out.println("Not a valid option. Try again.");
            }
        }
    }
}
