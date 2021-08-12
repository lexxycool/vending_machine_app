package com.techelevator;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class ChipTest {

    @Test
        public void get_message_test_returns_crunch_crunch_yum() {
        Chip chip = new Chip("A1", "chip", new BigDecimal("3.05"), 12);

        Assert.assertEquals("Crunch Crunch, Yum!", chip.getMessage());
    }
}