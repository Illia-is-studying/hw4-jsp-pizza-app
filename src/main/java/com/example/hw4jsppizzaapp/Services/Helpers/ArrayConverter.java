package com.example.hw4jsppizzaapp.Services.Helpers;

public class ArrayConverter {
    public static Long[] convertStringsToLongs(String[] array) {
        Long[] longs = new Long[array.length];

        for (int i = 0; i < array.length; i++) {
            try {
                longs[i] = Long.valueOf(array[i]);
            } catch (NumberFormatException e) {
                longs[i] = 0L;
            }
        }

        return longs;
    }
}
