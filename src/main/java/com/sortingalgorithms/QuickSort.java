package com.sortingalgorithms;

import com.sortalgorithmvisualiser.AnimationSetup;
import com.sortalgorithmvisualiser.SortingAlgorithmVisualiser;
import javafx.animation.SequentialTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class QuickSort {
    int pivot;
    AnimationSetup animationSetup = new AnimationSetup();
    public void sort(ArrayList<Integer> values, ArrayList<Rectangle> rect, int low, int high, SequentialTransition sq, SequentialTransition sq2, int s){
        if(low < high){
            int partition = partition(values, rect, low, high, sq, sq2, s);
            sort(values, rect, low, partition, sq, sq2, s);
            sort(values, rect, partition+1, high, sq, sq2, s);
        }
    }

    public int partition(ArrayList<Integer> values, ArrayList<Rectangle> rect, int low, int high, SequentialTransition sq, SequentialTransition sq2, int s){
        // Pivot is the values that other values will be compared to
        pivot = values.get((int)Math.floor(high+low)/2);

        // Coloring the pivot purple
        animationSetup.sequenceTransition0ColorSetup(rect.get((int)Math.floor(high+low)/2), s, Color.PURPLE);
        animationSetup.sequenceTransition1ColorSetup(rect.get((int)Math.floor(high+low)/2), s, Color.PURPLE);

        int i = low - 1;
        int j = high + 1;

        while(true){
            // Finding bigger rectangle left of the pivot
            do{
                if(i >= 0 && i < values.size()){
                    animationSetup.sequenceTransition0ColorSetup(rect.get(i), s, Color.BLACK);
                    animationSetup.sequenceTransition1ColorSetup(rect.get(i), s, Color.BLACK);
                }
                i++;
                animationSetup.sequenceTransition0ColorSetup(rect.get(i), s, Color.BLUE);
                animationSetup.sequenceTransition1ColorSetup(rect.get(i), s, Color.BLUE);
            } while (values.get(i) < pivot);
            // Finding a smaller rectangle on the right of the pivot
            do{
                if(j >= 0 && j < values.size()){
                    animationSetup.sequenceTransition0ColorSetup(rect.get(j), s, Color.BLACK);
                    animationSetup.sequenceTransition1ColorSetup(rect.get(j), s, Color.BLACK);
                }
                j--;
                animationSetup.sequenceTransition0ColorSetup(rect.get(j), s, Color.BLACK);
                animationSetup.sequenceTransition1ColorSetup(rect.get(j), s, Color.BLACK);
            } while (values.get(j) > pivot);
            if(i >= j) return j;

            // Animation for rectangle swap
            animationSetup.sequenceTransitionSetup(rect.get(i), rect.get(j), j*15-(values.size()-values.size()/2)*15, i*15-(values.size()-values.size()/2)*15, s);

            // Changing the swapped rectangles back to black
            animationSetup.sequenceTransition0ColorSetup(rect.get(i), s, Color.BLACK);
            animationSetup.sequenceTransition1ColorSetup(rect.get(j), s, Color.BLACK);


            // Coloring the pivot purple
            animationSetup.sequenceTransition0ColorSetup(rect.get((int)Math.floor(high+low)/2), s, Color.PURPLE);
            animationSetup.sequenceTransition1ColorSetup(rect.get((int)Math.floor(high+low)/2), s, Color.PURPLE);


            int temp = values.get(i);
            values.set(i, values.get(j));
            values.set(j, temp);

            Rectangle tempRect = rect.get(i);
            rect.set(i, rect.get(j));
            rect.set(j, tempRect);
        }
    }
}
