package com.company;
import java.io.PrintWriter;
import java.util.Scanner;

public abstract class Question {

    protected String text;
    protected Answer rightAnswer;
    protected Answer studentAnswer;
    protected double maxValue;

    protected Question (String input, double value) {
        maxValue = value;
        text = input;
    }

    public Question (Scanner scan)
    {
        maxValue = scan.nextDouble();
        scan.nextLine();

        text = scan.nextLine();
    }


    public void print()
    {
        System.out.println(text + "\n");
    }

    public void setRightAnswer (Answer rightAnswer)
    {
        this.rightAnswer = rightAnswer;
    }

    public abstract Answer getNewAnswer ();

    public abstract void getAnswerFromStudent();

    public double getValue()
    {
        return maxValue;
    }

    public abstract void save(PrintWriter writer);

    public void saveStudentAnswer (PrintWriter writer)
    {
        if(this instanceof SAQuestion)
        {
            writer.println("SAAnswer");
            if (studentAnswer == null)
            {
                studentAnswer = getNewAnswer();
            }
            writer.println(((SAAnswer)studentAnswer).text);
            writer.println();
        }
        else if (this instanceof MCSAQuestion)
        {
            writer.println("MCSAAnswer");
            if (studentAnswer == null)
            {
                studentAnswer = getNewAnswer();
            }
            writer.println(((MCSAAnswer)studentAnswer).text);
            writer.println();
        }
    }

    public void restoreStudentAnswers (Scanner scan)
    {
        System.out.println("Answer Type");
        String input = scan.nextLine();
        if(input.equals("SAAnswer"))
        {
            System.out.println("This is a SAAnswer");
            studentAnswer = new SAAnswer(scan);
        }
        else if (input.equals("MCSAAnswer"))
        {
            System.out.println("This is a MCSAAnswer");
            String nameOfMCSA = scan.nextLine();
            studentAnswer = new MCSAAnswer(nameOfMCSA, 0.0);
        }

    }

}



