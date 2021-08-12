package com.techelevator;

import com.techelevator.Snack;

import java.math.BigDecimal;

public class Candy extends Snack {

    public Candy(String vendingLocation, String nameOfItem, BigDecimal price, int inventoryRemaining) {
        super(vendingLocation, nameOfItem, price, inventoryRemaining);
    }

    @Override
    public String getMessage() {
        return "Munch Munch, Yum!";
    }
}
