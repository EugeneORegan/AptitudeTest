
package supersimplestockaptitudefeb2016;

/**
 *Custom exception class to prevent stock being set up with negative values for certain figures
 * @author Eugene O' Regan
 * @since Feb. 2016
 */

public class InvalidStockInfoInputRunTimeException extends RuntimeException {
String stock;
/**
 * Constructor which takes in symbol for the stock that has caused the exception which is 
 * printed in a relevant error message
 * @param stock 
 *  
 */
    public InvalidStockInfoInputRunTimeException(String stock) {
        this.stock = stock;
        System.out.println("Invalid information inputted for " +stock+ " shares");
        System.out.println("Negative values prohibited. Please re-input");
    }

    
}
