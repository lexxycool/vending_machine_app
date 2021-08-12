package com.techelevator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.*;

public class VendingMachineCLITest {
    @Test
    public void import_Inventory_Pass_In_Inventory_txt_return_List(){
        //Arrange
        VendingMachineCLI vendingMachineCLI = new VendingMachineCLI();

        //Act
        File inventoryFile = new File("C:\\Users\\Student\\Workspace\\mod1-capstone-green-t4\\java\\src\\main\\resources\\Inventory.txt");
        List<Snack> actual = vendingMachineCLI.importInventory(inventoryFile);
        List<Snack> expected = new ArrayList<>();
        Chip chip = new Chip("A1","Potato Crisps", new BigDecimal("3.05"),5);
        Candy moonPie = new Candy("B1","Moonpie",new BigDecimal("1.80"),5);
        Candy cowTales = new Candy("B2","Cowtales",new BigDecimal("1.50"),5);
        Drink cola = new Drink("C1","Cola",new BigDecimal("1.25"),5);
        expected.add(chip);
        expected.add(moonPie);
        expected.add(cowTales);
        expected.add(cola);

        //Assert
        for(int i = 0; i < actual.size();i++) {
            Assert.assertEquals(expected.get(i).getNameOfItem(), actual.get(i).getNameOfItem());
            Assert.assertEquals(expected.get(i).getPrice(),actual.get(i).getPrice());
            Assert.assertEquals(expected.get(i).getVendingLocation(),actual.get(i).getVendingLocation());
            Assert.assertEquals(expected.get(i).getInventoryRemaining(),actual.get(i).getInventoryRemaining());
            Assert.assertEquals(expected.get(i).getMessage(),actual.get(i).getMessage());
        }
    }

    @Test
    public void finishTransaction_passin_10_return_0() throws IOException {
        VendingMachineCLI vendingMachineCLI = new VendingMachineCLI();
        BigDecimal numberToPassIn = new BigDecimal(10);

        BigDecimal actual = vendingMachineCLI.finishTransaction(numberToPassIn);
        BigDecimal expected = new BigDecimal(0);
        Assert.assertEquals(expected, actual);

    }

    @Test
    public void finishTransaction_passin_1000_return_0() throws IOException {
        VendingMachineCLI vendingMachineCLI = new VendingMachineCLI();
        BigDecimal numberToPassIn = new BigDecimal(1000);

        BigDecimal actual = vendingMachineCLI.finishTransaction(numberToPassIn);
        BigDecimal expected = new BigDecimal(0);
        Assert.assertEquals(expected, actual);

    }

    @Test
    public void feedMoney_test_Pass_in_exit() throws IOException {
        VendingMachineCLI vendingMachineCLI = new VendingMachineCLI();

        ByteArrayInputStream in = new ByteArrayInputStream("exit".getBytes());
        System.setIn(in);

        BigDecimal numberToPassIn = new BigDecimal(10);

        BigDecimal actual = vendingMachineCLI.feedMoney(numberToPassIn);
        BigDecimal expected = new BigDecimal(10);
        Assert.assertEquals(expected, actual);

    }
    @Test
    public void feedMoney_test_Pass_in_int_return_fail() throws IOException {
        VendingMachineCLI vendingMachineCLI = new VendingMachineCLI();

        ByteArrayInputStream in = new ByteArrayInputStream("exit".getBytes());
        System.setIn(in);

        BigDecimal numberToPassIn = new BigDecimal(10);


        BigDecimal actual = vendingMachineCLI.feedMoney(numberToPassIn);
        int expected = 10;
        Assert.assertNotEquals(expected, actual);

    }
    @Test
    public void feedMoney_test_Pass_in_10_return_10() throws IOException {
        VendingMachineCLI vendingMachineCLI = new VendingMachineCLI();


        InputStream exit = new ByteArrayInputStream("exit".getBytes());




        System.setIn(exit);

        BigDecimal numberToPassIn = new BigDecimal(10);


        BigDecimal actual = vendingMachineCLI.feedMoney(numberToPassIn);
        BigDecimal expected = new BigDecimal(10);
        Assert.assertEquals(expected, actual);

    }

    @Test
    public void feedMoney_test_Pass_in_5_return_5() throws IOException {
        VendingMachineCLI vendingMachineCLI = new VendingMachineCLI();


        InputStream exit = new ByteArrayInputStream("exit".getBytes());


        System.setIn(exit);

        BigDecimal numberToPassIn = new BigDecimal(15);


        BigDecimal actual = vendingMachineCLI.feedMoney(numberToPassIn);
        BigDecimal expected = new BigDecimal(15);
        Assert.assertEquals(expected, actual);

    }


}