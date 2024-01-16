package com.sortalgorithmvisualiser;

import com.errorhandling.ErrorHandling;
import com.sortingalgorithms.QuickSort;
import com.sortingalgorithms.Selectionsort;
import com.sortingalgorithms.BubbleSort;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class UIBuilder {
  private String selectedAlgorithm = "Bubble Sort";
  private int selectedSpeed = 100;
  private boolean isPaused = false;
  private int arraySize = 10;
  private Text text = new Text(); // used in algorithmSelectDropdownMenu and getAlgorithmDescription, createLeftPane
  private final ArrayList<Integer> values = new ArrayList<>();
  private final ArrayList<Rectangle> rectangles = new ArrayList<>();
  private boolean isSorted = false;


  HBox createTopPane(){
    HBox topPane = new HBox();

    // Adding dropdown menu
    topPane.getChildren().add(algorithmicSelectDropdownMenu());
    // Adding speed selection dropdown menu
    topPane.getChildren().add(speedSelectDropdownMenu());
    // Adding the sort button
    topPane.getChildren().add(sortButton());
    // Adding pause/play button. Could also be two buttons next to each other instead of one button with changing text
    topPane.getChildren().add(pausePlayButton());

    return topPane;
  }

  //<editor-fold desc="TOP PANE EXTRACTED STUFF">
  ComboBox<String> algorithmicSelectDropdownMenu() {
    ComboBox<String> algorithmSelectComboBox = new ComboBox<>();

    algorithmSelectComboBox.getItems().addAll(
            "Bubble Sort",
            "Quicksort",
            "Selection sort"
    );

    // Ensuring the combo box isn't empty when app is launched
    algorithmSelectComboBox.getSelectionModel().selectFirst();

    // listener to change the text on the left pane to describe chosen algorithm
    algorithmSelectComboBox.valueProperty().addListener((ob, ov, nv)->{
      switch (nv) {
        case "Bubble Sort" -> {
          selectedAlgorithm = "Bubble Sort";
          text.setText(readAlgorithmDescriptionFile("BubbleSort"));
        }
        case "Quicksort" -> {
          selectedAlgorithm = "Quicksort";
          text.setText(readAlgorithmDescriptionFile("Quicksort"));
        }
        case "Selection sort" -> {
          selectedAlgorithm = "Selection Sort";
          text.setText(readAlgorithmDescriptionFile("SelectionSort"));
        }
        default -> System.out.println("Error in switch case");
      }
    });

    return algorithmSelectComboBox;
  }

  ComboBox<String> speedSelectDropdownMenu() {
    ComboBox<String> speedSelectComboBox = new ComboBox<>();
    speedSelectComboBox.getItems().addAll(
            "Fast",
            "Medium",
            "Slow"
    );
    speedSelectComboBox.getSelectionModel().selectFirst();

    speedSelectComboBox.valueProperty().addListener((ob, ov, nv) ->{
      switch(nv) {
        case "Medium" -> selectedSpeed = 1500;
        case "Slow" -> selectedSpeed = 4000;
        default -> selectedSpeed = 100;           // Default speed is "Fast"
      }
    });

    return speedSelectComboBox;
  }

  Button sortButton() {
    Button sortButton = new Button("Sort!");

    sortButton.setOnAction(actionEvent -> {
      if(!isSorted) {
        SortingAlgorithmVisualiser.sq.getChildren().clear();
        SortingAlgorithmVisualiser.sq2.getChildren().clear();

        switch (selectedAlgorithm) {
          case "Bubble Sort" -> {
            new BubbleSort().sort(this.values, this.rectangles, selectedSpeed);
            SortingAlgorithmVisualiser.sq.play();
            SortingAlgorithmVisualiser.sq2.play();
            isSorted = true;
          }
          case "Quicksort" -> {
            new QuickSort().sort(this.values, this.rectangles, 0, this.values.size() - 1, selectedSpeed);
            SortingAlgorithmVisualiser.sq.play();
            SortingAlgorithmVisualiser.sq2.play();
            isSorted = true;
          }
          case "Selection Sort" -> {
            new Selectionsort().sort(this.values, this.rectangles, selectedSpeed);
            SortingAlgorithmVisualiser.sq.play();
            SortingAlgorithmVisualiser.sq2.play();
            isSorted = true;
          }
          default -> System.out.println("Something went wrong in the switch case of sort Button");
        }
      }

    });

    return sortButton;
  }

  // Could also be two buttons next to each other instead of one button with changing text
  Button pausePlayButton() {
    Button pausePlayButton = new Button();
    pausePlayButton.setText("||");
    pausePlayButton.setMinWidth(25);

    pausePlayButton.setOnAction(actionEvent -> {
      boolean paused = isPaused;

      if(!paused) {
        pausePlayButton.setText(">");
        isPaused = !isPaused;
        SortingAlgorithmVisualiser.sq.pause();
        SortingAlgorithmVisualiser.sq2.pause();
      }
      if(paused) {
        pausePlayButton.setText("||");
        isPaused = !isPaused;
        SortingAlgorithmVisualiser.sq.play();
        SortingAlgorithmVisualiser.sq2.play();
      }
    });

    return pausePlayButton;
  }
  //</editor-fold>

  StackPane createCenterPane(){
    StackPane centerPane = new StackPane();

    // Adding rectangles to the pane
    centerPane.getChildren().addAll(createRectangles());

    return centerPane;
  }

  //<editor-fold desc="CENTER PANE individual component methods">
  ArrayList<Rectangle> createRectangles(){
    //ArrayList<Rectangle> rectangles = new ArrayList<>();

    shuffleValuesArray(values);
    rectangles.clear();
    for(int i = 0; i < values.size(); i++){
      rectangles.add(new Rectangle());
      rectangles.get(i).setTranslateY((values.get(i)/2)-values.get(i)+150);
      rectangles.get(i).setWidth(13);
      rectangles.get(i).setStrokeWidth(2);
      rectangles.get(i).setStroke(Color.BLACK);
      rectangles.get(i).setTranslateX(i*15-(values.size()-values.size()/2)*15);
      rectangles.get(i).setHeight(values.get(i));
    }

    return rectangles;
  }
  //</editor-fold>

  HBox createBottomPane(StackPane centerPane){
    HBox bottomPane = new HBox();

    // Adding slider of rectangle array size
    bottomPane.getChildren().add(arraySizeSlider());
    //bottomPane.getChildren().add(arraySizeTextField());
    // Adding generate array button (creates array of chosen size and scrambles it)
    bottomPane.getChildren().add(generateArrayButton(centerPane));

    return bottomPane;
  }

  //<editor-fold desc="BOTTOM PANE individual component methods">

  Slider arraySizeSlider() {
    Slider arraySizeSlider = new Slider();
    // Setting properties
    arraySizeSlider.setMin(10);
    arraySizeSlider.setMax(50);
    arraySizeSlider.setValue(10);
    arraySizeSlider.setMajorTickUnit(10);
    arraySizeSlider.setMinorTickCount(1);
    arraySizeSlider.setShowTickLabels(true);
    arraySizeSlider.setShowTickMarks(true);


    arraySizeSlider.setMaxWidth(300);
    arraySizeSlider.valueProperty().addListener((observableValue, number, newNumber) -> {
      //arraySizeValue.textProperty().setValue(String.valueOf((int)arraySizeSlider.getValue()));  // Setting the label to show slider's value, casting as int, so it wouldn't show .0
      arraySizeSlider.setValue(Math.round(newNumber.doubleValue()));                            // Making so the slider would snap to integer and wouldn't show floats
      arraySize = (int) arraySizeSlider.getValue();
    });

    return arraySizeSlider;
  }

  Button generateArrayButton(StackPane centerPane) {
    Button generateArrayButton = new Button("Generate");
    generateArrayButton.setTranslateX(20);

    // Generate array Button functionality
    generateArrayButton.setOnAction(actionEvent -> {
      isSorted = false;
      // Stopping and clearing animations so when you generate the array while animation is playing user would be able to play animations otherwise you wouldn't be able to play them
      SortingAlgorithmVisualiser.sq.stop();
      SortingAlgorithmVisualiser.sq2.stop();
      SortingAlgorithmVisualiser.sq.getChildren().clear();
      SortingAlgorithmVisualiser.sq2.getChildren().clear();

      // Clearing center pane of rectangles, values array, and array of rectangles
      centerPane.getChildren().clear();
      centerPane.getChildren().addAll(createRectangles());
    });

    return generateArrayButton;
  }


//  TextField arraySizeTextField() {
//    TextField arraySizeTextField = new TextField();
//    // Setting properties
//    arraySizeTextField.setPrefColumnCount(1);
//    arraySizeTextField.setPrefSize(30, 20);
//    arraySizeTextField.setTranslateX(10);
//    // Setting value, so it would be shown as soon as the app is launched
//    arraySizeTextField.textProperty().setValue(String.valueOf((int)arraySize.getValue()));
//
//    // Changing values on enter press, the listener didn't let setting values properly as you started inputting it would set the value to 10 since the initial value would be <10
//    // If entering <10 set to 10, if entering >50 set to 100 if entering non-number set to 10
//    arraySizeTextField.setOnKeyPressed(e -> {
//      if(e.getCode() == KeyCode.ENTER)
//        if(isNumber(arraySizeTextField.textProperty().getValue())) {
//          if (Integer.parseInt(arraySizeTextField.textProperty().getValue()) >= 10 || Integer.parseInt(arraySizeTextField.textProperty().getValue()) <= 100)
//            arraySize.setValue(Integer.parseInt(arraySizeTextField.textProperty().getValue()));
//          if(Integer.parseInt(arraySizeTextField.textProperty().getValue()) < 10) {
//            arraySizeTextField.textProperty().setValue("10");
//            //arraySize.setValue(10);
//            arraySizeInt = 10;
//          }
//          if(Integer.parseInt(arraySizeTextField.textProperty().getValue()) > 50) {
//            arraySizeValue.textProperty().setValue("50");
//            //arraySize.setValue(100);
//            arraySizeInt = 100;
//          }
//        }
//        else {
//          arraySizeTextField.textProperty().setValue("10");
//          //arraySize.setValue(10);
//          arraySizeInt = 10;
//        }
//    });
//
//    return arraySizeTextField;
//  }

  // ATM only used in array size text field to check if entered value is number, array size text field is currently disabled
//  boolean isNumber(String str){
//    if(str == null) return false;
//    try{
//      Integer.parseInt(str);
//    } catch (NumberFormatException e) {
//      return false;
//    }
//    return true;
//  }

  void shuffleValuesArray(ArrayList<Integer> values){
    // Clearing values and regenerating, should not be cleared, only shuffled in the future
    values.clear();
//    for(int i = 1; i < (int)arraySize.getValue()+1; i++)
//      values.add(i*(100/(int)arraySize.getValue()+1));
    for(int i = 1; i < arraySize +1; i++)
      values.add(i*(100/ arraySize +1));

    Random rand = new Random();
    for(int i = 0; i < values.size(); i++){
      int randToSwap = rand.nextInt(values.size());
      int temp = values.get(randToSwap);
      values.set(randToSwap, values.get(i));
      values.set(i, temp);
    }
  }

  //</editor-fold>

  StackPane createLeftPane(){
    StackPane leftPane = new StackPane();
    leftPane.setPrefWidth(300);
    leftPane.setMaxWidth(300);
    leftPane.setMinWidth(300);

    // Set text var and add it to pane
    text = algorithmDescriptionText();
    leftPane.getChildren().add(text);

    return leftPane;
  }

  //<editor-fold desc="LEFT PANE EXTRACTED STUFF">
  // Creating text object, setting the text to bubble sort desc by default
  Text algorithmDescriptionText() {
    Text text = new Text();

    text.setWrappingWidth(300);
    text.setText(readAlgorithmDescriptionFile("BubbleSort"));

    return text;
  }

  // Reading file with "name" name and returning its content as string
  public String readAlgorithmDescriptionFile(String name){
    StringBuilder algorithmDescription = new StringBuilder();

    try {
      File file = new File(name+"a.txt");
      Scanner reader = new Scanner(file);
      while(reader.hasNextLine()){
        algorithmDescription.append(reader.nextLine()).append("\n");
      }
      reader.close();
    } catch (FileNotFoundException e) {
      ErrorHandling.errorAlert(e.toString());
    }

    return algorithmDescription.toString();
  }

  //</editor-fold>
}
