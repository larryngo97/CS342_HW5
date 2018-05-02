package com.company;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

class MCMAQuestion extends MCQuestion {
    protected ArrayList<Answer> studentAnswer;
    protected double baseCredit;

    public MCMAQuestion (String input, double value, double credit)
    {
        super(input, value);
        baseCredit = credit;

        studentAnswer = new ArrayList<Answer>();
    }

    public MCMAQuestion (Scanner scan)
    {
        super(scan); // asks for name of question then value
        baseCredit = scan.nextDouble(); //sets baseCredit
        scan.nextLine();
        studentAnswer = new ArrayList<Answer>();
    }

    public Answer getNewAnswer()
    {
        MCMAAnswer newAnswer = new MCMAAnswer("null", 0.0);
        return newAnswer;
    }

    public Answer getNewAnswer(String input, double value)
    {
        MCMAAnswer newAnswer = new MCMAAnswer(input, value);
        return newAnswer;
    }

    public void getAnswerFromStudent()
    {
        studentAnswer = null; //sets null in case student changes answer
        studentAnswer = new ArrayList<Answer>();

        print();
        Scanner newScan = ScannerFactory.getKeyboardScanner();
        String input = newScan.nextLine();
        while (true)
        {
            if (input == "\n") // user must enter a space after finishing answering
            {
                break;
            }
            char c = Character.toUpperCase(input.charAt(0));
            int choice = c - 'A';

            if (choice < 0 || choice >= answers.size())
            {
                return;
            }
            studentAnswer.add(answers.get(choice));
            System.out.println("Added " + answers.get(choice));
            input = newScan.nextLine();
        }
        return;
    }

    public double getValue()
    {
        double value = 0.0;
        for (int i = 0; i < studentAnswer.size(); i++) //loops through all the answers
        {
            value += super.getValue(((MCAnswer)studentAnswer.get(i))); // adds up all the values of the answers
        }
        return value + (baseCredit * maxValue);
    }

    public void save (PrintWriter writer)
    {
        writer.println("MCMAQuestion");
        writer.println(maxValue);
        writer.println(text);
        writer.println(baseCredit);
        writer.println(answers.size());
        for (int i = 0; i < answers.size(); i++)
        {
            writer.print(((MCMAAnswer)answers.get(i)).creditIfSelected + " ");
            writer.println(((MCMAAnswer)answers.get(i)).text);
        }
        //writer.println(((MCSAAnswer)rightAnswer).text);
        writer.println("");
    }

    public void saveStudentAnswer(PrintWriter writer)
    {
        writer.println("MCMAAnswer");
        writer.println(studentAnswer.size());
        for (int i = 0; i < studentAnswer.size(); i++)
        {
            writer.println(((MCAnswer)studentAnswer.get(i)).text);
        }
        writer.println();
    }

    public void restoreStudentAnswers(Scanner scan)
    {
        System.out.println("Answer Type");
        scan.nextLine();
        System.out.println("This is a MCMAAnswer");
        int numberOfAnswers = scan.nextInt();
        System.out.println(numberOfAnswers);
        for (int i = 0; i < numberOfAnswers + 1; i++)
        {
            String input = scan.nextLine();
            MCMAAnswer answerMCMA = new MCMAAnswer(input, 0.0);
            studentAnswer.add(answerMCMA);
            //((MCAnswer)studentAnswer.get(i)).text = scan.nextLine();
        }
    }
}
