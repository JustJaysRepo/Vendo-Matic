package com.techelevator.view;

import com.techelevator.data.*;
import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Before;

import org.junit.Test;


import java.io.File;

import java.util.List;
import java.util.Map;

public class BuildVendingMachineTest {
    private Map<String, VendingMachineItem> items;

    @Before
    public void setUp() {
        items = BuildVendingMachine.readVendingMachine();
    }

    @Test
    public void testReadVendingMa111chine_ListOfItems() {

        // Test Chip items
        Assert.assertEquals("A1", items.get("A1").getID());
        Assert.assertEquals("Potato Crisps", items.get("A1").getItemName());
        Assert.assertEquals(3.05, items.get("A1").getCost(), 0.001);
        Assert.assertEquals(5, items.get("A1").getAmountRemaining());

        // Test Candy items
        Assert.assertEquals("B1", items.get("B1").getID());
        Assert.assertEquals("Moonpie", items.get("B1").getItemName());
        Assert.assertEquals(1.80, items.get("B1").getCost(), 0.001);
        Assert.assertEquals(5, items.get("B1").getAmountRemaining());

        // Test Drink items
        Assert.assertEquals("C1", items.get("C1").getID());
        Assert.assertEquals("Cola", items.get("C1").getItemName());
        Assert.assertEquals(1.25, items.get("C1").getCost(), 0.001);
        Assert.assertEquals(5, items.get("C1").getAmountRemaining());

        // Test Gum items
        Assert.assertEquals("D3", items.get("D3").getID());
        Assert.assertEquals("Chiclets", items.get("D3").getItemName());
        Assert.assertEquals(0.75, items.get("D3").getCost(), 0.001);
        Assert.assertEquals(5, items.get("D3").getAmountRemaining());
    }

    /**
     *
     *
     */

    @Test
    public void testReadVendingMachineFilePath() {
        // Arrange
        String filePath = "vendingmachine.csv";
        File file = new File(filePath);

        // Act & Assert
        Assert.assertNotNull("List should not be null", items);
        Assert.assertFalse("List should not be empty", items.isEmpty());

        for (VendingMachineItem item : items.values()) {
            Assert.assertTrue("Item should be instance of valid subclass", item instanceof ChipItem ||
                    item instanceof CandyItem || item instanceof DrinkItem || item instanceof GumItem);
        }
    }

    @Test
    public void testFilePathExists() {
        // Arrange
        String filePath = "vendingmachine.csv";
        File file = new File(filePath);

        // Act & Assert
        Assert.assertTrue("File should exist", file.exists());
    }
}

