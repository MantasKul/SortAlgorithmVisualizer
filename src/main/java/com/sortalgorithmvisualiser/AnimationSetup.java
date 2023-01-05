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
    public TranslateTransition animationSetup(ArrayList<Rectangle> rect, int a, double moveTo, int speed){
        TranslateTransition translate = new TranslateTransition(Duration.millis(1000));
        translate.setDelay(Duration.millis(speed));
        translate.setToX(moveTo);
        translate.setNode(rect.get(a));
        return translate;
    }

    public FillTransition changeToBlue(ArrayList<Rectangle> rect, int a, int speed){
        FillTransition ft = new FillTransition();
        ft.setDelay(Duration.millis(speed));
        ft.setToValue(Color.BLUE);
        ft.setShape(rect.get(a));
        return ft;
    }

    public FillTransition changeToBlack(ArrayList<Rectangle> rect, int a, int speed){
        FillTransition ft = new FillTransition();
        ft.setDelay(Duration.millis(speed));
        ft.setToValue(Color.BLACK);
        ft.setShape(rect.get(a));
        return ft;
    }

    public FillTransition changeToGreen(ArrayList<Rectangle> rect, int a, int speed){
        FillTransition ft = new FillTransition();
        ft.setDelay(Duration.millis(speed));
        ft.setToValue(Color.GREEN);
        ft.setShape(rect.get(a));
        return ft;
    }

    public FillTransition changeToRed(ArrayList<Rectangle> rect, int a, int speed){
        FillTransition ft = new FillTransition();
        ft.setDelay(Duration.millis(speed));
        ft.setToValue(Color.RED);
        ft.setShape(rect.get(a));
        return ft;
    }

    public FillTransition changeToPurple(ArrayList<Rectangle> rect, int a, int speed){
        FillTransition ft = new FillTransition();
        ft.setDelay(Duration.millis(speed));
        ft.setToValue(Color.PURPLE);
        ft.setShape(rect.get(a));
        return ft;
    }
}
