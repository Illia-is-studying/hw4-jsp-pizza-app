package com.example.hw4jsppizzaapp.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Pizza {
    private long id;
    private double price;
    private String name;
    private List<Ingredient> ingredients;

    public Pizza(long id, double price, String name) {
        this.id = id;
        this.price = price;
        this.name = name;
    }

    public void addIngredient(Ingredient ingredient) {
        this.ingredients.add(ingredient);
    }
}
