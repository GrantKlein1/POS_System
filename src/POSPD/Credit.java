package POSPD;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.math.*;

/**
 * represents the credit card the customer uses to purchase item(s)
 */
public class Credit extends AuthorizedPayment
{

	/**
	 * the type of credit card used to pay for the items
	 */
	private String cardType;
	/**
	 * the account number tied to the credit card
	 */
	private String accountNumber;
	/**
	 * the expiration date tied to the credit card
	 */
	private LocalDate expireDate;

	public String getCardType()
	{
		return this.cardType;
	}

	public void setCardType(String cardType)
	{
		this.cardType = cardType;
	}

	public String getAccountNumber()
	{
		return this.accountNumber;
	}

	public void setAccountNumber(String accountNumber)
	{
		this.accountNumber = accountNumber;
	}

	public LocalDate getExpireDate()
	{
		return this.expireDate;
	}

	public void setExpireDate(LocalDate expireDate)
	{
		this.expireDate = expireDate;
	}

	/**
	 * this is the default constructor for the Credit class, it initializes all class variables to null
	 */
	public Credit()
	{
		cardType = null;
		accountNumber = null;
		expireDate = null;
		super.countsAsCredit();
	}

	/**
	 * this is the constructor for the Credit class, it initializes the cardType, accountNumber, and expireDate class variables
	 * @param amount
	 * @param cardType
	 * @param accountNumber
	 * @param expireDate
	 */
	public Credit(String amount, String cardType, String accountNumber, String expireDate)
	{
		this.setAmount(new BigDecimal(amount));
		this.setAmtTendered(new BigDecimal(amount));
		this.cardType = cardType;
		this.accountNumber = accountNumber;
		super.countsAsCredit();
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/M/yyyy");
		CharSequence subSeq = expireDate.subSequence(0, expireDate.length());
		
		this.expireDate = LocalDate.parse(subSeq, formatter);
	}

	/**
	 * checks if the credit card payment is an authorized payment
	 * @return returns true if the payment is valid, false if it is not
	 */
	public Boolean isAuthorized()
	{
		if(expireDate.isAfter(LocalDate.now())) {//maybe take out if having trouble
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * creates a single String that contains everything that needs to be printed from the Credit class
	 * @return String that will be used to print result from the Credit class
	 */
	public String toString()
	{
		String creditString = "\nAmount: $" + super.getAmount() + "\nAmount Tendered: $" + super.getAmtTendered() + "\nCard Type: " + getCardType() + "\nAccount Number: " + getAccountNumber() + "\nExpiration Date: " + getExpireDate() + "\n";
		return creditString;
	}

}