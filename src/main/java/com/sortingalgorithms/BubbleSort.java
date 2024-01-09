package com.sortingalgorithms;

import com.sortalgorithmvisualiser.AnimationSetup;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class BubbleSort {
    private final AnimationSetup animationSetup = new AnimationSetup();

    public void sort(ArrayList<Integer> values, ArrayList<Rectangle> rect, int speed){

        for(int i = 0; i < values.size(); i++) {
            for (int j = 0; j < values.size() - 1; j++) {
                // Highlighting rectangle that are being currently compared blue
                animationSetup.sequenceTransitionColorSetup(rect.get(j), rect.get(j+1), speed, Color.BLUE, Color.BLUE);
                if (values.get(j) > values.get(j + 1)) {


                    // Animation setup
                    animationSetup.sequenceTransitionColorSetup(rect.get(j), rect.get(j+1), speed, Color.GREEN, Color.GREEN);
                    animationSetup.sequenceTransitionSetup(rect.get(j), rect.get(j+1), (j+1)*15-(values.size()-values.size()/2)*15, j*15-(values.size()-values.size()/2)*15, speed);

                    // Swapping values and rectangle objects
                    swapValues(values, j, j+1);
                    swapRectangles(rect, j, j+1);
                }
                else {
                    // If the rectangles aren't going to be switched their colors are changed to red
                    animationSetup.sequenceTransitionColorSetup(rect.get(j), rect.get(j+1), speed, Color.RED, Color.RED);
                }
                // Changes rectangles color back to black
                animationSetup.sequenceTransitionColorSetup(rect.get(j), rect.get(j+1), speed, Color.BLACK, Color.BLACK);
            }
        }
    }

    // Helper methods

    public void swapValues(ArrayList<Integer> values, int index1, int index2) {
        int temp = values.get(index1);
        values.set(index1, values.get(index2));
        values.set(index2, temp);
    }

    public void swapRectangles(ArrayList<Rectangle> rect, int index1, int index2) {
        Rectangle tempRect = rect.get(index1);
        rect.set(index1, rect.get(index2));
        rect.set(index2, tempRect);
    }
}
