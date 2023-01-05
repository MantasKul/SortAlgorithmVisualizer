package com.sortingalgorithms;

import com.sortalgorithmvisualiser.AnimationSetup;
import javafx.animation.SequentialTransition;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class QuickSort {
    int pivot;
    AnimationSetup as = new AnimationSetup();
    public void quickSort(ArrayList<Integer> values, ArrayList<Rectangle> rect, int low, int high, SequentialTransition sq, SequentialTransition sq2, int s){
        if(low < high){
            int partition = partition(values, rect, low, high, sq, sq2, s);
            quickSort(values, rect, low, partition, sq, sq2, s);
            quickSort(values, rect, partition+1, high, sq, sq2, s);
        }
    }

    public int partition(ArrayList<Integer> values, ArrayList<Rectangle> rect, int low, int high, SequentialTransition sq, SequentialTransition sq2, int s){
        // Pivot is the values that other values will be compared to
        pivot = values.get((int)Math.floor(high+low)/2);

        // Coloring the pivot purple
        sq.getChildren().add(as.changeToPurple(rect, (int)Math.floor(high+low)/2, s));
        sq2.getChildren().add(as.changeToPurple(rect, (int)Math.floor(high+low)/2, s));

        int i = low - 1;
        int j = high + 1;

        while(true){
            // Finding bigger rectangle left of the pivot
            do{
                if(i >= 0 && i < values.size()){
                    sq.getChildren().add(as.changeToBlack(rect, i, s));
                    sq2.getChildren().add(as.changeToBlack(rect, i, s));
                }
                i++;
                sq.getChildren().add(as.changeToBlue(rect, i, s));
                sq2.getChildren().add(as.changeToBlue(rect, i, s));
            } while (values.get(i) < pivot);
            // Finding a smaller rectangle on the right of the pivot
            do{
                if(j >= 0 && j < values.size()){
                    sq.getChildren().add(as.changeToBlack(rect, j, s));
                    sq2.getChildren().add(as.changeToBlack(rect, j, s));
                }
                j--;
                sq.getChildren().add(as.changeToBlue(rect, j, s));
                sq2.getChildren().add(as.changeToBlue(rect, j, s));
            } while (values.get(j) > pivot);
            if(i >= j) return j;

            // Animation for rectangle swap
            sq.getChildren().add(as.animationSetup(rect, i, j*15-(values.size()-values.size()/2)*15, s));
            sq2.getChildren().add(as.animationSetup(rect, j, i*15-(values.size()-values.size()/2)*15, s));

            // Changing the swapped rectangles back to black
            sq.getChildren().add(as.changeToBlack(rect, i, s));
            sq2.getChildren().add(as.changeToBlack(rect, i, s));
            sq.getChildren().add(as.changeToBlack(rect, j, s));
            sq2.getChildren().add(as.changeToBlack(rect, j, s));

            // Coloring the pivot purple
            sq.getChildren().add(as.changeToPurple(rect, (int)Math.floor(high+low)/2, s));
            sq2.getChildren().add(as.changeToPurple(rect, (int)Math.floor(high+low)/2, s));

            int temp = values.get(i);
            values.set(i, values.get(j));
            values.set(j, temp);

            Rectangle tempRect = rect.get(i);
            rect.set(i, rect.get(j));
            rect.set(j, tempRect);
        }
    }
}
