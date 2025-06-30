package POSUI;

import javax.swing.JFrame;
import javax.swing.JPanel;

import POSPD.Cashier;
import POSPD.Register;
import POSPD.Sale;
import POSPD.Session;
import POSPD.Store;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddPaymentPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public AddPaymentPanel(JFrame currentFrame, Store store, Sale sale, Register register, Session session, Cashier cashier) {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Payment");
		lblNewLabel.setBounds(192, 26, 106, 13);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Payment Due:");
		lblNewLabel_1.setBounds(33, 67, 116, 13);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Amount Tendered:");
		lblNewLabel_2.setBounds(33, 130, 127, 13);
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
		if (!sale.isPaymentEnough()) {
			btnNewButton_3.setEnabled(false);
		}
		else {
			btnNewButton_3.setEnabled(true);
		}
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new SaleEntryPanel(currentFrame, store, cashier, register, session, sale, true));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnNewButton_3.setBounds(158, 255, 159, 21);
		add(btnNewButton_3);
		
	}

}
