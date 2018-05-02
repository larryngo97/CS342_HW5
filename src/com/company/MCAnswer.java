package com.company;
import java.io.PrintWriter;
import java.util.Scanner;

abstract class MCAnswer extends Answer {
    protected String text;
    protected double creditIfSelected;

    protected MCAnswer(String input, double value)
    {
        text = input;
        creditIfSelected = value;
    }

    public MCAnswer (Scanner scan)
    {
        creditIfSelected = scan.nextDouble();
        scan.nextLine();
        text = scan.nextLine();
    }

    public void print()
    {
        System.out.println("The correct answer is: " + text);
    }

    public double getCredit(Answer rightAnswer)
    {
        if (this.equals(rightAnswer))
        {
            return creditIfSelected;
        }
        else
        {
            return 0.0;
        }
    }

    public void save(PrintWriter writer)
    {
        if(this instanceof MCSAAnswer)
        {
            writer.println("MCSAAnswer");
            writer.println(text);
            writer.println("");
        }
        else if (this instanceof MCMAAnswer)
        {
            writer.println("MCMAAnswer");
            writer.println(text);
            writer.println("");
        }
        return;
    }
}
