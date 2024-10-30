package com.example.hw4jsppizzaapp.Services.DatabaseManagement;

import java.util.ArrayList;
import java.util.List;

public class OrderService {
    private final DatabaseService databaseService;
    private String sql;

    public OrderService() {
        this.databaseService = new DatabaseService();
    }

    public void saveByPizzaIds(ArrayList<Long> pizzaIds, long userId) {
        StringBuilder insertIntoOrdersSql =
                new StringBuilder("INSERT INTO orders (user_id,pizza_id) VALUES ");

        for (Long pizzaId : pizzaIds) {
            insertIntoOrdersSql.append("(").append(userId).append(",")
                    .append(pizzaId).append("),");
        }
        sql = insertIntoOrdersSql.toString();
        sql = sql.substring(0, sql.length() - 1) + ";";

        databaseService.executeUpdateBySql(sql);
    }

    public List<Long> getOrderIdByPizzaIdAndUserId(long pizzaId, long userId) {
        sql = "SELECT id FROM ORDERS WHERE pizza_id = " + pizzaId + " AND user_id = " + userId + ";";
        List<List<String>> entities = databaseService.getAllEntitiesBySql(sql);
        List<Long> orderIds = new ArrayList<>();

        if (!entities.isEmpty()) {
            for (List<String> entity : entities) {
                orderIds.add(Long.parseLong(entity.get(0)));
            }
        }

        return orderIds;
    }

    public void saveToOrderTopping(long orderId, long toppingId) {
        sql = "INSERT INTO order_toppings (order_id, topping_id) VALUES ("
                + orderId + "," + toppingId + ");";

        databaseService.executeUpdateBySql(sql);
    }
}
