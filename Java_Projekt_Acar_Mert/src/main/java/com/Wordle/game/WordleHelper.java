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



//Helper Class, that does everything to set up the help section of the game
public class WordleHelper extends WordleGame {

    private Button generate;


    //Arrays that store the calculated words depending on the amount of letters
    //that are included in the corresponding words
    private List<String> OneLetterWords = new ArrayList<String>();
    private List<String> TwoLetterWords = new ArrayList<String>();
    private List<String> ThreeLetterWords = new ArrayList<String>();
    private List<String> FourLetterWords = new ArrayList<String>();

    //hashmap that corresponds the words with the amount of correct letters that are included
    // eg. user input Hello (e and o is correct)
    // Ultra : 1
    // Elif : 2
    private static HashMap<String, Integer> Solution = new HashMap<String, Integer>();


    //Function that sets up the help Window for general navigation with help
    public void WordleHelperShowWindow(){

        Stage helpStage = new Stage();
        helpStage.setTitle("Help");

        Label helpLabel = new Label("Try reading a dictionary!");

        generate = new Button("Generate");
        generate.setOnAction(e -> findWords());

        VBox helpLayout = new VBox(10);
        helpLayout.getChildren().addAll(helpLabel, generate);

        Scene helpScene = new Scene(helpLayout, 300, 200);
        helpStage.setScene(helpScene);
        helpStage.show();
    }

    public void findWords(){

        for(int i = 0; i < words.length; i++){
            int LetterCounter = 0;
            String UpperCase = words[i].toUpperCase();
            //temporary variable that is set up for this function, so that every word can be checked against
            //the amount of guessed letters. Guessed_Letters stems from the WordleGame class
            int Guessed_Letters_temp = guessedLetters;
            for(int j = 0; j < secretWordSolution.length(); j++){

                for(int k = 0; k < UpperCase.length(); k++){

                        if (secretWordSolution.charAt(j) == UpperCase.charAt(k)) {

                            if(Guessed_Letters_temp > 0){
                                LetterCounter++;
                            }
                            Guessed_Letters_temp--;
                            if(Guessed_Letters_temp == 0){
                                break;
                            }
                        }
                }
                if(j == secretWordSolution.length() - 1  && LetterCounter > 0){
                    Solution.put(UpperCase,LetterCounter);
                }
            }

            //Switch case to add the corresponding words to the sorted arrays
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
                //necessary as to not add double entries
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
        bottomPadding.setPrefHeight(20); // Adjustment of the height of the padding

        wordListLayout.getChildren().addAll(fourLetterLabel, fourLetterListView, threeLetterLabel, threeLetterListView, twoLetterLabel, twoLetterListView, oneLetterLabel, oneLetterListView, bottomPadding);

        Scene wordListScene = new Scene(wordListLayout, 400, 600);
        wordListStage.setScene(wordListScene);

        wordListStage.show();

    }
}

