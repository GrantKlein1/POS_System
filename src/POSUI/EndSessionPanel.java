package POSUI;

import javax.swing.JFrame;
import javax.swing.JPanel;

import POSPD.*;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class EndSessionPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Create the panel.
	 */
	public EndSessionPanel(JFrame currentFrame, Store store, Session session) {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Session Summary");
		lblNewLabel.setBounds(140, 21, 103, 13);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Cashier");
		lblNewLabel_1.setBounds(21, 50, 65, 13);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Register");
		lblNewLabel_2.setBounds(21, 73, 65, 13);
		add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel(session.getCashier().toString());
		lblNewLabel_3.setBounds(96, 50, 65, 13);
		add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel(session.getRegister().getNumber());
		lblNewLabel_4.setBounds(96, 73, 65, 13);
		add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Number Sales:");
		lblNewLabel_5.setBounds(21, 114, 84, 13);
		add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Total Sales:");
		lblNewLabel_6.setBounds(21, 137, 70, 13);
		add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel(String.valueOf(session.getSales().size()));
		lblNewLabel_7.setBounds(115, 114, 45, 13);
		add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel(session.calcTotal().toString());
		lblNewLabel_8.setBounds(115, 137, 76, 13);
		add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("Enter Cash:");
		lblNewLabel_9.setBounds(21, 159, 84, 13);
		add(lblNewLabel_9);
		
		JLabel lblNewLabel_10 = new JLabel("Cash Count Diff:");
		lblNewLabel_10.setBounds(21, 182, 94, 13);
		add(lblNewLabel_10);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					BigDecimal cashDiff = session.calcCashCountDiff(new BigDecimal(textField.getText()));
					session.setCountedCash(new BigDecimal(textField.getText()));
					textField_1.setText(cashDiff.toString());
					textField_1.setEnabled(false);
				}
			}
		});
		textField.setBounds(125, 156, 96, 19);
		add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(125, 179, 96, 19);
		add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton = new JButton("End Session");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				store.addSession(session);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new POSHomePage(store));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnNewButton.setBounds(115, 238, 128, 21);
		add(btnNewButton);
		
		
		
	}
}
