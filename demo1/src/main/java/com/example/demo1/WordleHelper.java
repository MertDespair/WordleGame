package com.example.demo1;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class WordleHelper extends WordleGame {


    public WordleHelper(){
        System.out.println("Helper");
        Stage helpStage = new Stage();
        helpStage.setTitle("Help");

        Label helpLabel = new Label("Try reading a dictionary!\n or visit www.word.tips/wordle");
        VBox helpLayout = new VBox(10);
        helpLayout.getChildren().add(helpLabel);

        Scene helpScene = new Scene(helpLayout, 300, 200);
        helpStage.setScene(helpScene);
        helpStage.show();
    }

}
