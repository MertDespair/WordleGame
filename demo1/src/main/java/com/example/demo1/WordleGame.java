package com.example.demo1;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class WordleGame extends Application {

    private static final int MAX_TRIES = 6; // Max number of tries allowed
    private static final String SECRET_WORD = "HELLO"; // Secret word to guess

    private int currentTry = 0; // Current try count
    private Text[][] resultTexts = new Text[MAX_TRIES][SECRET_WORD.length()]; // Texts to show results
    private TextField inputField; // Input field for guessing
    private Button guessButton; // Button to make a guess

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Wordle Game");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setHgap(10);

        // Set column constraints for consistent spacing
        for (int i = 0; i < SECRET_WORD.length(); i++) {
            ColumnConstraints column = new ColumnConstraints();
            column.setPercentWidth(20); // Each column takes 20% width
            grid.getColumnConstraints().add(column);
        }

        // Input field for guessing
        inputField = new TextField();
        inputField.setPrefWidth(100);
        grid.add(inputField, 0, MAX_TRIES + 1, SECRET_WORD.length(), 1);

        // Button to make a guess
        guessButton = new Button("Guess");
        guessButton.setOnAction(event -> checkGuess(grid));
        grid.add(guessButton, 0, MAX_TRIES + 2, SECRET_WORD.length(), 1);

        // Add initial row of squares for showing result
        addRowOfSquares(grid);

        Scene scene = new Scene(grid, 270, 250); // Adjusted scene size to accommodate larger squares and input field
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Method to add a row of squares for showing result
    // Method to add a row of squares for showing result
    private void addRowOfSquares(GridPane grid) {
        for (int i = 0; i < SECRET_WORD.length(); i++) {
            Rectangle square = new Rectangle(40, 40, Color.LIGHTGRAY); // Increased size to 40x40
            square.setStroke(Color.BLACK);
            square.setStrokeWidth(1.5);
            square.setArcWidth(5);
            square.setArcHeight(5);
            grid.add(square, i, currentTry);

            resultTexts[currentTry][i] = new Text("");
            resultTexts[currentTry][i].setFont(Font.font(18));
            grid.add(resultTexts[currentTry][i], i, currentTry);
            GridPane.setHalignment(resultTexts[currentTry][i], javafx.geometry.HPos.CENTER); // Center horizontally
            GridPane.setValignment(resultTexts[currentTry][i], javafx.geometry.VPos.CENTER); // Center vertically

        }
    }


    // Method to check the guess and update the result
    private void checkGuess(GridPane grid) {
        String guess = inputField.getText().toUpperCase();
        if (guess.length() != SECRET_WORD.length()) {
            return;
        }

        for (int i = 0; i < guess.length(); i++) {
            resultTexts[currentTry][i].setText(Character.toString(guess.charAt(i)));
            char guessedChar = guess.charAt(i);
            char secretChar = SECRET_WORD.charAt(i);
            if (guessedChar == secretChar) {
                resultTexts[currentTry][i].setFill(Color.GREEN);
            } else if (SECRET_WORD.contains(Character.toString(guessedChar))) {
                resultTexts[currentTry][i].setFill(Color.ORANGE);
            } else {
                resultTexts[currentTry][i].setFill(Color.RED);
            }
        }

        if (guess.equals(SECRET_WORD)) {
            displayResult("Congratulations! You've guessed the word: " + SECRET_WORD);
            guessButton.setDisable(true);
        } else {
            currentTry++;
            if (currentTry >= MAX_TRIES) {
                displayResult("Sorry, you've run out of tries. The word was: " + SECRET_WORD);
                guessButton.setDisable(true);
            } else {
                addRowOfSquares(grid); // Add a new row of squares
                inputField.clear();
            }
        }
    }

    // Method to display the result of the guess
    private void displayResult(String message) {
        System.out.println(message);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
