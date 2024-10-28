package com.example.hw4jsppizzaapp.Services.Helpers;

import java.io.BufferedReader;
import java.io.IOException;

public class JsonService {
    public static String getJsonDataString(BufferedReader requestReader) throws IOException {
        StringBuilder jsonBuilder = new StringBuilder();
        try (BufferedReader reader = requestReader) {
            String inputLine;
            while ((inputLine = reader.readLine()) != null) {
                jsonBuilder.append(inputLine);
            }
        }
        return jsonBuilder.toString();
    }

    public static Long getPizzaIdFromJsonData(String jsonData) {
        if (jsonData.contains("pizzaId")) {
            int start = jsonData.indexOf(":") + 1;
            int end = jsonData.indexOf("}");
            String pizzaIdLine = jsonData.substring(start, end).trim();

            try {
               return Long.parseLong(pizzaIdLine);
            } catch (NumberFormatException ex) {
                ex.printStackTrace();
            }
        }

        return null;
    }
}
