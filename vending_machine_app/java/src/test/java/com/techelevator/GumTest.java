package com.techelevator;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class GumTest {

    @Test

    public void get_message_returns_chew_chew_yum() {
        Gum gum = new Gum("D1", "gum", new BigDecimal("1.02"), 7);

        Assert.assertEquals("Chew Chew, Yum!", gum.getMessage());
    }
}