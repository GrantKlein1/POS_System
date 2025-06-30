package POSPD;
import java.math.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.GregorianCalendar;

/**
 * represents the price the item costs
 */
public class Price implements Comparable<Price>
{

	/**
	 * the item that is being priced
	 */
	private Item item;
	/**
	 * the price the item costs without tax
	 */
	private BigDecimal price;
	/**
	 * the date that the price for the item goes into effect
	 */
	private LocalDate effectiveDate;

	public Item getItem()
	{
		return this.item;
	}

	public void setItem(Item item)
	{
		this.item = item;
	}

	public BigDecimal getPrice()
	{
		price = price.setScale(2, RoundingMode.HALF_UP);
		return this.price;
	}

	public void setPrice(BigDecimal price)
	{
		this.price = price;
	}

	public LocalDate getEffectiveDate()
	{
		return this.effectiveDate;
	}

	public void setEffectiveDate(LocalDate effectiveDate)
	{
		this.effectiveDate = effectiveDate;
	}

	/**
	 * this is the default constructor of the Price class, it initializes price and effectiveDate to null
	 */
	public Price()
	{
		price = null;
		effectiveDate = null;
	}

	/**
	 * this is the constructor for the Price class, it initializes the price and effectiveDate class variables
	 * @param price
	 * @param effectiveDate
	 */
	public Price(String price, String effectiveDate)
	{
		this.price = new BigDecimal(price);
		//String[] efDate = effectiveDate.split("/");
		//GregorianCalendar greg = new GregorianCalendar(Integer.parseInt(efDate[2]), Integer.parseInt(efDate[0]), Integer.parseInt(efDate[1]));
		
		if (effectiveDate.charAt(1) == '/' && effectiveDate.charAt(3) == '/') {
			this.effectiveDate = LocalDate.parse(effectiveDate, DateTimeFormatter.ofPattern("M/d/yy"));
		}
		else if (effectiveDate.length() <= 8) {
			this.effectiveDate = LocalDate.parse(effectiveDate, DateTimeFormatter.ofPattern("MM/dd/yy"));
		}
		else {
			this.effectiveDate = LocalDate.parse(effectiveDate, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
		}
		
		
	}

	/**
	 * checks if the price's date is in effect
	 * @param date
	 * @return returns true if the price is in effect, false if it is not
	 */
	public Boolean isEffective(LocalDate date)
	{
		Boolean isInEffect = false;
		
		if (date.isAfter(effectiveDate) || date.equals(effectiveDate)) {
			isInEffect = true;
		}
		else {
			isInEffect = false;
		}
		
		return isInEffect;
	}

	/**
	 * calculates the total price for the items by multiplying their price by the passed in quantity
	 * @param quantity
	 * @return returns the total price for the items
	 */
	public BigDecimal calcAmountForQuantity(int quantity)
	{
		BigDecimal total = new BigDecimal(0);
		BigDecimal quantityBigDecimal = new BigDecimal(quantity);
		
		total = price.multiply(quantityBigDecimal);
		
		return total;
	}

	/**
	 * this compares the current price with the passed in price, the result is a -1 if the passed in price is greater, a 0 if they are equal and a 1 if the passed in price is less
	 * @param price
	 * @return returns an integer indicating the result of the comparison
	 */
	public int compareTo(Price price)
	{
	
		return price.getPrice().compareTo(this.price);
		
	}
	
	/**
	 * creates a single String that contains everything that needs to be printed from the Price class
	 * @return String that will be used to print result from the Price class
	 */
	public String toString()
	{
		String priceString = "Price: " + price + "\n";
		return priceString;
	}
	


}