package com.example.demo1;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class WordleHelper extends WordleGame {

    private Button generate;

    protected static String[] helperWords;

    public WordleHelper(){
        Stage helpStage = new Stage();
        helpStage.setTitle("Help");

        Label helpLabel = new Label("Try reading a dictionary!\n or visit www.word.tips/wordle");

        // Create the Generate button
        generate = new Button("Generate");
        // Set an action for the generate button to open another window
        generate.setOnAction(e -> openNewWindow());

        // Create a VBox layout to hold the label and button
        VBox helpLayout = new VBox(10);
        // Add the label and generate button to the VBox
        helpLayout.getChildren().addAll(helpLabel, generate);

        // Create the scene and set it to the help stage
        Scene helpScene = new Scene(helpLayout, 300, 200);
        helpStage.setScene(helpScene);
        // Show the help stage
        helpStage.show();
    }

    public void findWords(){

        VBox generateLayout = new VBox(10);

        for(int i = 0; i < words.length; i++){

            for(int j = 0; j < SECRET_WORD_SOLUTION.length(); j++){

                if(SECRET_WORD_SOLUTION.charAt(j) == words[i].charAt(j)){

                    // Add the word to the layout
                    Label wordLabel = new Label(words[i]);
                    generateLayout.getChildren().add(wordLabel);
                    break; // Exit the inner loop once the word is added

                }
            }
        }
    }

    private void openNewWindow() {
        // Create a new stage for the generated window
        Stage generateStage = new Stage();
        generateStage.setTitle("Generated Window");

        // Create a label for the generated window
        Label generateLabel = new Label("This is a generated window!");

        // Create a VBox layout for the generated window
        VBox generateLayout = new VBox(10);
        generateLayout.getChildren().add(generateLabel);

        // Create a scene for the generated window
        Scene generateScene = new Scene(generateLayout, 300, 200);
        generateStage.setScene(generateScene);
        // Show the generated window
        generateStage.show();
    }
}
