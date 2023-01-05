package com.sortalgorithmvisualiser;

import com.sortingalgorithms.QuickSort;
import com.sortingalgorithms.Selectionsort;
import com.sortingalgorithms.bubbleSort;
//import com.sortingalgorithms.Mergesort;

import javafx.animation.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.shape.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class SortingAlgorithmVisualiser extends Application {

    private ArrayList<Integer> values = new ArrayList<>();
    private ArrayList<Rectangle> rect = new ArrayList<>();
    private SequentialTransition sq = new SequentialTransition();
    private SequentialTransition sq2 = new SequentialTransition();
    private ComboBox algorithmSelect = new ComboBox();
    private Text text = new Text();
    boolean isSorted = false;
    int s;

    @Override
    public void start(Stage stage){
        // Creating panes, border pane has top/center, inside top part is the button pane, in center rectangles, both ar stack panes
        BorderPane root = new BorderPane();
        HBox topPane = createTopPane();
        StackPane centerPane = createCenterPane();
        HBox bottomPane = createBottomPane(centerPane);
        StackPane leftPane = createLeftPane();
        root.setTop(topPane);
        root.setCenter(centerPane);
        root.setBottom(bottomPane);
        root.setLeft(leftPane);

        Scene scene = new Scene(root, 600,300);
        stage.setMinHeight(400);
        stage.setMinWidth(1300);

        // Setting and enabling scene
        stage.setTitle("Sort algorithm Visualiser");
        stage.setScene(scene);
        stage.show();
    }

    static void main(String[] args) {
        launch();
    }

    void shuffleValuesArray(ArrayList<Integer> values){
        Random rand = new Random();
        for(int i = 0; i < values.size(); i++){
            int randToSwap = rand.nextInt(values.size());
            int temp = values.get(randToSwap);
            values.set(randToSwap, values.get(i));
            values.set(i, temp);
        }
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

    HBox createTopPane(){
        HBox topPane = new HBox();

        // Dropdown menu
        algorithmSelect.getItems().addAll(
                "Bubble Sort",
                "Quicksort",
                //"Merge sort",
                "Selection sort"
        );
        algorithmSelect.getSelectionModel().selectFirst();
        // listener to change the text on the left pane to describe chosen algorithm
        algorithmSelect.valueProperty().addListener((ob, ov, nv)->{
            switch (nv.toString()){
                case "Bubble Sort":
                    text.setText(getAlgorithmDescription("BubbleSort"));
                    break;
                case "Quicksort":
                    text.setText(getAlgorithmDescription("Quicksort"));
                    break;
//                case "Merge sort":
//                    text.setText(getAlgorithmDescription("MergeSort"));
//                    break;
                case "Selection sort":
                    text.setText(getAlgorithmDescription("SelectionSort"));
                    break;
                default:
                    System.out.println("Error in switch case");
                    break;
            }
        });
        topPane.getChildren().add(algorithmSelect);

        ComboBox speed = new ComboBox();
        speed.getItems().addAll(
                "Fast",
                "Medium",
                "Slow"
        );
        speed.getSelectionModel().selectFirst();
        topPane.getChildren().add(speed);


        // Creating the sort button
        Button sortButton = new Button("Sort!");
        //sortButton.translateXProperty().set(150);
        topPane.getChildren().add(sortButton);

        // Sort Button functionality
        s = 1000;
        sortButton.setOnAction(actionEvent -> {
            if(!isSorted) {
                sq.getChildren().clear();
                sq2.getChildren().clear();
                switch (speed.getValue().toString()) {
                    case "Fast":
                        s = 100;
                        break;
                    case "Medium":
                        s = 1500;
                        break;
                    case "Slow":
                        s = 4000;
                        break;
                    default:
                        s = 1000;
                        break;
                }
                String v = algorithmSelect.getValue().toString();
                switch (v) {
                    case "Bubble Sort":

                        new bubbleSort().bubbleSort(values, rect, sq, sq2, s);
                        sq.play();
                        sq2.play();
                        isSorted = true;
                        break;
                    case "Quicksort":
                        new QuickSort().quickSort(values, rect, 0, values.size() - 1, sq, sq2, s);
                        sq.play();
                        sq2.play();
                        isSorted = true;
                        break;
                    // Using current rectangles to swap isn't the best way to represent merge sort, the sort algorithm is working but no animation has been implemented
//                case "Merge sort":
//                    new Mergesort().mergeSort(values, rect, 0, values.size()-1);
//                    break;
                    case "Selection sort":
                        new Selectionsort().selectionSort(values, rect, sq, sq2, s);
                        sq.play();
                        sq2.play();
                        isSorted = true;
                        break;
                    default:
                        System.out.println("Something went wrong in the switch case of sort Button");
                        break;
                }
            }

        });

        // Sorting pause and continue buttons
        Button pause = new Button("||");
        Button cont = new Button(">");
        pause.setMinWidth(25);
        cont.setMinWidth(25);
        cont.setTranslateX(-25);
        cont.setVisible(false);
        topPane.getChildren().add(pause);
        pause.setOnAction(actionEvent -> {
            if(sq.getCurrentTime().compareTo(sq.totalDurationProperty().getValue()) == -1){
                pause.setVisible(false);
                cont.setVisible(true);
                sq.pause();
                sq2.pause();
            }
        });
        topPane.getChildren().add(cont);
        cont.setOnAction(actionEvent -> {
            pause.setVisible(true);
            cont.setVisible(false);
            sq.play();
            sq2.play();
        });

        return topPane;
    }

    StackPane createCenterPane(){
        StackPane centerPane = new StackPane();
        return centerPane;
    }

    HBox createBottomPane(StackPane centerPane){
        HBox bottomPane = new HBox();

        // creating array size slider and its representation
        TextField arraySizeValue = new TextField(); // Label to display array size slider's value
        Slider arraySize = new Slider(10, 50, 10); // min value, max value, default value
        //arraySizeValue.setTranslateX(-150);
        arraySizeValue.setPrefColumnCount(1);
        arraySizeValue.setPrefSize(30, 20);
        arraySizeValue.setTranslateX(10);
        // Listener to prevent user from setting values below 10 and above 100 and non-numeric(int) values
//        arraySizeValue.textProperty().addListener((observable, oldValue, newValue)->{
//            if(isNumber(newValue)) {
//                if (Integer.parseInt(newValue) > 10 || Integer.parseInt(newValue) < 100)
//                    arraySize.setValue(Integer.parseInt(newValue));
//            }
//            else arraySizeValue.textProperty().setValue(oldValue);
//        });
        // Changing values on enter press, the listener didn't let setting values properly as you started inputting it would set the value to 10 since the initial value would be <10
        // If entering <10 set to 10, if entering >100 set to 100 if entering non-number set to 10
        arraySizeValue.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.ENTER)
                if(isNumber(arraySizeValue.textProperty().getValue())) {
                    if (Integer.parseInt(arraySizeValue.textProperty().getValue()) >= 10 || Integer.parseInt(arraySizeValue.textProperty().getValue()) <= 100)
                        arraySize.setValue(Integer.parseInt(arraySizeValue.textProperty().getValue()));
                    if(Integer.parseInt(arraySizeValue.textProperty().getValue()) < 10) {
                        arraySizeValue.textProperty().setValue("10");
                        arraySize.setValue(10);
                    }
                    if(Integer.parseInt(arraySizeValue.textProperty().getValue()) > 100) {
                        arraySizeValue.textProperty().setValue("100");
                        arraySize.setValue(100);
                    }
                }
                else {
                    arraySizeValue.textProperty().setValue("10");
                    arraySize.setValue(10);
                }
        });

        arraySizeValue.textProperty().setValue(String.valueOf((int)arraySize.getValue())); // Setting value, so it would be shown as soon as the app is launched
        //arraySize.setTranslateY(-10);
        arraySize.setMaxWidth(300);
        arraySize.valueProperty().addListener((observableValue, number, newNumber) -> {
            arraySizeValue.textProperty().setValue(String.valueOf((int)arraySize.getValue()));  // Setting the label to show slider's value, casting as int, so it wouldn't show .0
            arraySize.setValue(Math.round(newNumber.doubleValue()));                            // Making so the slider would snap to integer and wouldn't show floats
        });
        arraySize.setMajorTickUnit(10);
        arraySize.setMinorTickCount(1);
        arraySize.setShowTickLabels(true);
        arraySize.setShowTickMarks(true);
        bottomPane.getChildren().add(arraySize);
        bottomPane.getChildren().add(arraySizeValue);

        // Creating array of values and rectangles
        for(int i = 1; i < (int)arraySize.getValue()+1; i++) values.add(i*(100/(int)arraySize.getValue()+1));
        shuffleValuesArray(values); // Randomizes array values
        createRectangles(centerPane);

        // Creating generate array button (creates array of chosen size and scrambles it)
        Button generateArray = new Button("Generate");
        generateArray.setTranslateX(20);
        bottomPane.getChildren().add(generateArray);

        // Generate array Button functionality
        generateArray.setOnAction(actionEvent -> {
            isSorted = false;
            // Stopping and clearing animations so when you generate the array while animation is playing user would be able to play animations otherwise you wouldn't be able to play them
            sq.stop();
            sq2.stop();
            sq.getChildren().clear();
            sq2.getChildren().clear();

            // Clearing center pane of rectangles, values array, and array of rectangles
            centerPane.getChildren().clear();
            values.clear();
            rect.clear();
            for(int i = 1; i < (int)arraySize.getValue()+1; i++) values.add(i*(100/(int)arraySize.getValue()+1));
            shuffleValuesArray(values);                                                                 // Scrambling the array of values
            createRectangles(centerPane);
        });

        return bottomPane;
    }

    StackPane createLeftPane(){
        StackPane leftPane = new StackPane();
        leftPane.setPrefWidth(300);
        leftPane.setMaxWidth(300);
        leftPane.setMinWidth(300);

        text.setText(getAlgorithmDescription("BubbleSort"));
        leftPane.getChildren().add(text);

        return leftPane;
    }

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

    void createRectangles(StackPane centerPane){
        for(int i = 0; i < values.size(); i++){                                                 // creating new rectangles
            rect.add(new Rectangle());//rect[i] = new Rectangle();
            rect.get(i).setTranslateY((values.get(i)/2)-values.get(i)+150);
            rect.get(i).setWidth(15);
            rect.get(i).setTranslateX(i*15-(values.size()-values.size()/2)*15);
            rect.get(i).setHeight(values.get(i));
            centerPane.getChildren().add(rect.get(i));
        }
    }
}