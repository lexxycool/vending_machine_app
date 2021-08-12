package com.techelevator;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class DrinkTest {
    @Test

        public void get_message_returns_glug_glug_yum() {
        Drink drink = new Drink("C1", "drink", new BigDecimal("1.25"), 8);

        Assert.assertEquals("Glug Glug, Yum!", drink.getMessage());
    }

}