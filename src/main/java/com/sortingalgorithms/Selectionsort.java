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
            animationSetup.sequenceTransition0ColorSetup(rect.get(minValueIndex), speed, Color.PURPLE);
            animationSetup.sequenceTransition1ColorSetup(rect.get(minValueIndex), speed, Color.PURPLE);
            for(int j = i+1; j < values.size(); j++){
                animationSetup.sequenceTransition0ColorSetup(rect.get(j), speed, Color.BLUE);
                animationSetup.sequenceTransition1ColorSetup(rect.get(j), speed, Color.BLUE);

                if(values.get(j) < values.get(minValueIndex)) {
                    animationSetup.sequenceTransition0ColorSetup(rect.get(minValueIndex), speed, Color.BLACK);
                    animationSetup.sequenceTransition1ColorSetup(rect.get(minValueIndex), speed, Color.BLACK);
                    minValueIndex = j;
                    animationSetup.sequenceTransition0ColorSetup(rect.get(minValueIndex), speed, Color.PURPLE);
                    animationSetup.sequenceTransition1ColorSetup(rect.get(minValueIndex), speed, Color.PURPLE);
                }
                else{
                    animationSetup.sequenceTransition0ColorSetup(rect.get(j), speed, Color.BLACK);
                    animationSetup.sequenceTransition1ColorSetup(rect.get(j), speed, Color.BLACK);
                }
            }
            animationSetup.sequenceTransition0ColorSetup(rect.get(minValueIndex), speed, Color.BLACK);
            animationSetup.sequenceTransition1ColorSetup(rect.get(minValueIndex), speed, Color.BLACK);

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
