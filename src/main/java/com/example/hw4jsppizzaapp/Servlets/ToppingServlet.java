package com.example.hw4jsppizzaapp.Servlets;

import com.example.hw4jsppizzaapp.Models.Pizza;
import com.example.hw4jsppizzaapp.Models.ViewModels.PizzaViewModel;
import com.example.hw4jsppizzaapp.Services.DatabaseManagement.PizzaService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(value = "/toppings")
public class ToppingServlet extends HttpServlet {
    private PizzaService pizzaService;

    @Override
    public void init() throws ServletException {
        pizzaService = new PizzaService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        HashMap<Long, Integer> selectedPizzas = (HashMap<Long, Integer>) session.getAttribute("selectedPizzas");

        if(selectedPizzas == null) {
            response.sendRedirect(request.getContextPath());
            return;
        }

        List<PizzaViewModel> pizzaViewModels = new ArrayList<>();
        int count = 0;
        for (Map.Entry<Long, Integer> entry : selectedPizzas.entrySet()) {
            for (int i = 0; i < entry.getValue(); i++) {
                Pizza pizza = pizzaService.getPizzaById(entry.getKey());

                PizzaViewModel pizzaViewModel = new PizzaViewModel(pizza, ++count);

                pizzaViewModels.add(pizzaViewModel);
            }
        }

        request.setAttribute("pizzas", pizzaViewModels);
        request.getRequestDispatcher("toppings.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Для каждого pizza'modelID' записывать результаты из checkbox.
    }
}
