package com.example.hw4jsppizzaapp.Services;

import com.example.hw4jsppizzaapp.Models.Ingredient;
import com.example.hw4jsppizzaapp.Models.Pizza;

import java.util.ArrayList;
import java.util.List;

public class PizzaService {
    private final DatabaseService databaseService;
    private final IngredientService ingredientService;
    private String sql;

    public PizzaService() {
        this.databaseService = new DatabaseService();
        this.ingredientService = new IngredientService();
    }

    public List<Pizza> getAllPizzas() {
        List<Pizza> pizzas = new ArrayList<>();

        sql = "SELECT * FROM pizza";
        List<List<String>> pizzasListList = databaseService.getAllEntitiesBySql(sql);

        for (List<String> pizzaList : pizzasListList) {
            sql = "SELECT ingredient_id FROM ingredient_pizza " +
                    "WHERE pizza_id = " + pizzaList.get(0) + ";";
            List<List<String>> ingredientIds = databaseService.getAllEntitiesBySql(sql);
            List<Ingredient> ingredients = ingredientService.getAllIngredientsByIds(ingredientIds);

            Pizza pizza = new Pizza(
                    Long.parseLong(pizzaList.get(0)),
                    pizzaList.get(1),
                    ingredients);

            pizzas.add(pizza);
        }

        return pizzas;
    }
}
