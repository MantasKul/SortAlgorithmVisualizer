package com.sortingalgorithms;

import com.sortalgorithmvisualiser.AnimationSetup;

import com.sortalgorithmvisualiser.SortingAlgorithmVisualiser;
import javafx.animation.SequentialTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class Selectionsort {
    AnimationSetup as = new AnimationSetup();
    public void sort(ArrayList<Integer> values, ArrayList<Rectangle> rect, SequentialTransition sq, SequentialTransition sq2, int speed){
        // Recursion version
        //        int minValueIndex = start;
//
//        for(int i = start+1; i < values.size(); i++){
//            if(values.get(i) < values.get(minValueIndex)) minValueIndex = i;
//        }
//
//        if(minValueIndex > start){
//            int temp = values.get(start);
//            values.set(start, values.get(minValueIndex));
//            values.set(minValueIndex, temp);
//        }
//        start++;
//        if(start < values.size()) selectionSort(values, rect, start);

        int minValueIndex;

        for(int i = 0; i < values.size()-1; i++){
            minValueIndex = i;
            changeColor(rect.get(minValueIndex), speed, Color.PURPLE);
            for(int j = i+1; j < values.size(); j++){
                changeColor(rect.get(j), speed, Color.BLUE);

                if(values.get(j) < values.get(minValueIndex)) {
                    changeColor(rect.get(minValueIndex), speed, Color.BLACK);
                    minValueIndex = j;
                    changeColor(rect.get(minValueIndex), speed, Color.PURPLE);
                }
                else{
                    changeColor(rect.get(j), speed, Color.BLACK);
                }
            }
            changeColor(rect.get(minValueIndex), speed, Color.BLACK);

            // If the minValueIndex didn't change then the value is already in its correct spot
            if(minValueIndex > i){
                animationSetup(rect.get(i), rect.get(minValueIndex), minValueIndex, values.size(), speed, i);

                Rectangle tempRect = rect.get(i);
                rect.set(i, rect.get(minValueIndex));
                rect.set(minValueIndex, tempRect);

                int temp = values.get(i);
                values.set(i, values.get(minValueIndex));
                values.set(minValueIndex, temp);
            }
        }
    }

    public void animationSetup(Rectangle rectA, Rectangle rectB, int minValueIndex, int values, int speed, int i) {
        SortingAlgorithmVisualiser.sq.getChildren().add(as.animationSetup(rectA, minValueIndex*15-(values-values/2)*15, speed));
        SortingAlgorithmVisualiser.sq2.getChildren().add(as.animationSetup(rectB, i*15-(values-values/2)*15, speed));
    }

    public void changeColor(Rectangle rect, int speed, Color color){
        SortingAlgorithmVisualiser.sq.getChildren().add(as.changeColor(rect, speed, color));
        SortingAlgorithmVisualiser.sq2.getChildren().add(as.changeColor(rect, speed, color));
    }
}
