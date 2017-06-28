package com.livelearn.ignorance.test.observablescrollview.model;

import java.util.ArrayList;

/**
 * Created on 2017/3/27.
 */

public class DummyData {

    private static final int NUM_OF_ITEMS = 100;

    public static ArrayList<String> getDummyData() {
        return getDummyData(NUM_OF_ITEMS);
    }

    private static ArrayList<String> getDummyData(int num) {
        ArrayList<String> items = new ArrayList<>();
        for (int i = 1; i <= num; i++) {
            items.add("Item " + i);
        }
        return items;
    }
}
