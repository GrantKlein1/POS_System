package POSUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import POSPD.Cashier;
import POSPD.Register;
import POSPD.Sale;
import POSPD.Session;
import POSPD.Store;
import POSPD.TaxCategory;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class POSLoginPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textField_2;
	private JTextField textField_3;
	private	JPasswordField passwordField;
	private boolean firstPasswordAttempt = true;
	DefaultComboBoxModel<Cashier> cashierList;
	JComboBox<Cashier> cashierComboBox;
	DefaultComboBoxModel<Register> registerList;
	JComboBox<Register> registerComboBox;

	/**
	 * Create the panel.
	 */
	public POSLoginPanel(JFrame currentFrame, Store store) {
		setLayout(null);
		JLabel lblNewLabel_5 = new JLabel("Password is invalid");
		lblNewLabel_5.setBounds(279, 175, 149, 13);
		lblNewLabel_5.setVisible(false);
		add(lblNewLabel_5);
		
		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.setBounds(163, 39, 45, 13);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Cashier Number:");
		lblNewLabel_1.setBounds(39, 88, 100, 13);
		add(lblNewLabel_1);
		
		cashierList = new DefaultComboBoxModel<Cashier>();
		for (Cashier cashier : store.getCashiers().values()) {
			cashierList.addElement(cashier);
		}
		cashierComboBox = new JComboBox<Cashier>(cashierList);
		cashierComboBox.setBounds(149, 84, 96, 21);
		add(cashierComboBox);

		JLabel lblNewLabel_2 = new JLabel("Register Number:");
		lblNewLabel_2.setBounds(39, 117, 100, 13);
		add(lblNewLabel_2);
		
		registerList = new DefaultComboBoxModel<Register>();
		for (Register register : store.getRegisters().values()) {
			registerList.addElement(register);
		}
		registerComboBox = new JComboBox<Register>(registerList);
		registerComboBox.setBounds(149, 113, 96, 21);
		add(registerComboBox);
		
		JLabel lblNewLabel_3 = new JLabel("Starting Cash:");
		lblNewLabel_3.setBounds(54, 146, 85, 13);
		add(lblNewLabel_3);
		
		textField_2 = new JTextField();	//starting cash
		textField_2.setBounds(149, 143, 96, 19);
		add(textField_2);
		textField_2.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (firstPasswordAttempt) {
					lblNewLabel_5.setVisible(false);
					firstPasswordAttempt = false;
				}
				else if (lblNewLabel_5.isVisible()) {
					lblNewLabel_5.setVisible(false);
				}
				else {
					lblNewLabel_5.setVisible(true);
				}
			}
		});
		
		passwordField.setBounds(149, 172, 96, 19);
		add(passwordField);
		passwordField.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Password:");
		lblNewLabel_4.setBounds(54, 175, 85, 13);
		add(lblNewLabel_4);
		
		JButton btnNewButton = new JButton("Cancel");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new POSHomePage(store));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnNewButton.setBounds(205, 227, 85, 21);
		add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Login");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					Cashier cashier = store.findCashierForNumber(cashierComboBox.getItemAt(cashierComboBox.getSelectedIndex()).getNumber());
					Register register = store.findRegisterByNumber(registerComboBox.getItemAt(registerComboBox.getSelectedIndex()).getNumber());
					register.getCashDrawer().setCashAmount(new BigDecimal(textField_2.getText()));
					
					Session session = new Session(cashier, register);
				if (cashier.getPassword().equals(passwordField.getText())) {
					Sale sale = new Sale();
					currentFrame.getContentPane().removeAll();
					currentFrame.getContentPane().add(new SaleEntryPanel(currentFrame, store, cashier, register, session, sale, false));
					currentFrame.getContentPane().revalidate();
				}
				else {
					lblNewLabel_5.setVisible(true);
				}
			}
		});
		btnNewButton_1.setBounds(64, 227, 85, 21);
		add(btnNewButton_1);

		
	}
}
