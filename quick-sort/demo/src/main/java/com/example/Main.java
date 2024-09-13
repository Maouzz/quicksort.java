package com.example;

public class Main {
    public static void main(String[] args) {
        int [] unsort_list = new int [9];
        for (int i = 0; i < unsort_list.length; i++) {
            unsort_list[i] = (int) Math.round((Math.random()*9));
        }
        sorter s = new sorter();
        s.sort(unsort_list);
    }
}