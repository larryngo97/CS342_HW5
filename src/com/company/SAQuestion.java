package com.company;
import java.io.PrintWriter;
import java.util.Scanner;

class SAQuestion extends Question{
    public SAQuestion (String input, double value)
    {
        super(input, value);
    }

    public SAQuestion (Scanner scan)
    {
        super(scan);
    }

    public Answer getNewAnswer()
    {
        SAAnswer newAnswer = new SAAnswer("null");
        return newAnswer;
    }

    public Answer getNewAnswer(String input)
    {
        SAAnswer newAnswer = new SAAnswer(input);
        return newAnswer;
    }

    public void getAnswerFromStudent()
    {
        Scanner newScan = ScannerFactory.getKeyboardScanner();
        print(); //prints question
        studentAnswer = new SAAnswer(newScan); //makes new student answer
        return;
    }

    public double getValue()
    {
        double correct = studentAnswer.getCredit(rightAnswer);
        if (correct == 1.0)
        {
            return maxValue;
        }
        else
        {
            return 0.0;
        }
    }

    public void save (PrintWriter writer)
    {
        writer.println("SAQuestion");
        writer.println(maxValue);
        writer.println(text);
        writer.println(((SAAnswer)rightAnswer).text);
        writer.println("");
    }
}
