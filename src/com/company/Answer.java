package com.company;

import java.io.PrintWriter;
import java.util.Scanner;

public abstract class Answer {

    protected Answer ()
    {

    }

    public Answer (Scanner scan)
    {
    }

    public void print()
    {
        System.out.println("Error");
    }

    public abstract double getCredit(Answer rightAnswer);

    public abstract void save (PrintWriter writer);

}

