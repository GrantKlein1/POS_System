package POSPD;
import java.math.*;

/**
 * represents the cash drawer that sits in the register
 */
public class CashDrawer
{

	/**
	 * the total amount of cash in the drawer
	 */
	private BigDecimal cashAmount;
	/**
	 * this is an integer of 1 or 0 whether the drawer is open or closed
	 */
	private int position;

	public BigDecimal getCashAmount()
	{
		return this.cashAmount;
	}

	public void setCashAmount(BigDecimal cashAmount)
	{
		this.cashAmount = cashAmount;
	}

	public int getPosition()
	{
		return this.position;
	}

	public void setPosition(int position)
	{
		this.position = position;
	}

	/**
	 * this is the default constructor for the CashDrawer class, it initializes cashAmount to null and position to -1
	 */
	public CashDrawer()
	{
		cashAmount = new BigDecimal(0);
		position = -1;
	}

	/**
	 * this removes the passed in amount of cash from the drawer
	 * @param cash
	 */
	public void removeCash(BigDecimal cash)
	{
		cashAmount = cashAmount.subtract(cash);
	}

	/**
	 * this adds the passed in amount of cash to the drawer
	 * @param cash
	 */
	public void addCash(BigDecimal cash)
	{
		cashAmount = cashAmount.add(cash);
	}

	/**
	 * creates a single String that contains everything that needs to be printed from the CashDrawer class
	 * @return String that will be used to print result from the CashDrawer class
	 */
	public String toString()
	{
		cashAmount = cashAmount.setScale(2, RoundingMode.HALF_UP);
		String cashDrawerString = "Cash in Drawer Amount: $" + cashAmount + "\n";
		return cashDrawerString;
	}

}