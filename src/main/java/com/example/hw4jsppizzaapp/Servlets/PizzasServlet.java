package com.example.hw4jsppizzaapp.Servlets;

import com.example.hw4jsppizzaapp.Models.Pizza;
import com.example.hw4jsppizzaapp.Models.ViewModels.PizzaViewModel;
import com.example.hw4jsppizzaapp.Services.DatabaseManagement.PizzaService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "pizzas", value = "/")
public class PizzasServlet extends HttpServlet {
    private PizzaService pizzaService;

    @Override
    public void init() throws ServletException {
        pizzaService = new PizzaService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        long id = 0;
        String method = request.getParameter("method");
        String idParam = request.getParameter("id");

        if (idParam != null) {
            id = Long.parseLong(idParam);
        }

        if (method != null) {
            if (method.equals("delete")) {
                if (id > 4) {
                    pizzaService.deletePizzaById(id);
                }
            }
        }

        List<Pizza> pizzas = pizzaService.getAllPizzas();

        List<PizzaViewModel> pizzaViewModels = new ArrayList<>();
        for (Pizza pizza : pizzas) {
            PizzaViewModel pizzaViewModel = new PizzaViewModel(pizza);

            pizzaViewModels.add(pizzaViewModel);
        }

        request.setAttribute("method", "");
        request.setAttribute("id", "");
        request.setAttribute("pizzas", pizzaViewModels);
        request.getRequestDispatcher("pizzas.jsp").forward(request, response);
    }
}
