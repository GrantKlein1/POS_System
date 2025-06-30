package POSUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import POSPD.*;
import javax.swing.JTextField;

public class CashPaymentEntryPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	Cash cashPayment = new Cash();

	/**
	 * Create the panel.
	 */
	public CashPaymentEntryPanel(JFrame currentFrame, Store store, Cashier cashier, Sale sale, Register register, Session session) {

		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Payment");
		lblNewLabel.setBounds(192, 26, 62, 13);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Payment Due:");
		lblNewLabel_1.setBounds(33, 67, 106, 13);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Amount Tendered:");
		lblNewLabel_2.setBounds(33, 130, 131, 13);
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
		
		JLabel lblNewLabel_5 = new JLabel("Enter Cash Payment");
		lblNewLabel_5.setBounds(192, 67, 131, 13);
		add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Amount Tendered:");
		lblNewLabel_6.setBounds(192, 90, 124, 13);
		add(lblNewLabel_6);
		
		textField = new JTextField();
		textField.setBounds(326, 87, 96, 19);
		add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton_4 = new JButton("Save");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				register.getCashDrawer().addCash(new BigDecimal(textField.getText()));
				cashPayment.countsAsCash();
				cashPayment.setAmount(sale.calcAmountForPayment(new BigDecimal(textField.getText())));
				cashPayment.setAmtTendered(new BigDecimal(textField.getText()));
				sale.addPayment(cashPayment);
				if (sale.isPaymentEnough()) {
					register.getCashDrawer().removeCash(sale.calcChange());
				}
				
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new AddPaymentPanel(currentFrame, store, sale, register, session, cashier));
				currentFrame.getContentPane().revalidate();
				
			}
		});
		btnNewButton_4.setBounds(212, 126, 85, 21);
		add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("Cancel");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new AddPaymentPanel(currentFrame, store, sale, register, session, cashier));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnNewButton_5.setBounds(318, 126, 85, 21);
		add(btnNewButton_5);
	}

}
