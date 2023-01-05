package com.sortingalgorithms;

import com.sortalgorithmvisualiser.AnimationSetup;
import javafx.animation.SequentialTransition;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class bubbleSort {
    public void bubbleSort(ArrayList<Integer> values, ArrayList<Rectangle> rect, SequentialTransition sq, SequentialTransition sq2, int s){
        for(int i = 0; i < values.size(); i++) {
            for (int j = 0; j < values.size() - 1; j++) {
                // Highlighting rectangle that are being currently compared blue
                sq.getChildren().add(new AnimationSetup().changeToBlue(rect, j, s));
                sq2.getChildren().add(new AnimationSetup().changeToBlue(rect, j+1, s));
                if (values.get(j) > values.get(j + 1)) {
                    // Swapping values in the array
                    int temp = values.get(j);
                    values.set(j, values.get(j+1));
                    values.set(j+1, temp);

                    // Setting up animations
                    // If the rectangles are going to be switched their colors are changed to green
                    sq.getChildren().add(new AnimationSetup().changeToGreen(rect, j, s));
                    sq2.getChildren().add(new AnimationSetup().changeToGreen(rect, j+1, s));
                    // Adding movement animations to sequence
                    sq.getChildren().add(new AnimationSetup().animationSetup(rect, j, (j+1)*15-(values.size()-values.size()/2)*15, s));
                    sq2.getChildren().add(new AnimationSetup().animationSetup(rect, (j+1), j*15-(values.size()-values.size()/2)*15, s));

                    // Swapping rectangle objects
                    Rectangle tempRect = rect.get(j);
                    rect.set(j, rect.get(j+1));//rect[j] = rect[j+1];
                    rect.set(j+1, tempRect);//rect[j+1] = tempRect;
                }
                else {
                    // If the rectangles aren't going to be switched their colors are changed to red
                    sq.getChildren().add(new AnimationSetup().changeToRed(rect, j, s));
                    sq2.getChildren().add(new AnimationSetup().changeToRed(rect, j+1, s));
                }
                // Changes rectangles color back to black
                sq.getChildren().add(new AnimationSetup().changeToBlack(rect, j, s));
                sq2.getChildren().add(new AnimationSetup().changeToBlack(rect, j+1, s));
            }
        }

        // Playing sequence translation animations
        sq.play();
        sq2.play();
    }
}
