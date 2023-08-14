package com.techelevator;

import com.techelevator.data.Logger;
import com.techelevator.data.VendingMachine;
import com.techelevator.view.Menu;

import java.util.Scanner;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String HIDDEN_OPTION = "";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT, HIDDEN_OPTION };
	private static final String FEED_MONEY_OPTION = "Feed Money";
	private static final String SELECT_PRODUCT_OPTION = "Select Product";
	private static final String FINISH_TRANSACTION_OPTION = "Finish Transaction";
	private static final String[] PURCHASE_MENU_OPTIONS = { FEED_MONEY_OPTION, SELECT_PRODUCT_OPTION, FINISH_TRANSACTION_OPTION };
	private Menu menu;

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}

	public void run() {
		VendingMachine machine = new VendingMachine();
		while (true) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				machine.DisplayVendingMachine();
			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				String purchaseChoice = "";
				do {
					System.out.printf("Current Money Provided: $%.2f%n", machine.getBalance());
					purchaseChoice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);
					Scanner in = new Scanner(System.in);
					switch (purchaseChoice) {
						case FEED_MONEY_OPTION:
							System.out.println("Enter full dollar amount to feed into the vending machine: ");
							String input = in.nextLine();
							try {
								machine.feedMoney(Integer.parseInt(input));
							} catch (NumberFormatException e) {
								System.out.println("Please input full dollar amount!");
							}
							break;
						case SELECT_PRODUCT_OPTION:
							machine.DisplayVendingMachine();
							System.out.printf("Your current balance is: %.2f. Please enter an item ID to purchase:", machine.getBalance());
							String inputChoice = in.nextLine();
							machine.purchaseItem(inputChoice);
							break;
						case FINISH_TRANSACTION_OPTION:
							machine.printChange();
							break;
					}
				}while (!purchaseChoice.equals(FINISH_TRANSACTION_OPTION));
			}
			else if (choice.equals(HIDDEN_OPTION)) {
					System.out.println("Sales report generated and saved using current date and time");
					Logger.writeReport(machine.generateReport());
			}
			else if (choice.equals(MAIN_MENU_OPTION_EXIT)) {
				System.out.println("Thank you for using our vending machine!");
				break;
			}
		}
	}

	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}
}
