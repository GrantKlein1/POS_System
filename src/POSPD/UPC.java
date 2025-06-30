package POSPD;
/**
 * represents the unique barcode numbers that are on each item
 */
public class UPC
{

	/**
	 * the item that the UPC is attached too
	 */
	private Item item;
	/**
	 * the unique barcode number on the outside of each item
	 */
	private String upc;

	public Item getItem()
	{
		return this.item;
	}

	public void setItem(Item item)
	{
		this.item = item;
	}

	public String getUpc()
	{
		return this.upc;
	}

	public void setUpc(String upc)
	{
		this.upc = upc;
	}

	/**
	 * this is the default constructor of the UPC class, it initializes the upc to null
	 */
	public UPC()
	{
		upc = null;
	}

	/**
	 * this is the constructor for the UPC class, it initializes the upc to the passed in value and creates a new blank item
	 * @param upc
	 */
	public UPC(String upc)
	{
		this.upc = upc;
		item = new Item();
	}

	/**
	 * creates a single String that contains everything that needs to be printed from the UPC class
	 * @return String that will be used to print result from the UPC class
	 */
	public String toString()
	{
		String upcString = "UPC: " + getUpc() + "\n";
		return upcString;
	}

}