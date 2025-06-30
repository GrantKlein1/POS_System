package POSUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import POSPD.*;

public class CheckPaymentEntryPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	Check checkPayment = new Check();
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	/**
	 * Create the panel.
	 */
	public CheckPaymentEntryPanel(JFrame currentFrame, Store store, Cashier cashier, Sale sale, Register register, Session session) {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Payment");
		lblNewLabel.setBounds(192, 26, 45, 13);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Payment Due:");
		lblNewLabel_1.setBounds(41, 67, 149, 13);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Amount Tendered:");
		lblNewLabel_2.setBounds(41, 130, 149, 16);
		add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel(sale.calcAmountForPayment(sale.calcAmountTendered()).toString());
		lblNewLabel_3.setBounds(41, 90, 98, 13);
		add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel(sale.calcAmountTendered().toString());
		lblNewLabel_4.setBounds(41, 156, 98, 13);
		add(lblNewLabel_4);
		
		JButton btnNewButton = new JButton("Cash");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new CashPaymentEntryPanel(currentFrame, store, cashier, sale, register, session));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnNewButton.setBounds(33, 184, 85, 21);
		add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Check");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new CheckPaymentEntryPanel(currentFrame, store, cashier, sale, register, session));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnNewButton_1.setBounds(33, 215, 85, 21);
		add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Credit");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new CreditPaymentEntryPanel(currentFrame, store, cashier, sale, register, session));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnNewButton_2.setBounds(33, 246, 85, 21);
		add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Payment Complete");
		btnNewButton_3.setEnabled(false);
		btnNewButton_3.setBounds(175, 255, 141, 21);
		add(btnNewButton_3);
		
		JLabel lblNewLabel_5 = new JLabel("Enter Check");
		lblNewLabel_5.setBounds(245, 56, 141, 13);
		add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Amount:");
		lblNewLabel_6.setBounds(212, 79, 77, 13);
		add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Routing Num:");
		lblNewLabel_7.setBounds(212, 102, 98, 13);
		add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("Account Num:");
		lblNewLabel_8.setBounds(212, 130, 104, 13);
		add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("Check Num:");
		lblNewLabel_9.setBounds(212, 156, 96, 13);
		add(lblNewLabel_9);
		
		textField_1 = new JTextField();
		textField_1.setBounds(320, 73, 96, 19);
		add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(320, 99, 96, 19);
		add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(319, 127, 96, 19);
		add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(318, 153, 96, 19);
		add(textField_4);
		textField_4.setColumns(10);
		
		
		JButton btnNewButton_4 = new JButton("Save");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkPayment.setAmount(new BigDecimal(textField_1.getText()));
				checkPayment.setAmtTendered(new BigDecimal(textField_1.getText()));
				checkPayment.setRoutingNumber(textField_2.getText());
				checkPayment.setAccountNumber(textField_3.getText());
				checkPayment.setCheckNumber(textField_4.getText());
				checkPayment.countsAsCheck();
				sale.addPayment(checkPayment);
				
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new AddPaymentPanel(currentFrame, store, sale, register, session, cashier));
				currentFrame.getContentPane().revalidate();
				
			}
		});
		btnNewButton_4.setBounds(216, 215, 85, 21);
		add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("Cancel");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new AddPaymentPanel(currentFrame, store, sale, register, session, cashier));
				currentFrame.getContentPane().revalidate();
			}
		});
		
		btnNewButton_5.setBounds(326, 215, 85, 21);
		add(btnNewButton_5);
		
		
		
	}

}
