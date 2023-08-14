package com.techelevator.data;

import com.techelevator.view.BuildVendingMachine;

import java.math.BigDecimal;
import java.util.*;

public class VendingMachine {
    private BigDecimal balance = new BigDecimal(0);
    private final Map<String,VendingMachineItem> vendingList;
    private final Map<String, Integer> purchaseHistory = new HashMap<>();

    public void feedMoney(int dollarsIn) {
        BigDecimal amountAdded = BigDecimal.valueOf(dollarsIn);
        balance = balance.add(amountAdded);
        Logger.logFeed(amountAdded, balance);
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public VendingMachine() {
        vendingList = BuildVendingMachine.readVendingMachine();
    }

    public void DisplayVendingMachine() {
        String output = BuildVendingMachine.writeVendingMachine(getMachineItems());
        System.out.println(output);
    }

    public String purchaseItem(String itemID) {
        itemID = itemID.toUpperCase();
        VendingMachineItem purchaseItem = vendingList.get(itemID);
        if (!vendingList.containsKey(itemID)) {
            System.out.println("Not a valid selection!");
            return "Not a valid selection!";
        }
        if (balance.compareTo(BigDecimal.valueOf(purchaseItem.getCost())) < 0) {
            System.out.println("Invalid funds, please feed more money!");
            Logger.notEnoughMoney(balance);
            return "Invalid funds, please feed more money!";
        }
        if (purchaseItem.getAmountRemaining() > 0) {
            Logger.logPurchase(purchaseItem, balance);
            balance = balance.subtract(BigDecimal.valueOf(purchaseItem.getCost()));
            purchaseItem.updateAmountRemaining();
            System.out.printf("%s $%.2f - your remaining balance is: $%.2f%n", purchaseItem.getItemName(), purchaseItem.getCost(), balance);
            System.out.println(purchaseItem.dispenseText());
            int currentPurchaseCount = 0;
            if(purchaseHistory.containsKey(purchaseItem.getID())) {
                currentPurchaseCount = purchaseHistory.get(purchaseItem.getID());
            }
            purchaseHistory.put(purchaseItem.getID(),currentPurchaseCount + 1);
            return String.format("%s $%.2f - your remaining balance is: $%.2f", purchaseItem.getItemName(), purchaseItem.getCost(), balance);
        } else {
            System.out.println("That item is currently SOLD OUT!");
            Logger.logOutOfStock(balance);
            return "That item is currently SOLD OUT!";
        }
    }

    public List<String> generateReport() {
        List<String> report = new ArrayList<>();
        double total = 0.0;

        for (VendingMachineItem item : vendingList.values()) {
            int purchaseCount = 0;
            if(purchaseHistory.containsKey(item.getID())) {
                purchaseCount = purchaseHistory.get(item.getID());
            }
            total += item.getCost() * purchaseCount;
            report.add(String.format("%s|%d", item.getItemName(), purchaseCount));
        }
        report.add(String.format("\n**TOTAL SALES** $%.2f", total));
        return report;
    }

    public void printChange() {
        BigDecimal quarter = new BigDecimal("0.25");
        BigDecimal dime = new BigDecimal("0.10");
        BigDecimal nickle = new BigDecimal("0.05");
        BigDecimal numQuarters = BigDecimal.ZERO;
        BigDecimal numDimes = BigDecimal.ZERO;
        BigDecimal numNickles = BigDecimal.ZERO;
        BigDecimal change = BigDecimal.valueOf(balance.doubleValue());

        while (change.compareTo(BigDecimal.ZERO) > 0) {
            if (change.doubleValue() >= quarter.doubleValue()) {
                change = change.subtract(quarter);
                numQuarters = numQuarters.add(new BigDecimal(1));


            } else if (change.doubleValue() >= dime.doubleValue()) {
                change = change.subtract(dime);
                numDimes = numDimes.add(new BigDecimal(1));

            } else {
                change = change.subtract(nickle);
                numNickles = numNickles.add(new BigDecimal(1));

            }
        }
        System.out.println("Your total change is: $" + balance.toString() + ": Quarters:" + numQuarters + ", Dimes:" + numDimes + ",  Nickels:" + numNickles);
        balance = BigDecimal.ZERO;
    }

    private List<VendingMachineItem> getMachineItems() {
        List<VendingMachineItem> allItems = new ArrayList<>();
        for (Map.Entry<String, VendingMachineItem> currItem : vendingList.entrySet()) {
            allItems.add(currItem.getValue());
        }
        // Sort the list by key.
        allItems.sort(Comparator.comparing(VendingMachineItem::getID));
        return allItems;
    }

}
