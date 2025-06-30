package POSPD;
import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * represents the promotional price of an item
 */
public class PromoPrice extends Price
{

	/**
	 * the date the promo price stops being in effect
	 */
	private LocalDate endDate;

	public LocalDate getEndDate()
	{
		return this.endDate;
	}

	public void setEndDate(String endDate)
	{
		if (endDate.charAt(1) == '/' && endDate.charAt(3) == '/') {
			this.endDate = LocalDate.parse(endDate, DateTimeFormatter.ofPattern("M/d/yy"));
		}
		else if (endDate.charAt(3) == '/' && !(endDate.charAt(3) == '/')) {
			this.endDate = LocalDate.parse(endDate, DateTimeFormatter.ofPattern("M/dd/yy"));
		}
		else {
			if (endDate.length() > 8) {
				char hold = endDate.charAt(8);
				char holder = endDate.charAt(9);
				endDate = endDate.substring(0, 6);
				endDate = endDate + hold + holder;
				this.endDate = LocalDate.parse(endDate, DateTimeFormatter.ofPattern("MM/dd/yy"));
			}
			else {
				this.endDate = LocalDate.parse(endDate, DateTimeFormatter.ofPattern("MM/dd/yy"));
			}
		}
	}

	/**
	 * this is the default constructor of the PromoPrice class, it initializes endDate to null
	 */
	public PromoPrice()
	{
		endDate = null;
	}

	/**
	 * this is the constructor of the PromoPrice class, it initializes the price, effectiveDate and endDate class variables
	 * @param price
	 * @param effectiveDate
	 * @param endDate
	 */
	public PromoPrice(String price, String effectiveDate, String endDate)
	{

		super(price, effectiveDate);
		if (endDate.charAt(1) == '/' && endDate.charAt(3) == '/') {
			this.endDate = LocalDate.parse(endDate, DateTimeFormatter.ofPattern("M/d/yy"));
		}
		else if (endDate.length() <= 8) {
			this.endDate = LocalDate.parse(endDate, DateTimeFormatter.ofPattern("MM/dd/yy"));
		}
		else {
			this.endDate = LocalDate.parse(endDate, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
		}
		
	}

	/**
	 * checks if the promotional price is in effect for the passed in date
	 * @param date
	 * @return returns true if the price is in effect, false if it is not
	 */
	public Boolean isEffective(LocalDate date)
	{
		Boolean isInEffect = false;
		
		//might need more parentheses
		if ((date.isAfter(this.getEffectiveDate()) || date.equals(this.getEffectiveDate())) && date.isBefore(endDate)) {
			isInEffect = true;
		}
		else {
			isInEffect = false;
		}
		
		return isInEffect;
	}

	/**
	 * creates a single String that contains everything that needs to be printed from the PromoPrice class
	 * @return String that will be used to print result from the PromoPrice class
	 */
	public String toString()
	{
		String PromoString = super.getPrice() + " " + super.getEffectiveDate() + " " + getEndDate();
		return PromoString;
	}

}