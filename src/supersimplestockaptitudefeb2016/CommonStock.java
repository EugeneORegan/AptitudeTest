/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supersimplestockaptitudefeb2016;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

/**
 * Class to cater for the main transactions of CommonStock
 * 
 * @author Eugene OR
 */
public class CommonStock {

    private String symbol;
    private double lastDividend;
    private double parValue;
    private double marketValue;
    private int quantityOfStock;
    public static long timeStampInMinutes;
    public static int totalStocks = 0;
    public static final long MILLISECONDS_IN_A_MINUTE = 60000;
    public static double gbceAllStock;
    public static double gbceStockOverZero;
    public static ArrayList <TransactionRecord> allTransactions = new <TransactionRecord> ArrayList();
    public static double volumeWeightedStockPrice;
    
    /*
     Constructors
     */
public CommonStock(){
    
}
    public CommonStock(String symbol) {
        this.symbol = symbol;
        totalStocks++; // Increment the number of stocks in portfolio
    }

    public CommonStock(String symbol, double lastDividend, double parValue, double marketValue, int quantityOfStock) {
        /*
         While there could be error checking at point of entry, this is done to quickly demonstrate the 
         use of 
         */
        if ((lastDividend < 0) || (parValue < 0) || (marketValue < 0) || (quantityOfStock < 0)) {
            throw new InvalidStockInfoInputRunTimeException(symbol);
        } else {
            this.symbol = symbol;
            this.lastDividend = lastDividend;
            this.parValue = parValue;
            this.marketValue = marketValue;
            this.quantityOfStock = quantityOfStock;
            totalStocks++; // Increment the number of stocks in portfolio
        }
    } // End of All Arg constructor

    public CommonStock(String symbol, double lastDividend, double parValue) {
        if ((lastDividend < 0) || (parValue < 0)) {
            throw new InvalidStockInfoInputRunTimeException(symbol);
        } else {
            this.symbol = symbol;
            this.lastDividend = lastDividend;
            this.parValue = parValue;
           totalStocks++; // Increment the number of stocks in portfolio
        }
    }// End of 3 arg constructor

    /*
     Getters and setters
     */
    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getLastDividend() {
        return lastDividend;
    }

    public void setLastDividend(double lastDividend) {
        this.lastDividend = lastDividend;
    }

    public double getParValue() {
        return parValue;
    }

    public void setParValue(double parValue) {
        this.parValue = parValue;
    }

    public double getMarketValue() {
        return marketValue;
    }

    public void setMarketValue(double marketValue) {
        this.marketValue = marketValue;
    }

    public int getQuantityOfStock() {
        return quantityOfStock;
    }

    public void setQuantityOfStock(int quantityOfStock) {
        this.quantityOfStock = quantityOfStock;
    }

    @Override
    public String toString() {
        return "CommonStock Info{" + "symbol:" + symbol + ", lastDividend : " + lastDividend + ", parValue=" + parValue + ", marketValue=" + marketValue + ", quantityOfStock=" + quantityOfStock + '}';
    }
    
    public double dividendYield(double marketPriceIn)
    { double dividenedYieldOfStock = 0;
    if ((marketPriceIn <= 0) || this.lastDividend == 0)
    {
        System.out.println("Either market price or last dividend is invalid for this calculation - Ensure data is correct ");
         return 0;
    }else{
        this.setMarketValue(marketPriceIn); //Update the market value of the share
        dividenedYieldOfStock = this.lastDividend / marketPriceIn;
        System.out.println("Dividend Yield of " +this.getSymbol()); // Printout including Stock Symbol
        return dividenedYieldOfStock;
    } // End of if...else
        
    } //End of dividendYield method
 public double PriceEarningsRatio(double marketPriceIn)
    { double priceEarningsRatioReturn = 0;
    if ((marketPriceIn <= 0) || this.lastDividend == 0)
    {
        System.out.println("Either market price or last dividend is invalid for this calculation - Ensure data is correct ");
         return 0;
    }else{
        this.setMarketValue(marketPriceIn); //Update the market value of the share
        priceEarningsRatioReturn =  marketPriceIn/this.lastDividend;
        System.out.println("Price Earnings Ratio of " +this.getSymbol()); // Printout including Stock Symbol
        return priceEarningsRatioReturn;
    } // End of if...else
        
    } //End of PriceEarningRatio method
 
