package com.techelevator.data;

public class CandyItem extends VendingMachineItem {

    public CandyItem(String ID, String itemName, double cost) {
        super(ID, itemName, cost);
    }

    @Override
    public String dispenseText() {
        return "Munch Munch, Yum!";
    }
}
