package com.techelevator.data;

public class GumItem extends VendingMachineItem {

    public GumItem(String ID, String itemName, double cost) {
        super(ID, itemName, cost);
    }

    @Override
    public String dispenseText() {
        return "Chew Chew, Yum!";
    }
}
