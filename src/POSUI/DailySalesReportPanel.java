package POSUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.TreeMap;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

import com.github.lgooddatepicker.components.DatePicker;

import POSPD.*;
import javax.swing.JScrollPane;

public class DailySalesReportPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	LocalDate date = null;
	DatePicker datePicker;
	JLabel lblNewLabel_2;
	JLabel lblNewLabel_8;
	JList<String> list;
	DefaultListModel<String> listModel;
	TreeMap<Integer, Integer> itemList;
	ArrayList<Sale> saleList;
	int cashPaymentCount = 0;
	int creditPaymentCount = 0;
	int checkPaymentCount = 0;
	int numOfItems = 0;
	Cash cash = new Cash();
	Check check = new Check();
	/**
	 * Create the panel.
	 */
	public DailySalesReportPanel(JFrame currentFrame, Store store) {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Daily Sales Report");
		lblNewLabel.setBounds(174, 23, 131, 13);
		add(lblNewLabel);
		
		
		listModel = new DefaultListModel<String>();
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(60, 102, 321, 106);
		add(scrollPane);
		
		list = new JList<String>(listModel);
		scrollPane.setViewportView(list);
		
		JLabel lblNewLabel_9 = new JLabel("Date:");
		lblNewLabel_9.setBounds(145, 48, 45, 13);
		add(lblNewLabel_9);
		
		datePicker = new DatePicker();
		datePicker.getComponentToggleCalendarButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				date = datePicker.getDate();
			}
		});
		datePicker.setBounds(184, 46, 165, 19);
		add(datePicker);
		
		JButton btnNewButton = new JButton("Generate");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				date = datePicker.getDate();
				currentFrame.getContentPane().revalidate();
				currentFrame.getContentPane().repaint();
				lblNewLabel_2.setText(date.toString());
				genDailyReport(date, store);
			}
		});
		btnNewButton.setBounds(89, 237, 111, 21);
		add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Close");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new POSHomePage(store));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnNewButton_1.setBounds(251, 237, 111, 21);
		add(btnNewButton_1);
		
		JLabel lblNewLabel_1 = new JLabel("Daily Sales Report For: ");
		lblNewLabel_1.setBounds(62, 71, 150, 13);
		add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBounds(194, 71, 68, 13);
		add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Total");
		lblNewLabel_3.setBounds(61, 87, 45, 13);
		add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("# of Items");
		lblNewLabel_4.setBounds(104, 87, 68, 13);
		add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Cash");
		lblNewLabel_5.setBounds(181, 87, 45, 13);
		add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Check");
		lblNewLabel_6.setBounds(251, 87, 45, 13);
		add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Credit");
		lblNewLabel_7.setBounds(326, 87, 45, 13);
		add(lblNewLabel_7);
		
		
		JEditorPane editorPane = new JEditorPane();
		editorPane.setBounds(62, 71, 319, 137);
		add(editorPane);
		
	}
	
	
	void genDailyReport(LocalDate date, Store store) {
		
		String reportString = "";
		
		listModel.removeAllElements();
		
		for (Session session : store.getSessions()) {
			if ( (session.getStartDateTime().getDayOfMonth() == date.getDayOfMonth()) && (session.getStartDateTime().getDayOfWeek() == date.getDayOfWeek()) && (session.getStartDateTime().getDayOfYear() == date.getDayOfYear()) ) {
				for (Sale sale : session.getSales()) {
					
					numOfItems = 0;
					sale.countPaymentTypes();
					numOfItems = sale.numberOfItems();
					
					if (sale.calcTotal().toString().length() > 4) {
						listModel.addElement(sale.calcTotal().toString() + "           " + numOfItems + "                   " + sale.getCashPaymentCount() + "                     " + sale.getCheckPaymentCount() + "                     " + sale.getCreditPaymentCount());
					}
					else if (sale.calcTotal().toString().length() > 5) {
						listModel.addElement(sale.calcTotal().toString() + "          " + numOfItems + "                   " + sale.getCashPaymentCount() + "                     " + sale.getCheckPaymentCount() + "                     " + sale.getCreditPaymentCount());
					}
					else {
						listModel.addElement(sale.calcTotal().toString() + "             " + numOfItems + "                   " + sale.getCashPaymentCount() + "                     " + sale.getCheckPaymentCount() + "                     " + sale.getCreditPaymentCount());
					}
				}
			}
		}
	}
	
}
