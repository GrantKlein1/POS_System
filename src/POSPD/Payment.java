package POSPD;
import java.math.*;

/**
 * represents the payment from the customer to the store
 */
public class Payment
{

	/**
	 * the amount the person is supposed to pay for the sale
	 */
	private BigDecimal amount;
	/**
	 * the amount the person paid for the sale, could be more than the sale
	 */
	private BigDecimal amtTendered;
	
	/**
	 * boolean if payment is cash
	 */
	private boolean isCash;
	
	/**
	 * boolean if payment is credit
	 */
	private boolean isCredit;
	
	/**
	 * boolean if payment is check
	 */
	private boolean isCheck;
	
	public Boolean getIsCash() 
	{
		return isCash;
	}

	public Boolean getIsCredit() 
	{
		return isCredit;
	}
	
	public Boolean getIsCheck() 
	{
		return isCheck;
	}
	
	public BigDecimal getAmount()
	{
		return this.amount;
	}

	public void setAmount(BigDecimal amount)
	{
		this.amount = amount;
	}

	public BigDecimal getAmtTendered()
	{
		return this.amtTendered;
	}

	public void setAmtTendered(BigDecimal amtTendered)
	{
		this.amtTendered = amtTendered;
	}

	/**
	 * checks if the payment type counts as cash
	 * @return returns true if the payment type counts as cash, false if it cannot
	 */
	public Boolean countsAsCash()
	{
		this.isCash = true;
		return true;
	}
	
	/**
	 * checks if the payment type counts as credit
	 * @return returns true if the payment type counts as credit, false if it cannot
	 */
	public Boolean countsAsCredit()
	{
		this.isCredit = true;
		return true;
	}
	
	/**
	 * checks if the payment type counts as check
	 * @return returns true if the payment type counts as check, false if it cannot
	 */
	public Boolean countsAsCheck()
	{
		this.isCheck = true;
		return true;
	}

}