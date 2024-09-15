public class Main {
    public static void main(String[] args) {
        Quiz quiz = new Quiz();

        // Adding questions to the quiz
        quiz.addQuestion(new QuizQuestion(
                "What is the capital of France?",
                new String[]{"Berlin", "London", "Paris", "Rome"},
                2 // correct answer index (Paris)
        ));

        quiz.addQuestion(new QuizQuestion(
                "Which planet is known as the Red Planet?",
                new String[]{"Earth", "Mars", "Jupiter", "Venus"},
                1 // correct answer index (Mars)
        ));

        quiz.addQuestion(new QuizQuestion(
                "What is the chemical symbol for water?",
                new String[]{"H2O", "O2", "NaCl", "CO2"},
                0 // correct answer index (H2O)
        ));

        // Start the quiz
        quiz.start();
    }
}
