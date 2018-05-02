package com.company;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

abstract class MCQuestion extends Question {
    protected ArrayList<Answer> answers;

    protected MCQuestion (String input, double value)
    {
        super(input, value);
        answers = new ArrayList<Answer>();
    }

    protected MCQuestion (Scanner scan)
    {
        super(scan);
        answers = new ArrayList<Answer>();
    }

    public void print()
    {
        System.out.println(text);
        for (int i = 0; i < answers.size(); i++)
        {
            String[] letterArray = {"A", "B", "C", "D", "E"};
            System.out.println(letterArray[i] + ". " + ((MCAnswer)answers.get(i)).text + " (Worth: " + ((MCAnswer)answers.get(i)).creditIfSelected + ")");
        }
        System.out.println("");
    }

    public void addAnswer(MCAnswer ans)
    {
        answers.add(ans);
    }

    public void reorderAnswers()
    {
        Collections.shuffle(answers);
    }

    public double getValue(MCAnswer ans)
    {
        double value = 0.0;
        for (int i = 0; i < answers.size(); i++)
        {
            if (answers.get(i).equals(ans))
            {
                value += maxValue * ans.creditIfSelected;
            }
        }
        return value;
    }

    public void save (PrintWriter writer)
    {
        writer.println("MCQuestion");
        writer.println(maxValue);
        writer.println(text);
    }
}
