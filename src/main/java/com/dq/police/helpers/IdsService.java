package com.dq.police.helpers;

public class IdsService {

    private static int current=0;

    public static int getNext() {
        return current++;
    }
}
