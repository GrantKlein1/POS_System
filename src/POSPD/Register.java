package POSPD;
import java.util.*;

/**
 * this represents the register that collects money in the store
 */
public class Register
{

	/**
	 * the identification number of the register
	 */
	private String number;
	/**
	 * the cash drawer associated with the register
	 */
	private CashDrawer cashDrawer;
	/**
	 * the current session that a cashier is working on the register
	 */
	private ArrayList<Session> session;

	public String getNumber()
	{
		return this.number;
	}

	public void setNumber(String number)
	{
		this.number = number;
	}

	public CashDrawer getCashDrawer()
	{
		return this.cashDrawer;
	}

	public void setCashDrawer(CashDrawer cashDrawer)
	{
		this.cashDrawer = cashDrawer;
	}

	public ArrayList<Session> getSession()
	{
		return this.session;
	}

	public void setSession(ArrayList<Session> session)
	{
		this.session = session;
	}

	/**
	 * this is the default constructor of the Register class, it initializes number to null
	 */
	public Register()
	{
		
	}

	/**
	 * this is the constructor of the Register class, it initializes number and creates a blank cashDrawer and session
	 * @param number
	 */
	public Register(String number)
	{
		this.number = number;
		cashDrawer = new CashDrawer();
		session = new ArrayList<Session>();
	}

	/**
	 * creates a single String that contains everything that needs to be printed from the Register class
	 * @return String that will be used to print result from the Register class
	 */
	public String toString()
	{
		String registerString = number;//"Register number: " + number + "\n" + cashDrawer.toString() + "\n";
		return registerString;
	}

	/**
	 * checks to see if this register has any sessions that have any sales in them
	 * @return boolean used that shows whether or not the register is active
	 */
	public boolean Used() {
		boolean used = true;
		if (session == null) {
			return false;
		}
		return used;
	}

}