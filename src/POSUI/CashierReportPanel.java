package POSUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;

import POSPD.Cashier;
import POSPD.Session;
import POSPD.Store;
import POSPD.UPC;

import javax.swing.JLabel;
import javax.swing.JList;
import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;
import javax.swing.JScrollPane;

public class CashierReportPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	DefaultListModel<String> listModel;
	LocalDate date = null;
	DatePicker datePicker;
	JLabel lblNewLabel_2;
	JLabel lblNewLabel_8;
	BigDecimal totalSales;
	BigDecimal countedCash;
	Boolean hasCountedCash;
	String firstFirstSpacing = "     ";
	String firstSpacing = "           ";
	String secondSpacing = "              ";
	String printer = "";
	JList<String> list;

	/**
	 * Create the panel.
	 */
	public CashierReportPanel(JFrame currentFrame, Store store) {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Cashier Report");
		lblNewLabel.setBounds(174, 23, 131, 13);
		add(lblNewLabel);
		totalSales = new BigDecimal(0);
		
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
		scrollPane.setBounds(62, 120, 319, 65);
		add(scrollPane);
		
		list = new JList<String>(listModel);
		scrollPane.setViewportView(list);
		
		JButton btnNewButton = new JButton("Generate");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				date = datePicker.getDate();
				currentFrame.getContentPane().revalidate();
				currentFrame.getContentPane().repaint();
				lblNewLabel_2.setText(date.toString());
				genCashierReport(date, store);
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
		
		JLabel lblNewLabel_1 = new JLabel("Cashier Report For: ");
		lblNewLabel_1.setBounds(62, 71, 128, 13);
		add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBounds(180, 71, 68, 13);
		add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Number");
		lblNewLabel_3.setBounds(62, 101, 68, 13);
		add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Name");
		lblNewLabel_4.setBounds(122, 101, 68, 13);
		add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Count");
		lblNewLabel_5.setBounds(190, 101, 68, 13);
		add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Amount");
		lblNewLabel_6.setBounds(251, 101, 68, 13);
		add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Total:");
		lblNewLabel_7.setBounds(220, 195, 68, 13);
		add(lblNewLabel_7);
		
		lblNewLabel_8 = new JLabel("0");
		lblNewLabel_8.setBounds(261, 195, 68, 13);
		add(lblNewLabel_8);
		
		JLabel lblNewLabel_10 = new JLabel("Difference");
		lblNewLabel_10.setBounds(316, 101, 65, 13);
		add(lblNewLabel_10);
		
		JEditorPane editorPane = new JEditorPane();
		editorPane.setBounds(62, 71, 319, 137);
		add(editorPane);

	}
	
	
	void genCashierReport(LocalDate date, Store store) {
		listModel.removeAllElements();
		totalSales = totalSales.subtract(totalSales);
		lblNewLabel_8.setText(totalSales.toString());
		
		for (Cashier cashier : store.getCashiers().values()) {
			printer = firstFirstSpacing + cashier.getNumber() + firstSpacing + cashier.getPerson().getName() + secondSpacing + cashier.calcCountedCash(date) + secondSpacing + cashier.calcTotalSessionSales(date) + secondSpacing + cashier.calcDifference(date);
			listModel.addElement(printer);
			printer = "";
			totalSales = totalSales.add(cashier.calcTotalSessionSales(date));
		}
		
		list = new JList<String>(listModel);
		lblNewLabel_8.setText(totalSales.toString());
		
	}
}
