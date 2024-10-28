package com.example.hw4jsppizzaapp.Models.ViewModels;

import com.example.hw4jsppizzaapp.Models.Ingredient;
import com.example.hw4jsppizzaapp.Models.Pizza;
import com.example.hw4jsppizzaapp.Models.Position;
import com.example.hw4jsppizzaapp.Services.Helpers.CalculatePositionService;

import java.util.HashMap;
import java.util.List;

public class PizzaViewModel {
    private long modelId;
    private Pizza pizza;
    private String pizzaIngredientsLine;
    private HashMap<String, List<Position>> ingredientsPosition;

    public PizzaViewModel(Pizza pizza) {
        this.pizza = pizza;
        ingredientsPosition = new HashMap<>();
        setPizzaIngredientsLine(pizza.getIngredients());
        setIngredientsPosition(pizza.getIngredients());
    }

    public PizzaViewModel(Pizza pizza, long modelId) {
        this(pizza);
        this.modelId = modelId;
    }

    public long getModelId() {
        return modelId;
    }

    public void setModelId(long modelId) {
        this.modelId = modelId;
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
            ingredients.append(ingredient.getName()).append(", ");
        }

        String ingredientsLine = ingredients.toString();
        this.pizzaIngredientsLine = ingredientsLine.substring(0, ingredientsLine.length() - 2) + ".";
    }

    public HashMap<String, List<Position>> getIngredientsPosition() {
        return ingredientsPosition;
    }

    public void setIngredientsPosition(List<Ingredient> ingredients)
    {
        setIngredientsPosition(ingredients, 1);
    }

    public void setIngredientsPosition(List<Ingredient> ingredients, int quantity) {
        for (Ingredient ingredient : ingredients) {
            String ingredientName = ingredient.getName();

            List<Position> positions = CalculatePositionService
                    .getPositions(3 * ingredient.getQuantity());

            ingredientsPosition.put(ingredient.getName(), positions);
        }
    }
}
