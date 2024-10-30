package com.example.hw4jsppizzaapp.Services.DatabaseManagement;

import com.example.hw4jsppizzaapp.Models.Pizza;
import com.example.hw4jsppizzaapp.Services.Helpers.ListConverter;

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

        for (List<String> entity : pizzasListList) {
            Pizza pizza = ListConverter.getObjectByListString(entity, new Pizza());
            pizza.setIngredients(ingredientService.getIngredientByPizzaId(pizza.getId()));

            pizzas.add(pizza);
        }

        return pizzas;
    }

    public Pizza getPizzaById(long id) {
        sql = "SELECT * FROM pizza WHERE id = " + id;
        List<List<String>> pizzaListList = databaseService.getAllEntitiesBySql(sql);
        List<String> entity = pizzaListList.get(0);

        Pizza pizza = ListConverter.getObjectByListString(entity, new Pizza());
        pizza.setIngredients(ingredientService.getIngredientByPizzaId(pizza.getId()));

        return pizza;
    }

    public void deletePizzaById(long id) {
        if (checkPizzaIdExist(id)) {
            sql = "DELETE FROM ingredient_pizza WHERE pizza_id = " + id;
            databaseService.executeUpdateBySql(sql);
            sql = "DELETE FROM pizza WHERE id = " + id;
            databaseService.executeUpdateBySql(sql);
        } else {
            System.out.println("The passed pizza ID = " + id + " was not found.");
        }
    }

    public String savePizza(String pizzaName, double price, String[] ingredientsId) {
        if (isPizzaNameExist(pizzaName)) {
            return "A pizza by that name already exists.\nTry another one...";
        }

        if (isPizzaIngredientsExist(ingredientsId)) {
            return "Pizza with these ingredients already exists.\nTry another one...";
        }

        sql = "INSERT INTO pizza (name,price) VALUES ('" + pizzaName + "'," + price + ");";
        databaseService.executeUpdateBySql(sql);

        sql = "SELECT id FROM pizza WHERE name='" + pizzaName + "';";
        List<List<String>> idList = databaseService.getAllEntitiesBySql(sql);
        long pizzaId = Long.parseLong(idList.get(0).get(0));

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

    public boolean checkPizzaIdExist(long id) {
        sql = "SELECT * FROM pizza WHERE id = " + id;

        List<List<String>> pizzasListList = databaseService.getAllEntitiesBySql(sql);

        return !pizzasListList.isEmpty();
    }

    public boolean isPizzaNameExist(String pizzaName) {
        sql = "SELECT * FROM pizza WHERE name='" + pizzaName + "'";

        List<List<String>> pizzasListList = databaseService.getAllEntitiesBySql(sql);

        return !pizzasListList.isEmpty();
    }

    public boolean isPizzaIngredientsExist(String[] ingredientsId) {
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

        return !pizzasListList.isEmpty();
    }
}
