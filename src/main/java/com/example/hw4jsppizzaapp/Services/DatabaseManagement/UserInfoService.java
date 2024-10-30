package com.example.hw4jsppizzaapp.Services.DatabaseManagement;

import java.util.List;

public class UserInfoService {
    private final DatabaseService databaseService;
    private String sql;

    public UserInfoService() {
        this.databaseService = new DatabaseService();
    }

    public void save(String firstName, String lastName, String email, String address, String phone) {
        sql = "INSERT INTO user_info (first_name,last_name,email,delivery_address,phone_number) VALUES " +
                "('" + firstName + "','" + lastName + "','" + email + "','" + address + "','" + phone + "');";

        databaseService.executeUpdateBySql(sql);
    }

    public long getUserIdByEmail(String email) {
        sql = "SELECT id FROM user_info WHERE email = '" + email + "'";

        List<List<String>> entities = databaseService.getAllEntitiesBySql(sql);
        long id = 0;

        if (!entities.isEmpty()) {
            id = Long.parseLong(entities.get(0).get(0));
        }

        return id;
    }
}
