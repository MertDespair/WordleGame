package com.Wordle.game;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.control.ListView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javafx.scene.layout.Region;


public class WordleHelper extends WordleGame {

    private Button generate;

    protected static String[] helperWords;



    List<String> OneLetterWords = new ArrayList<String>();
    List<String> TwoLetterWords = new ArrayList<String>();
    List<String> ThreeLetterWords = new ArrayList<String>();
    List<String> FourLetterWords = new ArrayList<String>();


    private static HashMap<String, Integer> Solution = new HashMap<String, Integer>();

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
            int LetterCounter = 0;
            String UpperCase = words[i].toUpperCase();
            int Guessed_Letters_temp = Guessed_Letters;
            for(int j = 0; j < SECRET_WORD_SOLUTION.length(); j++){

                for(int k = 0; k < UpperCase.length(); k++){

                        if (SECRET_WORD_SOLUTION.charAt(j) == UpperCase.charAt(k)) {

                            if(Guessed_Letters_temp > 0){
                                LetterCounter++;
                            }
                            Guessed_Letters_temp--;
                            if(Guessed_Letters_temp == 0){
                                break;
                            }
                        }
                }
                if(j == SECRET_WORD_SOLUTION.length() - 1  && LetterCounter > 0){
                    Solution.put(UpperCase,LetterCounter);
                }
            }

            if(i == words.length - 1){
                for (String key : Solution.keySet()) {
                    switch(Solution.get(key)) {
                        case 1:
                            OneLetterWords.add(key);
                            break;
                        case 2:
                            TwoLetterWords.add(key);
                            break;
                        case 3:
                            ThreeLetterWords.add(key);
                            break;
                        case 4:
                            FourLetterWords.add(key);
                            break;
                        default:
                            break;
                    }
                }
                Solution.clear();
            }
        }

        ShowWordList();

    }

    public void ShowWordList(){
        Stage wordListStage = new Stage();
        wordListStage.setTitle("Word Lists");

        VBox wordListLayout = new VBox(10);

        //listviews of the corresponding arrays
        ListView<String> fourLetterListView = new ListView<>();
        fourLetterListView.getItems().addAll(FourLetterWords);
        ListView<String> threeLetterListView = new ListView<>();
        threeLetterListView.getItems().addAll(ThreeLetterWords);
        ListView<String> twoLetterListView = new ListView<>();
        twoLetterListView.getItems().addAll(TwoLetterWords);
        ListView<String> oneLetterListView = new ListView<>();
        oneLetterListView.getItems().addAll(OneLetterWords);


        //Headlines between the boxes
        Label fourLetterLabel = new Label("Four Letter Words:");
        Label threeLetterLabel = new Label("Three Letter Words:");
        Label twoLetterLabel = new Label("Two Letter Words:");
        Label oneLetterLabel = new Label("One Letter Words:");

        //padding at bottom for style
        Region bottomPadding = new Region();
        bottomPadding.setPrefHeight(20); // Adjust the height of the padding as needed

        wordListLayout.getChildren().addAll(fourLetterLabel, fourLetterListView, threeLetterLabel, threeLetterListView, twoLetterLabel, twoLetterListView, oneLetterLabel, oneLetterListView, bottomPadding);

        Scene wordListScene = new Scene(wordListLayout, 400, 600);
        wordListStage.setScene(wordListScene);

        wordListStage.show();

    }

}

