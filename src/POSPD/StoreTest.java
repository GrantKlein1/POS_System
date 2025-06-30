package POSPD;
import POSDM.StoreDM;
import java.math.BigDecimal;
import java.time.LocalDate;

public class StoreTest {
	public static void main(String[] args) {
		
		
		//Store store = new Store();
		//StoreDM.loadStore(store);

		/*
		//AC1
		Item item1 = new Item("12121212", "potato");
		Item item2 = new Item("34343434", "carrot");
		Item item3 = new Item("56565656", "tomato");
		
		Price price1 = new Price("4", "2023-01-01");
		Price price2 = new Price("10", "2019-01-01");
		Price price3 = new Price("21", "2024-01-01");		//String price, String effectiveDate
		
		UPC upc1 = new UPC("20202020");
		UPC upc2 = new UPC("30303030");
		UPC upc3 = new UPC("40404040");
		
		TaxCategory taxCat1 = new TaxCategory("groundFood");
		TaxCategory taxCat2 = new TaxCategory("notGroundFood");
		
		BigDecimal placeHolder = new BigDecimal(.07);
		TaxRate taxRate1 = new TaxRate("2023-05-02", placeHolder);
		
		placeHolder = new BigDecimal(.4141414143);
		TaxRate taxRate2 = new TaxRate("2022-08-01", placeHolder);
		
		taxCat1.addTaxRate(taxRate1);
		taxCat2.addTaxRate(taxRate2);
		
		item1.addPrice(price1);
		item2.addPrice(price2);
		item3.addPrice(price3);
		
		item1.addUPC(upc1);
		item2.addUPC(upc2);
		item3.addUPC(upc3);
		
		item1.setTaxCategory(taxCat1);
		item2.setTaxCategory(taxCat1);
		item3.setTaxCategory(taxCat2);
		
		store.addItem(item1);
		store.addItem(item2);
		store.addItem(item3);
		
		//AC2
		Person potatoMan = new Person("Frederick", "12 house", "Edmond", "Oklahoma", "73102", "213-313-1312", "212-42-4412");
		Cashier cash = new Cashier("99999999", potatoMan, "easyPassword");
		store.addCashier(cash);
		
		Person carrotMan = new Person("Billy", "512 apartment", "CityNameHere", "StateNameHere", "34521", "342-634-2342", "323-12-6888");						
		Cashier notCash = new Cashier("11111111", carrotMan, "hardPassword");
		store.addCashier(notCash);
		
		Person tomatoMan = new Person("Thomas", "394 condo", "San Diego", "California", "95631", "943-615-9175", "491-73-3930");						
		Cashier middleCash = new Cashier("10101010", tomatoMan, "shortPassword");
		store.addCashier(middleCash);
		
		//AC3
		CashDrawer cashDrawer1 = new CashDrawer();
		CashDrawer cashDrawer2 = new CashDrawer();
		
		placeHolder = new BigDecimal(12000);
		cashDrawer1.setCashAmount(placeHolder);
		
		placeHolder = new BigDecimal(21000);
		cashDrawer2.setCashAmount(placeHolder);
		
		Register reg1 = new Register("249219391");
		Register reg2 = new Register("923251411");
		
		reg1.setCashDrawer(cashDrawer1);
		reg2.setCashDrawer(cashDrawer2);
		
		store.addRegister(reg1);
		store.addRegister(reg2);
		
		//AC4
		Session session1 = new Session(cash, reg1);
		
		Sale sale1 = new Sale("false");
		Sale sale2 = new Sale("false");
		
		SaleLineItem sli1 = new SaleLineItem(sale1, item1, "5");
		SaleLineItem sli2 = new SaleLineItem(sale1, item3, "2");
		
		SaleLineItem sli3 = new SaleLineItem(sale2, item2, "27");
		SaleLineItem sli4 = new SaleLineItem(sale2, item3, "3");
		
		sale1.addSaleLineItem(sli1);
		sale1.addSaleLineItem(sli2);
		
		sale2.addSaleLineItem(sli3);
		sale2.addSaleLineItem(sli4);
		
		session1.addSale(sale1);
		session1.addSale(sale2);
		
		store.addSession(session1);
		
		System.out.println(store.toString());
		
		*/
		/*
		Person potatoMan = new Person();						//String name, String address, String city, String state, String zip, String phone, String SSN
		Cashier cash = new Cashier("99999999", potatoMan, "easyPassword");			//String number, Person person, String password
		store.addCashier(cash);
		
		Person carrotMan = new Person();						//String name, String address, String city, String state, String zip, String phone, String SSN
		Cashier notCash = new Cashier("11111111", carrotMan, "hardPassword");			//String number, Person person, String password
		store.addCashier(notCash);
		
		Register reg1 = new Register("249219391");
		Register reg2 = new Register("923251411");
		store.addRegister(reg1);
		store.addRegister(reg2);
		
		Item item1 = new Item("12121212", "potato");		//String number, String description
		Item item2 = new Item("34343434", "carrot");
		Item item3 = new Item("56565656", "tomato");
		
		Price price1 = new Price("3.49", "2024-03-02");				//String price, String effectiveDate
		Price price2 = new Price("1.20", "2023-08-03");
		Price price3 = new Price("41.78", "2024-01-01");
		
		TaxCategory taxCat1 = new TaxCategory("groundFood");
		TaxCategory taxCat2 = new TaxCategory("notGroundFood");
		
		UPC upc1 = new UPC("20202020");
		UPC upc2 = new UPC("30303030");
		UPC upc3 = new UPC("40404040");
		
		item1.addPrice(price1);
		item2.addPrice(price2);
		item3.addPrice(price3);
		
		item1.addUPC(upc1);
		item2.addUPC(upc2);
		item3.addUPC(upc3);
		
		item1.setTaxCategory(taxCat1);
		item2.setTaxCategory(taxCat1);
		item3.setTaxCategory(taxCat2);
		
		store.addItem(item1);
		store.addItem(item2);
		store.addItem(item3);
		*/
		
	}
	
}
