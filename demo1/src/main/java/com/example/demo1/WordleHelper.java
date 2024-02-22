package com.example.demo1;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


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
            for(int j = 0; j < SECRET_WORD_SOLUTION.length(); j++){

                if(SECRET_WORD_SOLUTION.charAt(j) == UpperCase.charAt(j)){
                    LetterCounter++;
                }
                if(j == SECRET_WORD_SOLUTION.length() -1  && LetterCounter > 0){
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
                System.out.println("OneLetter:");
                for(int a = 0; a < OneLetterWords.size(); a++){
                    System.out.println(OneLetterWords.get(a));
                }
                System.out.println("TwoLetter:");
                for(int b = 0; b < TwoLetterWords.size(); b++){
                    System.out.println(TwoLetterWords.get(b));
                }
                System.out.println("ThreeLetter:");
                for(int c = 0; c < ThreeLetterWords.size(); c++){
                    System.out.println(ThreeLetterWords.get(c));
                }
                System.out.println("FourLetter:");
                for(int d = 0; d < FourLetterWords.size(); d++){
                    System.out.println(FourLetterWords.get(d));
                }
                Solution.clear();


                //System.out.println(Solution);
            }
        }




    }
}

