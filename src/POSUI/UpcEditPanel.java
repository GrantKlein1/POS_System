package POSUI;

import javax.swing.JFrame;
import javax.swing.JPanel;

import POSPD.*;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class UpcEditPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public UpcEditPanel(JFrame currentFrame, Store store, Item item, UPC upc, Boolean isAdd, JPanel currentPanel) {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Edit UPC");
		lblNewLabel.setBounds(166, 28, 84, 13);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("UPC Code:");
		lblNewLabel_1.setBounds(50, 62, 75, 13);
		add(lblNewLabel_1);
		
		if (isAdd) {
		textField = new JTextField();
		}
		else {
			textField = new JTextField(upc.getUpc());
		}
		textField.setBounds(135, 59, 96, 19);
		add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!isAdd && !upc.getUpc().equals(textField.getText())) {		//if true then we know we are changing the key value
					store.removeUPC(upc);
					item.removeUPC(upc);
					upc.setUpc(textField.getText());		//take it out then add it back with the new key value
					store.addUPC(upc);
					item.addUPC(upc);
					upc.setItem(item);
				}
				else {
					upc.setUpc(textField.getText());
					if (isAdd) { 
						store.addUPC(upc);
						item.addUPC(upc);
						upc.setItem(item);
					}
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

	}

}
