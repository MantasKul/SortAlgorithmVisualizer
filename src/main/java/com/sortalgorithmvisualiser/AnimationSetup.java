package com.sortalgorithmvisualiser;

import javafx.animation.FillTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.ArrayList;

public class AnimationSetup {

    public void sequenceTransitionSetup(Rectangle rectA, Rectangle rectB, float moveToA, float moveToB, int speed) {
        SortingAlgorithmVisualiser.sq.getChildren().add(animationSetup(rectA, moveToA, speed));
        SortingAlgorithmVisualiser.sq2.getChildren().add(animationSetup(rectB, moveToB, speed));
    }

    public void sequenceTransitionColorSetup(Rectangle rect0, Rectangle rect1, int speed, Color color0, Color color1) {
        SortingAlgorithmVisualiser.sq.getChildren().add(changeColor(rect0, speed, color0));
        SortingAlgorithmVisualiser.sq2.getChildren().add(changeColor(rect1, speed, color1));
    }

    // rect - rectangle to move, moveTo - coordinate to move rectangle to, speed - animation play time in ms
    // Returns Translate Transition object that can be added to sequential transition
    public TranslateTransition animationSetup(Rectangle rect, double moveTo, int speed){
        TranslateTransition translate = new TranslateTransition(Duration.millis(1000));
        translate.setDelay(Duration.millis(speed));
        translate.setToX(moveTo);
        translate.setNode(rect);
        return translate;
    }

    public FillTransition changeColor(Rectangle rect, int speed, Color c) {
        FillTransition ft = new FillTransition();
        ft.setDelay(Duration.millis(speed));
        ft.setToValue(c);
        ft.setShape(rect);
        return ft;
    }
}
