package com.company;
import java.io.PrintWriter;
import java.util.Scanner;

class NumAnswer extends Answer {
    private Double answerNum;

    public NumAnswer(double value)
    {
        answerNum = value;
    }

    public NumAnswer(Scanner scan)
    {
        answerNum = scan.nextDouble();
    }

    public void print()
    {
        System.out.println("The answer is: " + answerNum);
    }

    public double getCredit(Answer rightAnswer)
    {
        if(((NumAnswer)rightAnswer).answerNum.equals(answerNum))
        {
            return 1.0;
        }
        else
        {
            return 0.0;
        }
    }

    public void save (PrintWriter writer)
    {
        writer.println("NumAnswer");
        writer.println(answerNum);
    }
}
