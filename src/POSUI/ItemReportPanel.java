package POSUI;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.github.lgooddatepicker.components.DatePicker;

import POSPD.Cashier;
import POSPD.Item;
import POSPD.Sale;
import POSPD.SaleLineItem;
import POSPD.Session;
import POSPD.Store;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.TreeMap;
import java.awt.event.ActionEvent;
import javax.swing.JEditorPane;
import javax.swing.JFormattedTextField;
import javax.swing.JScrollPane;

public class ItemReportPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	DefaultListModel<String> listModel;
	LocalDate date = null;
	DatePicker datePicker;
	JLabel lblNewLabel_2;
	JLabel lblNewLabel_8;
	JList<String> list;
	TreeMap<Integer, Integer> itemList;
	String spacing = "";
	String spacingTwo = "                ";
	String spacingThree = "  ";
	String tab = "\t";
	
	
	/**
	 * Create the panel.
	 */
	public ItemReportPanel(JFrame currentFrame, Store store) {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Item Report");
		lblNewLabel.setBounds(174, 23, 131, 13);
		add(lblNewLabel);
		
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
		
		listModel = new DefaultListModel<String>();
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(60, 104, 321, 104);
		add(scrollPane);
		
		list = new JList<String>(listModel);
		scrollPane.setViewportView(list);
		
		JButton btnNewButton = new JButton("Generate");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().revalidate();
				currentFrame.getContentPane().repaint();
				date = datePicker.getDate();
				lblNewLabel_2.setText(date.toString());
				genItemReport(date, store);
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
		
		JLabel lblNewLabel_1 = new JLabel("Item Report For: ");
		lblNewLabel_1.setBounds(62, 71, 128, 13);
		add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBounds(160, 71, 68, 13);
		add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Number");
		lblNewLabel_3.setBounds(60, 89, 73, 13);
		add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Name");
		lblNewLabel_4.setBounds(145, 89, 80, 13);
		add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Qty Sold");
		lblNewLabel_5.setBounds(278, 89, 103, 13);
		add(lblNewLabel_5);
		
		
		JEditorPane editorPane = new JEditorPane();
		editorPane.setBounds(62, 71, 319, 137);
		add(editorPane);
		
	}
	
	
	void genItemReport(LocalDate date, Store store) {
		
		listModel.removeAllElements();

		itemList = store.calcItemQuantitySold(date);
		
		int descriptionLen = 0;
		for (Item item : store.getItems().values()) {
			
			while (  (item.getDescription().length() + spacing.length()) < 34) {
				spacing = spacing + " ";
			}
			
			while ( (descriptionLen + item.getDescription().length()) < 15) {
				spacing = spacing + " ";
				descriptionLen++;
			}
			
			listModel.addElement(spacingThree + item.getNumber() + spacingTwo + item.getDescription() + spacing + itemList.get(Integer.parseInt(item.getNumber())));
			
			descriptionLen = 0;
			spacing = "";

		}
	}
}
