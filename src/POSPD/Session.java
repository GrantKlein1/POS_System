package POSPD;
import java.math.*;
import java.time.*;
import java.time.format.*;
import java.util.*;

/**
 * this represents the session of work the cashier is in
 */
public class Session
{

	/**
	 * the start time of the cashier at the register
	 */
	private LocalDateTime startDateTime;
	/**
	 * the end time of the cashier's time at the register
	 */
	private LocalDateTime endDateTime;
	/**
	 * the cashier who is working the session
	 */
	private Cashier cashier;
	/**
	 * the register that is being worked during the session
	 */
	private Register register;
	/**
	 * the ArrayList collection of sales generated during the session
	 */
	private ArrayList<Sale> sales = new ArrayList<>();
	/**
	 * the ArrayList collection of sales generated during the session
	 */
	private BigDecimal countedCash;
	
	public BigDecimal getCountedCash()
	{
		return this.countedCash;
	}

	public void setCountedCash(BigDecimal cash)
	{
		this.countedCash = cash;
	}
	public LocalDateTime getStartDateTime()
	{
		return this.startDateTime;
	}

	public void setStartDateTime(LocalDateTime startDateTime)
	{
		this.startDateTime = startDateTime;
	}

	public LocalDateTime getEndDateTime()
	{
		return this.endDateTime;
	}

	public void setEndDateTime(LocalDateTime endDateTime)
	{
		this.endDateTime = endDateTime;
	}

	public Cashier getCashier()
	{
		return this.cashier;
	}

	public void setCashier(Cashier cashier)
	{
		this.cashier = cashier;
	}

	public Register getRegister()
	{
		return this.register;
	}

	public void setRegister(Register register)
	{
		this.register = register;
	}

	public ArrayList<Sale> getSales()
	{
		return this.sales;
	}

	/**
	 * this is the default constructor of the Session class, it initializes startDateTime and endDateTime to null
	 */
	public Session()
	{
		startDateTime = null;
		endDateTime = null;
		setCountedCash(new BigDecimal(0));
	}

	/**
	 * this is the constructor for the Session class, it initializes startDateTime to the current time and creates empty cashier, register and sales objects
	 * @param cashier
	 * @param register
	 */
	public Session(Cashier cashier, Register register)
	{
		this.startDateTime = LocalDateTime.now();
		this.cashier = cashier;
		this.register = register;
		sales = new ArrayList<Sale>();
		setCountedCash(new BigDecimal(0));
		
	}

	/**
	 * adds a Sale to the Session
	 * @param sale
	 */
	public void addSale(Sale sale)
	{
		sales.add(sale);
	}

	/**
	 * removes a sale from the session
	 * @param sale
	 */
	public void removeSale(Sale sale)
	{
		sales.remove(sale);
	}

	/**
	 * calculates the difference between the total sales made and the cash in the drawer
	 * @param cash
	 * @return the difference between the cash in the drawer and the total sales
	 */
	public BigDecimal calcCashCountDiff(BigDecimal cash)
	{
		BigDecimal difference = new BigDecimal(0);
		
		difference = cash.subtract(register.getCashDrawer().getCashAmount());
		
		return difference;
	}

	/**
	 * calculates the total of the entire session
	 * @return the total amount of sales that were made during the session
	 */
	public BigDecimal calcTotal()
	{
		BigDecimal total = new BigDecimal(0);
		
		for (int i = 0; i < sales.size(); i++) {
			total = total.add(sales.get(i).calcTotal());
		}
		
		
		return total;
	}

	/**
	 * creates a single String that contains everything that needs to be printed from the Session class
	 * @return String that will be used to print result from the Session class
	 */
	public String toString()
	{
		String sessionString = "Cashier: " + getCashier().getPerson().getName() + "\nRegister: " + register.getNumber() + "\n";
		
		
		sessionString = sessionString + "\nTotal Sales: $" + calcTotal() + "\n";
		
		for (int i = 0; i < sales.size(); i++) {
			sessionString = sessionString + sales.get(i).toString();
		}
		
		sessionString = sessionString + "\n----------------------------------\n";
		return sessionString;
	}

	/**
	 * calculates the total of the all the sales in the session
	 * @return BigDecimal total of all sales in session
	 */
	public BigDecimal getTotalSales() {
		BigDecimal totalSales = new BigDecimal(0);
		for (Sale sale : sales) {
			totalSales = totalSales.add(sale.calcTotal());
		}
		return totalSales;
	}

}