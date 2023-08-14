package com.techelevator.data;

import com.techelevator.data.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class VendingMachineTest {
    private VendingMachine vendingMachine;
    private final int DOLLARS_IN = 13;

    @Before
    public void setUp() {
        vendingMachine = new VendingMachine();
        vendingMachine.feedMoney(DOLLARS_IN);
    }

    @Test
    public void testFeedMoney() {
        BigDecimal expectedBalance = new BigDecimal(DOLLARS_IN);
        assert expectedBalance.compareTo(vendingMachine.getBalance()) == 0;
    }

    @Test
    public void testPurchaseItem() {
        String expectedOutput1 = "Mountain Melter $1.50 - your remaining balance is: $2.50";
        String expectedOutput2 = "That item is currently SOLD OUT!";
        String expectedOutput3 = "Invalid funds, please feed more money!";

        vendingMachine.purchaseItem("B1");
        vendingMachine.purchaseItem("B1");
        vendingMachine.purchaseItem("B1");
        vendingMachine.purchaseItem("B1");
        vendingMachine.purchaseItem("B1");


        // Test for a successful purchase with sufficient funds and available quantity
        Assert.assertEquals(expectedOutput1, vendingMachine.purchaseItem("C3"));
        Assert.assertEquals(new BigDecimal("2.5"), vendingMachine.getBalance());

        // Test for an item that is sold out
        Assert.assertEquals(expectedOutput2, vendingMachine.purchaseItem("B1"));

        // Test for insufficient funds
        vendingMachine.purchaseItem("C1");
        Assert.assertEquals(expectedOutput3, vendingMachine.purchaseItem("A1"));
    }

    @Test
    public void testPrintChange() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        vendingMachine.printChange();

        // Assert
        String expectedOutput = "Your total change is: $13: Quarters:52, Dimes:0,  Nickels:0\n";
        String actualOutput = outContent.toString();
        Assert.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testGenerateReport() {
        List<String> reportTest = new ArrayList<>();
        reportTest.add("Potato Crisps|2");
        reportTest.add("Stackers|0");
        reportTest.add("Grain Waves|0");
        reportTest.add("Cloud Popcorn|0");
        reportTest.add("Moonpie|0");
        reportTest.add("Cowtales|0");
        reportTest.add("Wonka Bar|0");
        reportTest.add("Crunchie|0");
        reportTest.add("Cola|0");
        reportTest.add("Dr. Salt|0");
        reportTest.add("Mountain Melter|1");
        reportTest.add("Heavy|0");
        reportTest.add("U-Chews|0");
        reportTest.add("Little League Chew|0");
        reportTest.add("Chiclets|0");
        reportTest.add("Triplemint|0");
        reportTest.add("\n**TOTAL SALES** $7.60");

        vendingMachine.purchaseItem("A1");
        vendingMachine.purchaseItem("A1");
        vendingMachine.purchaseItem("C3");

        List<String> actualReport = vendingMachine.generateReport();
        Collections.sort(reportTest);
        Collections.sort(actualReport);

        Assert.assertEquals(reportTest, actualReport);
    }
}


