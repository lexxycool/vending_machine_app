package com.techelevator;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Purchase {
    private static  BigDecimal balance;
    private List<Snack> snackList;
    private String choice;
    private static Chip chip;
    private static Drink drink;
    private static Gum gum;
    private static Candy candy;
//    protected static Map<String,Integer> snackMap = new HashMap<>();
    private Scanner fileReader = new Scanner("src/main/resources/Hidden.txt");


    public Purchase(BigDecimal balance, List<Snack> snackList, String choice){
        this.balance = balance;
        this.snackList = snackList;
        this.choice = choice;
        while (fileReader.hasNextLine()){
            String currentLine = fileReader.nextLine();
            String[] array = currentLine.split("\\|");
//            snackMap.put(array[0],Integer.parseInt(array[1]));
        }
    }
    
    public static BigDecimal updateBalance(BigDecimal balance, String choice, List<Snack> snackList){
        if(isPurchaseable(balance, snackList, choice)) {
            balance = balance.subtract(getPrice(choice, snackList));
        }
        else{
            System.out.println("Not enough funds");
        }
        return balance;
    }
    public static boolean isPurchaseable(BigDecimal balance, List<Snack> snackList, String choice){
        int inventory = 0;
        String button ="";
        BigDecimal price = new BigDecimal(0);
        for(Snack location : snackList){
            if(location.getVendingLocation().equals(choice)){
                inventory = location.getInventoryRemaining();
                price = location.getPrice();
                button = location.getVendingLocation();
            }
        }

        if(!choice.equals(button)){
            System.out.println("No snack at that location");
        }
        else if(balance.compareTo(price) == -1){
            System.out.println("Not enough funds");
        }
        else if(inventory == 0){
            System.out.println("Out of stock");
        }
        return (balance.compareTo(price) > -1 && inventory > 0);
    }
    public static BigDecimal getPrice(String choice, List<Snack> snackList) {
       
        BigDecimal price = new BigDecimal(0);
        for (Snack location : snackList) {
            if (location.getVendingLocation().equals(choice)) {
                
                price = location.getPrice();
            }
        }
        return price;
    }
    public static int getInventory(String choice,List<Snack> snackList){
        int inventory  = 0;
        for(Snack location : snackList){
            if(location.getVendingLocation().equals(choice)){
                inventory = location.getInventoryRemaining();

            }
        }
        return inventory;
    }
    public static List<Snack> updateInventory(String choice, List<Snack> snackList, BigDecimal balance) throws IOException {
        Logging logger = new Logging("src/main/resources/Log.txt");

        if (getInventory(choice, snackList)>0) {

            for (int i = 0; i<snackList.size();i++){
                if (snackList.get(i).getVendingLocation().equals(choice)){
                    if (getInventory(choice, snackList) > 0){
                   String type = snackList.get(i).getClass().toString().toLowerCase();
                        type = type.substring(23);
                   switch(type) {
                       case "chip":
                           chip = (Chip) snackList.get(i);
                           chip.setInventoryRemaining(chip.getInventoryRemaining()-1);
                           snackList.set(i, chip);
                           System.out.println(chip.getMessage());
                           logger.write(LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss: a"))
                                   + " " + chip.getNameOfItem() + " " + chip.getVendingLocation()+ " $" + (balance.add(chip.getPrice())) + " $" + balance);
//                           if (snackMap.containsKey(chip.getNameOfItem())){
//                               Integer count = snackMap.get(chip.getNameOfItem());
//                               snackMap.put(chip.getNameOfItem(),snackMap.get(chip.getNameOfItem())+1);
//                           }
                           break;
                       case "candy":
                           candy = (Candy) snackList.get(i);
                           candy.setInventoryRemaining(candy.getInventoryRemaining()-1);
                           snackList.set(i, candy);
                           System.out.println(candy.getMessage());
                           logger.write(LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss: a"))
                                   + " " + candy.getNameOfItem() + " " + candy.getVendingLocation()+ " $" + (balance.add(candy.getPrice())) + " $" + balance);
//                           if (snackMap.containsKey(candy.getNameOfItem())){
//                               Integer count = snackMap.get(candy.getNameOfItem());
//                               snackMap.put(candy.getNameOfItem(),snackMap.get(candy.getNameOfItem())+1);
//                           }
                           break;
                       case "gum":
                           gum = (Gum) snackList.get(i);
                           gum.setInventoryRemaining(gum.getInventoryRemaining()-1);
                           snackList.set(i, gum);
                           System.out.println(gum.getMessage());
                           logger.write(LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss: a"))
                                   + " " + gum.getNameOfItem() + " " + gum.getVendingLocation()+ " $" + (balance.add(gum.getPrice())) + " $" + balance);
//                           if (snackMap.containsKey(gum.getNameOfItem())){
//                               Integer count = snackMap.get(gum.getNameOfItem());
//                               snackMap.put(gum.getNameOfItem(),snackMap.get(gum.getNameOfItem())+1);
//                           }
                           break;
                       case "drink":
                           drink = (Drink) snackList.get(i);
                           drink.setInventoryRemaining(drink.getInventoryRemaining()-1);
                           snackList.set(i, drink);
                           System.out.println(drink.getMessage());
                           logger.write(LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss: a"))
                                   + " " + drink.getNameOfItem() + " " + drink.getVendingLocation()+ " $" + (balance.add(drink.getPrice())) + " $" + balance);
//                           if (snackMap.containsKey(drink.getNameOfItem())){
//                               Integer count = snackMap.get(drink.getNameOfItem());
//                               snackMap.put(drink.getNameOfItem(),snackMap.get(drink.getNameOfItem())+1);
//                           }
                           break;
                   }

                   }else {
                        System.out.println("Sorry " + choice + " out of stock");
                    }
                }
            }
        }
        else {
            System.out.println("Sorry " + choice + " out of stock");
        }
        logger.close();
        return snackList;
    }

        
}
