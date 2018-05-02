package com.company;

import java.io.PrintWriter;
import java.util.*;

public class Exam {
    private ArrayList<Question> question;
    private String text;

    public Exam (String input)
    {
        question = new ArrayList<Question>();
        text = input;
    }

    public Exam (Scanner scan)
    {
        question = new ArrayList<Question>();
        text = scan.nextLine();
    }

    public void printQuestion(int position)
    {
        question.get(position-1).print();
    }

    public void resetAnswers ()
    {
        for (int i = 0; i < question.size(); i++)
        {
            question.get(i).getNewAnswer();
        }
    }

    public void print()
    {
        System.out.println(text);
        for (int i = 0; i < question.size(); i++)
        {
            System.out.println("Question #" + (i+1));
            if(question.get(i) instanceof SAQuestion)
            {
                System.out.println("SAQuestion");
                question.get(i).print();
            }
            else if(question.get(i) instanceof MCSAQuestion)
            {
                System.out.println("MCSAQuestion");
                question.get(i).print();
            }
            else if(question.get(i) instanceof MCMAQuestion)
            {
                System.out.println("MCMAQuestion");
                question.get(i).print();
            }
            else if(question.get(i) instanceof NumQuestion)
            {
                System.out.println("NumQuestion");
                question.get(i).print();
            }
            else
            {
                System.out.println("INVALID QUESTION TYPE: ERROR");
                question.get(i).print();
            }
        }
    }

    public void addQuestion(Question q)
    {
        question.add(q); //uses add() method to add into an array list
    }

    public void removeQuestion(int position)
    {
        question.remove(position-1); //removes the element at position given
    }


    public void reorderQuestions()
    {
        Collections.shuffle(question); //shuffles all the questions in the array list
    }

    public void reorderMCAnswers(int position)
    {
        if (position < 0) // checks if it should do all
        {
            for (int i = 0; i < question.size(); i++) // goes through all questions
            {
                if(question.get(i) instanceof MCQuestion) //sees if the question is a MC
                {
                    ((MCQuestion) question.get(i)).reorderAnswers(); //shuffles the question's answers
                }
            }
        }
        else // instead just does one question
        {
            if(question.get(position-1) instanceof MCQuestion) // sees if the question is a MC
            {
                ((MCQuestion) question.get(position-1)).reorderAnswers(); //shuffles the question's answers
            }
        }
    }

    public void getAnswerFromStudent(int position)
    {
        question.get(position-1).getAnswerFromStudent(); //gets answer from student in that position
    }

    public double getValue()
    {
        double score = 0;

        for(int i = 0; i < question.size(); i++)
        {
            score += question.get(i).getValue();

            System.out.println("Score received for Question " + (i+1) + ": " + question.get(i).getValue());
        }
        return score;
    }

    public void reportQuestionValues()
    {
        double score = 0.0;
        for(int i = 0; i < question.size(); i++)
        {
            score += question.get(i).maxValue;
            System.out.println("Question " + (i+1) + " value: " + question.get(i).maxValue);
        }
        System.out.println("Total score for this exam: " + score);
    }

    public void save (PrintWriter writer)
    {
        writer.println(text);

        Date date = new Date();
        String dateString = String.format("Date and time: %tc\n", date);
        writer.println(dateString);

        for (int i = 0; i < question.size(); i++)
        {
            question.get(i).save(writer);
        }
    }

    public void saveStudentAnswers(PrintWriter writer)
    {
        writer.println(text); //prints exam name
        writer.println();

        Date date = new Date();
        String dateString = String.format("#Date and time last saved: %tc\n\n", date);
        writer.println(dateString);

        for (int i = 0; i < question.size(); i++)
        {
            question.get(i).saveStudentAnswer(writer);
        }
    }

    public void restoreStudentAnswers (Scanner scan)
    {
        String studentName = scan.nextLine();
        for (int i = 0; i < question.size(); i++)
        {
            scan.nextLine();
            question.get(i).restoreStudentAnswers(scan);
        }
    }
}
