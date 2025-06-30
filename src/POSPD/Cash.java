package POSPD;
import java.math.*;

/**
 * represents the cash the customer pays the store
 */
public class Cash extends Payment
{

	/**
	 * this is the default constructor of the Cash class
	 */
	public Cash()
	{
		super.setAmount(new BigDecimal(0));
		super.setAmtTendered(new BigDecimal(0));
		super.countsAsCash();
	}

	/**
	 * this is the constructor for the Cash class, it initializes the amount and amtTendered class variables
	 * @param amount
	 * @param amtTendered
	 */
	public Cash(String amount, BigDecimal amtTendered)
	{
		this.setAmount(new BigDecimal(amount));
		this.setAmtTendered(amtTendered);
		super.countsAsCash();
	}

	/**
	 * checks if the cash is acceptable
	 * @return returns true if it is cash and false if it is not
	 */
	public Boolean countsAsCash()
	{
		return true;
	}

	/**
	 * creates a single String that contains everything that needs to be printed from the Cash class
	 * @return String that will be used to print result from the Cash class
	 */
	public String toString()
	{
		String cashString = "\nAmount in Cash: $" + super.getAmount() + "\nAmount Tendered: $" + super.getAmtTendered() + "\n";
		return cashString;
	}

}