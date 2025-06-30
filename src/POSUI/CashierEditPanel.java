package POSUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import POSPD.Cashier;
import POSPD.Store;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class CashierEditPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_7;
	private JTextField textField_8;
	private JPasswordField passwordField;

	/**
	 * Create the panel.
	 */
	public CashierEditPanel(JFrame currentFrame, Store store, Cashier cashier, Boolean isAdd) {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Edit Cashier");
		lblNewLabel.setBounds(166, 30, 86, 13);
		add(lblNewLabel);
		
		if (isAdd) {
			textField = new JTextField();
		}
		else {
			textField = new JTextField(cashier.getNumber());
		}
		textField.setBounds(101, 58, 61, 19);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Number:");
		lblNewLabel_1.setBounds(28, 61, 63, 13);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("SSN:");
		lblNewLabel_2.setBounds(196, 61, 45, 13);
		add(lblNewLabel_2);
		
		if (isAdd) {
			textField_1 = new JTextField();
		}
		else {
			textField_1 = new JTextField(cashier.getPerson().getName());
		}
		textField_1.setBounds(257, 58, 96, 19);
		add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Name:");
		lblNewLabel_3.setBounds(28, 90, 45, 13);
		add(lblNewLabel_3);
		
		if (isAdd) {
			textField_2 = new JTextField();
		}
		else {
			textField_2 = new JTextField(cashier.getPerson().getName());
		}
		textField_2.setBounds(101, 87, 96, 19);
		add(textField_2);
		textField_2.setColumns(10);
		
		if (isAdd) {
			textField_3 = new JTextField();
		}
		else {
			textField_3 = new JTextField(cashier.getPerson().getAddress());
		}
		textField_3.setBounds(101, 116, 96, 19);
		add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Address:");
		lblNewLabel_4.setBounds(28, 119, 63, 13);
		add(lblNewLabel_4);
		
		if (isAdd) {
			textField_4 = new JTextField();
		}
		else {
			textField_4 = new JTextField(cashier.getPerson().getCity());
		}
		textField_4.setBounds(101, 145, 96, 19);
		add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("City:");
		lblNewLabel_5.setBounds(28, 148, 45, 13);
		add(lblNewLabel_5);
		
		if (isAdd) {
			textField_5 = new JTextField();
		}
		else {
			textField_5 = new JTextField(cashier.getPerson().getPhone());
		}
		textField_5.setBounds(101, 174, 96, 19);
		add(textField_5);
		textField_5.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Phone:");
		lblNewLabel_6.setBounds(28, 177, 45, 13);
		add(lblNewLabel_6);
		
		if (isAdd) {
			passwordField = new JPasswordField();
		}
		else {
			passwordField = new JPasswordField(cashier.getPassword());
		}
		passwordField.setBounds(364, 55, 7, 19);
		add(passwordField);
		
		JLabel lblNewLabel_7 = new JLabel("Password:");
		lblNewLabel_7.setBounds(28, 206, 63, 13);
		add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("State:");
		lblNewLabel_8.setBounds(217, 148, 45, 13);
		add(lblNewLabel_8);
		
		if (isAdd) {
			textField_7 = new JTextField();
		}
		else {
			textField_7 = new JTextField(cashier.getPerson().getState());
		}
		textField_7.setBounds(257, 145, 31, 19);
		add(textField_7);
		textField_7.setColumns(10);
		
		JLabel lblNewLabel_9 = new JLabel("Zip:");
		lblNewLabel_9.setBounds(313, 148, 31, 13);
		add(lblNewLabel_9);
		
		if (isAdd) {
			textField_8 = new JTextField();
		}
		else {
			textField_8 = new JTextField(cashier.getPerson().getZip());
		}
		textField_8.setBounds(354, 145, 59, 19);
		add(textField_8);
		textField_8.setColumns(10);
		
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!isAdd && !cashier.getNumber().equals(textField.getText())) {
					store.removeCashier(cashier);
					
					cashier.setNumber(textField.getText());
					cashier.getPerson().setSSN(textField_1.getText());
					cashier.getPerson().setName(textField_2.getText());
					cashier.getPerson().setAddress(textField_3.getText());
					cashier.getPerson().setCity(textField_4.getText());
					cashier.getPerson().setPhone(textField_5.getText());
					cashier.setPassword(passwordField.getText());
					cashier.getPerson().setState(textField_7.getText());
					cashier.getPerson().setZip(textField_8.getText());
					
					store.addCashier(cashier);
				}
				else {
					cashier.setNumber(textField.getText());
					cashier.getPerson().setSSN(textField_1.getText());
					cashier.getPerson().setName(textField_2.getText());
					cashier.getPerson().setAddress(textField_3.getText());
					cashier.getPerson().setCity(textField_4.getText());
					cashier.getPerson().setPhone(textField_5.getText());
					cashier.setPassword(passwordField.getText());
					cashier.getPerson().setState(textField_7.getText());
					cashier.getPerson().setZip(textField_8.getText());
					
					if (isAdd) store.addCashier(cashier);
				}
				
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new CashierListPanel(currentFrame, store));
				currentFrame.getContentPane().revalidate();
			}
		});
		
		btnNewButton.setBounds(68, 233, 85, 21);
		add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new CashierListPanel(currentFrame, store));
				currentFrame.getContentPane().revalidate();
			}
		});
		
		btnNewButton_1.setBounds(203, 233, 85, 21);
		add(btnNewButton_1);
		
		passwordField = new JPasswordField(cashier.getPassword());
		passwordField.setBounds(92, 203, 97, 19);
		add(passwordField);
	}
}
