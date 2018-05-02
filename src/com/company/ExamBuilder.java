package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;


public class ExamBuilder extends JFrame {
    private JFrame mainFrame;

    private JPanel panelMain;
    private JPanel panelButtons;
    private JPanel panelButtonInfo;
    private JPanel panelHeader;
    private JButton button_help;
    private JButton buttonLoadExam;
    private JButton buttonAddQuestion;
    private JButton buttonRemoveQuestion;
    private JButton buttonReorderQuestions;
    private JButton buttonPrintExam;
    private JButton buttonSaveExam;
    private JButton buttonQuit;

    private static Exam exam;

    public ExamBuilder() {
        buttonLoadExam.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                File examFile = new File("examData.txt");
                Scanner reader = new Scanner(System.in);
                try {
                    reader = new Scanner(examFile);
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }

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
                JOptionPane.showMessageDialog(null, "Successfully loaded the exam!");
            }
        });

        buttonAddQuestion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(exam == null) //checks to see if the exam is empty
                {
                    String examName = (String)JOptionPane.showInputDialog(null, "Enter a name for an exam:", "Exam is empty!", JOptionPane.INFORMATION_MESSAGE);
                    System.out.println("Exam name is: " + examName); //debugging
                    if (examName == null || examName.isEmpty())
                    {
                        exam = null;
                        return;
                    }
                    exam = new Exam(examName);
                }

                Object[] questionOptions = {"SAQuestion", "NumQuestion", "MCSAQuestion", "MCMAQuestion"};
                String input = (String)JOptionPane.showInputDialog(null, "What type of question do you want to add?",
                        "Select type of question", JOptionPane.QUESTION_MESSAGE, null, questionOptions, "Select an option");

                if (input.equals("SAQuestion"))
                {
                    System.out.println("You chose SA");
                    SAQuestion questionSA;
                    SAAnswer answerSA;

                    String questionName = JOptionPane.showInputDialog(
                            null, "Enter title for this question:", "Enter a title", JOptionPane.INFORMATION_MESSAGE);

                    if (questionName == null || questionName.isEmpty()) //checks if it is empty or null, if it is, return
                    {
                        JOptionPane.showMessageDialog(null, "ERROR: Name was not created",
                                "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    String questionValueString = JOptionPane.showInputDialog(
                            null, "Enter value for this question:", "Enter a value", JOptionPane.INFORMATION_MESSAGE);

                    if (questionValueString == null || questionValueString.isEmpty()) //checks if it is empty or null, if it is, return
                    {
                        JOptionPane.showMessageDialog(null, "ERROR: value was not created",
                                "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    Double questionValue = Double.parseDouble(questionValueString); //converts to double

                    if(questionValue.isNaN()) //checks if the number is an actual number
                    {
                        JOptionPane.showMessageDialog(null, "ERROR: value is NaN",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }

                    String correctAnswer = JOptionPane.showInputDialog(
                            null, "Enter the correct answer for this question", "Enter correct answer", JOptionPane.INFORMATION_MESSAGE);
                    if (correctAnswer == null || correctAnswer.isEmpty()) //checks if it is empty or null, if it is, return
                    {
                        JOptionPane.showMessageDialog(null, "ERROR: Correct answer was not created",
                                "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    //creates the question
                    answerSA = new SAAnswer(correctAnswer);
                    questionSA = new SAQuestion(questionName, questionValue);
                    questionSA.setRightAnswer(answerSA);
                    exam.addQuestion(questionSA);

                    //tells user it was created
                    JOptionPane.showMessageDialog(null, "Successfully added question!\nName: "
                            + questionName + "\nValue:" + questionValue + "\nCorrect Answer:" + correctAnswer);

                }
                else if (input.equals("MCSAQuestion"))
                {
                    System.out.println("You chose MCSAQuestion");
                    MCSAQuestion questionMCSA;
                    MCSAAnswer answerMCSA;

                    String questionName = JOptionPane.showInputDialog(
                            null, "Enter title for this question:", "Enter a title", JOptionPane.INFORMATION_MESSAGE);

                    if (questionName == null || questionName.isEmpty()) //checks if it is empty or null, if it is, return
                    {
                        JOptionPane.showMessageDialog(null, "ERROR: Name was not created",
                                "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    String questionValueString = JOptionPane.showInputDialog(
                            null, "Enter value for this question:", "Enter a value", JOptionPane.INFORMATION_MESSAGE);

                    if (questionValueString == null || questionValueString.isEmpty()) //checks if it is empty or null, if it is, return
                    {
                        JOptionPane.showMessageDialog(null, "ERROR: value was not created",
                                "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    Double questionValue = Double.parseDouble(questionValueString); //converts to double

                    if(questionValue.isNaN()) //checks if the number is an actual number
                    {
                        JOptionPane.showMessageDialog(null, "ERROR: value is NaN",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }

                    String answerChoicesString = JOptionPane.showInputDialog(
                            null, "Enter number of answer choices:", "Enter # of answers", JOptionPane.INFORMATION_MESSAGE);
                    if (answerChoicesString == null || answerChoicesString.isEmpty()) //checks if it is empty or null, if it is, return
                    {
                        JOptionPane.showMessageDialog(null, "ERROR: value was not created",
                                "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    Double numberOfAnswers = Double.parseDouble(answerChoicesString);

                    if(numberOfAnswers.isNaN()) //checks if the number is an actual number
                    {
                        JOptionPane.showMessageDialog(null, "ERROR: value is NaN",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }

                    questionMCSA = new MCSAQuestion(questionName, questionValue); //asks for name and value

                    for (int i = 0; i < numberOfAnswers; i++) {
                        Double answerValue;
                        String answerName;

                        answerName = JOptionPane.showInputDialog(
                                null, "Enter name for answer #" + (i+1) + ":", "Enter name", JOptionPane.INFORMATION_MESSAGE);

                        if (answerName  == null || answerName .isEmpty()) //checks if it is empty or null, if it is, return
                        {
                            JOptionPane.showMessageDialog(null, "ERROR: Name was not created",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        String answerValueString = JOptionPane.showInputDialog(
                                null, "Enter value for answer #" + (i+1) + ":", "Enter a value", JOptionPane.INFORMATION_MESSAGE);

                        if (answerValueString == null || answerValueString.isEmpty()) //checks if it is empty or null, if it is, return
                        {
                            JOptionPane.showMessageDialog(null, "ERROR: value was not created",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        answerValue = Double.parseDouble(answerValueString); //converts to double

                        if(answerValue.isNaN()) //checks if the number is an actual number
                        {
                            JOptionPane.showMessageDialog(null, "ERROR: value is NaN",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                        }

                        answerMCSA = new MCSAAnswer(answerName, answerValue); // makes new answer class
                        questionMCSA.addAnswer(answerMCSA); //adds answer to question
                        JOptionPane.showMessageDialog(null, "Successfully added answer!\nAnswer Name: "
                                + answerName + "\nAnswer Value:" + answerValue);
                    }
                    exam.addQuestion(questionMCSA);
                    JOptionPane.showMessageDialog(null, "Successfully added question!\nName: "
                            + questionName + "\nValue: " + questionValue);
                }
                else if (input.equals("MCMAQuestion"))
                {
                    System.out.println("You chose MCSAQuestion");
                    MCMAQuestion questionMCMA;
                    MCMAAnswer answerMCMA;

                    String questionName = JOptionPane.showInputDialog(
                            null, "Enter title for this question:", "Enter a title", JOptionPane.INFORMATION_MESSAGE);

                    if (questionName == null || questionName.isEmpty()) //checks if it is empty or null, if it is, return
                    {
                        JOptionPane.showMessageDialog(null, "ERROR: Name was not created",
                                "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    String questionValueString = JOptionPane.showInputDialog(
                            null, "Enter value for this question:", "Enter a value", JOptionPane.INFORMATION_MESSAGE);

                    if (questionValueString == null || questionValueString.isEmpty()) //checks if it is empty or null, if it is, return
                    {
                        JOptionPane.showMessageDialog(null, "ERROR: value was not created",
                                "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    Double questionValue = Double.parseDouble(questionValueString); //converts to double

                    if(questionValue.isNaN()) //checks if the number is an actual number
                    {
                        JOptionPane.showMessageDialog(null, "ERROR: value is NaN",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }

                    String baseCreditString = JOptionPane.showInputDialog(
                            null, "Enter value for this question:", "Enter a value", JOptionPane.INFORMATION_MESSAGE);

                    if (baseCreditString == null || baseCreditString.isEmpty()) //checks if it is empty or null, if it is, return
                    {
                        JOptionPane.showMessageDialog(null, "ERROR: value was not created",
                                "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    Double baseCredit = Double.parseDouble(baseCreditString); //converts to double

                    if(baseCredit.isNaN()) //checks if the number is an actual number
                    {
                        JOptionPane.showMessageDialog(null, "ERROR: value is NaN",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }

                    String answerChoicesString = JOptionPane.showInputDialog(
                            null, "Enter number of answer choices:", "Enter # of answers", JOptionPane.INFORMATION_MESSAGE);
                    if (answerChoicesString == null || answerChoicesString.isEmpty()) //checks if it is empty or null, if it is, return
                    {
                        JOptionPane.showMessageDialog(null, "ERROR: value was not created",
                                "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    Double numberOfAnswers = Double.parseDouble(answerChoicesString);
                    if(numberOfAnswers.isNaN()) //checks if the number is an actual number
                    {
                        JOptionPane.showMessageDialog(null, "ERROR: value is NaN",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }

                    questionMCMA = new MCMAQuestion(questionName, questionValue, baseCredit); //asks for name and value
                    for (int i = 0; i < numberOfAnswers; i++) {
                        Double answerValue;
                        String answerName;

                        answerName = JOptionPane.showInputDialog(
                                null, "Enter name for answer #" + (i+1) + ":", "Enter name", JOptionPane.INFORMATION_MESSAGE);

                        if (answerName  == null || answerName .isEmpty()) //checks if it is empty or null, if it is, return
                        {
                            JOptionPane.showMessageDialog(null, "ERROR: Name was not created",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        String answerValueString = JOptionPane.showInputDialog(
                                null, "Enter value for answer #" + (i+1) + ":", "Enter a value", JOptionPane.INFORMATION_MESSAGE);

                        if (answerValueString == null || answerValueString.isEmpty()) //checks if it is empty or null, if it is, return
                        {
                            JOptionPane.showMessageDialog(null, "ERROR: value was not created",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        answerValue = Double.parseDouble(answerValueString); //converts to double

                        if(answerValue.isNaN()) //checks if the number is an actual number
                        {
                            JOptionPane.showMessageDialog(null, "ERROR: value is NaN",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                        }

                        answerMCMA = new MCMAAnswer(answerName, answerValue); // makes new answer class
                        questionMCMA.addAnswer(answerMCMA); //adds answer to question
                        JOptionPane.showMessageDialog(null, "Successfully added answer!\nAnswer Name: "
                                + answerName + "\nAnswer Value:" + answerValue);
                    }
                    exam.addQuestion(questionMCMA);
                    JOptionPane.showMessageDialog(null, "Successfully added question!\nName: "
                            + questionName + "\nValue: " + questionValue);
                }
                else if (input.equals("NumQuestion"))
                {
                    System.out.println("You chose NumQuestion");
                    NumQuestion questionNum;
                    NumAnswer answerNum;

                    String questionName = JOptionPane.showInputDialog(
                            null, "Enter title for this question:", "Enter a title", JOptionPane.INFORMATION_MESSAGE);

                    if (questionName == null || questionName.isEmpty()) //checks if it is empty or null, if it is, return
                    {
                        JOptionPane.showMessageDialog(null, "ERROR: Name was not created",
                                "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    String questionValueString = JOptionPane.showInputDialog(
                            null, "Enter value for this question:", "Enter a value", JOptionPane.INFORMATION_MESSAGE);

                    if (questionValueString == null || questionValueString.isEmpty()) //checks if it is empty or null, if it is, return
                    {
                        JOptionPane.showMessageDialog(null, "ERROR: value was not created",
                                "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    Double questionValue = Double.parseDouble(questionValueString); //converts to double

                    if(questionValue.isNaN()) //checks if the number is an actual number
                    {
                        JOptionPane.showMessageDialog(null, "ERROR: value is NaN",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }

                    String correctAnswerString = JOptionPane.showInputDialog(
                            null, "Enter the correct answer for this question", "Enter correct answer", JOptionPane.INFORMATION_MESSAGE);
                    if (correctAnswerString == null || correctAnswerString.isEmpty()) //checks if it is empty or null, if it is, return
                    {
                        JOptionPane.showMessageDialog(null, "ERROR: Correct answer was not created",
                                "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    Double correctAnswer = Double.parseDouble(correctAnswerString);
                    if(correctAnswer.isNaN()) //checks if the number is an actual number
                    {
                        JOptionPane.showMessageDialog(null, "ERROR: value is NaN",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }

                    questionNum = new NumQuestion(questionName, questionValue);
                    answerNum = new NumAnswer(correctAnswer);
                    questionNum.setRightAnswer(answerNum);
                    exam.addQuestion(questionNum);
                }
                else {
                    System.out.println("Invalid type of question. Please try again.");
                }
            }
        });

        buttonRemoveQuestion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (exam == null)
                {
                    JOptionPane.showMessageDialog(null, "No exam loaded or created!",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                String stringInput = (String)JOptionPane.showInputDialog(null, "Enter question number to remove:",
                                    "Remove Questions", JOptionPane.INFORMATION_MESSAGE);

                int questionNumber = Integer.parseInt(stringInput);
                try {
                    questionNumber = Integer.parseInt(stringInput);
                } catch (NumberFormatException er) {
                    er.printStackTrace();
                }

                exam.removeQuestion(questionNumber);
                JOptionPane.showMessageDialog(null, "Successfully removed question " + questionNumber + "!");

            }
        });

        buttonReorderQuestions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (exam == null)
                {
                    JOptionPane.showMessageDialog(null, "No exam loaded or created!",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Object[] reorderOptions = {"Questions", "Answers", "Questions AND Answers"};
                String userInput = (String)JOptionPane.showInputDialog(null, "Select an option to reorder:",
                        "Reorder Questions", JOptionPane.INFORMATION_MESSAGE, null, reorderOptions, "Questions");

                String answerInputString;
                int answerInput;
                switch (userInput)
                {
                    case "Questions":
                        exam.reorderQuestions();
                        break;
                    case "Answers":
                        answerInputString = JOptionPane.showInputDialog(null, "Enter question number to reorder (Must be a MCQuestion!)\nEnter -1 for all MCQuestions:",
                                "Reorder Answers", JOptionPane.INFORMATION_MESSAGE);

                        answerInput = Integer.parseInt(answerInputString);
                        try {
                            answerInput = Integer.parseInt(answerInputString);
                        } catch (NumberFormatException er) {
                            er.printStackTrace();
                        }
                        exam.reorderMCAnswers(answerInput);
                        break;
                    case "Questions AND Answers":
                        answerInputString = JOptionPane.showInputDialog(null, "Enter question number to reorder (Must be a MCQuestion!)\nEnter -1 for all MCQuestions:",
                                "Reorder Answers", JOptionPane.INFORMATION_MESSAGE);

                        answerInput = Integer.parseInt(answerInputString);
                        try {
                            answerInput = Integer.parseInt(answerInputString);
                        } catch (NumberFormatException er) {
                            er.printStackTrace();
                        }

                        //reorders the input, THEN shuffles all the questions to avoid shuffling a random question
                        exam.reorderMCAnswers(answerInput);
                        exam.reorderQuestions();
                        break;
                    default:
                        System.out.println("Going back");
                }
            }
        });

        buttonPrintExam.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String examPrintFile = "examPrint.txt";

                if (exam == null)
                {
                    JOptionPane.showMessageDialog(null, "No exam loaded or created!",
                                                "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Object[] printOptions = {"Screen", "File"};
                String userInput = (String)JOptionPane.showInputDialog(null, "Where do you want to print?",
                        "Print Window", JOptionPane.QUESTION_MESSAGE, null, printOptions, "Screen");

                switch(userInput)
                {
                    case "Screen":
                        JOptionPane.showMessageDialog(null, "Screen!");
                        exam.print();
                        break;
                    case "File":
                        PrintWriter examPrintWriter = null;

                        try {
                            examPrintWriter = new PrintWriter(examPrintFile);
                        } catch (FileNotFoundException error) {
                            error.printStackTrace();
                        }

                        exam.save(examPrintWriter);
                        examPrintWriter.close();

                        JOptionPane.showMessageDialog(null, "Successfully saved printed exam to " + examPrintFile + "!");
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Error!");
                        break;
                }
            }
        });

        buttonSaveExam.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (exam == null)
                {
                    JOptionPane.showMessageDialog(null, "No exam loaded or created!",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String examDataFile = "examData.txt";
                PrintWriter examData = null;

                try {
                    examData = new PrintWriter(examDataFile);
                } catch (FileNotFoundException error) {
                    error.printStackTrace();
                }

                exam.save(examData);
                examData.close();
                JOptionPane.showMessageDialog(null, "Successfully saved an exam to " + examDataFile + "!");
            }
        });

        buttonQuit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int x = JOptionPane.showConfirmDialog(null,
                        "Are you sure you want to close the program?", "Exit Program",
                        JOptionPane.YES_NO_OPTION);

                if(x == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
    }

    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame mainFrame = new JFrame("Exam Builder by Larry Ngo");
                mainFrame.setContentPane(new ExamBuilder().panelMain);
                mainFrame.setSize(400, 500);
                mainFrame.setLocation(300, 300);
                mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                mainFrame.pack();
                mainFrame.setVisible(true);

            }
        });
    }
}
