package com.example.hw4jsppizzaapp.Services.DatabaseManagement;

import com.example.hw4jsppizzaapp.Models.Ingredient;
import com.example.hw4jsppizzaapp.Services.Helpers.ListConverter;

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
            ingredient = ListConverter.getObjectByListString(entity, ingredient);
        } catch (IndexOutOfBoundsException ex) {
            System.out.println(ex.getMessage());
        }

        return ingredient;
    }

    public List<Ingredient> getIngredientByPizzaId(long pizzaId) {
        sql = "SELECT ingredient_id FROM ingredient_pizza " +
                "WHERE pizza_id = " + pizzaId + ";";
        List<List<String>> ingredientIds = databaseService.getAllEntitiesBySql(sql);

        return getAllIngredientsByIds(ingredientIds);
    }

    public List<Ingredient> getAllIngredientsByIds(List<List<String>> ids) {
        List<Ingredient> ingredients = new ArrayList<>();

        for (List<String> ingredientId : ids) {
            Ingredient ingredient = getIngredientById(Long.parseLong(ingredientId.get(0)));

            ingredients.add(ingredient);
        }

        if(ingredients.get(0).getId() == 1) {
            ingredients.remove(ingredients.get(0));//Видаляє перший інгредієнт(той що за замовченням).
        }

        return ingredients;
    }

    public List<Ingredient> getAllIngredients() {
        sql = "SELECT * FROM ingredient";
        List<Ingredient> ingredients = new ArrayList<>();

        List<List<String>> entities = databaseService.getAllEntitiesBySql(sql);
        for (List<String> entity : entities) {
            Ingredient ingredient = ListConverter.getObjectByListString(entity, new Ingredient());

            ingredients.add(ingredient);
        }

        return ingredients;
    }
}
