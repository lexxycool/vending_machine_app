package com.techelevator;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class CandyTest {

    @Test

    public void get_message_test_returns_munch_munch_yum() {
        Candy candy = new Candy("B1", "candy", new BigDecimal(10.00), 10);


        Assert.assertEquals("Munch Munch, Yum!", candy.getMessage());
    }

}