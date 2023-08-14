package com.techelevator.view;

import com.techelevator.data.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class BuildVendingMachine {
    private static final String STARS = "*********************************************************************************************************";
    private static final int LONGEST_NAME_LENGTH = 18;

    public static Map<String,VendingMachineItem> readVendingMachine() {
        File filePath = new File("vendingmachine.csv");
        File dataFile = new File(filePath.getAbsolutePath());
        Map<String,VendingMachineItem> items = new HashMap<>();

        try (Scanner vendingList = new Scanner(dataFile)) {
            while(vendingList.hasNextLine()) {
                String line = vendingList.nextLine();
                String[] vendingItem = line.split("\\|");
                String id = vendingItem[0];
                String name = vendingItem[1];
                String strPrice = vendingItem[2];
                switch (vendingItem[3]) {
                    case "Chip":
                        items.put(id,new ChipItem(id, name, Double.parseDouble(strPrice)));
                        break;
                    case "Candy":
                        items.put(id,new CandyItem(id, name, Double.parseDouble(strPrice)));
                        break;
                    case "Drink":
                        items.put(id,new DrinkItem(id, name, Double.parseDouble(strPrice)));
                        break;
                    case "Gum":
                        items.put(id,new GumItem(id, name, Double.parseDouble(strPrice)));
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("This file does not exist!");
        }
        return items;
    }

    public static String writeVendingMachine(List<VendingMachineItem> vendingMachine) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < vendingMachine.size(); i += 4) {
                    VendingMachineItem vendingItem = vendingMachine.get(i);
                    VendingMachineItem vendingItem2 = vendingMachine.get(i + 1);
                    VendingMachineItem vendingItem3 = vendingMachine.get(i + 2);
                    VendingMachineItem vendingItem4 = vendingMachine.get(i + 3);
                    sb.append(STARS);
            sb.append("\n");
            sb.append("|");
            appendVendingItemName(sb, vendingItem);
            appendVendingItemName(sb, vendingItem2);
            appendVendingItemName(sb, vendingItem3);
            appendVendingItemName(sb, vendingItem4);
            sb.append("\n");
            sb.append("|");
            appendVendingItemPriceAmount(sb, vendingItem);
            appendVendingItemPriceAmount(sb, vendingItem2);
            appendVendingItemPriceAmount(sb, vendingItem3);
            appendVendingItemPriceAmount(sb, vendingItem4);
            sb.append("\n");
        }
        sb.append(STARS);
        return sb.toString();
    }

    public static void appendVendingItemName(StringBuilder sb, VendingMachineItem vendingItem) {
        sb.append(" ");
        int nameLength = vendingItem.getItemName().length();
        int paddingStart = (LONGEST_NAME_LENGTH - nameLength) / 2;
        int paddingEnd = (LONGEST_NAME_LENGTH - nameLength - paddingStart);
        sb.append(" ".repeat(Math.max(0, paddingStart)));
        sb.append(String.format("(%s) %s", vendingItem.getID(), vendingItem.getItemName()));
        sb.append(" ".repeat(Math.max(0, paddingEnd)));
        sb.append(" |");
    }

    public static void appendVendingItemPriceAmount(StringBuilder sb, VendingMachineItem vendingItem) {
        String price = String.format("$%.2f - ", vendingItem.getCost());
        String remaining;

        if (vendingItem.getAmountRemaining() <= 0) {
            remaining = "SOLD OUT";
        }
        else remaining = String.format("%d left", vendingItem.getAmountRemaining());

        int length = price.length() + remaining.length();
        int paddingStart = (25 - length) / 2;
        int paddingEnd = 25 - length - paddingStart;

        sb.append(" ".repeat(Math.max(0, paddingStart)));
        sb.append(price).append(remaining);

        sb.append(" ".repeat(Math.max(0, paddingEnd)));
        sb.append("|");

    }
}
