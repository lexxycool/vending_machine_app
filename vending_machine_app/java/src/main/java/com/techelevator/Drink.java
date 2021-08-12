package com.techelevator;

import com.techelevator.Snack;

import java.math.BigDecimal;

public class Drink extends Snack {
    public Drink(String vendingLocation, String nameOfItem, BigDecimal price, int inventoryRemaining) {
        super(vendingLocation, nameOfItem, price,inventoryRemaining);
    }

    @Override
    public String getMessage() {
        return "Glug Glug, Yum!";
    }
}
