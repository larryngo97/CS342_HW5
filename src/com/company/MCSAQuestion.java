package com.company;
import java.io.PrintWriter;
import java.util.Scanner;

class MCSAQuestion extends MCQuestion {
    public MCSAQuestion (String input, double value)
    {
        super(input, value);
    }

    public MCSAQuestion (Scanner scan)
    {
        super(scan);
    }

    public Answer getNewAnswer()
    {

        MCSAAnswer newAnswer = new MCSAAnswer("null", 0.0);
        return newAnswer;
    }

    public Answer getNewAnswer(String input, double value)
    {
        MCSAAnswer newAnswer = new MCSAAnswer(input,value);
        return newAnswer;
    }

    public void getAnswerFromStudent()
    {
        studentAnswer = null; //change to null in case student changes answer
        print();
        Scanner newScan = ScannerFactory.getKeyboardScanner();
        String input = newScan.nextLine();
        char c = Character.toUpperCase(input.charAt(0));
        int choice = c - 'A';

        if (choice < 0 || choice >= answers.size())
        {
            return;
        }

        studentAnswer = answers.get(choice);
    }

    public double getValue()
    {
        return super.getValue(((MCAnswer)studentAnswer));
    }

    public void save (PrintWriter writer)
    {
        writer.println("MCSAQuestion");
        writer.println(maxValue);
        writer.println(text);
        writer.println(answers.size());
        for (int i = 0; i < answers.size(); i++)
        {
            writer.print(((MCSAAnswer)answers.get(i)).creditIfSelected + " ");
            writer.println(((MCSAAnswer)answers.get(i)).text);
        }
        //writer.println(((MCSAAnswer)rightAnswer).text);
        writer.println("");
    }

}
