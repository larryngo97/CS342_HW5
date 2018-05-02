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
Tyler Huston - April 11, 2018
CommandLine arguement wanted for the answers file. The Exam file will be found
from that, assuming the first line of the answers file is the name of the exam
file.

Also, the answer file is assumed to finish with only the word end. See example
student data file at the bottom


Exam.java was written by a partner in order to commidate their needs, and it
has become difficult to follow the recipie in their terms. Assuming there was
a more generalized Exam.java written, the ExamBuilder would process the
studentData and transform it into two parts, the exam and the answers. The
process would build the exam line by line and the answers line by line and
then report the values in a CSV.

ISSUES: Exam does not properly rebuild from a data file that is not exactly to
the description. When it does build the questions class can sometimes fail if
there was a MCMAQuestion due to the commidation needed for the ExamBuilder.
Report values was not thoroughly tested because of this.

Assuming the perfect conditions exist, this application would produce a
comma-seperated value form that would list every student score.

To run the ExamGrader:
	make
	java ExamGrader exampleStudent.txt

----------------------------------------------------------------------------------