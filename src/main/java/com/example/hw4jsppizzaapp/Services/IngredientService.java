package com.example.hw4jsppizzaapp.Services;

import com.example.hw4jsppizzaapp.Models.Ingredient;

import java.util.ArrayList;
import java.util.List;

public class IngredientService {
    private final DatabaseService databaseService;
    private String sql;

    public IngredientService() {
        this.databaseService = new DatabaseService();
    }

    public Ingredient getIngredientById(long id) {
        sql = "SELECT * FROM ingredient WHERE id = " + id;
        List<List<String>> entities = databaseService.getAllEntitiesBySql(sql);

        Ingredient ingredient = new Ingredient();

        try {
            List<String> entity = entities.get(0);
            ingredient = ListConverterService.getObjectByListString(entity, ingredient);
        } catch (IndexOutOfBoundsException ex) {
            System.out.println(ex.getMessage());
        }

        return ingredient;
    }

    public List<Ingredient> getAllIngredientsByIds(List<List<String>> ids) {
        List<Ingredient> ingredients = new ArrayList<>();

        for (List<String> ingredientId : ids) {
            Ingredient ingredient = getIngredientById(Long.parseLong(ingredientId.get(0)));

            ingredients.add(ingredient);
        }

        return ingredients;
    }

    public List<Ingredient> getAllIngredients() {
        sql = "SELECT * FROM ingredient";
        List<Ingredient> ingredients = new ArrayList<>();

        List<List<String>> entities = databaseService.getAllEntitiesBySql(sql);
        for (List<String> entity : entities) {
            Ingredient ingredient = ListConverterService.getObjectByListString(entity, new Ingredient());

            ingredients.add(ListConverterService.getObjectByListString(entity, ingredient));
        }

        return ingredients;
    }
}
