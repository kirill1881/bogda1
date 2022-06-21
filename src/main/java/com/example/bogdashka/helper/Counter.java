package com.example.bogdashka.helper;

import lombok.Data;


public class Counter {
    public static int count = 1005;

    public static String getCount() {
        return String.valueOf(count++);
    }
}
