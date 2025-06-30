package POSUI;

import javax.swing.JFrame;
import javax.swing.JPanel;

import POSPD.*;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;

public class PriceEditPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_1;
	Boolean isPromoPrice = false;
	private JTextField textField_2;
	JLabel lblNewLabel_3;

	/**
	 * Create the panel.
	 */
	public PriceEditPanel(JFrame currentFrame, Store store, Item item, Price price, Boolean isAdd, JPanel currentPanel, Boolean isPromo, PromoPrice pPrice) {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Edit Price");
		lblNewLabel.setBounds(180, 28, 89, 13);
		add(lblNewLabel);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Promo Price");
		chckbxNewCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!isPromoPrice) {
					isPromoPrice = true;
				}
				else if (isPromoPrice) {
					isPromoPrice = false;
				}
				if (isPromoPrice) {
					lblNewLabel_3.setVisible(true);
					textField_2.setVisible(true);
				}
				else {
					lblNewLabel_3.setVisible(false);
					textField_2.setVisible(false);
				}
			}
		});
		
		PromoPrice promoPrice = new PromoPrice();
		
		chckbxNewCheckBox.setBounds(118, 58, 110, 21);
		add(chckbxNewCheckBox);
		
		JLabel lblNewLabel_1 = new JLabel("Price:");
		lblNewLabel_1.setBounds(20, 106, 64, 13);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Effective Date:");
		lblNewLabel_2.setBounds(20, 147, 89, 13);
		add(lblNewLabel_2);
		
		if (isAdd) {
			textField = new JTextField();
		}
		else {
			textField = new JTextField(price.getPrice().toString());
		}
		textField.setBounds(118, 103, 96, 19);
		add(textField);
		textField.setColumns(10);
	
		if (isAdd) {
			textField_1 = new JTextField();
		}
		else {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			textField_1 = new JTextField(price.getEffectiveDate().format(formatter));
		}
		textField_1.setBounds(118, 144, 96, 19);
		add(textField_1);
		textField_1.setColumns(10);
		
		lblNewLabel_3 = new JLabel("Promo End Date:");
		lblNewLabel_3.setBounds(20, 182, 114, 13);
		lblNewLabel_3.setVisible(false);
		add(lblNewLabel_3);
		
		textField_2 = new JTextField();
		textField_2.setBounds(144, 179, 96, 19);
		textField_2.setVisible(false);
		add(textField_2);
		textField_2.setColumns(10);
		
		
		if (isPromo) {
			lblNewLabel_3.setVisible(true);
			textField_2.setVisible(true);
			textField_2.setText(pPrice.getEndDate().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
		}
		else {
			lblNewLabel_3.setVisible(false);
			textField_2.setVisible(false);
		}
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
				if (isAdd) {	//should have more here
					if (isPromoPrice) {
						promoPrice.setEffectiveDate(LocalDate.parse(textField_1.getText(), formatter));	
						promoPrice.setEndDate(textField_2.getText());
						promoPrice.setPrice(new BigDecimal(textField.getText()));
						
						promoPrice.setItem(item);
						item.addPrice(promoPrice);
					}
					else {
						Price price = new Price(textField.getText(),textField_1.getText());
						price.setEffectiveDate(LocalDate.parse(textField_1.getText(), formatter));
						price.setPrice(new BigDecimal(textField.getText()));
						price.setItem(item);
						item.addPrice(price);
					}
				}
				else {
					if (isPromoPrice) {
						promoPrice.setEffectiveDate(LocalDate.parse(textField_1.getText(), formatter));
						promoPrice.setEndDate(textField_2.getText());
						promoPrice.setPrice(new BigDecimal(textField.getText()));
						promoPrice.setItem(item);
						item.addPrice(promoPrice);
					}
					else {
						price.setEffectiveDate(LocalDate.parse(textField_1.getText(), formatter));
						price.setPrice(new BigDecimal(textField.getText()));
						price.setItem(item);
						item.addPrice(price);
					}
				}
				
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(currentPanel);
				currentFrame.getContentPane().revalidate();
				currentFrame.getContentPane().repaint();
			}
		});
		btnNewButton.setBounds(55, 228, 85, 21);
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
		btnNewButton_1.setBounds(184, 228, 85, 21);
		add(btnNewButton_1);

		
		
	}
}
