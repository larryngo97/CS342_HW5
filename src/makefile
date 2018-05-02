JCC = javac
JFLAGS = -g
default: ExamBuilder.class ExamTaker.class ExamGrader.class Question.class Exam.class Answer.class MCAnswer.class MCSAAnswer.class MCMAAnswer.class SAAnswer.class NumAnswer.class Question.class MCQuestion.class MCSAQuestion.class MCMAQuestion.class SAQuestion.class NumQuestion.class ScannerFactory.class

ExamBuilder.class: ExamBuilder.java
		$(JCC) $(JFLAGS) ExamBuilder.java

ExamTaker.class: ExamTaker.java
		$(JCC) $(JFLAGS) ExamTaker.java

ExamGrader.class: ExamGrader.java
		$(JCC) $(JFLAGS) ExamGrader.java

Question.class: Question.java
		$(JCC) $(JFLAGS) Question.java
		
MCQuestion.class: MCQuestion.java
		$(JCC) $(JFLAGS) MCQuestion.java
		
SAQuestion.class: SAQuestion.java
		$(JCC) $(JFLAGS) SAQuestion.java
		
NumQuestion.class: NumQuestion.java
		$(JCC) $(JFLAGS) NumQuestion.java
		
MCSAQuestion.class: MCSAQuestion.java
		$(JCC) $(JFLAGS) MCSAQuestion.java
		
MCMAQuestion.class: MCMAQuestion.java
		$(JCC) $(JFLAGS) MCMAQuestion.java

Answer.class: Answer.java
		$(JCC) $(JFLAGS) Answer.java
		
MCAnswer.class: MCAnswer.java
		$(JCC) $(JFLAGS) MCAnswer.java
		
SAAnswer.class: SAAnswer.java
		$(JCC) $(JFLAGS) SAAnswer.java

NumAnswer.class: NumAnswer.java
		$(JCC) $(JFLAGS) NumAnswer.java
		
MCSAAnswer.class: MCSAAnswer.java
		$(JCC) $(JFLAGS) MCSAAnswer.java
		
MCMAAnswer.class: MCMAAnswer.java
		$(JCC) $(JFLAGS) MCMAAnswer.java

Exam.class: Exam.java
		$(JCC) $(JFLAGS) Exam.java

ScannerFactory.class: ScannerFactory.java
		$(JCC) $(JFLAGS) ScannerFactory.java
		
clean: 
		$(RM) *.class
