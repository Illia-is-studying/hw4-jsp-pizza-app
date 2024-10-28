package com.example.hw4jsppizzaapp.Models.Enums;

public enum ToppingPriceEnum {
    EXTRA_CHEESE(4.0),
    OLIVES(1.5),
    CAPERS(2.5);

    private final double value;

    ToppingPriceEnum(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}
