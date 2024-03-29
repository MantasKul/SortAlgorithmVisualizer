package com.sortingalgorithms;

import com.sortalgorithmvisualiser.AnimationSetup;
import com.sortalgorithmvisualiser.SortingAlgorithmVisualiser;
import javafx.animation.SequentialTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class QuickSort {
    private int pivot;
    private final AnimationSetup animationSetup = new AnimationSetup();

    public void sort(ArrayList<Integer> values, ArrayList<Rectangle> rect, int low, int high, int speed){
        if(low < high){
            int partition = partition(values, rect, low, high, speed);
            sort(values, rect, low, partition, speed);
            sort(values, rect, partition+1, high, speed);
        }
    }

    public int partition(ArrayList<Integer> values, ArrayList<Rectangle> rect, int low, int high, int speed){
        // Pivot is the value that other values will be compared to
        pivot = values.get((int)Math.floor(high+low)/2);

        // Getting pivot rectangle to be able to reset its colors later
        Rectangle pivotRect = rect.get((int)Math.floor(high+low)/2);
        // Coloring the pivot's borders purple
        animationSetup.sequenceTransitionBorderColor(pivotRect, pivotRect, speed, Color.PURPLE, Color.PURPLE);

        int i = low - 1;
        int j = high + 1;

        while(true){
            // Finding bigger rectangle left of the pivot
            do{
                if(i >= 0 && i < values.size()){
                    animationSetup.sequenceTransitionColorSetup(rect.get(i), rect.get(i), speed, Color.BLACK, Color.BLACK);
                }
                i++;
                animationSetup.sequenceTransitionColorSetup(rect.get(i), rect.get(i), speed, Color.GREEN, Color.GREEN);
            } while (values.get(i) < pivot);
            // Finding a smaller rectangle on the right of the pivot
            do{
                if(j >= 0 && j < values.size()){
                    animationSetup.sequenceTransitionColorSetup(rect.get(j), rect.get(j), speed, Color.BLACK, Color.BLACK);
                }
                j--;
                animationSetup.sequenceTransitionColorSetup(rect.get(j), rect.get(j), speed, Color.BLUE, Color.BLUE);
            } while (values.get(j) > pivot);
            if(i >= j) {
                // Returning pivot's borders back to black
                animationSetup.sequenceTransitionBorderColor(pivotRect, pivotRect, speed, Color.BLACK, Color.BLACK);

                // Changing the swapped rectangles back to black
                animationSetup.sequenceTransitionColorSetup(rect.get(i), rect.get(j), speed, Color.BLACK, Color.BLACK);
                return j;
            }

            // Animation for rectangle swap
            animationSetup.sequenceTransitionSetup(rect.get(i), rect.get(j), j*15-(values.size()-values.size()/2)*15, i*15-(values.size()-values.size()/2)*15, speed);

            // Changing the swapped rectangles back to black
            animationSetup.sequenceTransitionColorSetup(rect.get(i), rect.get(j), speed, Color.BLACK, Color.BLACK);

            // Swapping values and rectangles
            swapValues(values, i, j);
            swapRectangles(rect, i, j);
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
