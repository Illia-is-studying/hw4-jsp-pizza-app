package com.example.hw4jsppizzaapp.Models.ViewModels;

import com.example.hw4jsppizzaapp.Models.Ingredient;
import com.example.hw4jsppizzaapp.Models.Pizza;
import com.example.hw4jsppizzaapp.Models.Position;
import com.example.hw4jsppizzaapp.Services.CalculatePositionService;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;

public class PizzaViewModel {
    private Pizza pizza;
    private String pizzaIngredientsLine;
    private HashMap<String, List<Position>> ingredientsPosition;

    public PizzaViewModel(Pizza pizza) {
        this.pizza = pizza;
        ingredientsPosition = new HashMap<>();
        setPizzaIngredientsLine(pizza.getIngredients());
        setIngredientsPosition(pizza.getIngredients());
    }

    public Pizza getPizza() {
        return pizza;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }

    public String getPizzaIngredientsLine() {
        return pizzaIngredientsLine;
    }

    public void setPizzaIngredientsLine(List<Ingredient> pizzaIngredients) {
        StringBuilder ingredients = new StringBuilder();
        ingredients.append("marinara, cheese, ");

        for (Ingredient ingredient : pizzaIngredients) {
            ingredients.append(ingredient.getName() + ", ");
        }

        String ingredientsLine = ingredients.toString();
        this.pizzaIngredientsLine = ingredientsLine.substring(0, ingredientsLine.length() - 2) + ".";
    }

    public HashMap<String, List<Position>> getIngredientsPosition() {
        return ingredientsPosition;
    }

    public void setIngredientsPosition(List<Ingredient> ingredients) {
        for (Ingredient ingredient : ingredients) {
            String ingredientName = ingredient.getName();

            List<Position> positions = CalculatePositionService
                    .getPositions(3 * ingredient.getQuantity());

            ingredientsPosition.put(ingredient.getName(), positions);
        }
    }
}
