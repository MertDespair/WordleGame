package com.example.demo1;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;


public class WordleHelper extends WordleGame {

    private Button generate;

    protected static String[] helperWords;

    public void WordleHelperShowWindow(){
        Stage helpStage = new Stage();
        helpStage.setTitle("Help");

        Label helpLabel = new Label("Try reading a dictionary!\n or visit www.word.tips/wordle");

        // Create the Generate button
        generate = new Button("Generate");
        // Set an action for the generate button to open another window
        generate.setOnAction(e -> findWords());

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
            String help = "";
            List<String> solutionWords = new ArrayList<String>();
            String UpperCase = words[i].toUpperCase();
            for(int j = 0; j < SECRET_WORD_SOLUTION.length(); j++){


                if(SECRET_WORD_SOLUTION.charAt(j) == UpperCase.charAt(j)){

                    help += UpperCase.charAt(j);
                    solutionWords.add(UpperCase);
                }
                if(j == SECRET_WORD_SOLUTION.length()){
                    System.out.println(help);
                    solutionWords.add(UpperCase);
                }
            }
        }


    }
}

