package com.sortingalgorithms;

import com.sortalgorithmvisualiser.AnimationSetup;

import com.sortalgorithmvisualiser.SortingAlgorithmVisualiser;
import javafx.animation.SequentialTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class Selectionsort {
    private final AnimationSetup animationSetup = new AnimationSetup();

    public void sort(ArrayList<Integer> values, ArrayList<Rectangle> rect, int speed){
        int minValueIndex;

        for(int i = 0; i < values.size()-1; i++){
            minValueIndex = i;
            animationSetup.sequenceTransitionColorSetup(rect.get(minValueIndex), rect.get(minValueIndex), speed, Color.PURPLE, Color.PURPLE);
            for(int j = i+1; j < values.size(); j++){
                animationSetup.sequenceTransitionColorSetup(rect.get(j), rect.get(j), speed, Color.BLUE, Color.BLUE);

                if(values.get(j) < values.get(minValueIndex)) {
                    animationSetup.sequenceTransitionColorSetup(rect.get(minValueIndex), rect.get(minValueIndex), speed, Color.BLACK, Color.BLACK);
                    minValueIndex = j;
                    animationSetup.sequenceTransitionColorSetup(rect.get(minValueIndex), rect.get(minValueIndex), speed, Color.PURPLE, Color.PURPLE);
                }
                else{
                    animationSetup.sequenceTransitionColorSetup(rect.get(j), rect.get(j), speed, Color.BLACK, Color.BLACK);
                }
            }
            animationSetup.sequenceTransitionColorSetup(rect.get(minValueIndex), rect.get(minValueIndex), speed, Color.BLACK, Color.BLACK);

            // If the minValueIndex didn't change then the value is already in its correct spot
            if(minValueIndex > i){
                animationSetup.sequenceTransitionSetup(rect.get(i), rect.get(minValueIndex), minValueIndex*15-(values.size()-values.size()/2)*15, i*15-(values.size()-values.size()/2)*15, speed);

                // Swapping values and rectangles
                swapValues(values, i, minValueIndex);
                swapRectangles(rect, i, minValueIndex);
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
