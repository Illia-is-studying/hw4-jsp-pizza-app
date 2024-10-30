package com.example.hw4jsppizzaapp.Servlets;

import com.example.hw4jsppizzaapp.Models.Pizza;
import com.example.hw4jsppizzaapp.Models.Topping;
import com.example.hw4jsppizzaapp.Models.ViewModels.PizzaViewModel;
import com.example.hw4jsppizzaapp.Services.DatabaseManagement.PizzaService;
import com.example.hw4jsppizzaapp.Services.DatabaseManagement.ToppingService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(value = "/toppings")
public class ToppingServlet extends HttpServlet {
    private PizzaService pizzaService;
    private ToppingService toppingService;

    @Override
    public void init() throws ServletException {
        pizzaService = new PizzaService();
        toppingService = new ToppingService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        ArrayList<Long> selectedPizzas = (ArrayList<Long>) session.getAttribute("selectedPizzas");

        String action = request.getParameter("action");
        if(action != null && selectedPizzas != null && action.equals("delete")) {
            String idParam = request.getParameter("id");

            if(idParam != null) {
                long id = Long.parseLong(idParam);

                Pizza pizza = pizzaService.getPizzaById(id);

                if(pizza.getId() != 0) {
                    selectedPizzas.remove(id);

                    int totalPizzas = (Integer) session.getAttribute("totalPizzas");
                    double totalPrice = (Double) session.getAttribute("totalPrice");
                    totalPrice -= pizza.getPrice();

                    session.setAttribute("totalPizzas", --totalPizzas);
                    session.setAttribute("totalPrice", totalPrice);
                    session.setAttribute("selectedPizzas", selectedPizzas);
                }
            }
        }

        if (selectedPizzas == null || selectedPizzas.isEmpty()) {
            response.sendRedirect(request.getContextPath());
            return;
        }

        List<PizzaViewModel> pizzaViewModels = new ArrayList<>();
        for (int i = 0; i < selectedPizzas.size(); i++) {
            Pizza pizza = pizzaService.getPizzaById(selectedPizzas.get(i));

            PizzaViewModel pizzaViewModel = new PizzaViewModel(pizza, i);

            pizzaViewModels.add(pizzaViewModel);
        }

        List<Topping> toppings = toppingService.getAllToppings();

        request.setAttribute("toppings", toppings);
        request.setAttribute("pizzas", pizzaViewModels);
        request.getRequestDispatcher("toppings.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        int pizzaCount = (Integer) session.getAttribute("totalPizzas");

        List<String[]> toppingIdsList = new ArrayList<>();

        for (int i = 0; i < pizzaCount; i++) {
            String[] toppingIds = request.getParameterValues("pizza" + i);

            toppingIdsList.add(toppingIds);
        }

        session.setAttribute("toppingIds", toppingIdsList);
        response.sendRedirect("cart");
    }
}
