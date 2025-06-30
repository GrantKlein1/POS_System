package POSPD;
import java.math.*;

/**
 * represents the check the customer uses to purchase item(s)
 */
public class Check extends AuthorizedPayment
{

	/**
	 * the number used to send and receive money from the customer's bank account
	 */
	private String routingNumber;
	/**
	 * the number uniquely identifying the customer's bank account
	 */
	private String accountNumber;
	/**
	 * the unique identifying number on the check
	 */
	private String checkNumber;

	public String getRoutingNumber()
	{
		return this.routingNumber;
	}

	public void setRoutingNumber(String routingNumber)
	{
		this.routingNumber = routingNumber;
	}

	public String getAccountNumber()
	{
		return this.accountNumber;
	}

	public void setAccountNumber(String accountNumber)
	{
		this.accountNumber = accountNumber;
	}

	public String getCheckNumber()
	{
		return this.checkNumber;
	}

	public void setCheckNumber(String checkNumber)
	{
		this.checkNumber = checkNumber;
	}

	/**
	 * this is the default constructor for the Check class
	 */
	public Check()
	{
		super.setAmount(new BigDecimal(0));
		super.setAmtTendered(new BigDecimal(0));
		super.countsAsCheck();
		routingNumber = "";
		accountNumber = "";
	}

	/**
	 * this is the constructor for the Check class, it initializes the amount, accountNumber, and checkNumber class variables
	 * @param amount
	 * @param accountNumber
	 * @param checkNumber
	 */
	public Check(String amount, String accountNumber, String checkNumber)
	{
		this.setAmount(new BigDecimal(amount));
		this.setAmtTendered(new BigDecimal(amount));
		this.accountNumber = accountNumber;
		this.checkNumber = checkNumber;
		super.countsAsCheck();
	}

	/**
	 * checks if the check is a valid payment
	 * @return returns true if the check is valid, false if it is not
	 */
	public Boolean isAuthorized()
	{
		return true;
	}

	/**
	 * creates a single String that contains everything that needs to be printed from the Check class
	 * @return String that will be used to print result from the Check class
	 */
	public String toString()
	{
		String checkString = "\nAmount: $" + super.getAmount() + "\nAmount Tendered: $" + super.getAmtTendered() + "\nAccount Number: " + getAccountNumber() + "\nCheck Number: " + getCheckNumber() + "\n";
		return checkString;
	}

}