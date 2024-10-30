package com.example.hw4jsppizzaapp.Servlets;

import com.example.hw4jsppizzaapp.Models.Pizza;
import com.example.hw4jsppizzaapp.Models.Topping;
import com.example.hw4jsppizzaapp.Models.ViewModels.CartViewModel;
import com.example.hw4jsppizzaapp.Services.DatabaseManagement.OrderService;
import com.example.hw4jsppizzaapp.Services.DatabaseManagement.ToppingService;
import com.example.hw4jsppizzaapp.Services.DatabaseManagement.UserInfoService;
import com.example.hw4jsppizzaapp.Services.Helpers.ArrayConverter;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@WebServlet(value = "/cart")
public class CartServlet extends HttpServlet {
    private PizzaService pizzaService;
    private ToppingService toppingService;
    private UserInfoService userInfoService;
    private OrderService orderService;

    @Override
    public void init() throws ServletException {
        pizzaService = new PizzaService();
        toppingService = new ToppingService();
        userInfoService = new UserInfoService();
        orderService = new OrderService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        ArrayList<Long> selectedPizzas = (ArrayList<Long>) session.getAttribute("selectedPizzas");
        List<String[]> toppings = (List<String[]>) session.getAttribute("toppingIds");

        if (selectedPizzas == null) {
            response.sendRedirect(request.getContextPath());
            return;
        }

        List<CartViewModel> cartViewModels = new ArrayList<>();
        Double totalPizzasPrice = 0.0;

        if(toppings != null) {
            for(int i = 0; i < selectedPizzas.size(); i++) {
                CartViewModel cartViewModel = new CartViewModel();

                Pizza pizza = pizzaService.getPizzaById(selectedPizzas.get(i));
                double totalPrice = pizza.getPrice();
                String plusToppingString = "no extra toppings";

                String[] toppingIdLines = toppings.get(i);

                if(toppingIdLines != null) {
                    Long[] toppingIds = ArrayConverter.convertStringsToLongs(toppings.get(i));
                    StringBuilder plusTopping = new StringBuilder();

                    for (Long toppingId : toppingIds) {
                        if (toppingId != 0) {
                            Topping topping = toppingService.getToppingById(toppingId);
                            plusTopping.append("+").append(topping.getName().toLowerCase()).append(", ");

                            totalPrice += topping.getPrice();
                        }
                    }

                    plusToppingString = plusTopping.toString();
                    plusToppingString = plusToppingString.substring(0, plusToppingString.length() - 2);
                }

                cartViewModels.add(new CartViewModel(pizza.getName(), plusToppingString, totalPrice));
                totalPizzasPrice += totalPrice;
            }
        } else {
            for (Long selectedPizza : selectedPizzas) {
                CartViewModel cartViewModel = new CartViewModel();

                Pizza pizza = pizzaService.getPizzaById(selectedPizza);
                String plusToppingString = "no extra toppings";

                cartViewModels.add(new CartViewModel(pizza.getName(), plusToppingString, pizza.getPrice()));
                totalPizzasPrice += pizza.getPrice();
            }
        }

        request.setAttribute("totalPizzasPrice", totalPizzasPrice);
        request.setAttribute("cartViewModels", cartViewModels);
        request.getRequestDispatcher("/cart.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        ArrayList<Long> selectedPizzas = (ArrayList<Long>) session.getAttribute("selectedPizzas");
        List<String[]> toppings = (List<String[]>) session.getAttribute("toppingIds");

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");

        userInfoService.save(firstName, lastName, email, address, phone);
        long userId = userInfoService.getUserIdByEmail(email);

        orderService.saveByPizzaIds(selectedPizzas, userId);

        selectedPizzas = new ArrayList<>(new HashSet<>(selectedPizzas));

        int toppingCounter = 0;
        for(int i = 0; i < selectedPizzas.size(); i++) {
            List<Long> orderIds = orderService.getOrderIdByPizzaIdAndUserId(selectedPizzas.get(i), userId);

            for (int j = 0; j < orderIds.size(); j++, toppingCounter++) {
                Long orderId = orderIds.get(j);

                String[] toppingIdLines = toppings.get(toppingCounter);

                for (String toppingIdLine : toppingIdLines) {
                    long toppingId = Long.parseLong(toppingIdLine);

                    orderService.saveToOrderTopping(orderId, toppingId);
                }
            }
        }

        session.setAttribute("selectedPizzas", null);
        session.setAttribute("toppingIds", null);
        session.setAttribute("totalPrice", null);
        session.setAttribute("totalPizzas", null);

        response.sendRedirect("order-shipped");
    }
}
