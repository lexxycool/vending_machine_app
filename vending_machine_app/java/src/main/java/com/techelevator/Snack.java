package com.techelevator;

import java.math.BigDecimal;

public abstract class Snack {
    private String vendingLocation;
    private String nameOfItem;
    private BigDecimal price;
    private int inventoryRemaining;

    public Snack (String vendingLocation, String nameOfItem, BigDecimal price, int inventoryRemaining){
        this.vendingLocation = vendingLocation;
        this.nameOfItem = nameOfItem;
        this.price = price;
        this.inventoryRemaining = inventoryRemaining;
    }

    public String getVendingLocation() {
        return vendingLocation;
    }

    public String getNameOfItem() {
        return nameOfItem;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getInventoryRemaining() {
        return inventoryRemaining;
    }

    public void setInventoryRemaining(int inventoryRemaining) {
        this.inventoryRemaining = inventoryRemaining;
    }

    public abstract String getMessage();


}
