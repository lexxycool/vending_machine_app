package com.techelevator;

import com.techelevator.view.Menu;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_SALES_REPORT = "";
	private static final String MAIN_MENU_EXIT = "Exit";

	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_EXIT, MAIN_MENU_SALES_REPORT };
	File importFile = new File("C:\\Users\\Student\\workspace\\mod1-capstone-green-t4\\java\\vendingmachine.csv");
	protected final List<Snack> snackList = importInventory(importFile);
	private Menu menu;

	private BigDecimal balance = new BigDecimal(0.0);

	public VendingMachineCLI() {

	}

	public static void main(String[] args) throws IOException {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		Logging.createLogFile("src/main/resources/Log.txt");
		File hiddenfile = new File("src/main/resources/Hidden.txt");
		if (hiddenfile.length()==0) {
			Logging.createLogFile("src/main/resources/Hidden.txt");
		}
		cli.run();
	}

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}

	public void run() throws IOException {
		while (true) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
			File importFile = new File("C:\\Users\\Student\\workspace\\mod1-capstone-green-t4\\java\\vendingmachine.csv");
			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				// display vending machine items
				System.out.println(MAIN_MENU_OPTION_DISPLAY_ITEMS);

				displayItems(importInventory(importFile));

			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				purchaseMenu(importInventory(importFile));

			}else if(choice.equals(MAIN_MENU_SALES_REPORT)) {
				System.out.println("sales report");
			}
			else if(choice.equals(MAIN_MENU_EXIT)) {
				System.exit(1);
			}
		}
	}
	public List<Snack> importInventory(File inventory){
		List<Snack> snackList = new ArrayList<>();
		try {
			Scanner fileScanner = new Scanner(inventory);
			while (fileScanner.hasNextLine()){
				String currentItem = fileScanner.nextLine();
				String[] itemArray = currentItem.split("\\|");
				String snackType = itemArray[3];
				BigDecimal price = new BigDecimal(itemArray[2]);
				switch (snackType) {
					case "Chip":
						Chip currentChip = new Chip(itemArray[0],itemArray[1], price, 5);
						snackList.add(currentChip);
						break;
					case "Candy":
						Candy currentCandy = new Candy(itemArray[0],itemArray[1], price, 5);
						snackList.add(currentCandy);
						break;
					case "Drink":
						Drink currentDrink = new Drink(itemArray[0],itemArray[1], price,5);
						snackList.add(currentDrink);
						break;
					case "Gum":
						Gum currentGum = new Gum(itemArray[0],itemArray[1], price,5);
						snackList.add(currentGum);
						break;
 				}

			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		}



		return snackList;
	}

	public void purchaseMenu(List<Snack> snackList) throws IOException {
		Logging.initializeHidden("src/main/resources/Hidden.txt", snackList);
		final String MENU_FEED_MONEY = "Feed Money";
		final String MENU_SELECT_PRODUCT = "Select Product";
		final String MENU_FINISH_TRANSACTION = "Finish Transaction";

		final String[] MENU_ARRAY = {MENU_FEED_MONEY, MENU_SELECT_PRODUCT, MENU_FINISH_TRANSACTION};

		boolean isLooping = true;
		Scanner userInput = new Scanner(System.in);
		while(isLooping) {
			String choice = (String) menu.getChoiceFromOptions(MENU_ARRAY);
			switch (choice) {
				case MENU_FEED_MONEY:
					balance = feedMoney(balance);
					break;
				case MENU_SELECT_PRODUCT:
					System.out.println("Select product or type exit to leave: example (C1): ");
					File importFile = new File("C:\\Users\\Student\\workspace\\mod1-capstone-green-t4\\java\\src\\main\\resources\\Inventory.txt");
					displayItems(snackList);
					String itemChoice = userInput.nextLine();
					if (itemChoice.equals("exit")){
						break;
					}
					boolean haveItem = false;
					while(!haveItem) {
						if(Purchase.isPurchaseable(balance, snackList, itemChoice)){
							haveItem = true;
						}
						else{
							System.out.println("Invalid selection, Try again.");
							itemChoice = userInput.nextLine();
							if (itemChoice.equals("exit")){
								break;
							}
						}
					}
					balance = Purchase.updateBalance(balance, itemChoice, snackList);
					snackList = Purchase.updateInventory(itemChoice, snackList, balance);
					System.out.println("Your remaining balance is " + balance);
					break;
				case MENU_FINISH_TRANSACTION:
					balance = finishTransaction(balance);
					isLooping = false;
			}
		}

	}

	public BigDecimal feedMoney(BigDecimal balance) throws IOException {
		Scanner userInput = new Scanner(System.in);
		Logging logger = new Logging("src/main/resources/Log.txt");
		Logging hiddenLogger = new Logging("src/main/resources/Hidden.txt");
		boolean isLooping = true;

		while (isLooping) {

			System.out.println("Insert $1, $2, $5, $10 example(10) or exit to Go Back");
			String price = userInput.nextLine();

			switch (price) {
				case "1":
					balance = balance.add(new BigDecimal(1));
					System.out.println("Your current balance is " + balance);
					logger.write(LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss: a"))
							+ " FEED MONEY: $" + price +".00 $"  + balance+".00");
					break;
				case "2":
					balance = balance.add(new BigDecimal(2));
					System.out.println("Your current balance is " + balance);
					logger.write(LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss: a"))
							+ " FEED MONEY: $" + price +".00 $"  + balance+".00");
					break;
				case "5":
					balance = balance.add(new BigDecimal(5));
					System.out.println("Your current balance is " + balance);
					logger.write(LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss: a"))
							+ " FEED MONEY: $" + price +".00 $"  + balance +".00");
					break;
				case "10":
					balance = balance.add(new BigDecimal(10));
					System.out.println("Your current balance is " + balance);
					logger.write(LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss: a"))
							+ " FEED MONEY: $" + price +".00 $"  + balance +".00");

					break;
				case "exit":
					System.out.println("Your total balance is " + balance);
					isLooping = false;

				default:
					System.out.println("Please enter the correct amount");
			}
		}
		logger.close();
		return balance;
	}
	public void displayItems(List<Snack> snackList){
		for(Snack item : snackList){
			System.out.println("");
			System.out.println(item.getNameOfItem() + "  " + item.getPrice() + "  " + item.getVendingLocation());

		}

	}

	public BigDecimal finishTransaction(BigDecimal balance) throws IOException {
		Logging logger = new Logging("src/main/resources/Log.txt");
		//Logging hiddenLogger = new Logging("src/main/resources/Hidden.txt");

		BigDecimal bdAmount = balance.multiply(new BigDecimal("100"));
		int remainingBalance = bdAmount.intValue();
		int quarter = remainingBalance / 25;
		remainingBalance -= quarter * 25;
		int dime = remainingBalance /10;
		remainingBalance -= dime *10;
		int nickel = remainingBalance / 5;
		remainingBalance -= nickel * 5;
//		if (Purchase.snackMap.entrySet() != null) {
//			for (Map.Entry<String, Integer> snack : Purchase.snackMap.entrySet()) {
//				hiddenLogger.write(snack.getKey()+ "\\|"+snack.getValue());
//			}
			/*Set<String> keys = Purchase.snackMap.keySet();
			System.out.println(Purchase.snackMap.get("Potato Crisps"));
			for (String name : keys){
				System.out.println("the for loop works");
				hiddenLogger.write(name + "\\|" + Purchase.snackMap.get(name));
			}*/
//			hiddenLogger.close();
//		}
		System.out.println("Your change is " + quarter + " quarters " + nickel + " nickles "
							+ dime + " dimes " + "and your remaining balance is $0.00");


		logger.write(LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss: a"))
				+ " GIVE CHANGE: $" + balance +" $" +remainingBalance +".00");
		logger.close();
		return new BigDecimal(remainingBalance);
	}

}
