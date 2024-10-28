package com.example.hw4jsppizzaapp.Servlets;

import com.example.hw4jsppizzaapp.Models.Pizza;
import com.example.hw4jsppizzaapp.Services.Helpers.JsonService;
import com.example.hw4jsppizzaapp.Services.DatabaseManagement.PizzaService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

@WebServlet(value = "/cart")
public class CartServlet extends HttpServlet {
    private PizzaService pizzaService;

    @Override
    public void init() throws ServletException {
        pizzaService = new PizzaService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");

        String jsonData = JsonService.getJsonDataString(request.getReader());
        Long pizzaId = JsonService.getPizzaIdFromJsonData(jsonData);

        Pizza pizza = new Pizza();

        if(pizzaId != null) {
            pizza = pizzaService.getPizzaById(pizzaId);
            pizzaId = pizza.getId();
        }

        HttpSession session = request.getSession();
        HashMap<Long, Integer> selectedPizzas = (HashMap<Long, Integer>) session.getAttribute("selectedPizzas");
        Integer totalPizzas = (Integer) session.getAttribute("totalPizzas");
        Double totalPrice = (Double) session.getAttribute("totalPrice");

        if (selectedPizzas == null) {
            selectedPizzas = new HashMap<>();
        }
        if (totalPizzas == null) {
            totalPizzas = 0;
        }
        if (totalPrice == null) {
            totalPrice = 0.0;
        }

        try (PrintWriter out = response.getWriter()) {
            if(pizzaId != null && pizzaId != 0) {
                Integer numberOfPizza = selectedPizzas.get(pizzaId);

                if (numberOfPizza == null) {
                    selectedPizzas.put(pizzaId, 1);
                } else {
                    selectedPizzas.put(pizzaId, ++numberOfPizza);
                }
                totalPrice += pizza.getPrice();

                session.setAttribute("totalPrice", totalPrice);
                session.setAttribute("totalPizzas", ++totalPizzas);
                session.setAttribute("selectedPizzas", selectedPizzas);

                out.println("{\"success\": true}");
            } else {
                out.println("{\"success\": false}");
            }
        }
    }
}
