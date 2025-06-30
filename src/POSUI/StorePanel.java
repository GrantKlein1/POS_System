package POSUI;

import javax.swing.JPanel;

import POSPD.Store;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StorePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public StorePanel(JFrame currentFrame, Store store) {
		setLayout(null);//might need to take out to display screen
		
		JLabel lblNewLabel = new JLabel("Edit Store");
		lblNewLabel.setBounds(187, 10, 63, 13);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Name: ");
		lblNewLabel_1.setBounds(100, 88, 45, 13);
		add(lblNewLabel_1);
		
		textField = new JTextField(store.getName());
		textField.setBounds(155, 85, 159, 19);
		add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Cancel");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new POSHomePage(store));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnNewButton.setBounds(99, 193, 85, 21);
		add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Save");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				store.setName(textField.getText());
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new POSHomePage(store));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnNewButton_1.setBounds(259, 193, 85, 21);
		add(btnNewButton_1);
		
	}
}
