package com.example.hw4jsppizzaapp.Models.ViewModels;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CartViewModel {
    private String pizzaName;
    private String plusToppings;
    private double totalPrice;
}
