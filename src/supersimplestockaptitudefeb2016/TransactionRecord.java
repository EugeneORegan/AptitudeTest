package supersimplestockaptitudefeb2016;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Class used to record buy or sell transactions in lieu of having no database
 * @author Eugene OR
 */
public class TransactionRecord {
    private String stockSymbol; 
    private char transactionType;
    private double transactionPrice;
    private int quantityOfShares;
    private long timeStamp;
    private String timeStampFormatted;

    public TransactionRecord(String stockSymbol, char transactionType, double transactionPrice, int quantityOfShares, 
            long timeStamp, String timeStampFormatted) {
        this.stockSymbol = stockSymbol;
        this.transactionType = transactionType;
        this.transactionPrice = transactionPrice;
        this.quantityOfShares = quantityOfShares;
        this.timeStamp = timeStamp;
        this.timeStampFormatted = timeStampFormatted;
    }

    public int getQuantityOfShares() {
        return quantityOfShares;
    }

    public void setQuantityOfShares(int quantityOfShares) {
        this.quantityOfShares = quantityOfShares;
    }

    public String getStockSymbol() {
        return stockSymbol;
    }

    public void setStockSymbol(String stockSymbol) {
        this.stockSymbol = stockSymbol;
    }

    public char getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(char transactionType) {
        this.transactionType = transactionType;
    }

    public double getTransactionPrice() {
        return transactionPrice;
    }

    public void setTransactionPrice(double transactionPrice) {
        this.transactionPrice = transactionPrice;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public String toString() {
        return "TransactionRecord{" + "stockSymbol=" + stockSymbol + ", transactionType=" + transactionType + ", transactionPrice=" + transactionPrice + ", quantityOfShares=" + quantityOfShares + ", timeStamp=" + timeStamp + ", timeStampFormatted=" + timeStampFormatted + '}';
    }
    
    
//    public String timeOfTransaction()
//    {
//       DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd / hh:mm:ss");
//      String dateStringFormat = dateFormat.format(this.timeStamp);
//        System.out.println(" Transaction Date / Time : " +dateStringFormat);
//     //   System.out.print(this.timeStamp+ " " +this.getTimeStamp()+ "====");
//        return dateStringFormat;
//    }

    public String getTimeStampFormatted() {
        return timeStampFormatted;
    }

    public void setTimeStampFormatted(String timeStampFormatted) {
        this.timeStampFormatted = timeStampFormatted;
    }
   
    
    
    
}
