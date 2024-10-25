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

    public String savePizza(String pizzaName, String[] ingredientsId) {
        if (!checkPizzaNameExist(pizzaName)) {
            return "A pizza by that name already exists.\nTry another one...";
        }

        if (!checkPizzaIngredientsExist(ingredientsId)) {
            return "Pizza with these ingredients already exists.\nTry another one...";
        }

        sql = "INSERT INTO pizza (name) VALUES ('" + pizzaName + "');";
        databaseService.executeUpdateBySql(sql);

        sql = "SELECT id FROM pizza WHERE name='" + pizzaName + "';";
        List<List<String>> idList = databaseService.getAllEntitiesBySql(sql);
        int pizzaId = Integer.parseInt(idList.get(0).get(0));

        StringBuilder insertIntoIngredientPizzaSql =
                new StringBuilder("INSERT INTO ingredient_pizza (ingredient_id, pizza_id) VALUES ");

        for (String ingredientId : ingredientsId) {
            sql = "SELECT name FROM ingredient WHERE id=" + ingredientId;
            List<List<String>> ingredientIdList = databaseService.getAllEntitiesBySql(sql);

            if (!ingredientIdList.isEmpty()) {
                insertIntoIngredientPizzaSql.append("(").append(ingredientId).append(",")
                        .append(pizzaId).append("),");
            }
        }
        sql = insertIntoIngredientPizzaSql.toString();
        sql = sql.substring(0, sql.length() - 1) + ";";

        databaseService.executeUpdateBySql(sql);

        return "ok";
    }

    public boolean checkPizzaNameExist(String pizzaName) {
        sql = "SELECT * FROM pizza WHERE name='" + pizzaName + "'";

        List<List<String>> pizzasListList = databaseService.getAllEntitiesBySql(sql);

        return pizzasListList.isEmpty();
    }

    public boolean checkPizzaIngredientsExist(String[] ingredientsId) {
        StringBuilder checkIngredientsSql =
                new StringBuilder("SELECT pizza_id FROM ingredient_pizza " +
                        "JOIN pizza AS p ON p.id = pizza_id");
        StringBuilder inRange = new StringBuilder(" IN(");
        for (String ingredientId : ingredientsId) {
            inRange.append(ingredientId).append(", ");
        }
        String tmp = inRange.toString();
        inRange = new StringBuilder(tmp.substring(0, tmp.length() - 2) + ")");
        checkIngredientsSql.append(" GROUP BY pizza_id HAVING COUNT(DISTINCT ingredient_id) = ")
                .append(ingredientsId.length)
                .append(" AND SUM(CASE WHEN ingredient_id")
                .append(inRange)
                .append(" THEN 1 ELSE 0 END) = ")
                .append(ingredientsId.length)
                .append(" AND COUNT(DISTINCT ingredient_id) = COUNT(DISTINCT CASE WHEN ingredient_id")
                .append(inRange)
                .append(" THEN ingredient_id END);");
        sql = checkIngredientsSql.toString();

        List<List<String>> pizzasListList = databaseService.getAllEntitiesBySql(sql);

        return pizzasListList.isEmpty();
    }
}
