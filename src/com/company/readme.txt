LARRY NGO	    Tyler Huston    Kristi Gjoni
LNGO4		    THusto3         kgjoni2
677609505	    662165197
ExamBuilder     ExamGrader      ExamTaker

==========================GENERAL ASSUMPTIONS AND WORK PROGRESS=================

The project can successfully read in files but will
need user to type "end" to break out of the loop in making the questions...
(See examData.txt)


When answering MCMAQuestions... user must type all the letters one at a time
then enter in a space/newline in order to get out of answering the question.
Ex:
A
B
C
(space here) <-- continues onto next question

Use the examData.txt to read in the test (that gives a good idea on how to structure it)

Things that don't work:
- restoring student answers (it can take in a file name but does not correlate well)


----------------ExamBuilder---------------------------------------------------
Finished, everything should be working. MAKE SURE examData.txt is in the directory
or it will not work. Also make sure examPrint.txt is there so it creates a printable version


----------------------------------------------------------------------------------

----------------ExamTaker---------------------------------------------------
Finished. Make sure studentData.txt is in the directory

----------------------------------------------------------------------------------

----------------ExamGrader--------------------------------------------------
Tyler Huston - May 2, 2018
Upon running the app_ExamGrader, the prompt will have a text field with two buttons. I have 
assumed that the text field is correct and the open button will work, otherwise the program will error
and need to be restarted. There is no functionality to redo the text field. The open button will remake
and exam and be visible for use. 

Exam.java was written by a partner in order to commidate their needs, and it
has become difficult to follow the recipie in their terms. Assuming there was
a more generalized Exam.java written, the ExamBuilder would process the
studentData and transform it into two parts, the exam and the answers. The
process would build the exam line by line and the answers line by line and
then report the values in a CSV. Similar issues occured during homework 4.

ISSUES: Exam does not properly rebuild from a data file that is not exactly to
the description. When it does build the questions class can sometimes fail if
there was a MCMAQuestion due to the commidation needed for the ExamBuilder.
Report values was not thoroughly tested because of this.

During testing, several issues concerning the base files will appear. My ExamGrader program 
will read through a student file and create an examfile from the first line of that student file,
but the grading will not work without changing the contents of another students application. 

This application was assumed to be run from an IDE, specifically from IntelliJ IDEA.

----------------------------------------------------------------------------------
