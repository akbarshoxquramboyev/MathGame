package com.example.mathforeveryone.sizecomp;

public class RandNubber {
    public static int fruit[];
    public static int fruit_id[];
    public static int test_count;
    public static int olqishlash;

    public static void RandNubberNew(){
//        if(fruit_id[4])
        fruit = new int[5];
        fruit_id = new int[5];
        for (int i = 0; i < 5; i++) {
            fruit_id[i] = i;
        }
    }

    private static void sortMass(){
        for (int i = 0; i < 4; i++) {
            for (int j = i+1; j < 5; j++) {
                if(fruit[i]>fruit[j]){
                    int a = fruit[i];
                    fruit[i] = fruit[j];
                    fruit[j] = a;
                    a = fruit_id[i];
                    fruit_id[i] = fruit_id[j];
                    fruit_id[j] = a;
                }
            }
        }
    }

    public static int randNextFruit(){
        sortMass();
        fruit[0]++;
        return fruit_id[0];
    }
}
