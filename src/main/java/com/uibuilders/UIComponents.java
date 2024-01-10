package com.uibuilders;

import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class UIComponents {
  private Text text = new Text();

  StackPane createCenterPane(){
    StackPane centerPane = new StackPane();
    return centerPane;
  }

  // Create the left pane consisting of algorithm description text
  StackPane createLeftPane(){
    StackPane leftPane = new StackPane();
    leftPane.setPrefWidth(300);
    leftPane.setMaxWidth(300);
    leftPane.setMinWidth(300);

    text.setText(getAlgorithmDescription("BubbleSort"));
    text.setWrappingWidth(300);
    leftPane.getChildren().add(text);

    return leftPane;
  }

  // Read file contents with "name" name and return it as string
  String getAlgorithmDescription(String name){
    String info = "";
    try {
      File file = new File(name+".txt");
      Scanner reader = new Scanner(file);
      while(reader.hasNextLine()){
        info += reader.nextLine() + "\n";
      }
      reader.close();
    } catch (FileNotFoundException e) {
      System.out.println(e.toString());
      e.printStackTrace();
    }

    return info;
  }
}
