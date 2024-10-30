package com.example.hw4jsppizzaapp.Services.DatabaseManagement;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseService {
    private final static String URL = "jdbc:mysql://localhost:3306/pizzeria";
    private final static String USERNAME = "root";
    private final static String PASSWORD = "root";

    static {
        initializeAllTables();
    }

    private static void initializeAllTables() {
        String createPizzaTable = "CREATE TABLE pizza ("
                + "id INT AUTO_INCREMENT PRIMARY KEY, "
                + "price DECIMAL(10, 2) NOT NULL, "
                + "name VARCHAR(255) NOT NULL);";

        String createIngredientTable = "CREATE TABLE ingredient ("
                + "id INT AUTO_INCREMENT PRIMARY KEY, "
                + "quantity INT, "
                + "name VARCHAR(255) NOT NULL);";

        String createIngredientPizzaTable = "CREATE TABLE ingredient_pizza ("
                + "id INT AUTO_INCREMENT PRIMARY KEY, "
                + "pizza_id INT, "
                + "ingredient_id INT, "
                + "FOREIGN KEY (pizza_id) REFERENCES pizza(id), "
                + "FOREIGN KEY (ingredient_id) REFERENCES ingredient(id));";

        String createUserInfoTable = "CREATE TABLE user_info ("
                + "id INT AUTO_INCREMENT PRIMARY KEY, "
                + "first_name VARCHAR(255) NOT NULL, "
                + "last_name VARCHAR(255) NOT NULL, "
                + "phone_number VARCHAR(20) NOT NULL, "
                + "email VARCHAR(255) NOT NULL, "
                + "delivery_address TEXT NOT NULL);";

        String createOrdersTable = "CREATE TABLE orders ("
                + "id INT AUTO_INCREMENT PRIMARY KEY, "
                + "user_id INT, "
                + "pizza_id INT, "
                + "FOREIGN KEY (user_id) REFERENCES user_info(id), "
                + "FOREIGN KEY (pizza_id) REFERENCES pizza(id));";

        String createToppingsTable = "CREATE TABLE toppings ("
                + "id INT AUTO_INCREMENT PRIMARY KEY, "
                + "ingredient_name VARCHAR(255) NOT NULL, "
                + "price DECIMAL(10, 2) NOT NULL"
                + ");";

        String createOrderToppingsTable = "CREATE TABLE order_toppings ("
                + "order_id INT, "
                + "topping_id INT, "
                + "FOREIGN KEY (order_id) REFERENCES orders(id), "
                + "FOREIGN KEY (topping_id) REFERENCES toppings(id)"
                + ");";

        String fillIngredientTable = "INSERT INTO ingredient (quantity,name) VALUES "
                + "(0,'marinara and cheese'),(3,'olives'),(4,'pineapple'),(3,'ham'),(1,'gorgonzola'),(1,'parmesan'),"
                + "(1,'ricotta'),(3,'artichoke'),(2,'basil'),(2,'mushrooms'),(2,'capers');";

        String fillPizzaTable = "INSERT INTO pizza (name,price) VALUES "
                + "('Margarita',10.99),('Capricciosa',14.50),('Hawaiian',12.70),('Four Cheese',13.25);";

        String fillIngredientPizzaTable = "INSERT INTO ingredient_pizza (pizza_id, ingredient_id) VALUES "
                + "(1, 1),(1, 9),"
                + "(2, 1),(2, 2),(2, 4),(2, 8),(2, 10),"
                + "(3, 1),(3, 2),(3, 3),(3, 4),"
                + "(4, 1),(4, 5),(4, 6),(4, 7);";

        String fillToppingsTable = "INSERT INTO toppings (ingredient_name, price) VALUES "
                + "('More Olives', 1.5), "
                + "('More Capers', 2.5), "
                + "('Extra Cheese', 4.0);";

        tableInitialization(createPizzaTable, fillPizzaTable, "pizza");
        tableInitialization(createIngredientTable, fillIngredientTable, "ingredient");
        tableInitialization(createIngredientPizzaTable, fillIngredientPizzaTable, "ingredient_pizza");
        tableInitialization(createToppingsTable, fillToppingsTable, "toppings");
        tableInitialization(createUserInfoTable, "", "user_info");
        tableInitialization(createOrdersTable, "", "orders");
        tableInitialization(createOrderToppingsTable, "", "order_toppings");
    }

    private static void tableInitialization(String createTableSql, String insertInTableSql, String tableName) {
        String html = "";
        try {
            Class.forName("com.mysql.jdbc.Driver");

            try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
                Statement statement = connection.createStatement();

                statement.executeUpdate(createTableSql);
                System.out.println("The table '" + tableName + "' created successfully!");

                if(!insertInTableSql.isEmpty()) {
                    int rows = statement.executeUpdate(insertInTableSql);
                    System.out.println("The table '" + tableName + "' was filled with " + rows + " rows.");
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<List<String>> getAllEntitiesBySql(String sql) {
        List<List<String>> entities = new ArrayList<>();

        try {
            Class.forName("com.mysql.jdbc.Driver");

            try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
                Statement statement = connection.createStatement();

                ResultSet resultSet = statement.executeQuery(sql);

                while (resultSet.next()) {
                    List<String> entity = new ArrayList<>();

                    int count = 1;
                    while (count != 0) {
                        try {
                            String fieldValue = resultSet.getString(count);
                            entity.add(fieldValue);
                            count++;
                        } catch (SQLException ex) {
                            count = 0;
                        }
                    }

                    entities.add(entity);
                }
            }
        } catch (Exception ex) {
            System.out.println("\n\n\n" + ex.getMessage() + "\n\n\n");
        }

        return entities;
    }

    public void executeUpdateBySql(String sql) {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
                Statement statement = connection.createStatement();

                statement.executeUpdate(sql);
            }
        } catch (Exception ex) {
            System.out.println("\n\n\n" + ex.getMessage() + "\n\n\n");
        }
    }
}
