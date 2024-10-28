package com.example.hw4jsppizzaapp.Services.Helpers;

import com.example.hw4jsppizzaapp.Models.Ingredient;
import com.example.hw4jsppizzaapp.Models.Pizza;

import java.util.List;

public class ListConverterService {
    public static <T> T getObjectByListString(List<String> list, T testObj) {
        try {
            if (testObj instanceof Ingredient) {
                testObj = (T) new Ingredient(
                        Long.parseLong(list.get(0)),
                        list.get(2),
                        Integer.parseInt(list.get(1)));
            } else if (testObj instanceof Pizza) {
                testObj = (T) new Pizza(
                        Long.parseLong(list.get(0)),
                        Double.parseDouble(list.get(1)),
                        list.get(2));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return testObj;
    }
}
