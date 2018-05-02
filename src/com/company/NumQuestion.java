package com.company;
import java.io.PrintWriter;
import java.util.Scanner;

class NumQuestion extends Question{
    public NumQuestion(String input, double value)
    {
        super(input, value);
    }

    public NumQuestion(Scanner scan)
    {
        super(scan);
    }

    public Answer getNewAnswer()
    {
        NumAnswer newAnswer = new NumAnswer(Double.parseDouble("null"));
        return newAnswer;
    }

    public void getAnswerFromStudent()
    {
        Scanner scan = new Scanner(System.in);
        int input;
        System.out.print("Type in a number: ");
        input = scan.nextInt();
        text = Integer.toString(input);
    }

    public double getValue()
    {
        return ((NumAnswer)getNewAnswer()).getCredit(getNewAnswer());
    }

    public void save (PrintWriter writer)
    {
        writer.println("NumQuestion");
        writer.println(maxValue);
        writer.println(text);
    }

}
