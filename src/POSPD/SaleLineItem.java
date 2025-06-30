package POSPD;
import java.math.*;
import java.time.*;
import java.util.*;

/**
 * the line of information about an item that is sold
 */
public class SaleLineItem
{

	/**
	 * the sale the sale line item is a part of
	 */
	private Sale sale;
	/**
	 * the number of items
	 */
	private int quantity;
	/**
	 * the item that is being sold
	 */
	private Item item = new Item();

	public Sale getSale()
	{
		return this.sale;
	}

	public void setSale(Sale sale)
	{
		this.sale = sale;
	}

	public int getQuantity()
	{
		return this.quantity;
	}

	public void setQuantity(int quantity)
	{
		this.quantity = quantity;
	}

	public Item getItem()
	{
		return this.item;
	}

	public void setItem(Item item)
	{
		this.item = item;
	}

	/**
	 * this is the default constructor for the SaleLineItem class, it initializes the quantity to 0
	 */
	public SaleLineItem()
	{
		quantity = 0;
	}

	/**
	 * this is the constructor for the SaleLineItem class, it initializes sale, item, and quantity class variables
	 * @param sale
	 * @param item
	 * @param quantity
	 */
	public SaleLineItem(Sale sale, Item item, String quantity)
	{
		this.sale = sale;
		this.item = item;
		this.quantity = Integer.parseInt(quantity);
	}

	/**
	 * calculates the total before tax for the item(s)
	 * @return returns the subtotal of the items
	 */
	public BigDecimal calcSubTotal()
	{
		
		BigDecimal subTotal = new BigDecimal(0);
		BigDecimal quantityBigDecimal = new BigDecimal(quantity);
		
		subTotal = subTotal.add(item.getPriceForDate(LocalDate.now()).getPrice());
		
		subTotal = subTotal.multiply(quantityBigDecimal);
		
		
		subTotal = subTotal.setScale(2, RoundingMode.HALF_UP);
		
		return subTotal;
		
	}

	/**
	 * calculates the tax that will be charged for the item(s)
	 * @return returns the tax for the items
	 */
	public BigDecimal calcTax()
	{
		BigDecimal tax = new BigDecimal(0);
		if (sale.getTaxFree() == false) {
			BigDecimal quantityBigDecimal = new BigDecimal(quantity);
			
			tax = tax.add(item.getPriceForDate(LocalDate.now()).getPrice());
			tax = tax.multiply(quantityBigDecimal);
			
			tax = tax.multiply(item.getTaxRateForDate(LocalDate.now()));
			
			tax = tax.setScale(2, RoundingMode.HALF_UP);
		}
		return tax;
	}

	/**
	 * creates a single String that contains everything that needs to be printed from the SaleLineItem class
	 * @return String that will be used to print result from the SaleLineItem class
	 */
	public String toString()
	{
		//String sliString = "Item: " + item.getDescription() + "\nPrice: $" + item.getPriceForDate(LocalDate.now()).getPrice() + "\nQuantity: " + quantity + "\nTax: $" + calcTax() + "\nSubtotal: $" + calcSubTotal() +  "\nTotal: $" + calcTax().add(calcSubTotal()) + "\n";
		String sliString = item.getNumber() + " " + item.getDescription() + " " + getQuantity() + "@" + calcSubTotal() + " " + calcTax().add(calcSubTotal());
		return sliString;
	}

}