package com.techelevator.data;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.io.FileNotFoundException;

public class Logger {

    public static void logPurchase(VendingMachineItem item, BigDecimal initialBalance) {
        BigDecimal endingBalance = initialBalance.subtract(BigDecimal.valueOf(item.getCost()));
        String event = item.getItemName() + "  " + item.getID();
        printToLogFile(event,initialBalance,endingBalance);
    }


    public static void logFeed(BigDecimal amountAdded, BigDecimal initialBalance)  {
        BigDecimal endingBalance = initialBalance.add(amountAdded);
        printToLogFile("FEED $"+amountAdded,initialBalance,endingBalance);
    }


    public static void logOutOfStock(BigDecimal balance)  {
        printToLogFile("Item unavailable.",balance,balance);
    }

    public static void notEnoughMoney(BigDecimal balance)  {
        printToLogFile("Insufficent funds.",balance,balance);
    }


    public static void logChange(BigDecimal availableFunds)  {
        printToLogFile("DISPENSED CHANGE $"+availableFunds.toString(),availableFunds, new BigDecimal("0.00"));
    }


    private static void printToLogFile(String event, BigDecimal start, BigDecimal finish) { //throws IOException {

        // Prepare line of output

        StringBuilder logEntry = new StringBuilder();
        logEntry.append(String.format("%-24s", new SimpleDateFormat("MM/dd/YYYY hh:mm:ss a").format(new java.util.Date())));
        logEntry.append(String.format("%-30s", event));
        logEntry.append(String.format("%1$8s","$"+start.toString()));
        logEntry.append(String.format("%1$8s","$"+finish.toString()));

        // Define log file

        File logFile = new File("Log.txt");

        // If log file does not exist, create it

        if (!logFile.exists()) {

            try {
                logFile.createNewFile();
            } catch (IOException e) {
                System.out.println("\n***WARNING: UNABLE TO CREATE LOG FILE***\n");
            }


        } else if (logFile.exists() && logFile.isDirectory()) {
            System.out.println("\n***WARNING: DIRECTORY WITH NAME \"Log.txt\" exists.***\n");
        }


        try (FileOutputStream f = new FileOutputStream(logFile,true);
             PrintWriter pw = new PrintWriter(f)) {

            // make the log entry

            pw.println(logEntry);
            pw.flush();

        } catch (IOException e) {
            System.out.println("\n***WARNING: LOG FILE HAS BEEN DELETED.  COULD NOT RECORD TRANSACTION.***\n");
        }

    }
    public static void writeReport(List<String> report) {
        // Get the date and time in a string format
        LocalDateTime current = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy_HH.mm.ss");
        String formattedDateTime = current.format(formatter);

        // Create a new file using date and time
        File fileReport = new File("report_" + formattedDateTime);

        try (PrintWriter writer = new PrintWriter(fileReport)) {
            for (String line : report) {
                writer.println(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

}

