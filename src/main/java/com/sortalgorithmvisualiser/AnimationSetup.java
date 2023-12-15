package com.sortalgorithmvisualiser;

import javafx.animation.FillTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.ArrayList;

public class AnimationSetup {
    // rect - array of rectangles, int a - index of which rectangle  to move, int moveTo - position to move the rectangle to, speed - set animation speed (in milli seconds)
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
