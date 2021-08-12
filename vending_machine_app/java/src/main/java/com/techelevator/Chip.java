package com.techelevator;

import com.techelevator.Snack;

import java.math.BigDecimal;

public class Chip extends Snack {
    public Chip(String vendingLocation, String nameOfItem, BigDecimal price, int inventoryRemaining) {
        super(vendingLocation, nameOfItem, price, inventoryRemaining);
    }

    @Override
    public String getMessage() {
        return "Crunch Crunch, Yum!";
    }
}
