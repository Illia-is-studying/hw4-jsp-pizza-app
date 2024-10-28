package com.example.hw4jsppizzaapp.Servlets;

import com.example.hw4jsppizzaapp.Models.Ingredient;
import com.example.hw4jsppizzaapp.Models.Pizza;
import com.example.hw4jsppizzaapp.Models.ViewModels.PizzaViewModel;
import com.example.hw4jsppizzaapp.Services.DatabaseManagement.IngredientService;
import com.example.hw4jsppizzaapp.Services.DatabaseManagement.PizzaService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(value = "/pizza-constructor")
public class PizzaConstructorServlet extends HttpServlet {
    private PizzaService pizzaService;
    private IngredientService ingredientService;

    @Override
    public void init() throws ServletException {
        pizzaService = new PizzaService();
        ingredientService = new IngredientService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Ingredient> ingredients = ingredientService.getAllIngredients();

        PizzaViewModel pizzaViewModel = new PizzaViewModel(
                new Pizza(0, 5.0, "", ingredientService.getAllIngredients()));

        request.setAttribute("pizza", pizzaViewModel);
        request.getRequestDispatcher("pizza-constructor.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pizzaName = request.getParameter("pizzaName");
        String[] ingredientsArray = request.getParameterValues("ingredients");
        String resultMessage = "";

        String priceParam = request.getParameter("price");
        try {
            double price = Double.parseDouble(priceParam);

            if(price < 5.0) {
                price = 5.0;
            }

            resultMessage = pizzaService.savePizza(pizzaName, price, ingredientsArray);

            if (resultMessage.equals("ok")) {
                response.sendRedirect(request.getContextPath() + "/");
                return;
            }
        } catch (NumberFormatException ex) {
            resultMessage = "The 'Price $' field is filled incorrectly.\nFor example of correct entry: 7.35";
        }

        PizzaViewModel pizzaViewModel = new PizzaViewModel(
                new Pizza(0, 5.0, pizzaName, ingredientService.getAllIngredients()));

        request.setAttribute("pizza", pizzaViewModel);
        request.setAttribute("errorMessage", resultMessage);
        request.getRequestDispatcher("pizza-constructor.jsp").forward(request, response);
    }
}
