package com.company;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ExamGrader {
    public static void main(String[] args) throws FileNotFoundException {
       System.out.print("Tyler Huston \nthuston \nProject4\nArpil 13, 2018\n\n\n"); //Information 

        File answersFile = new File(args[0]); //reads in student answers from args
        Scanner answerReader = new Scanner(answersFile);

	File examFile = new File(answerReader.nextLine()); //reads the first line of answers to find the exam
	Scanner newReader = new Scanner(examFile);

	Exam exam = new Exam(newReader);

	newReader.nextLine();

	while(newReader.hasNext())
        {
            String line = newReader.nextLine();
            if (line.equals("SAQuestion"))
            {
                SAQuestion q1 = new SAQuestion(newReader);
                SAAnswer ans = new SAAnswer(newReader);
                q1.setRightAnswer(ans);
                exam.addQuestion(q1);
            }
            else if(line.equals("MCSAQuestion"))
            {
                MCSAQuestion q1 = new MCSAQuestion(newReader);
                int numAnswers = newReader.nextInt();
                newReader.nextLine();

                for(int i = 0; i < numAnswers; i++)
                {
                    double credit = newReader.nextDouble();
                    String ans = newReader.nextLine();
                    MCSAAnswer ans1 = new MCSAAnswer(ans, credit);
                    q1.addAnswer(ans1);
                }
                exam.addQuestion(q1);
            }
            else if(line.equals("MCMAQuestion"))
            {
                MCMAQuestion q1 = new MCMAQuestion(newReader);
                int numAnswers = newReader.nextInt();
                newReader.nextLine();
                for(int i = 0; i < numAnswers; i++)
                {
                    double credit = newReader.nextDouble();
                    String ans = newReader.nextLine();
                    MCSAAnswer ans1 = new MCSAAnswer(ans, credit);
                    q1.addAnswer(ans1);
                }
                exam.addQuestion(q1);
            }
            else if(line.equals("end"))
            {
                break;
            }
            else
            {
                continue;
            }
        }

        double score = exam.getValue();
        System.out.println("Student Score: " + score);	

	PrintWriter feedback = new PrintWriter("feedback.csv");

	exam.reportQuestionValues();

	feedback.println("Score: " + score);

	
    }
}