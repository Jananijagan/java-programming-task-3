import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Quiz {
    private ArrayList<QuizQuestion> questions;
    private int score;
    private int currentQuestionIndex;
    private boolean answered;
    private int timeLimitPerQuestion = 10; // in seconds

    public Quiz() {
        questions = new ArrayList<>();
        score = 0;
        currentQuestionIndex = 0;
        answered = false;
    }

    public void addQuestion(QuizQuestion question) {
        questions.add(question);
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        for (currentQuestionIndex = 0; currentQuestionIndex < questions.size(); currentQuestionIndex++) {
            QuizQuestion question = questions.get(currentQuestionIndex);
            displayQuestion(question);

            answered = false;
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    if (!answered) {
                        System.out.println("\nTime's up! Moving to the next question.");
                        nextQuestion();
                    }
                }
            }, timeLimitPerQuestion * 1000);

            int userAnswer = -1;
            while (!answered) {
                System.out.print("Enter your answer (1-4): ");
                if (scanner.hasNextInt()) {
                    userAnswer = scanner.nextInt() - 1;
                    if (userAnswer >= 0 && userAnswer < 4) {
                        answered = true;
                        timer.cancel();
                        checkAnswer(userAnswer);
                    } else {
                        System.out.println("Invalid option. Please choose between 1 and 4.");
                    }
                } else {
                    System.out.println("Invalid input. Please enter a number between 1 and 4.");
                    scanner.next(); // Clear the invalid input
                    scanner.close();
                }
            }
        }

        displayResults();
        scanner.close();
    }

    private void displayQuestion(QuizQuestion question) {
        System.out.println("\nQuestion " + (currentQuestionIndex + 1) + ": " + question.getQuestion());
        String[] options = question.getOptions();
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ": " + options[i]);
        }
    }

    private void checkAnswer(int answerIndex) {
        QuizQuestion question = questions.get(currentQuestionIndex);
        if (question.isCorrect(answerIndex)) {
            System.out.println("Correct!");
            score++;
        } else {
            System.out.println("Incorrect. The correct answer was: " + (question.getCorrectAnswerIndex() + 1));
        }
    }

    private void nextQuestion() {
        answered = true; // To prevent further input for the current question
    }

    private void displayResults() {
        System.out.println("\nQuiz completed!");
        System.out.println("Your final score is: " + score + "/" + questions.size());
        System.out.println("Review of questions and answers:");
        for (int i = 0; i < questions.size(); i++) {
            QuizQuestion question = questions.get(i);
            System.out.println("\nQuestion " + (i + 1) + ": " + question.getQuestion());
            String[] options = question.getOptions();
            for (int j = 0; j < options.length; j++) {
                System.out.println((j + 1) + ": " + options[j]);
            }
            System.out.println("Correct answer: " + (question.getCorrectAnswerIndex() + 1));
        
           
        }
       
    }
}
