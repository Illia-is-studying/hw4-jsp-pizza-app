package com.example.hw4jsppizzaapp.Models.ViewModels;

import com.example.hw4jsppizzaapp.Models.Ingredient;
import com.example.hw4jsppizzaapp.Models.Pizza;
import com.example.hw4jsppizzaapp.Models.Position;
import com.example.hw4jsppizzaapp.Services.CalculatePositionService;

import java.util.HashMap;
import java.util.List;

public class PizzaViewModel {
    private long pizzaId;
    private String pizzaName;
    private String pizzaIngredientsLine;
    private List<Ingredient> pizzaIngredientsList;
    private HashMap<String, List<Position>> ingredientsPosition;

    public PizzaViewModel(Pizza pizza) {
        pizzaId = pizza.getId();
        pizzaName = pizza.getName();
        pizzaIngredientsList = pizza.getIngredients();
        ingredientsPosition = new HashMap<>();
        setPizzaIngredientsLine(pizzaIngredientsList);
        setIngredientsPosition(pizzaIngredientsList);
    }

    public long getPizzaId() {
        return pizzaId;
    }

    public void setPizzaId(long pizzaId) {
        this.pizzaId = pizzaId;
    }

    public String getPizzaName() {
        return pizzaName;
    }

    public void setPizzaName(String pizzaName) {
        this.pizzaName = pizzaName;
    }

    public void setPizzaIngredientsList(List<Ingredient> pizzaIngredientsList) {
        this.pizzaIngredientsList = pizzaIngredientsList;
    }

    public List<Ingredient> getPizzaIngredientsList() {
        return pizzaIngredientsList;
    }

    public String getPizzaIngredientsLine() {
        return pizzaIngredientsLine;
    }

    public void setPizzaIngredientsLine(List<Ingredient> pizzaIngredients) {
        StringBuilder ingredients = new StringBuilder("Ingredients: ");

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

            List<Position> positions = CalculatePositionService.getPositions(8);

            ingredientsPosition.put(ingredient.getName(), positions);
        }
    }
}
