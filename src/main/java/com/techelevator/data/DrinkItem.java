package com.techelevator.data;

public class DrinkItem extends VendingMachineItem {

    public DrinkItem(String ID, String itemName, double cost) {
        super(ID, itemName, cost);
    }

    @Override
    public String dispenseText() {
        return "Glug Glug, Yum!";
    }
}
