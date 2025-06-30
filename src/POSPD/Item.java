package POSPD;
import java.math.*;
import java.time.*;
import java.util.*;

/**
 * represents the physical item that the store possesses and sells
 */
public class Item implements Comparable<Item>
{

	/**
	 * the identification number of the item
	 */
	private String number;
	/**
	 * the description of what the item is
	 */
	private String description;
	/**
	 * the tax category that the item falls in
	 */
	private TaxCategory taxCategory;
	/**
	 * the TreeSet collection of prices the item costs
	 */
	private TreeSet<Price> prices;
	/**
	 * the TreeMap collection of unique barcode number on the outside of each item
	 */
	private TreeMap<String, UPC> upcs;
	/**
	 * the ArrayList collection of the lines of information about the sold item
	 */
	private ArrayList<SaleLineItem> saleLineItems;

	public String getNumber()
	{
		return this.number;
	}

	public void setNumber(String number)
	{
		this.number = number;
	}

	public String getDescription()
	{
		return this.description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public TaxCategory getTaxCategory()
	{
		return this.taxCategory;
	}

	public void setTaxCategory(TaxCategory taxCategory)
	{
		this.taxCategory = taxCategory;
	}

	public TreeSet<Price> getPrices()
	{
		return this.prices;
	}

	public TreeMap<String, UPC> getUpcs()
	{
		return this.upcs;
	}

	public ArrayList<SaleLineItem> getSaleLineItems()
	{
		return this.saleLineItems;
	}

	/**
	 * this is the default constructor of the Item class, it initializes number and description to null
	 */
	public Item()
	{
		number = null;
		description = null;
		taxCategory = new TaxCategory();
		prices = new TreeSet<Price>();
		upcs = new TreeMap<String, UPC>();
		saleLineItems = new ArrayList<SaleLineItem>();
	}

	/**
	 * this is the constructor for the Item class, it initializes the number and description class variables
	 * @param number
	 * @param description
	 */
	public Item(String number, String description)
	{
		this.number = number;
		this.description = description;
		taxCategory = new TaxCategory();
		prices = new TreeSet<Price>();
		upcs = new TreeMap<String, UPC>();
		saleLineItems = new ArrayList<SaleLineItem>();
		taxCategory.addItem(this);
	}

	/**
	 * this adds the passed in price to the price collection
	 * @param price
	 */
	public void addPrice(Price price)
	{
		prices.add(price);
	}

	/**
	 * this removes the passed in price from the price collection
	 * @param price
	 */
	public void removePrice(Price price)
	{
		prices.remove(price);
	}

	/**
	 * this adds the passed in UPC to the UPC collection
	 * @param upcs
	 */
	public void addUPC(UPC upc)
	{
		upcs.put(upc.getUpc(), upc);

	}

	/**
	 * this removes the passed in UPC from the UPC collection
	 * @param upcs
	 */
	public void removeUPC(UPC upcs)
	{
		this.upcs.remove(upcs.getUpc(), upcs);
	}

	/**
	 * locates the price of an item on the passed in date
	 * @param date
	 * @return returns the price of the item
	 */
	public Price getPriceForDate(LocalDate date)
	{
		Price price = new Price();
		
	    for (Price p : prices) {
	    	if (p.isEffective(date)) {
	    		price = p;
	    	}
	    }

	    return price;
	
	}

	/**
	 * locates the tax rate on the passed in date
	 * @param date
	 * @return returns the tax rate
	 */
	public BigDecimal getTaxRateForDate(LocalDate date)
	{

		BigDecimal taxRate = new BigDecimal(0);
		
		for(TaxRate t : taxCategory.getTaxRates()) {
			if (t.isEffective(date)) {
				taxRate = t.getTaxRate();
			}
		}
		
		taxRate = taxRate.setScale(2, RoundingMode.HALF_UP);
		
		return taxRate;
	}

	/**
	 * calculates the price for an item based on the date and quantity passed in
	 * @param date
	 * @param quantity
	 * @return returns the calculated price
	 */
	public BigDecimal calcAmountForDateQty(LocalDate date, int quantity)
	{
		BigDecimal total = new BigDecimal(0);
		BigDecimal quantityBigDecimal = new BigDecimal(quantity);
		
		total = getPriceForDate(date).getPrice().multiply(quantityBigDecimal);
		
		return total;
	}

	/**
	 * this adds the passed in line item to the sale line item collection
	 * @param saleLineItem
	 */
	public void addSaleLineItem(SaleLineItem saleLineItem)
	{
		saleLineItems.add(saleLineItem);
	}

	/**
	 * this removes the passed in line item from the sale line item collection
	 * @param saleLineItem
	 */
	public void removeSaleLineItem(SaleLineItem saleLineItem)
	{
		saleLineItems.remove(saleLineItem);
	}

	/**
	 * creates a single String that contains everything that needs to be printed from the Item class
	 * @return String that will be used to print result from the Item class
	 */
	public String toString()
	{
		String itemString = getNumber() + " " + getDescription() + " " + getPriceForDate(LocalDate.now()).getPrice() + " " + getTaxRateForDate(LocalDate.now());
		return itemString;
	}
	
	/**
	 * creates a single String that contains part of what needs to be printed from the Item class
	 * @return String that will be used to print result from the Item class
	 */
	public String extraToString() {
		String itemString = "  " + getNumber() + "                " + getDescription();
		return itemString;
	}

	/**
	 * this compares the current Item with the passed in Item, the result is a -1 if the passed in Item is greater, a 0 if they are equal and a 1 if the passed in Item is less
	 * @param Item
	 * @return returns an integer indicating the result of the comparison
	 */
	public int compareTo(Item item) 
	{
		 return this.number.compareTo(item.number);
	}
	
}