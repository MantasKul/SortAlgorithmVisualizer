package com.sortingalgorithms;

import com.sortalgorithmvisualiser.AnimationSetup;
import com.sortalgorithmvisualiser.SortingAlgorithmVisualiser;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class bubbleSort {
    public void sort(ArrayList<Integer> values, ArrayList<Rectangle> rect, int speed){
        for(int i = 0; i < values.size(); i++) {
            for (int j = 0; j < values.size() - 1; j++) {
                // Highlighting rectangle that are being currently compared blue
                SortingAlgorithmVisualiser.sq.getChildren().add(new AnimationSetup().changeColor(rect.get(j), speed, Color.BLUE));
                SortingAlgorithmVisualiser.sq2.getChildren().add(new AnimationSetup().changeColor(rect.get(j+1), speed,  Color.BLUE));
                if (values.get(j) > values.get(j + 1)) {
                    // Swapping values in the array
                    int temp = values.get(j);
                    values.set(j, values.get(j+1));
                    values.set(j+1, temp);

                    // Animation setup
                    animationSetup(rect, j, speed, values);

                    // Swapping rectangle objects
                    Rectangle tempRect = rect.get(j);
                    rect.set(j, rect.get(j+1));
                    rect.set(j+1, tempRect);
                }
                else {
                    // If the rectangles aren't going to be switched their colors are changed to red
                    SortingAlgorithmVisualiser.sq.getChildren().add(new AnimationSetup().changeColor(rect.get(j), speed, Color.RED));
                    SortingAlgorithmVisualiser.sq2.getChildren().add(new AnimationSetup().changeColor(rect.get(j+1), speed,  Color.RED));
                }
                // Changes rectangles color back to black
                SortingAlgorithmVisualiser.sq.getChildren().add(new AnimationSetup().changeColor(rect.get(j), speed, Color.BLACK));
                SortingAlgorithmVisualiser.sq2.getChildren().add(new AnimationSetup().changeColor(rect.get(j+1), speed,  Color.BLACK));
            }
        }
    }

    public void animationSetup(ArrayList<Rectangle> rect, int j, int s, ArrayList<Integer> values){
        // Setting up animations
        // The rectangles are going to be switched their colors are changed to green
        SortingAlgorithmVisualiser.sq.getChildren().add(new AnimationSetup().changeColor(rect.get(j), s, Color.GREEN));
        SortingAlgorithmVisualiser.sq2.getChildren().add(new AnimationSetup().changeColor(rect.get(j+1), s, Color.GREEN));
        // Adding movement animations to sequence
        SortingAlgorithmVisualiser.sq.getChildren().add(new AnimationSetup().animationSetup(rect.get(j), (j+1)*15-(values.size()-values.size()/2)*15, s));
        SortingAlgorithmVisualiser.sq2.getChildren().add(new AnimationSetup().animationSetup(rect.get(j+1), j*15-(values.size()-values.size()/2)*15, s));
    }

//    public void changeColor(ArrayList<Rectangle> rect, int j, int s, Color color){
//        SortingAlgorithmVisualiser.sq.getChildren().add(new AnimationSetup().changeColor(rect.get(j), s, color));
//    }
}
