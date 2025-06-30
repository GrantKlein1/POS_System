package POSPD;
import java.math.*;
import java.time.*;
import java.time.format.*;

/**
 * represents the tax rate that is applied to items
 */
public class TaxRate implements Comparable<TaxRate>
{

	/**
	 * the tax rate that is applied to items
	 */
	private BigDecimal taxRate;
	/**
	 * the date the tax rate should be applied to items
	 */
	private LocalDate effectiveDate;

	public BigDecimal getTaxRate()
	{
		return this.taxRate;
	}

	public void setTaxRate(BigDecimal taxRate)
	{
		this.taxRate = taxRate;
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
	 * this is the default constructor for the TaxRate class, it initializes taxRate to null
	 */
	public TaxRate()
	{
		taxRate = null;
		
	}

	/**
	 * this is the constructor for the TaxRate class, it initializes the effectiveDate and taxRate class variables
	 * @param effectiveDate
	 * @param taxRate
	 */
	public TaxRate(String effectiveDate, BigDecimal taxRate)
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yy");
		CharSequence subSeq = effectiveDate.subSequence(0, effectiveDate.length());
		this.effectiveDate = LocalDate.parse(subSeq, formatter);
		this.taxRate = taxRate;
	}

	/**
	 * this checks if the tax rate is in effect for the passed in date
	 * @param date
	 * @return returns true if the tax rate is in effect, returns false if it is not
	 */
	public Boolean isEffective(LocalDate date)
	{
		if (date.isAfter(effectiveDate)) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * this compares the current taxRate with the passed in taxRate, 
	 * the result is a -1 if the passed in tax rate is greater, 
	 * a 0 if they are equal and a 1 if the passed in tax rate is less
	 * @param taxRate
	 * @return returns an integer indicating the result of the comparison
	 */
	public int compareTo(TaxRate taxrate) 
	{
		 return this.effectiveDate.compareTo(taxrate.effectiveDate);
	}

	/**
	 * creates a single String that contains everything that needs to be printed from the TaxRate class
	 * @return String that will be used to print result from the TaxRate class
	 */
	public String toString()
	{
		String TaxRateString = taxRate + "," + effectiveDate;
		return TaxRateString;
	}

}