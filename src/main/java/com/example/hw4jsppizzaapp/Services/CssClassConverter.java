package com.example.hw4jsppizzaapp.Services;

public class CssClassConverter {
    public static String convertStringToCssClass(String name) {
        switch (name) {
            case "tomato sauce":
                return "pizza-edge";
            case "gorgonzola":
                return "cheese-spot gorgonzola";
            case "ricotta":
                return "cheese-spot ricotta";
            case "parmesan":
                return "cheese-spot parmesan";
            case "mushrooms":
                return "mushroom";
            case "capers":
                return "caper";
            default:
                return name;
        }
    }
}
