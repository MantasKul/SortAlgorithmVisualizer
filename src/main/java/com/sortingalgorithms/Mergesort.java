package com.sortingalgorithms;

import javafx.scene.shape.Rectangle;
import org.controlsfx.control.tableview2.filter.filtereditor.SouthFilter;

import java.util.ArrayList;

public class Mergesort {
    public void mergeSort(ArrayList<Integer> values, ArrayList<Rectangle> rect, int beginning, int end){
        if(beginning < end){
            int mid = (beginning+end)/2;

            mergeSort(values, rect, beginning, mid);
            mergeSort(values, rect, mid+1, end);
            merge(values, rect, beginning, mid, end);
        }
    }

    public void merge(ArrayList<Integer> values, ArrayList<Rectangle> rect, int beginning, int mid, int end){
        //int i,j,k;
        int n1 = mid-beginning+1;
        int n2 = end-mid;

        ArrayList<Integer> leftArray = new ArrayList<>();
        ArrayList<Integer> rightArray = new ArrayList<>();

        for(int i = 0; i < n1; i++) leftArray.add(values.get(beginning+i));
        for(int i = 0; i < n2; i++) rightArray.add(values.get(mid+1+i));

        int i = 0;
        int j = 0;
        int k = beginning;

        while(i < n1 && j < n2){
            if(leftArray.get(i) <= rightArray.get(j)){
                values.set(k, leftArray.get(i));
                i++;
            } else{
                values.set(k, rightArray.get(j));
                j++;
            }
            k++;
        }

        while(i < n1){
            values.set(k, leftArray.get(i));
            i++;
            k++;
        }
        while(j < n2){
            values.set(k, rightArray.get(j));
            j++;
            k++;
        }

    }
}
