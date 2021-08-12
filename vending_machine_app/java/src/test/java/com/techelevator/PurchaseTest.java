package com.techelevator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.Assert.*;

public class PurchaseTest  {



    @Test
    public void update_balance_of_snackList_returns_balance() {

        List<Snack> snack = new ArrayList<>();
        Chip chip = new Chip("A1","Potato Crisps", new BigDecimal("3.05"),5);
        Candy moonPie = new Candy("B1","Moonpie",new BigDecimal("1.80"),5);
        Candy cowTales = new Candy("B2","Cowtales",new BigDecimal("1.50"),5);
        Drink cola = new Drink("C1","Cola",new BigDecimal("1.25"),5);
        snack.add(chip);
        snack.add(moonPie);
        snack.add(cowTales);
        snack.add(cola);

        BigDecimal actual = Purchase.updateBalance(new BigDecimal(20), "A1", snack);
        BigDecimal expected = new BigDecimal(16.95);

        MathContext mathContext = new MathContext(4);
        Assert.assertEquals(expected.round(mathContext), actual);
    }

    @Test
    public void getInventory_return_Inventory(){
        List<Snack> snack = new ArrayList<>();
        Chip chip = new Chip("A1","Potato Crisps", new BigDecimal("3.05"),5);
        Candy moonPie = new Candy("B1","Moonpie",new BigDecimal("1.80"),5);
        Candy cowTales = new Candy("B2","Cowtales",new BigDecimal("1.50"),5);
        Drink cola = new Drink("C1","Cola",new BigDecimal("1.25"),5);
        snack.add(chip);
        snack.add(moonPie);
        snack.add(cowTales);
        snack.add(cola);

        int actual = Purchase.getInventory("C1", snack);
        int expected  = 5;
        Assert.assertEquals(expected, actual);

    }
    @Test
    public void updateInventory_return_list_candy() throws IOException {
        List<Snack> snack = new ArrayList<>();
        Chip chip = new Chip("A1","Potato Crisps", new BigDecimal("3.05"),5);
        Candy moonPie = new Candy("B1","Moonpie",new BigDecimal("1.80"),5);
        Candy cowTales = new Candy("B2","Cowtales",new BigDecimal("1.50"),5);
        Drink cola = new Drink("C1","Cola",new BigDecimal("1.25"),5);
        snack.add(chip);
        snack.add(moonPie);
        snack.add(cowTales);
        snack.add(cola);

        List<Snack> actual = Purchase.updateInventory("B1", snack, new BigDecimal(10));

        List<Snack> expected = new ArrayList<>();
        Chip chips = new Chip("A1","Potato Crisps", new BigDecimal("3.05"),5);
        Candy moonPies = new Candy("B1","Moonpie",new BigDecimal("1.80"),5);
        Candy cowTaless = new Candy("B2","Cowtales",new BigDecimal("1.50"),4);
        Drink colas = new Drink("C1","Cola",new BigDecimal("1.25"),5);
        expected.add(chips);
        expected.add(moonPies);
        expected.add(cowTaless);
        expected.add(colas);
        Assert.assertEquals(expected.get(0).getMessage(), actual.get(0).getMessage());
    }
    @Test
    public void updateInventory_return_list_chip() throws IOException {
        List<Snack> snack = new ArrayList<>();
        Chip chip = new Chip("A1","Potato Crisps", new BigDecimal("3.05"),5);
        Candy moonPie = new Candy("B1","Moonpie",new BigDecimal("1.80"),5);
        Candy cowTales = new Candy("B2","Cowtales",new BigDecimal("1.50"),5);
        Drink cola = new Drink("C1","Cola",new BigDecimal("1.25"),5);
        snack.add(chip);
        snack.add(moonPie);
        snack.add(cowTales);
        snack.add(cola);

        List<Snack> actual = Purchase.updateInventory("A1", snack, new BigDecimal(10));

        List<Snack> expected = new ArrayList<>();
        Chip chips = new Chip("A1","Potato Crisps", new BigDecimal("3.05"),5);
        Candy moonPies = new Candy("B1","Moonpie",new BigDecimal("1.80"),5);
        Candy cowTaless = new Candy("B2","Cowtales",new BigDecimal("1.50"),4);
        Drink colas = new Drink("C1","Cola",new BigDecimal("1.25"),5);
        expected.add(chips);
        expected.add(moonPies);
        expected.add(cowTaless);
        expected.add(colas);
        Assert.assertEquals(expected.get(0).getMessage(), actual.get(0).getMessage());
    }

    @Test
    public void updateInventory_return_list_gum() throws IOException {
        List<Snack> snack = new ArrayList<>();
        Chip chip = new Chip("A1","Potato Crisps", new BigDecimal("3.05"),5);
        Candy moonPie = new Candy("B1","Moonpie",new BigDecimal("1.80"),5);
        Gum cowTales = new Gum("D1","Mint",new BigDecimal("1.50"),5);
        Drink cola = new Drink("C1","Cola",new BigDecimal("1.25"),5);
        snack.add(chip);
        snack.add(moonPie);
        snack.add(cowTales);
        snack.add(cola);

        List<Snack> actual = Purchase.updateInventory("D1", snack, new BigDecimal(10));

        List<Snack> expected = new ArrayList<>();
        Chip chips = new Chip("A1","Potato Crisps", new BigDecimal("3.05"),5);
        Candy moonPies = new Candy("B1","Moonpie",new BigDecimal("1.80"),5);
        Gum cowTaless = new Gum("D1","Mint",new BigDecimal("1.50"),4);
        Drink colas = new Drink("C1","Cola",new BigDecimal("1.25"),5);
        expected.add(chips);
        expected.add(moonPies);
        expected.add(cowTaless);
        expected.add(colas);
        Assert.assertEquals(expected.get(0).getMessage(), actual.get(0).getMessage());
    }
    @Test
    public void updateInventory_return_list_drink() throws IOException {
        List<Snack> snack = new ArrayList<>();
        Chip chip = new Chip("A1","Potato Crisps", new BigDecimal("3.05"),5);
        Candy moonPie = new Candy("B1","Moonpie",new BigDecimal("1.80"),5);
        Candy cowTales = new Candy("B2","Cowtales",new BigDecimal("1.50"),5);
        Drink cola = new Drink("C1","Cola",new BigDecimal("1.25"),5);
        snack.add(chip);
        snack.add(moonPie);
        snack.add(cowTales);
        snack.add(cola);

        List<Snack> actual = Purchase.updateInventory("C1", snack, new BigDecimal(10));

        List<Snack> expected = new ArrayList<>();
        Chip chips = new Chip("A1","Potato Crisps", new BigDecimal("3.05"),5);
        Candy moonPies = new Candy("B1","Moonpie",new BigDecimal("1.80"),5);
        Candy cowTaless = new Candy("B2","Cowtales",new BigDecimal("1.50"),4);
        Drink colas = new Drink("C1","Cola",new BigDecimal("1.25"),5);
        expected.add(chips);
        expected.add(moonPies);
        expected.add(cowTaless);
        expected.add(colas);
        Assert.assertEquals(expected.get(0).getMessage(), actual.get(0).getMessage());
    }

}


