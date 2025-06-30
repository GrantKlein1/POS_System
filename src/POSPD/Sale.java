package POSPD;
import java.math.*;
import java.time.*;
import java.util.*;

/**
 * this represents the sale of item(s) from the store
 */
public class Sale
{

	/**
	 * the time and day the sale was made
	 */
	private LocalDateTime dateTime;
	/**
	 * the true or false value of whether the sale is tax free or not
	 */
	private Boolean taxFree;
	/**
	 * the ArrayList collection of the line of information about the sold item
	 */
	private ArrayList<SaleLineItem> saleLineItems = new ArrayList<>();
	/**
	 * the ArrayList collection of payments that was used for the sale
	 */
	private ArrayList<Payment> payments = new ArrayList<>();
	/**
	 * the number of cash payments
	 */
	private int cashPaymentCount;
	/**
	 * the number of credit payments
	 */
	private int creditPaymentCount;
	/**
	 * the number of check payments
	 */
	private int checkPaymentCount;
	
	public int getCashPaymentCount() 
	{
		return cashPaymentCount;
	}
	
	public int getCreditPaymentCount() 
	{
		return creditPaymentCount;
	}
	
	public int getCheckPaymentCount() 
	{
		return checkPaymentCount;
	}

	public LocalDateTime getDateTime()
	{
		return this.dateTime;
	}

	public void setDateTime(LocalDateTime dateTime)
	{
		this.dateTime = dateTime;
	}

	public Boolean getTaxFree()
	{
		return this.taxFree;
	}

	public void setTaxFree(Boolean taxFree)
	{
		this.taxFree = taxFree;
	}

	public ArrayList<SaleLineItem> getSaleLineItems()
	{
		return this.saleLineItems;
	}

	public ArrayList<Payment> getPayments()
	{
		return this.payments;
	}

	/**
	 * this is the default constructor for the Sale class, it initializes dateTime to null and taxFree to false
	 */
	public Sale()
	{
		dateTime = null;
		taxFree = false;
	}

	/**
	 * this is the constructor for the Sale class, it initializes the dateTime to the current time and creates blank saleLineItems and payments ArrayLists
	 * @param taxFree
	 */
	public Sale(String taxFree)
	{
		dateTime = LocalDateTime.now();
		if (taxFree == "Y") {
			this.taxFree = true;
		}
		else {
			this.taxFree = false;
		}
		saleLineItems = new ArrayList<SaleLineItem>();
		payments = new ArrayList<Payment>();
	}

	/**
	 * adds a payment to the class's payments collection
	 * @param payment
	 */
	public void addPayment(Payment payment)
	{
		payments.add(payment);
	}

	/**
	 * removes a payment to the class's payments collection
	 * @param payment
	 */
	public void removePayment(Payment payment)
	{
		payments.remove(payment);
	}

	/**
	 * adds a sale line item to the class's sale line item collection
	 * @param saleLineItem
	 */
	public void addSaleLineItem(SaleLineItem saleLineItem)
	{
		saleLineItems.add(saleLineItem);
	}

	/**
	 * removes a sale line item to the class's sale line item collection
	 * @param saleLineItem
	 */
	public void removeSaleLineItem(SaleLineItem saleLineItem)
	{
		saleLineItems.remove(saleLineItem);
	}

	/**
	 * calculates the total dollar amount the items will all cost together
	 * @return returns the total dollar amount of the sale
	 */
	public BigDecimal calcTotal()
	{
		BigDecimal total = new BigDecimal(0);
		
		total = total.add(calcSubTotal());
		
		if (!taxFree) {
			total = total.add(calcTax());
		}
		
		return total;
	}

	/**
	 * calculates the subtotal, total without tax, of all the items will cost together
	 * @return returns the subtotal of the sale
	 */
	public BigDecimal calcSubTotal() 
	{
		BigDecimal subTotal = new BigDecimal(0);
			
		for (int i = 0; i < saleLineItems.size(); i++) {
			subTotal = subTotal.add(saleLineItems.get(i).calcSubTotal());//gets the untaxed subtotal
			
			//subTotal = subTotal.subtract(saleLineItems.get(i).calcTax());//subtracts the tax so you have the untaxed subtotal
		}
		
		return subTotal;
		
	}
	
