package com.sortingalgorithms;

import com.sortalgorithmvisualiser.AnimationSetup;

import javafx.animation.SequentialTransition;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class Selectionsort {
    AnimationSetup as = new AnimationSetup();
    public void selectionSort(ArrayList<Integer> values, ArrayList<Rectangle> rect, SequentialTransition sq, SequentialTransition sq2, int s){
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
            sq.getChildren().add(as.changeToPurple(rect, minValueIndex, s));
            sq2.getChildren().add(as.changeToPurple(rect, minValueIndex, s));
            for(int j = i+1; j < values.size(); j++){

                sq.getChildren().add(as.changeToBlue(rect, j, s));
                sq2.getChildren().add(as.changeToBlue(rect, j, s));

                if(values.get(j) < values.get(minValueIndex)) {
                    sq.getChildren().add(as.changeToBlack(rect, minValueIndex, s));
                    sq2.getChildren().add(as.changeToBlack(rect, minValueIndex, s));
                    minValueIndex = j;
                    sq.getChildren().add(as.changeToPurple(rect, minValueIndex, s));
                    sq2.getChildren().add(as.changeToPurple(rect, minValueIndex, s));
                }
                else{
                    sq.getChildren().add(as.changeToBlack(rect, j, s));
                    sq2.getChildren().add(as.changeToBlack(rect, j, s));
                }
            }
            sq.getChildren().add(as.changeToBlack(rect, minValueIndex, s));
            sq2.getChildren().add(as.changeToBlack(rect, minValueIndex, s));

            // If the minValueIndex didn't change then the value is already in its correct spot
            if(minValueIndex > i){
                sq.getChildren().add(as.animationSetup(rect.get(i), minValueIndex*15-(values.size()-values.size()/2)*15, s));
                sq2.getChildren().add(as.animationSetup(rect.get(minValueIndex), i*15-(values.size()-values.size()/2)*15, s));

                Rectangle tempRect = rect.get(i);
                rect.set(i, rect.get(minValueIndex));
                rect.set(minValueIndex, tempRect);

                int temp = values.get(i);
                values.set(i, values.get(minValueIndex));
                values.set(minValueIndex, temp);
            }
        }

        sq.play();
        sq2.play();
    }
}
