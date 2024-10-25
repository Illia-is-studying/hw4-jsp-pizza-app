package com.example.hw4jsppizzaapp.Services;

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
                + "ingredient_id INT, "
                + "order_id INT, "
                + "quantity INT NOT NULL, "
                + "FOREIGN KEY (ingredient_id) REFERENCES ingredient(id), "
                + "FOREIGN KEY (order_id) REFERENCES orders (id));";

        String fillIngredientTable = "INSERT INTO ingredient (quantity,name) VALUES "
                + "(2,'basil'),(4,'pineapple'),(3,'ham'),(1,'gorgonzola'),(1,'parmesan'),"
                + "(1,'ricotta'),(3,'artichoke'),(3,'olives'),(2,'mushrooms'),(2,'capers');";

        String fillPizzaTable = "INSERT INTO pizza (name) VALUES "
                + "('Margarita'),('Capricciosa'),('Four Cheese'),('Hawaiian');";

        String fillIngredientPizzaTable = "INSERT INTO ingredient_pizza (pizza_id, ingredient_id) VALUES "
                + "(1, 1),"
                + "(2, 3),(2, 7),(2, 8),(2, 9),"
                + "(3, 4),(3, 5),(3, 6),"
                + "(4, 2),(4, 3),(4, 8);";

        tableInitialization(createPizzaTable, fillPizzaTable, "pizza");
        tableInitialization(createIngredientTable, fillIngredientTable, "ingredient");
        tableInitialization(createIngredientPizzaTable, fillIngredientPizzaTable, "ingredient_pizza");
        tableInitialization(createUserInfoTable, "", "user_info");
        tableInitialization(createOrdersTable, "", "orders");
        tableInitialization(createToppingsTable, "", "createToppingsTable");
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

    public List<String> getStringsBySql(String sql) {
        List<String> strings = new ArrayList<>();

        try {
            Class.forName("com.mysql.jdbc.Driver");

            try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
                Statement statement = connection.createStatement();

                ResultSet resultSet = statement.executeQuery(sql);

                while (resultSet.next()) {
                    strings.add(resultSet.getString(1));
                }
            }
        } catch (Exception ex) {
            System.out.println("\n\n\n" + ex.getMessage() + "\n\n\n");
        }

        return strings;
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
