package com.techelevator;

import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class SnackTest {

    @Test
    public void getInventoryRemaining() {
        Snack snack = Mockito.mock(Snack.class, Mockito.CALLS_REAL_METHODS);
        int expected = 0;
        assertEquals(expected, snack.getInventoryRemaining());
    }
    @Test
    public void setInventoryRemaining_test(){
        Snack snack = Mockito.mock(Snack.class, Mockito.CALLS_REAL_METHODS);
        snack.setInventoryRemaining(10);
        assertEquals(10, snack.getInventoryRemaining());


    }
}