	/**
	 * calculates the tax amount of the sale
	 * @return returns the total tax amount of the sale
	 */
	public BigDecimal calcTax()
	{
		BigDecimal totalTax = new BigDecimal(0);
		if (!taxFree) {
			for (int i = 0; i < saleLineItems.size(); i++) {
				totalTax = totalTax.add(saleLineItems.get(i).calcTax());
			}
		}
		return totalTax;
	}

	/**
	 * calculates the combined total of the payments used for the sale
	 * @return returns the total of the payments used
	 */
	public BigDecimal getTotalPayments()
	{
		BigDecimal totalPayments = new BigDecimal(0);
		
		for (int i = 0; i < payments.size(); i++) {
			totalPayments = totalPayments.add(payments.get(i).getAmtTendered());
		}
		
		return totalPayments;
	}

	/**
	 * checks if the amount the person paid was enough for the sale
	 * @return returns true if the person paid enough, false if they did not
	 */
	public Boolean isPaymentEnough()
	{	
		if((getTotalPayments().equals(calcTotal())) || (getTotalPayments().compareTo(calcTotal()) > 0)) { //greater than 0 if calcTotal is bigger
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * calculates the amount the person needs to pay for the sale
	 * @param amtTendered
	 * @return returns the total the person needs to pay
	 */
	public BigDecimal calcAmountForPayment(BigDecimal amtTendered)
	{
		
		BigDecimal total = new BigDecimal(0);
		
		total = calcTotal().subtract(amtTendered);
		
		return total;
		
	}
	

	/**
	 * calculates the change of the sale
	 * @return returns the change of the sale
	 */
	public BigDecimal calcChange()
	{
		
		BigDecimal change = new BigDecimal(0);
		
		//if(!calcAmountTendered().equals(new BigDecimal(0))) {
			change = calcAmountTendered().subtract(calcTotal());
		//}
		
		return change;

	}

	/**
	 * calculates the amount the person was charged for the sale
	 * @return returns the amount the person was charged
	 */
	public BigDecimal calcAmountTendered()
	{
		BigDecimal totalAmtTendered = new BigDecimal(0);
		
		for (int i = 0; i < payments.size(); i++) {
			totalAmtTendered = totalAmtTendered.add(payments.get(i).getAmtTendered());
		}
		
		return totalAmtTendered;
		
	}
	

	public int numberOfItems() {
		int numOfItems = 0;
		for (SaleLineItem sli : getSaleLineItems()) {
			numOfItems += sli.getQuantity();
		}
		return numOfItems;
	}
	
	/**
	 * calculates each of the payment types and sets the class variables appropriately
	 * @return returns nothing
	 */
	public void countPaymentTypes() {
		for (Payment payment : getPayments()) {
			if (payment.getIsCash()) {
				this.cashPaymentCount++;
			}
			else if (payment.getIsCheck()) {
				this.checkPaymentCount++;
			}
			else if (payment.getIsCredit()){
				this.creditPaymentCount++;
			}
		}
	}
	
	/**
	 * creates a single String that contains everything that needs to be printed from the Sale class
	 * @return String that will be used to print result from the Sale class
	 */
	public String toString()
	{ 
		String saleString = "\n----------------------------------\n" +  "\nSale line items:\n";
		
		for (int i = 0; i < saleLineItems.size(); i++) {
			saleString = saleString + "\n" + saleLineItems.get(i).toString(); 
		}
		
		saleString += "\nSubtotal: $" + calcSubTotal() + "\nTax: $" + calcTax() +  "\nTotal: $" + calcTotal() + "\n" + "\n\nChange: " + calcChange() + "\n\n";
		
		for (int i = 0; i < payments.size(); i++) {
			saleString = saleString +  "Payment Type: " + "\n";//payments.get(i).getClass().getName().substring(12) + "\n";
		}
		
		return saleString;
	}

}