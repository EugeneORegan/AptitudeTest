/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supersimplestockaptitudefeb2016;

/**
 *
 * @author Eugene OR
 */
public class PreferredStock extends CommonStock {
    private double fixedDividend;

    public PreferredStock(String symbol) {
        super(symbol);
    }

    public PreferredStock(String symbol, double lastDividend, double parValue, double fixedDividend) {
        super(symbol, lastDividend, parValue);
        this.fixedDividend = fixedDividend;
    }

    public PreferredStock(String symbol, double lastDividend, double parValue, 
                                        double marketValue, int quantityOfStock, double fixedDividend) {
        super(symbol, lastDividend, parValue, marketValue, quantityOfStock);
        this.fixedDividend = fixedDividend;
    }

    public double getFixedDividend() {
        return fixedDividend;
    }

    public void setFixedDividend(double fixedDividend) {
        if (fixedDividend < 0)  {
            CommonStock.totalStocks--; // Reduce this number as would have incremented it before coming to here
            throw new InvalidStockInfoInputRunTimeException("Preferred Sock Problem");
        } else {
        this.fixedDividend = fixedDividend;
    }
    }

    @Override
    public String toString() {
        String CommonStockInfo = super.toString().replace("CommonStock Info{", ""); // Prints CommonStock information
        return "PreferredStock Info{"+CommonStockInfo+   "fixedDividend=" + fixedDividend +   '}';
    }
    /**
     * Overridden method as Dividend Yield calculated differently for Preferred Stock
     * @param marketPriceIn
     * @return 
     */
  @Override
 public double dividendYield(double marketPriceIn)
    { double dividenedYieldOfStock = 0;
    if ((marketPriceIn <= 0) || this.fixedDividend == 0 || super.getParValue() == 0)
    {
        System.out.println("Either market price, fixed dividend or par value is invalid for this calculation - Ensure data is correct ");
         return 0;
    }else{
        super.setMarketValue(marketPriceIn); //Update the market value of the share
        dividenedYieldOfStock =  (this.fixedDividend * super.getParValue()) / marketPriceIn;
        System.out.println("Dividend Yield of " +super.getSymbol()); // Printout including Stock Symbol
        return dividenedYieldOfStock;
    } // End of if...else
        
    } //End of dividendYield method    
    
}// End of class
