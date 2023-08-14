package com.techelevator.data;

import com.techelevator.data.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class VendingMachineItemTest {
    private VendingMachineItem chipItem;
    private VendingMachineItem candyItem;
    private VendingMachineItem drinkItem;
    private VendingMachineItem gumItem;

    @Before
    public void setup(){
        this.chipItem = new ChipItem("C1", "Dr. salt", 1.50);
        this.candyItem = new CandyItem("C1", "cola", 1.25);
        this.drinkItem = new DrinkItem("D1", "pepsi", 3.05);
        this.gumItem = new GumItem("G1", "U-Chews", 0.85);
    }

    @Test
    public void chipItem_return_correct_message() {
        assertEquals("the chip item does not return the correct message", chipItem.dispenseText(), "Crunch Crunch, Yum!");
    }
    @Test
    public void candyItem_return_correct_message() {
        assertEquals("the candy item does not return the correct message", candyItem.dispenseText(), "Munch Munch, Yum!");
    }
    @Test
    public void drinkItem_return_correct_message() {
        assertEquals("the chip item does not return the correct message", drinkItem.dispenseText(), "Glug Glug, Yum!");
    }
    @Test
    public void gumItem_return_correct_message() {
        assertEquals("the chip item does not return the correct message", gumItem.dispenseText(), "Chew Chew, Yum!");
    }

    @Test
    public void machine_item_should_update_amount_remaining_correctly(){
        chipItem.updateAmountRemaining();
        candyItem.updateAmountRemaining();
        assertEquals("The machine item should reduce by one when update",chipItem.getAmountRemaining(), 4);
        assertEquals("The machine item should reduce by one when update",candyItem.getAmountRemaining(), 4);
    }
    @Test
    public void machine_item_amount_remaining_should_have__correct_values(){
        assertEquals("The machine should start with 5 items",drinkItem.getAmountRemaining(), 5);
        assertEquals("The machine should start with 5 items",gumItem.getAmountRemaining(), 5);
        for(int i=0; i<10;i++){
            drinkItem.updateAmountRemaining();
            gumItem.updateAmountRemaining();
        }
        assertEquals("The machine item should reduce by one when update",drinkItem.getAmountRemaining(), 0);
        assertEquals("The machine item should reduce by one when update",gumItem.getAmountRemaining(), 0);
    }

}
