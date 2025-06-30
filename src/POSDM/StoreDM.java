package POSDM;

import java.math.*;
import java.time.LocalDateTime;

import POSPD.*;

import java.io.*;

public class StoreDM {

	public static void loadStore(Store store) {
		String fileName ="src/POSDM/StoreData.csv";
		String line = null;
		
		 try {
		    	
		        FileReader fileReader =  new FileReader(fileName);

		        BufferedReader bufferedReader = new BufferedReader(fileReader);
		        
		        Session currentSession = new Session();
		        Sale currentSale = new Sale();

		        while((line = bufferedReader.readLine()) != null) {
		        	
		        	String[] lines = line.split(",");
		        	
		   
		        	
		        	if (lines[0].equals("Store")) {
		        		store.setName(lines[1]);
		        		
		        	}
		        	else if (lines[0].equals("TaxCategory")) {
		        		TaxCategory taxCat = new TaxCategory(lines[1]);
		        		BigDecimal placeHolder1 = new BigDecimal(lines[2]);
		        		
		        		TaxRate taxRate = new TaxRate(lines[3], placeHolder1);
		        		taxCat.addTaxRate(taxRate);

		        		store.addTaxCategory(taxCat);
		        		
		        	}
		        	else if (lines[0].equals("Cashier")) {
		        		Person person = new Person(lines[2], lines[4], lines[5], lines[6], lines[7], lines[8], lines[3]);
		        		Cashier cashier = new Cashier(lines[1], person, lines[9]);
		        		store.addCashier(cashier);
		        		
		        	}
		        	else if (lines[0].equals("Item")) {
		        		Item item = new Item(lines[1], lines[3]);
		        		UPC upc = new UPC(lines[2]);
		        		upc.setItem(item);
		        		item.addUPC(upc);
		        		
		        		Price price = new Price(lines[5], lines[6]);
		        		
		        		if (lines.length > 7) {
		        			PromoPrice promoPrice = new PromoPrice(lines[7], lines[8], lines[9]);
		        			item.addPrice(promoPrice);
		        		} 
		        		
		        		TaxCategory taxcategory = store.findTaxCategoryByName(lines[4]);
		        		item.setTaxCategory(taxcategory);
		        		item.addPrice(price);
		        		store.findTaxCategoryByName(lines[4]).addItem(item);
		        		
		        		store.addItem(item);
		        		
		        	}
		        	else if (lines[0].equals("Register")) {
		        		Register register = new Register(lines[1]);
		        		store.addRegister(register);
		        		
		        	}
		        	else if (line.startsWith("Session") ) {
		        		
		        		if (currentSession.getSales().isEmpty()) {//runs the first session
		        			currentSession = new Session(store.findCashierForNumber(lines[1]), store.findRegisterByNumber(lines[2]));
		        			currentSession.setStartDateTime(LocalDateTime.now());
		        		}
		        		else {//runs all subsequent sessions
		        			Cashier c =  store.findCashierForNumber(lines[2]);
		        			store.findCashierForNumber(c.getNumber()).addSession(currentSession);
		        			currentSession.setEndDateTime(LocalDateTime.now());
		        			store.addSession(currentSession);//previous session has ended so add previous session to the store
		        			
		        			//now put the new session into current session
		        			currentSession = new Session(store.findCashierForNumber(lines[1]), store.findRegisterByNumber(lines[2]));
		        			currentSession.setStartDateTime(LocalDateTime.now());
		        		}
		        			
		        		
		        	}
		        	
		        	else if (lines[0].equals("Sale")) {
		        		currentSale = new Sale(lines[1]);
		        	
		        	}
		        	
		        	else if (lines[0].equals("SaleLineItem")) {

		        		if (lines[1] == "") {//test here to see if promo price works by changing the date
		        			
		        		}
    					SaleLineItem sli = new SaleLineItem(currentSale, store.findItemForNumber(lines[1]), lines[2]);
    					currentSale.addSaleLineItem(sli);
		        	}
		        	
		        	else if (line.startsWith("Payment")) {
		        		
		        		if (lines[1].equals("Cash")) {
        					Cash cashPayment = new Cash(lines[2], new BigDecimal(lines[3]));
        					cashPayment.countsAsCash();
        					currentSale.addPayment(cashPayment);
        				}
        				else if (lines[1].equals("Credit")) {
        					Credit creditPayment = new Credit(lines[2], lines[4], lines[5], lines[6]);
        					creditPayment.countsAsCredit();
        					currentSale.addPayment(creditPayment);
        				}
        				else if (lines[1].equals("Check")) {
        					Check checkPayment = new Check(lines[2], lines[4], lines[5]);
        					checkPayment.countsAsCheck();
        					currentSale.addPayment(checkPayment);
        				}
		        		
		        		currentSession.addSale(currentSale);
		        	}
		        	else {
		        		
		        		System.out.println("Else: " + lines[0]);
		        		
		        	}
		        		
		        }    
		      currentSession.setEndDateTime(LocalDateTime.now());
		      store.findCashierForNumber(currentSession.getCashier().getNumber()).addSession(currentSession);
		      store.addSession(currentSession);

		      bufferedReader.close();

		    }
		   
		    catch(FileNotFoundException ex) 
		    {
		      System.out.println("Unable to open file " +  fileName);                
		    }
		    catch(IOException ex) 
		    {
		       System.out.println ("Error reading file " + fileName);   	
		    } 
		    
	}
	
       
}
