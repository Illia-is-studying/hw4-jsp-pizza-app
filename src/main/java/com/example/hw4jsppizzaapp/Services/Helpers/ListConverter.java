package com.example.hw4jsppizzaapp.Services.Helpers;

import com.example.hw4jsppizzaapp.Models.Ingredient;
import com.example.hw4jsppizzaapp.Models.Pizza;
import com.example.hw4jsppizzaapp.Models.Topping;

import java.util.List;

public class ListConverter {
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
            } else if (testObj instanceof Topping) {
                testObj = (T) new Topping(
                        Long.parseLong(list.get(0)),
                        list.get(1),
                        Double.parseDouble(list.get(2)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return testObj;
    }
}
