package com.sortalgorithmvisualiser;

import com.sortingalgorithms.QuickSort;
import com.sortingalgorithms.Selectionsort;
import com.sortingalgorithms.BubbleSort;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
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
  private final Button pauseButton = new Button();
  private final Button continueButton = new Button();
  private final TextField arraySizeValue = new TextField(); // Label to display array size slider's value
  private final Slider arraySize = new Slider();
  private Text text = new Text(); // used in algorithmSelectDropdownMenu and getAlgorithmDescription, createLeftPane
  private final ArrayList<Integer> values = new ArrayList<>();
  private final ArrayList<Rectangle> rectangles = new ArrayList<>();
  private boolean isSorted = false;
  private int speed;


  HBox createTopPane(){
    HBox topPane = new HBox();

    // Adding dropdown menu
    topPane.getChildren().add(algorithmicSelectDropdownMenu());
    // Adding speed selection dropdown menu
    topPane.getChildren().add(speedSelectDropdownMenu());
    // Adding the sort button
    topPane.getChildren().add(sortButton());

    // Figure out how to have buttons individual methods with returns
    // Sorting pause and continue buttons
    pauseButton();
    continueButton();
    topPane.getChildren().add(pauseButton);
    topPane.getChildren().add(continueButton);

    return topPane;
  }

  //<editor-fold desc="TOP PANE EXTRACTED STUFF">
  ComboBox<String> algorithmicSelectDropdownMenu() {
    ComboBox<String> algorithmSelectComboBox = new ComboBox();

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
    ComboBox<String> speedSelectComboBox = new ComboBox();
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

  void pauseButton() {
    // Setting Button properties
    pauseButton.setText("||");
    pauseButton.setMinWidth(25);

    // Button functionality
    pauseButton.setOnAction(actionEvent -> {
      if(SortingAlgorithmVisualiser.sq.getCurrentTime().compareTo(SortingAlgorithmVisualiser.sq.totalDurationProperty().getValue()) < 0){
        pauseButton.setVisible(false);
        continueButton.setVisible(true);
        SortingAlgorithmVisualiser.sq.pause();
        SortingAlgorithmVisualiser.sq2.pause();
      }
    });
  }

  void continueButton() {
    // Setting Button properties
    continueButton.setText(">");
    continueButton.setMinWidth(25);
    continueButton.setTranslateX(-25);
    continueButton.setVisible(false);

    // Button functionality
    continueButton.setOnAction(actionEvent -> {
      pauseButton.setVisible(true);
      continueButton.setVisible(false);
      SortingAlgorithmVisualiser.sq.play();
      SortingAlgorithmVisualiser.sq2.play();
    });
  }
  //</editor-fold>

  StackPane createCenterPane(){
    StackPane centerPane = new StackPane();

    return centerPane;
  }

  HBox createBottomPane(StackPane centerPane){
    HBox bottomPane = new HBox();

    // If possible should be individual items that are returned
    // Creating slider and text box (editable) representing slider's value
    createArraySizeSlider();
    bottomPane.getChildren().add(arraySize);
    bottomPane.getChildren().add(arraySizeValue);

    // Creating array of values and rectangles
    // Can't be in the createCenterPane() method for now since it needs arraySize created
    centerPane.getChildren().addAll(createRectangles());

    // Creating generate array button (creates array of chosen size and scrambles it)
    bottomPane.getChildren().add(generateArrayButton(centerPane, arraySize));

    return bottomPane;
  }

  //<editor-fold desc="BOTTOM PANE EXTRACTED STUFF">
  void createArraySizeSlider() {
    // creating array size slider and its representation
    arraySize.setMin(10);
    arraySize.setMax(50);
    arraySize.setValue(10);
    arraySizeValue.setPrefColumnCount(1);
    arraySizeValue.setPrefSize(30, 20);
    arraySizeValue.setTranslateX(10);

    // Listener to prevent user from setting values below 10 and above 100 and non-numeric(int) values
    arraySizeValue.textProperty().addListener((observable, oldValue, newValue)->{
      if(isNumber(newValue)) {
        if (Integer.parseInt(newValue) > 10 || Integer.parseInt(newValue) < 100)
          arraySize.setValue(Integer.parseInt(newValue));
      }
      else arraySizeValue.textProperty().setValue(oldValue);
    });

    // Changing values on enter press, the listener didn't let setting values properly as you started inputting it would set the value to 10 since the initial value would be <10
    // If entering <10 set to 10, if entering >50 set to 100 if entering non-number set to 10
    arraySizeValue.setOnKeyPressed(e -> {
      if(e.getCode() == KeyCode.ENTER)
        if(isNumber(arraySizeValue.textProperty().getValue())) {
          if (Integer.parseInt(arraySizeValue.textProperty().getValue()) >= 10 || Integer.parseInt(arraySizeValue.textProperty().getValue()) <= 100)
            arraySize.setValue(Integer.parseInt(arraySizeValue.textProperty().getValue()));
          if(Integer.parseInt(arraySizeValue.textProperty().getValue()) < 10) {
            arraySizeValue.textProperty().setValue("10");
            arraySize.setValue(10);
          }
          if(Integer.parseInt(arraySizeValue.textProperty().getValue()) > 50) {
            arraySizeValue.textProperty().setValue("50");
            arraySize.setValue(100);
          }
        }
        else {
          arraySizeValue.textProperty().setValue("10");
          arraySize.setValue(10);
        }
    });

    // Setting value, so it would be shown as soon as the app is launched
    arraySizeValue.textProperty().setValue(String.valueOf((int)arraySize.getValue()));
    arraySize.setMaxWidth(300);
    arraySize.valueProperty().addListener((observableValue, number, newNumber) -> {
      arraySizeValue.textProperty().setValue(String.valueOf((int)arraySize.getValue()));  // Setting the label to show slider's value, casting as int, so it wouldn't show .0
      arraySize.setValue(Math.round(newNumber.doubleValue()));                            // Making so the slider would snap to integer and wouldn't show floats
    });
    arraySize.setMajorTickUnit(10);
    arraySize.setMinorTickCount(1);
    arraySize.setShowTickLabels(true);
    arraySize.setShowTickMarks(true);
  }

  boolean isNumber(String str){
    if(str == null) return false;
    try{
      Integer.parseInt(str);
    } catch (NumberFormatException e) {
      return false;
    }
    return true;
  }

  Button generateArrayButton(StackPane centerPane, Slider arraySize) {
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

  void shuffleValuesArray(ArrayList<Integer> values){
    // Clearing values and regenerating, should not be cleared, only shuffled in the future
    values.clear();
    for(int i = 1; i < (int)arraySize.getValue()+1; i++)
      values.add(i*(100/(int)arraySize.getValue()+1));

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
    StringBuilder info = new StringBuilder();

    try {
      File file = new File(name+".txt");
      Scanner reader = new Scanner(file);
      while(reader.hasNextLine()){
        info.append(reader.nextLine()).append("\n");
      }
      reader.close();
    } catch (FileNotFoundException e) {
      System.out.println(e.toString());
      e.printStackTrace();
    }

    return info.toString();
  }

  //</editor-fold>
}
