package POSUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import POSPD.Store;
import POSPD.TaxCategory;
import POSPD.TaxRate;

public class TaxRateEditPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textField_1;
	private JTextField textField_2;
	
	/**
	 * Create the panel.
	 */
	public TaxRateEditPanel(JFrame currentFrame, Store store, TaxRate taxRate, TaxCategory taxCat, Boolean isAdd, JPanel currentPanel) {
		
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Edit Tax Rate");
		lblNewLabel.setBounds(156, 31, 114, 13);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Rate:");
		lblNewLabel_1.setBounds(74, 72, 45, 13);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Date:");
		lblNewLabel_2.setBounds(74, 134, 45, 13);
		add(lblNewLabel_2);
		
		if (isAdd) {
			textField_1 = new JTextField();
		}
		else {
			textField_1 = new JTextField(taxRate.getTaxRate().toString());
		}
		textField_1.setBounds(174, 69, 96, 19);
		add(textField_1);
		textField_1.setColumns(10);
		
		if (isAdd) {
			textField_2 = new JTextField();
		}
		else {
			textField_2 = new JTextField(taxRate.getEffectiveDate().toString());
		}
		textField_2.setBounds(174, 131, 96, 19);
		add(textField_2);
		textField_2.setColumns(10);
		
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				if (!isAdd && !taxRate.getEffectiveDate().equals(LocalDate.parse(textField_2.getText(), formatter))) {		//if true then we know we are changing teh key value
					taxCat.removeTaxRate(taxRate);
					taxRate.setTaxRate(new BigDecimal(textField_1.getText()));
					taxRate.setEffectiveDate(LocalDate.parse(textField_2.getText(), formatter));		//take it out then add it back with the new key value
					taxCat.addTaxRate(taxRate);
				}
				else {
					taxRate.setTaxRate(new BigDecimal(textField_1.getText()));
					taxRate.setEffectiveDate(LocalDate.parse(textField_2.getText(), formatter));
					if (isAdd) taxCat.addTaxRate(taxRate);
				}
				
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(currentPanel);
				currentFrame.getContentPane().revalidate();
				currentFrame.getContentPane().repaint();
			}
		});
		btnNewButton.setBounds(66, 201, 85, 21);
		add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(currentPanel);
				currentFrame.getContentPane().revalidate();
				currentFrame.getContentPane().repaint();
			}
		});
		btnNewButton_1.setBounds(247, 201, 85, 21);
		add(btnNewButton_1);
		
		JLabel lblNewLabel_3 = new JLabel("dd/mm/yyyy");
		lblNewLabel_3.setBounds(74, 155, 77, 13);
		add(lblNewLabel_3);
		
	}
}