 public void BuyStock(){
     Scanner num = new Scanner (System.in);
        Scanner num2 = new Scanner (System.in);
     System.out.println("You have " +this.getQuantityOfStock()+ " in your portfolio");
     System.out.println(" Quantity to be bought ==>");
     int qtyToBeBought = num.nextInt();
     System.out.println("Price to be bought at ==>");
     double priceToBeBought = num2.nextDouble();
     if (priceToBeBought < 0){
         System.out.println("You are entering a negative price value for the stock. Please restart transaction");
     } else{
         // Transaction ok. Process details. 
         this.setMarketValue(priceToBeBought);
         this.setQuantityOfStock(this.getQuantityOfStock()+qtyToBeBought);
         long timeOfTransaction = currentTimeInMinutes(CurrentTime());
         String tStamp = timeStampDateFormat(CurrentTime());
         // Instantiate an object of TransactionRecord to record the share purchase
         TransactionRecord buyStock = new TransactionRecord (this.getSymbol(), 
                 'B',this.getMarketValue(), qtyToBeBought, timeOfTransaction, tStamp);
         System.out.println(this.getSymbol()+ " AMOUNT " +qtyToBeBought+ " at " +this.getMarketValue());
         //System.out.print("Time " +timeStampDateFormat(CurrentTime()));
         allTransactions.add(buyStock);
     }
 }
 
 public void SellStock (){
     Scanner num = new Scanner (System.in);
     System.out.println("You have " +this.getQuantityOfStock()+ " in your portfolio");
     System.out.println(" Quantity to be sold ==>");
     int qtyToBeSold = num.nextInt();
     System.out.println("Price to be sold at ==>");
     double priceToBeSold = num.nextDouble();
     if ((qtyToBeSold > this.getQuantityOfStock())){
         System.out.println("You have not that amount of stock to sell. Please restart transaction");
     } else if (priceToBeSold < 0){
         System.out.println("You are entering a negative price value for the stock. Please restart transaction");
     } else{
         // Transaction ok. Process details. 
         this.setMarketValue(priceToBeSold);
         this.setQuantityOfStock(this.getQuantityOfStock()-qtyToBeSold);
         long timeOfTransaction = currentTimeInMinutes(CurrentTime());
         String tStamp = timeStampDateFormat(CurrentTime());
         // Instantiate an object of TransactionRecord to record the share sale
         TransactionRecord sellStock = new TransactionRecord (this.getSymbol(), 
                 'S',this.getMarketValue(), qtyToBeSold, timeOfTransaction, tStamp);
         System.out.println(this.getSymbol()+ " AMOUNT " +qtyToBeSold+ " at " +this.getMarketValue());
         System.out.print("Time " +timeStampDateFormat(CurrentTime()));
         //System.out.println(sellStock.getTimeStamp()+ " " +timeOfTransaction);
         allTransactions.add(sellStock);
     }
 }// End of Sell record
/**
 * Method to generate Calendar object to use in subsequent time calculations
 * @return A Calendar object 
 */
    
 public static Calendar CurrentTime()
{
    Calendar timeRightNow = Calendar.getInstance();
    return timeRightNow;
}   
 /**
  *  Method to convert current time from milliseconds into minutes 
  * @param timeRightNowIn
  * @return 
  */
public static long currentTimeInMinutes(Calendar timeRightNowIn)
{
    long timeStampInMinutes =timeRightNowIn.getTimeInMillis() / (MILLISECONDS_IN_A_MINUTE);
    return timeStampInMinutes;
}
/**
 *  Converts Calendar object generated by currentTime() to a date format 
 * 
 * @param timeNow 
 *   */
public static String timeStampDateFormat ( Calendar timeNow){
       DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd / hh:mm:ss");
       String dateStringFormat = dateFormat.format(timeNow.getTime());
        System.out.println("Current Date / Time : " +dateStringFormat);
        return dateStringFormat;
}

}// End of class