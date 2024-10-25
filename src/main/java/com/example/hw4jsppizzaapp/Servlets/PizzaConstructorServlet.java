package com.example.hw4jsppizzaapp.Servlets;

import com.example.hw4jsppizzaapp.Models.Ingredient;
import com.example.hw4jsppizzaapp.Models.Pizza;
import com.example.hw4jsppizzaapp.Models.ViewModels.PizzaViewModel;
import com.example.hw4jsppizzaapp.Services.IngredientService;
import com.example.hw4jsppizzaapp.Services.PizzaService;
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
        String errorMessage = (String) request.getAttribute("errorMessage");
        List<Ingredient> ingredients = ingredientService.getAllIngredients();

        PizzaViewModel pizzaViewModel = new PizzaViewModel(
                new Pizza(0, "",  ingredientService.getAllIngredients()));

        request.setAttribute("pizza", pizzaViewModel);
        request.setAttribute("errorMessage", errorMessage);
        request.getRequestDispatcher("pizza-constructor.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pizzaName = request.getParameter("pizzaName");
        String[] ingredientsArray = request.getParameterValues("ingredients");

        String resultMessage = pizzaService.savePizza(pizzaName, ingredientsArray);

        if(resultMessage.equals("ok")) {
            response.sendRedirect(request.getContextPath() + "/");
            return;
        }

        PizzaViewModel pizzaViewModel = new PizzaViewModel(
                new Pizza(0, "", ingredientService.getAllIngredients()));

        request.setAttribute("pizza", pizzaViewModel);
        request.setAttribute("errorMessage", resultMessage);
        request.getRequestDispatcher("pizza-constructor.jsp").forward(request, response);
    }
}
