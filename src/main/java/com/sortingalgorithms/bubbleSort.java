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
                changeColor(rect.get(j), rect.get(j+1), speed, Color.BLUE);
                if (values.get(j) > values.get(j + 1)) {
                    // Swapping values in the array
                    int temp = values.get(j);
                    values.set(j, values.get(j+1));
                    values.set(j+1, temp);

                    // Animation setup
                    changeColor(rect.get(j), rect.get(j+1), speed, Color.GREEN);
                    animationSetup(rect.get(j), rect.get(j+1), (j+1)*15-(values.size()-values.size()/2)*15, j*15-(values.size()-values.size()/2)*15, speed);

                    // Swapping rectangle objects
                    Rectangle tempRect = rect.get(j);
                    rect.set(j, rect.get(j+1));
                    rect.set(j+1, tempRect);
                }
                else {
                    // If the rectangles aren't going to be switched their colors are changed to red
                    changeColor(rect.get(j), rect.get(j+1), speed, Color.RED);
                }
                // Changes rectangles color back to black
                changeColor(rect.get(j), rect.get(j+1), speed, Color.BLACK);
            }
        }
    }

    public void animationSetup(Rectangle rectA, Rectangle rectB, float moveToA, float moveToB, int speed){
        // Setting up animations
        // Adding movement animations to sequence
        SortingAlgorithmVisualiser.sq.getChildren().add(new AnimationSetup().animationSetup(rectA, moveToA, speed));
        SortingAlgorithmVisualiser.sq2.getChildren().add(new AnimationSetup().animationSetup(rectB, moveToB, speed));
    }

    public void changeColor(Rectangle rectA, Rectangle rectB, int speed, Color color){
        SortingAlgorithmVisualiser.sq.getChildren().add(new AnimationSetup().changeColor(rectA, speed, color));
        SortingAlgorithmVisualiser.sq2.getChildren().add(new AnimationSetup().changeColor(rectB, speed, color));
    }
}
