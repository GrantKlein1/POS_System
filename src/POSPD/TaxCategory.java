package POSPD;
import java.math.*;
import java.time.*;
import java.util.*;

/**
 * this represents the tax category that applies to Items
 */
public class TaxCategory implements Comparable<TaxCategory>
{

	/**
	 * this is the category of tax the item is in
	 */
	private String category;
	/**
	 * this is the TreeSet collection of tax rates that applies to the Items
	 */
	private TreeSet<TaxRate> taxRates;
	/**
	 * this is the TreeMap collections of items that tax category contains
	 */
	private TreeMap<String, Item> items;

	public String getCategory()
	{
		return this.category;
	}

	public void setCategory(String category)
	{
		this.category = category;
	}

	public TreeSet<TaxRate> getTaxRates()
	{
		return this.taxRates;
	}

	public TreeMap<String, Item> getItems()
	{
		return this.items;
	}
	
	public void addItem(Item item) {
		items.put(item.getNumber(), item);
	}

	/**
	 * this is the default constructor for the taxCategory class, it initializes category to null
	 */
	public TaxCategory()
	{
		taxRates = new TreeSet<TaxRate>();
		items = new TreeMap<String, Item>();
	}

	/**
	 * this is the constructor for the TaxCategory class, it initializes category and creates new blank items and taxRates
	 * @param category
	 */
	public TaxCategory(String category)
	{
		this.category = category;
		taxRates = new TreeSet<TaxRate>();
		items = new TreeMap<String, Item>();
	}

	/**
	 * this locates the tax rate for the passed in date
	 * @param date
	 * @return returns the tax rate for a certain date
	 */
	public BigDecimal getTaxRateForDate(LocalDate date)
	{
		
		BigDecimal taxRate = new BigDecimal(0);
		
		for(TaxRate t : taxRates) {
			if (t.isEffective(date)) {
				taxRate = t.getTaxRate();
			}
		}
		taxRate = taxRate.setScale(2, RoundingMode.HALF_UP);
		return taxRate;
	}

	/**
	 * this adds a tax rate to the taxRates collection
	 * @param taxRate
	 */
	public void addTaxRate(TaxRate taxRate)
	{
		taxRates.add(taxRate);
	}

	/**
	 * this removes a tax rate from the taxRate collection
	 * @param taxRate
	 */
	public void removeTaxRate(TaxRate taxRate)
	{
		taxRates.remove(taxRate);
	}

	/**
	 * creates a single String that contains everything that needs to be printed from the TaxCategory class
	 * @return String that will be used to print result from the TaxCategory class
	 */
	public String toString()
	{
		String taxCategoryString = getCategory();
		/*
		for (TaxRate element : taxRates) {
			taxCategoryString = taxCategoryString + element.getTaxRate() + ", " + element.getEffectiveDate();
		}
		*/
		return taxCategoryString;
	}
	
	
	/**
	 * this compares the current Item with the passed in Item, the result is a -1 if the passed in Item is greater, a 0 if they are equal and a 1 if the passed in Item is less
	 * @param Item
	 * @return returns an integer indicating the result of the comparison
	 */
	public int compareTo(TaxCategory taxCat) 
	{
		 return this.category.compareTo(taxCat.category);
	}

}