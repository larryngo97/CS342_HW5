package com.company;
import java.io.PrintWriter;
import java.util.Scanner;

class SAAnswer extends Answer {
    protected String text;

    public SAAnswer(String input)
    {
        text = input;
    }

    public SAAnswer(Scanner scan)
    {
        text = scan.nextLine();
    }

    public void print()
    {
        System.out.println(text);
    }

    public double getCredit(Answer rightAnswer)
    {
        text = text.toUpperCase(); //makes the student answer all caps
        ((SAAnswer)rightAnswer).text = ((SAAnswer)rightAnswer).text.toUpperCase(); //makes the right answer all caps
        if(text.equals(((SAAnswer)rightAnswer).text))
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
        writer.println("SAAnswer");
        writer.println(text);
    }
}
