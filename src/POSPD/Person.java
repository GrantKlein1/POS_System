package POSPD;
/**
 * this represents the person being checked out at the convenience store
 */
public class Person
{

	/**
	 * the person's name
	 */
	private String name;
	/**
	 * the person's home address
	 */
	private String address;
	/**
	 * the city where the person lives
	 */
	private String city;
	/**
	 * the state where the person lives
	 */
	private String state;
	/**
	 * the zip of where the person lives
	 */
	private String zip;
	/**
	 * the phone number of the person
	 */
	private String phone;
	/**
	 * the social security number of the person
	 */
	private String SSN;
	/**
	 * the cashier that is checking out the person
	 */
	private Cashier cashier;

	public String getName()
	{
		return this.name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getAddress()
	{
		return this.address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	public String getCity()
	{
		return this.city;
	}

	public void setCity(String city)
	{
		this.city = city;
	}

	public String getState()
	{
		return this.state;
	}

	public void setState(String state)
	{
		this.state = state;
	}

	public String getZip()
	{
		return this.zip;
	}

	public void setZip(String zip)
	{
		this.zip = zip;
	}

	public String getPhone()
	{
		return this.phone;
	}

	public void setPhone(String phone)
	{
		this.phone = phone;
	}

	public String getSSN()
	{
		return this.SSN;
	}

	public void setSSN(String SSN)
	{
		this.SSN = SSN;
	}

	public Cashier getCashier()
	{
		return this.cashier;
	}

	public void setCashier(Cashier cashier)
	{
		this.cashier = cashier;
	}

	/**
	 * this is the default constructor of the Person class, it initializes all the class variables to null
	 */
	public Person()
	{
		name = null;
		address = null;
		city = null;
		state = null;
		zip = null;
		phone = null;
		SSN = null;
	}

	/**
	 * this is the constructor of the Person class, it initializes all the class variables
	 * @param name
	 * @param address
	 */
	public Person(String name, String address, String city, String state, String zip, String phone, String SSN)
	{
		this.name = name;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.phone = phone;
		this.SSN = SSN;
	}

	/**
	 * creates a single String that contains everything that needs to be printed from the Person class
	 * @return String that will be used to print result from the Person class
	 */
	public String toString()
	{
		String personString = "Person: " + getName() + "\n";
		return personString;
	}

}