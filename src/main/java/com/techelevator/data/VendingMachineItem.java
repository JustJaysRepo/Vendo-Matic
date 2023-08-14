package com.techelevator.data;

public abstract class VendingMachineItem {
    
    private final int STARTING_AMOUNT = 5;
    private String ID;
    private String itemName;
    private double cost;
    private int amountRemaining = STARTING_AMOUNT;

    public VendingMachineItem(String ID, String itemName, double cost) {
        this.ID = ID;
        this.itemName = itemName;
        this.cost = cost;
    }

    public void updateAmountRemaining(){
        if(amountRemaining > 0) amountRemaining--;
    }
    
    public abstract String dispenseText();

    public String getID() {
        return ID;
    }

    public String getItemName() {
        return itemName;
    }

    public double getCost() {
        return cost;
    }

    public int getAmountRemaining() {
        return amountRemaining;
    }
}
