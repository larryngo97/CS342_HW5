package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class app_ExamBuilder {
    private JPanel ExamBuilder;
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
    private JTextField textConsole;
    private JTextField textInput;

    public static int WINDOW_WIDTH = 400;
    public static int WINDOW_HEIGHT = 500;
    Exam exam;

    public app_ExamBuilder() {

        buttonLoadExam.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){

                Scanner reader = null;
                try {
                    File examFile = new File(new File("examData.txt").getAbsolutePath());
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
        buttonPrintExam.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String examPrintFile = "examPrint.txt";

                textConsole.setText("Select an option to print: 1. Exam to screen 2. Exam to file");

                Scanner print = new Scanner(textInput.getText());
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
            }
        });
    }

    public static void main(String[] args) throws IOException {
        JFrame frame = new JFrame("Exam Builder v1.0 by Larry Ngo");
        frame.setContentPane(new app_ExamBuilder().ExamBuilder);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setLocation(300, 300);

    }
}
