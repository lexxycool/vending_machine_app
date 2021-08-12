package com.techelevator;

import com.techelevator.Snack;

import java.math.BigDecimal;

public class Gum extends Snack {
    public Gum(String vendingLocation, String nameOfItem, BigDecimal price, int inventoryRemaining) {
        super(vendingLocation, nameOfItem, price, inventoryRemaining);
    }

    @Override
    public String getMessage() {
        return "Chew Chew, Yum!";
    }
}
