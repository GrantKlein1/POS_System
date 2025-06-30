package POSUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import POSPD.*;
import javax.swing.JLabel;
import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SaleEntryPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	DefaultListModel<SaleLineItem> listModel;
	
	Boolean isTaxFree = false;
	Boolean saleComplete = false;
	private JTextField textField;
	private JTextField textField_1;
	JLabel lblNewLabel_12 = new JLabel();
	JLabel lblNewLabel_13 = new JLabel();
	JLabel lblNewLabel_14 = new JLabel();
	JLabel lblNewLabel_15 = new JLabel();
	JLabel lblNewLabel_16 = new JLabel();
	JLabel lblNewLabel_17 = new JLabel();
	JButton btnNewButton_1;
	JButton btnNewButton_3;

	/**
	 * Create the panel.
	 */
	public SaleEntryPanel(JFrame currentFrame, Store store, Cashier cashier, Register register, Session session, Sale sale, Boolean saleComplete) {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Cashier:");
		lblNewLabel.setBounds(10, 35, 55, 13);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Register");
		lblNewLabel_1.setBounds(10, 52, 55, 13);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel(cashier.toString());
		lblNewLabel_2.setBounds(63, 35, 60, 13);
		add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel(register.getNumber());
		lblNewLabel_3.setBounds(63, 52, 60, 13);
		add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Sale");
		lblNewLabel_4.setBounds(178, 35, 45, 13);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Tax Free");
		chckbxNewCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!isTaxFree) {
					isTaxFree = true;
				}
				else if (isTaxFree) {
					isTaxFree = false;
				}
				sale.setTaxFree(isTaxFree);
				lblNewLabel_12.setText(sale.calcSubTotal().toString());
				lblNewLabel_13.setText(sale.calcTax().toString());
				lblNewLabel_14.setText(sale.calcTotal().toString());
				lblNewLabel_15.setText(sale.calcAmountTendered().toString());
				lblNewLabel_16.setText(sale.calcChange().toString());
				currentFrame.revalidate();
				currentFrame.repaint();
			}
		});
		chckbxNewCheckBox.setBounds(321, 48, 93, 21);
		add(chckbxNewCheckBox);
		
		listModel = new DefaultListModel<SaleLineItem>();
		for (SaleLineItem sli : sale.getSaleLineItems()) {
			listModel.addElement(sli);
		}
		
		JList<SaleLineItem> list = new JList<SaleLineItem>(listModel);
		list.setBounds(24, 105, 242, 109);
		add(list);
		
		JLabel lblNewLabel_5 = new JLabel("SubTotal:");
		lblNewLabel_5.setBounds(276, 117, 60, 13);
		add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Tax:");
		lblNewLabel_6.setBounds(276, 140, 60, 13);
		add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Total:");
		lblNewLabel_7.setBounds(276, 163, 60, 13);
		add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("Amt Tendered:");
		lblNewLabel_8.setBounds(276, 186, 96, 13);
		add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("Change:");
		lblNewLabel_9.setBounds(276, 211, 60, 13);
		add(lblNewLabel_9);
		
		JLabel lblNewLabel_10 = new JLabel("Item:");
		lblNewLabel_10.setBounds(18, 82, 45, 13);
		add(lblNewLabel_10);
		
		
		
		textField = new JTextField();
		textField.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (textField_1.getText().isBlank()) {
						textField_1.setText("1");
					}
					if (!(store.findItemForNumber(textField.getText()) == null)) {
						btnNewButton_1.setEnabled(false);
						btnNewButton_3.setEnabled(false);
						lblNewLabel_17.setVisible(false);
						Item item = store.findItemForNumber(textField.getText());
						SaleLineItem saleLineItem = new SaleLineItem(sale, item, textField_1.getText());
						sale.addSaleLineItem(saleLineItem);
						listModel.addElement(saleLineItem);
						lblNewLabel_12.setText(sale.calcSubTotal().toString());
						lblNewLabel_13.setText(sale.calcTax().toString());
						lblNewLabel_14.setText(sale.calcTotal().toString());
						lblNewLabel_15.setText(sale.calcAmountTendered().toString());
						lblNewLabel_16.setText(sale.calcChange().toString());
					}
					else {
						lblNewLabel_17.setVisible(true);
					}
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		
		});
		
		textField.setBounds(63, 79, 87, 19);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_11 = new JLabel("Quantity:");
		lblNewLabel_11.setBounds(160, 82, 55, 13);
		add(lblNewLabel_11);
		
		textField_1 = new JTextField();
		textField_1.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (!(store.findItemForNumber(textField.getText()) == null)) {
						
					Item item = store.findItemForNumber(textField.getText());
					SaleLineItem saleLineItem = new SaleLineItem(sale, item, textField_1.getText());
					sale.addSaleLineItem(saleLineItem);
					listModel.addElement(saleLineItem);
					lblNewLabel_12.setText(sale.calcSubTotal().toString());
					lblNewLabel_13.setText(sale.calcTax().toString());
					lblNewLabel_14.setText(sale.calcTotal().toString());
					lblNewLabel_15.setText(sale.calcAmountTendered().toString());
					lblNewLabel_16.setText(sale.calcChange().toString());
					}
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		
		});
		textField_1.setBounds(225, 79, 41, 19);
		add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton = new JButton("Payment");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new AddPaymentPanel(currentFrame, store, sale, register, session, cashier));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnNewButton.setBounds(10, 235, 96, 21);
		add(btnNewButton);
		
		btnNewButton_1 = new JButton("Complete Sale");
		
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (sale.isPaymentEnough()) {
					session.addSale(sale);
					currentFrame.getContentPane().removeAll();
					currentFrame.getContentPane().add(new SaleEntryPanel(currentFrame, store, cashier, register, session, new Sale(), true));
					currentFrame.getContentPane().revalidate();
				}
			}
		});
		btnNewButton_1.setBounds(130, 235, 123, 21);
		add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Cancel Sale");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new SaleEntryPanel(currentFrame, store, cashier, register, session, new Sale(), true));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnNewButton_2.setBounds(10, 269, 96, 21);
		add(btnNewButton_2);
		
		if (saleComplete) {
			btnNewButton_1.setEnabled(true);
		}
		else {
			btnNewButton_1.setEnabled(false);
		}
		
		
		btnNewButton_3 = new JButton("End Session");
		if(saleComplete && sale.getSaleLineItems().isEmpty()) {
			btnNewButton_3.setEnabled(true);
		}
		else {
			btnNewButton_3.setEnabled(false);
		}
		
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				session.setEndDateTime(LocalDateTime.now());
				cashier.addSession(session);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new EndSessionPanel(currentFrame, store, session));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnNewButton_3.setBounds(130, 269, 123, 21);
		add(btnNewButton_3);
		
		lblNewLabel_12 = new JLabel(sale.calcSubTotal().toString());
		lblNewLabel_12.setBounds(333, 117, 81, 13);
		add(lblNewLabel_12);
		
		lblNewLabel_13 = new JLabel(sale.calcTax().toString());
		lblNewLabel_13.setBounds(333, 140, 81, 13);
		add(lblNewLabel_13);
		
		lblNewLabel_14 = new JLabel(sale.calcTotal().toString());
		lblNewLabel_14.setBounds(333, 163, 81, 13);
		add(lblNewLabel_14);
		
		lblNewLabel_15 = new JLabel(sale.calcAmountTendered().toString());
		lblNewLabel_15.setBounds(382, 186, 58, 13);
		add(lblNewLabel_15);
		
		lblNewLabel_16 = new JLabel(sale.calcChange().toString());
		lblNewLabel_16.setBounds(333, 211, 81, 13);
		add(lblNewLabel_16);
		
		lblNewLabel_17 = new JLabel("Item Not Found");
		lblNewLabel_17.setVisible(false);
		lblNewLabel_17.setBounds(63, 66, 87, 13);
		add(lblNewLabel_17);
		
	}
}
