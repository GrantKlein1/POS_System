package POSPD;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

/**
 * this represents the cashier checking out the customer
 */
public class Cashier implements Comparable<Cashier>
{
	/**
	 * the cashier's identification number
	 */
	private String number;
	/**
	 * the ArrayList collection of sessions the cashier works
	 */
	private ArrayList<Session> sessions;
	/**
	 * the cashier's login password
	 */
	private String password;
	/**
	 * the person the cashier is currently working with
	 */
	private Person person;
	
	public String getNumber()
	{
		return this.number;
	}

	public void setNumber(String number)
	{
		this.number = number;
	}

	public ArrayList<Session> getSessions()
	{
		return this.sessions;
	}

	public String getPassword()
	{
		return this.password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public Person getPerson()
	{
		return this.person;
	}

	public void setPerson(Person person)
	{
		this.person = person;
	}

	/**
	 * this is the default constructor of the Cashier class, it initializes number and password to null
	 */
	public Cashier()
	{
		number = null;
		password = null;
		Person person = new Person();
		setPerson(person);
	}

	/**
	 * this is the constructor of the Cashier class, it initializes the cashier's number, person, and password and also creates an empty session ArrayList
	 * @param number
	 * @param person
	 * @param password
	 */
	public Cashier(String number, Person person, String password)
	{
		this.number = number;
		this.person = person;
		this.password = password;
		sessions = new ArrayList<Session>();
	}

	/**
	 * this adds a session to the cashier
	 * @param session
	 */
	public void addSession(Session session)
	{
		if (sessions.isEmpty()) {
			sessions.add(session);
		}
		else {
			for (int i = 1; i < sessions.size(); i++) {
	            if (session.getStartDateTime().isBefore(sessions.get(i).getStartDateTime()) 
	            		&& session.getStartDateTime().isAfter(sessions.get(i-1).getStartDateTime())) {
	                sessions.add(i, session);
	                i = sessions.size();
	          }
			}
			sessions.add(session);
		}
	}

	/**
	 * this removes a session from the cashier class
	 * @param session
	 */
	public void removeSession(Session session)
	{
		sessions.remove(session);
	}

	/**
	 * this checks if the cashier's password is correct and authorized to use the system
	 * @param password
	 * @return returns true if the cashier's password is correct and false if it is incorrect
	 */
	public Boolean isAuthorized(String password)
	{
		if(this.password == password) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * creates a single String that contains everything that needs to be printed from the Cashier class
	 * @return String that will be used to print result from the Cashier class
	 */
	public String toString()
	{
		String cashierString = number + " " + person.getName();
		return cashierString;
	}

	/**
	 * this compares the current Cashier with the passed in Cashier, 
	 * the result is a -1 if the passed in Cashier is greater, 
	 * a 0 if they are equal and a 1 if the passed in Cashier is less
	 * @param Item
	 * @return returns an integer indicating the result of the comparison
	 */
	public int compareTo(Cashier cashier) {
		return this.number.compareTo(cashier.number);
	}
	
	/**
	 * checks to see if this register has any sessions that have any sales in them
	 * @return boolean used that shows whether or not the register is active
	 */
	public boolean Used() {
		boolean used = true;
		if (sessions == null) {
			return false;
		}
		return used;
	}
	
	/**
	 * calculates all the sessions combined total sales
	 * @param date the date that the sessions are looked for
	 * @return sessionTotalSales the BigDecimal of the total session sales
	 */
	public BigDecimal calcTotalSessionSales(LocalDate date) {
		BigDecimal sessionTotalSales = new BigDecimal(0);
		if (!(getSessions() == null)) {
			for (Session session : getSessions()) {
				if ( (session.getStartDateTime().getDayOfMonth() == date.getDayOfMonth()) && (session.getStartDateTime().getDayOfWeek() == date.getDayOfWeek()) && (session.getStartDateTime().getDayOfYear() == date.getDayOfYear()) ) {
					sessionTotalSales = sessionTotalSales.add(session.getTotalSales());
				}
			}
		}
		return sessionTotalSales;
	}
	
	/**
	 * calculates all the sessions combined total cash counted by the cashier at the end of the sessions
	 * @param date the date that the sessions are looked for
	 * @return countedCash the BigDecimal of the total counted cash
	 */
	public BigDecimal calcCountedCash(LocalDate date) {
		BigDecimal countedCash = new BigDecimal(0);
		
		if (!(getSessions() == null)) {
		for (Session session : getSessions()) {
			if ( (session.getStartDateTime().getDayOfMonth() == date.getDayOfMonth()) && (session.getStartDateTime().getDayOfWeek() == date.getDayOfWeek()) && (session.getStartDateTime().getDayOfYear() == date.getDayOfYear()) ) {
				if (!(session.getCountedCash().equals(new BigDecimal(0)))) {
					countedCash = countedCash.add(session.getCountedCash());
				}
				}
			}
		}
		
		return countedCash;
	}
	
	/**
	 * calculates all the sessions combined total cash counted by the cashier - the total session sales
	 * @param date the date that the sessions are looked for
	 * @return difference the BigDecimal of the difference between countedCash-sessionTotalSales
	 */
	public BigDecimal calcDifference(LocalDate date) {
		BigDecimal difference = new BigDecimal(0);
		difference = calcCountedCash(date).subtract(calcTotalSessionSales(date));
		return difference;
	}
	
	
	
	/**
	 * calculates the total sales and total number of sales for the cashier and returns a string containing some info about the cashier
	 * @param date the date that the sessions are looked for
	 * @return secondString the string that contains some info about the cashier
	 */
	public String secondToString(LocalDate date) {
		int saleCount = 0;
		BigDecimal saleTotals = new BigDecimal(0);
		
		if (!(getSessions() == null)) {
			for (Session session : sessions) {
				if ( (session.getStartDateTime().getDayOfMonth() == date.getDayOfMonth()) && (session.getStartDateTime().getDayOfWeek() == date.getDayOfWeek()) && (session.getStartDateTime().getDayOfYear() == date.getDayOfYear()) ) {
					saleCount = saleCount + session.getSales().size();
					for (Sale sale : session.getSales()) {
						saleTotals = saleTotals.add(sale.calcTotal());				//maybe should not be calcTotal()
					}
				}	
			}
		}
		String secondString = getNumber() + "                          " + person.getName() + "                        " + saleCount + "                                " + saleTotals.toString();
		return secondString;
		
	}

}