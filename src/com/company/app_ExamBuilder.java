package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    public app_ExamBuilder() {

        buttonLoadExam.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Exam Builder v1.0 by Larry Ngo");
        frame.setContentPane(new app_ExamBuilder().ExamBuilder);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
    }
}
