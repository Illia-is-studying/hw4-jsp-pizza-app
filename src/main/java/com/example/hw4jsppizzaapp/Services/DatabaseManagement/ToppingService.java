package com.example.hw4jsppizzaapp.Services.DatabaseManagement;

import com.example.hw4jsppizzaapp.Models.Topping;
import com.example.hw4jsppizzaapp.Services.Helpers.ListConverter;

import java.util.ArrayList;
import java.util.List;

public class ToppingService {
    private final DatabaseService databaseService;
    private String sql;

    public ToppingService() {
        this.databaseService = new DatabaseService();
    }

    public List<Topping> getAllToppings() {
        sql = "SELECT * FROM toppings";
        List<Topping> toppings = new ArrayList<>();

        List<List<String>> entities = databaseService.getAllEntitiesBySql(sql);
        for (List<String> entity : entities) {
            Topping topping = ListConverter.getObjectByListString(entity, new Topping());

            toppings.add(ListConverter.getObjectByListString(entity, topping));
        }

        return toppings;
    }

    public Topping getToppingById(long id) {
        sql = "SELECT * FROM toppings WHERE id = " + id;
        List<List<String>> entities = databaseService.getAllEntitiesBySql(sql);

        Topping topping = new Topping();

        try {
            List<String> entity = entities.get(0);
            topping = ListConverter.getObjectByListString(entity, topping);
        } catch (IndexOutOfBoundsException ex) {
            System.out.println(ex.getMessage());
        }

        return topping;
    }
}
