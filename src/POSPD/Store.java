package POSPD;
import java.time.LocalDate;
import java.util.*;

import POSDM.StoreDM;

/**
 * This represents the physical store
 */
public class Store
{

	/**
	 * this is the name of the store
	 */
	private String name;
	/**
	 * this is the TreeMap collection of items contained in the store
	 */
	private TreeMap<Integer, Item> items = new TreeMap<>();
	/**
	 * this is the TreeMap collection of all barcodes on items
	 */
	private TreeMap<String, UPC> upcs = new TreeMap<>();
	/**
	 * this is the TreeMap collection of the different amounts items are taxed
	 */
	private TreeMap<String, TaxCategory> taxCategories = new TreeMap<>();
	/**
	 * this is the TreeMap collection of all registers in the store
	 */
	private TreeMap<Integer, Register> registers = new TreeMap<>();
	/**
	 * this is the TreeMap collection of cashiers at the store
	 */
	private TreeMap<Integer, Cashier> cashiers = new TreeMap<>();
	/**
	 * this is the ArrayList collection of all sessions on registers in the store
	 */
	private ArrayList<Session> sessions = new ArrayList<>();

	public String getName()
	{
		return this.name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public TreeMap<Integer, Item> getItems() 
	{
		return this.items;
	}
	public TreeMap<String, UPC> getUpcs()
	{
		return this.upcs;
	}

	public TreeMap<String, TaxCategory> getTaxCategories()
	{
		return this.taxCategories;
	}

	public TreeMap<Integer, Register> getRegisters()
	{
		return this.registers;
	}

	public ArrayList<Session> getSessions()
	{
		return this.sessions;
	}

	public TreeMap<Integer, Cashier> getCashiers()
	{
		return this.cashiers;
	}

	/**
	 * this is the default constructor for the Store class, it initializes number and name to null
	 * @param name 
	 */
	public Store()
	{
		name = null;
	}

	/**
	 * this is the constructor for the Store class, it initializes number and name as well as all the collections to be empty
	 * @param number
	 * @param name
	 */
	public Store(String name)
	{
		this.name = name;
		items = new TreeMap<Integer, Item>();
		upcs = new TreeMap<String, UPC>();
		taxCategories = new TreeMap<String, TaxCategory>();
		registers = new TreeMap<Integer, Register>();
		sessions = new ArrayList<Session>();
		cashiers = new TreeMap<Integer, Cashier>();
	}
	

	/**
	 * this adds an item to the stores inventory
	 * @param item
	 */
	public void addItem(Item item)
	{
		items.put(Integer.parseInt(item.getNumber()), item);
	}

	/**
	 * this removes an item from the stores inventory
	 * @param item
	 */
	public void removeItem(Item item)
	{
		items.remove(Integer.parseInt(item.getNumber()), item);
		for (UPC upc : item.getUpcs().values()) {
			this.removeUPC(upc);
		}
		for (UPC upc : this.getUpcs().values()) {
			System.out.println(upc.toString());
		}
	}

	/**
	 * this adds a UPC to the store
	 * @param upc
	 */
	public void addUPC(UPC upc)
	{
		upcs.put(upc.getUpc(), upc);
	}

	/**
	 * this removes a UPC from the store
	 * @param upc
	 */
	public void removeUPC(UPC upc)
	{
			upcs.remove(upc.getUpc(), upc);
	}

	/**
	 * this adds a register to the store
	 * @param register
	 */
	public void addRegister(Register register)
	{
		registers.put(Integer.parseInt(register.getNumber()), register);
	}

	/**
	 * this removes a register from the store
	 * @param register
	 */
	public void removeRegister(Register register)
	{
		registers.remove(Integer.parseInt(register.getNumber()), register);
	}

	/**
	 * this adds adds a cashier to the store
	 * @param cashier
	 */
	public void addCashier(Cashier cashier)
	{
		cashiers.put(Integer.parseInt(cashier.getNumber()), cashier);
	}

	/**
	 * this removes a cashier from the store
	 * @param cashier
	 */
	public void removeCashier(Cashier cashier)
	{
		cashiers.remove(Integer.parseInt(cashier.getNumber()), cashier);
	}

	/**
	 * this adds a tax category to the store
	 * @param taxCategory
	 */
	public void addTaxCategory(TaxCategory taxCategory)
	{
		taxCategories.put(taxCategory.getCategory(), taxCategory);
	}

	/**
	 * this removes a tax category from the store
	 * @param taxCategory
	 */
	public void removeTaxCategory(TaxCategory taxCategory)
	{
		taxCategories.remove(taxCategory.getCategory(), taxCategory);
	}

	/**
	 * this adds a session to the store
	 * @param session
	 */
	public void addSession(Session session)
	{
		if (sessions.isEmpty()) {
			sessions.add(session);
		}
		else {
			for (int i = 1; i < sessions.size(); i++) {
	            if (session.getStartDateTime().isBefore(sessions.get(i).getStartDateTime()) && session.getStartDateTime().isAfter(sessions.get(i-1).getStartDateTime())) {
	                sessions.add(i, session);
	                i = sessions.size();
	          }
			}
			sessions.add(session);
		}
	}
	
	/**
	 * this removes a session from the store
	 * @param session
	 */
	public void removeSession(Session session)
	{
		sessions.remove(session);
	}

	/**
	 * finds a specific register that has the proper ID number
	 * @param number
	 * @return Register that matches the ID number passed in, returns null if it is not found
	 */
	public Register findRegisterByNumber(String number)
	{
		return registers.get(Integer.parseInt(number));
	}

	/**
	 * this function locates an Item by it's upc
	 * @param upc
	 * @return Item that matches the upc passed in, returns null if not found
	 */
	public Item findItemForUPC(String upc)
	{
		return upcs.get(upc).getItem();
	}

	/**
	 * locates an Item based off the number passed in
	 * @param number
	 * @return Item that matches the number passed in, returns null if not found
	 */
	public Item findItemForNumber(String number)
	{
		return items.get(Integer.parseInt(number));
	}

	/**
	 * locates a cashier based off the number passed in
	 * @param number
	 * @return Cashier that matches the number passed in, returns null if not found
	 */
	public Cashier findCashierForNumber(String number)
	{
		return cashiers.get(Integer.parseInt(number));
	}

	/**
	 * locates a TaxCategory based of the category passed in
	 * @param category
	 * @return TaxCategory that matches the category passed in, returns null if not found
	 */
	public TaxCategory findTaxCategoryByName(String category)
	{
		return taxCategories.get(category);
	}
	
	/**
	 * 
	 */
	public TreeMap<Integer, Integer> calcItemQuantitySold(LocalDate date) {
		TreeMap<Integer, Integer> itemList = new TreeMap<Integer, Integer>();
		
		for (Item item : getItems().values()) {
			itemList.put(Integer.parseInt(item.getNumber()), 0);
		}
		
		for (Session session : getSessions()) {
			if ( (session.getStartDateTime().getDayOfMonth() == date.getDayOfMonth()) && (session.getStartDateTime().getDayOfWeek() == date.getDayOfWeek()) && (session.getStartDateTime().getDayOfYear() == date.getDayOfYear()) ) {
				for (Sale sale : session.getSales()) {
					for (SaleLineItem sli : sale.getSaleLineItems()) {
						itemList.put(Integer.parseInt(sli.getItem().getNumber()), sli.getQuantity());
					}
				}
			}
		}
		return itemList;
	}

	/**
	 * creates a single String that contains everything that needs to be printed from the Store class
	 * @return String that will be used to print result from the Store class
	 */
	public String toString()
	{
		String storeString = "Store: " + getName() + "\n\n";
		
		int x = 0;
		storeString = storeString + "----------------------------------\n\n" + "Items:\n";
		for (Item it : items.values()) {
			x++;
			storeString = storeString + it.toString();
		}
		
		storeString = storeString  + "----------------------------------\n\n" + "Cashiers:\n";
		x = 0;
		for (Cashier c : cashiers.values()) {
			x++;
			storeString = storeString + cashiers.get(x).toString();
		}
		
		storeString = storeString  + "----------------------------------\n\n" + "Registers:\n";
		x = 0;
		for (Register r : registers.values()) {
			x++;
			storeString = storeString + registers.get(x).toString();
		}
		
		storeString = storeString  + "----------------------------------\n\n" + "Sessions:\n\n";
		for (int i = 0; i < sessions.size(); i++) {
			
			storeString = storeString + "Session " + (i+1) + "\n" + sessions.get(i).toString();
		}
		
		return storeString;
	}

}