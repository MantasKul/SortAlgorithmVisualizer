package com.sortalgorithmvisualiser;

import javafx.animation.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class SortingAlgorithmVisualiser extends Application {

    static public SequentialTransition sq = new SequentialTransition();
    static public SequentialTransition sq2 = new SequentialTransition();

    private UIBuilder uiBuilder = new UIBuilder();

    @Override
    public void start(Stage stage){
        // Creating panes, border pane has top/center, inside top part is the button pane, in center rectangles, both ar stack panes
        BorderPane root = new BorderPane();
        HBox topPane = uiBuilder.createTopPane();
        StackPane centerPane = uiBuilder.createCenterPane();
        HBox bottomPane = uiBuilder.createBottomPane(centerPane);
        StackPane leftPane = uiBuilder.createLeftPane();
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
}