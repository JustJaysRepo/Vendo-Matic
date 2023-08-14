package com.techelevator.data;

public class ChipItem extends VendingMachineItem {

    public ChipItem(String ID, String itemName, double cost) {
        super(ID, itemName, cost);
    }

    @Override
    public String dispenseText() {
        return "Crunch Crunch, Yum!";
    }
}
