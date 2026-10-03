package oop.class_problems;

import java.util.*;

public class OnlineExaminationSystem {

    public static void main(String[] args) {

        Student student = new Student("Student 1");

        Examination exam = new Examination("Exam A");

        exam.addQuestion(
                new MCQQuestion(
                        "Question 1",
                        "Which language is object oriented?",
                        "C",
                        5
                )
        );

        exam.addQuestion(
                new TrueFalseQuestion(
                        "Question 2",
                        "Java supports multiple inheritance through classes.",
                        true,
                        5
                )
        );

        Attempt attempt = new Attempt(student, exam);

        System.out.println(
                "Exam A started by " + student.getName() + "."
        );

        attempt.answer("Question 1", "C");

        System.out.println(
                "Answer recorded for Question 1."
        );

        attempt.answer("Question 2", "False");

        System.out.println(
                "Answer recorded for Question 2."
        );

        attempt.submit();

        System.out.println(
                "Exam A submitted by " + student.getName() + "."
        );

        attempt.displayResult();

        attempt.answer("Question 1", "A");
    }
}

class Student {

    private String name;

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

abstract class Question {

    protected String id;
    protected String text;
    protected int marks;

    public Question(String id, String text, int marks) {
        this.id = id;
        this.text = text;
        this.marks = marks;
    }

    public String getId() {
        return id;
    }

    public int getMarks() {
        return marks;
    }

    public abstract boolean evaluate(String answer);
}

class MCQQuestion extends Question {

    private String correctAnswer;

    public MCQQuestion(
            String id,
            String text,
            String correctAnswer,
            int marks) {

        super(id, text, marks);
        this.correctAnswer = correctAnswer;
    }

    @Override
    public boolean evaluate(String answer) {
        return correctAnswer.equalsIgnoreCase(answer);
    }
}

class TrueFalseQuestion extends Question {

    private boolean correctAnswer;

    public TrueFalseQuestion(
            String id,
            String text,
            boolean correctAnswer,
            int marks) {

        super(id, text, marks);
        this.correctAnswer = correctAnswer;
    }

    @Override
    public boolean evaluate(String answer) {
        return Boolean.parseBoolean(answer) == correctAnswer;
    }
}

class ShortAnswerQuestion extends Question {

    private String correctAnswer;

    public ShortAnswerQuestion(
            String id,
            String text,
            String correctAnswer,
            int marks) {

        super(id, text, marks);
        this.correctAnswer = correctAnswer;
    }

    @Override
    public boolean evaluate(String answer) {
        return correctAnswer.equalsIgnoreCase(answer);
    }
}

class Examination {

    private String name;
    private ArrayList<Question> questions;

    public Examination(String name) {
        this.name = name;
        questions = new ArrayList<>();
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }
}

class Attempt {

    private Student student;
    private Examination examination;
    private HashMap<String, String> answers;
    private boolean submitted;

    public Attempt(Student student, Examination examination) {

        this.student = student;
        this.examination = examination;
        this.answers = new HashMap<>();
        this.submitted = false;
    }

    public void answer(String questionId, String answer) {

        if (submitted) {
            System.out.println(
                    "Cannot change answers for a submitted examination."
            );
            return;
        }

        answers.put(questionId, answer);

        System.out.println(
                "Answer recorded for " + questionId + "."
        );
    }

    public void submit() {

        submitted = true;
    }

    public void displayResult() {

        int totalScore = 0;
        int totalMarks = 0;

        for (Question question : examination.getQuestions()) {

            String answer = answers.get(question.getId());

            boolean correct =
                    answer != null && question.evaluate(answer);

            totalMarks += question.getMarks();

            if (correct) {

                totalScore += question.getMarks();

                System.out.println(
                        "Result: " + question.getId()
                        + ": Correct ("
                        + question.getMarks()
                        + " points)"
                );

            } else {

                System.out.println(
                        "Result: " + question.getId()
                        + ": Incorrect (0 points)"
                );
            }
        }

        System.out.println(
                "Total score: "
                + totalScore
                + "/"
                + totalMarks
                + "."
        );
    }
}