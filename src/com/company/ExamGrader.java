package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ExamGrader {


    private JButton fileButton;
    private JTextField file;
    private JButton feedbackButton;
    private JTextArea Information;
    private JPanel panelMain;
    protected Exam exam;

    public ExamGrader() {
        fileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                File answersFile = new File(file.getText()); //reads in student answers from args
                Scanner answerReader = null;
                try {
                    answerReader = new Scanner(answersFile);
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }

                File examFile = new File(answerReader.nextLine()); //reads the first line of answers to find the exam
                Scanner newReader = null;
                try {
                    newReader = new Scanner(examFile);
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }

                exam = new Exam(newReader);

                newReader.nextLine();

                while(newReader.hasNext())
                {
                    String line = newReader.nextLine();
                    if (line.equals("SAQuestion"))
                    {
                        SAQuestion q1 = new SAQuestion(newReader);
                        SAAnswer ans = new SAAnswer(newReader);
                        q1.setRightAnswer(ans);
                        exam.addQuestion(q1);
                    }
                    else if(line.equals("MCSAQuestion"))
                    {
                        MCSAQuestion q1 = new MCSAQuestion(newReader);
                        int numAnswers = newReader.nextInt();
                        newReader.nextLine();

                        for(int i = 0; i < numAnswers; i++)
                        {
                            double credit = newReader.nextDouble();
                            String ans = newReader.nextLine();
                            MCSAAnswer ans1 = new MCSAAnswer(ans, credit);
                            q1.addAnswer(ans1);
                        }
                        exam.addQuestion(q1);
                    }
                    else if(line.equals("MCMAQuestion"))
                    {
                        MCMAQuestion q1 = new MCMAQuestion(newReader);
                        int numAnswers = newReader.nextInt();
                        newReader.nextLine();
                        for(int i = 0; i < numAnswers; i++)
                        {
                            double credit = newReader.nextDouble();
                            String ans = newReader.nextLine();
                            MCSAAnswer ans1 = new MCSAAnswer(ans, credit);
                            q1.addAnswer(ans1);
                        }
                        exam.addQuestion(q1);
                    }
                    else if(line.equals("end"))
                    {
                        break;
                    }
                    else
                    {
                        continue;
                    }
                }
            }
        });

        feedbackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                double score = exam.getValue();

                System.out.println("Student Score: " + score);

                PrintWriter feedback = null;
                try {
                    feedback = new PrintWriter("feedback.csv");
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }

                exam.reportQuestionValues();
                feedback.println("Score: " + score);

                JOptionPane.showMessageDialog(null, score);
            }
        });
    }

    public static void main(String[] args) throws FileNotFoundException {
        JFrame frame = new JFrame("ExamGrader");
        frame.setContentPane(new ExamGrader().panelMain);
        frame.setLocation(300,300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }
}
