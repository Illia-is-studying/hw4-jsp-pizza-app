package com.example.hw4jsppizzaapp.Services;

import com.example.hw4jsppizzaapp.Models.Ingredient;

import java.util.List;

public class ListConverterService {
    public static <T> T getObjectByListString(List<String> list, T testObj) {
        if (testObj instanceof Ingredient) {
            testObj = (T) new Ingredient(
                    Long.parseLong(list.get(0)),
                    list.get(1));
        }

        return testObj;
    }
}
