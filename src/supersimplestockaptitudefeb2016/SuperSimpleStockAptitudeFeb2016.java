package supersimplestockaptitudefeb2016;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class SuperSimpleStockAptitudeFeb2016 {

    /*
     AllStock is an Arraylist used for basic storage if no database used. Declared at this point 
     so can be read at any point in either this class or another, without having to pass as an 
     argument in a related method. Access level set to default. 
     */
    static ArrayList<CommonStock> AllStock = new <CommonStock> ArrayList();

    public static void main(String[] args) {
        /*
         Would assume that the stock MUST exist on the portfolio in order for any of the transactions to occur.
         Another assumption is that there is basic error handling but nothing complex as not asked for GUI. 
         */
        int optionNumber = 0;
        final int EXITMENU = 7;
        try {
            CommonStock a = new CommonStock("TEA", 0, 100, 0, 1000);
            AllStock.add(a);
            CommonStock b = new CommonStock("POP", 8, 100, 500, 5000);
            AllStock.add(b);
            CommonStock c = new CommonStock("ALE", 23, 60, 30, 15000);
            AllStock.add(c);
            PreferredStock d = new PreferredStock("GIN", 8, 100, 110, 1000, .02);
            AllStock.add(d);
            CommonStock e = new CommonStock("JOE", 13, 100, 10, 15000);
            AllStock.add(c);
        } catch (InvalidStockInfoInputRunTimeException e) {
        }
        //printAllStock(AllStock);  
        boolean isValidMenuOption = true;
        Scanner inputOption = new Scanner(System.in);

        do {
            isValidMenuOption = true; // Resettting boolean if turned to false

            System.out.println(" \t === Main Menu ===");
            System.out.println("1. Dividend Yield Calculation");
            System.out.println("2. Price Earnings Ratio Calculation");
            System.out.println("3. Trading Menu");
            System.out.println("4. Volume Weighted Stock Price");
            System.out.println("5. GBCE All Share Index");
            System.out.println("6. Print All Stock Info");
            System.out.println("7. Exit");
            System.out.println("Select Menu option by inputting relevant number ==> ");

            optionNumber = inputOption.nextInt();

            if (optionNumber > EXITMENU || optionNumber < 1) {
                isValidMenuOption = false;
                System.out.println("Invalid Option - Please reselect correct option");
            }
            switch (optionNumber) {
                case 1:
                    System.out.println("Dividend Yield Calculation");
                    System.out.println(AllStock.get(SelectSymbol()).dividendYield(MarketPriceOfShare()));
                    break;
                case 2:
                    System.out.println("Price Earnings Ratio");
                    System.out.println(AllStock.get(SelectSymbol()).PriceEarningsRatio(MarketPriceOfShare()));
                    break;
                case 3:
                    System.out.println("\t === Trading Menu ===");
                    System.out.println("You can only trade in shares currently on system. Functionality to add stock"
                            + " to existing portfolio to be added later");
                    int stockToTrade = SelectSymbol();
                    Scanner buySell = new Scanner(System.in);
                    System.out.println("Enter B for Buy Stock, S for Sell Stock");
                    String buySellOption = buySell.next().toUpperCase();
                    switch (buySellOption) {
                        case "B":
                            AllStock.get(stockToTrade).BuyStock();
                            break;
                        case "S":
                            AllStock.get(stockToTrade).SellStock();
                            break;
                        default:
                            System.out.println("\n \t You selected an incorrect option. Returning to Main Menu");
                    } // End of Trading options switch
                    break;
                case 4:
                    System.out.println("\t ===Volume Weighted Stock Price ===");
                    VolumeWeightedStockPrice();
                    break;
                case 5:
                    System.out.println("\t === GBCE All Share Index ===");
                    GBCEAllShareIndex();
                    break;
                case 6:
                    System.out.println("\t === ALL STOCK INFORMATION ===");
                    PrintAllStock(AllStock);
                    break;
                case 7:
                    System.out.println("EXITING PROGRAM");
                    break;
            }

        } while (!isValidMenuOption || optionNumber != EXITMENU);

    }// End of main

    /**
     * Method to allow user to select the relevant stock when all is displayed
     *
     * @return
     */
    public static int SelectSymbol() {
        PrintAllStock(AllStock);
        Scanner inputStock = new Scanner(System.in);
        System.out.println("Please enter the stock Ref #");
        int stockSelected = inputStock.nextInt();
        return stockSelected - 1;
    }

    /**
     * Method to allow user to enter the marketprice per share when buying or
     * selling stock
     *
     * @return mktPrice
     */
    public static double MarketPriceOfShare() {
        double mktPrice = 0;
        Scanner inputPrice = new Scanner(System.in);
        do {
            System.out.println("Enter Share Price  ==> ");
            mktPrice = inputPrice.nextDouble();
        } while (mktPrice <= 0);
        return mktPrice;
    }

    /**
     * Method to print all current stock in portfolio
     *
     * @param allStockInfoIn
     *
     */
    public static void PrintAllStock(ArrayList<CommonStock> allStockInfoIn) {
        System.out.println("\t === Current Stock Portfolio ===");
        System.out.print("\t Total Stocks = " + CommonStock.totalStocks + "\t");
        CommonStock.timeStampDateFormat(CommonStock.CurrentTime());
        for (int count = 0; count < allStockInfoIn.size(); count++) {
            System.out.println("Stock Ref. # " + (count + 1) + ". " + allStockInfoIn.get(count).toString());
        }// end of for loop  

    }// End of method

    /**
     * Method to print GBCE values of shares
     */
    public static void GBCEAllShareIndex() {
        double avgSharePriceWithZero = 0, counterWithZero = 0; // Variables to cater for ALL share prices
        double avgSharePriceWithoutZero = 0, counterWithoutZero = 0; // Variables to cater for share prices > 0 
        for (int count = 0; count < AllStock.size(); count++) {
            counterWithZero = counterWithZero + AllStock.get(count).getMarketValue();
        }// End for
        avgSharePriceWithZero = counterWithZero / AllStock.size();
        System.out.println("\t GBCE for ALL Stock ==> " + avgSharePriceWithZero);
        CommonStock.gbceAllStock = avgSharePriceWithZero; // Write this value then to the common stock variable

        //Next section of code calculates average price of stock with a market price greater than 0
        int countOfSharesNotZero = 0;
        for (int count = 0; count < AllStock.size(); count++) {
            if (AllStock.get(count).getMarketValue() <= 0) {
                continue;
            } else {
                countOfSharesNotZero++;
                counterWithoutZero = counterWithoutZero + AllStock.get(count).getMarketValue();
            }
        }
        avgSharePriceWithoutZero = counterWithoutZero / countOfSharesNotZero;
        System.out.println("GBCE of shares with value > 0 ==> " + avgSharePriceWithoutZero);

    }// End of GCBE method

    public static void VolumeWeightedStockPrice() {
        long timeNowInMins = CommonStock.currentTimeInMinutes(CommonStock.CurrentTime());
        long reportInterval = CommonStock.MILLISECONDS_IN_A_MINUTE * 15;
        long withinInterval = timeNowInMins - reportInterval;
        int counterAllInInterval = 0;
        double sumQuantity = 0, sumTPxQty = 0;
        
        for (int counterAll = 0; counterAll < CommonStock.allTransactions.size(); counterAll++) {
            if (CommonStock.allTransactions.get(counterAll).getTimeStamp() > withinInterval){
            System.out.print((counterAll+1)+ " . Symbol: " +CommonStock.allTransactions.get(counterAll).getStockSymbol());
           System.out.print( " Time :" +CommonStock.allTransactions.get(counterAll).getTimeStampFormatted());
           System.out.print( "  Buy-Sell : " +CommonStock.allTransactions.get(counterAll).getTransactionType());
           System.out.println( "  Price : " +CommonStock.allTransactions.get(counterAll).getTransactionPrice());
           System.out.print(" Quantity : " +CommonStock.allTransactions.get(counterAll).getQuantityOfShares());
            counterAllInInterval++;
            sumQuantity = CommonStock.allTransactions.get(counterAll).getQuantityOfShares();
            sumTPxQty = CommonStock.allTransactions.get(counterAll).getTransactionPrice() * CommonStock.allTransactions.get(counterAll).getQuantityOfShares();
            }
          }
        double vWPS = sumTPxQty / sumQuantity;
        System.out.println("For " +counterAllInInterval+ " trades the VWSP is " +vWPS);
        CommonStock.volumeWeightedStockPrice = vWPS; 
    }
} // End of class
