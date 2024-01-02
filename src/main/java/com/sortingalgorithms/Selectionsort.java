package com.sortingalgorithms;

import com.sortalgorithmvisualiser.AnimationSetup;

import com.sortalgorithmvisualiser.SortingAlgorithmVisualiser;
import javafx.animation.SequentialTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class Selectionsort {
    AnimationSetup animationSetup = new AnimationSetup();
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

                Rectangle tempRect = rect.get(i);
                rect.set(i, rect.get(minValueIndex));
                rect.set(minValueIndex, tempRect);

                int temp = values.get(i);
                values.set(i, values.get(minValueIndex));
                values.set(minValueIndex, temp);
            }
        }
    }
}
