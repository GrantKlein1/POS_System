package POSUI;

import javax.swing.JFrame;
import javax.swing.JPanel;

import POSPD.Register;
import POSPD.Store;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegisterEditPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public RegisterEditPanel(JFrame currentFrame, Store store, Register register,  Boolean isAdd) {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Edit Register");
		lblNewLabel.setBounds(185, 31, 85, 13);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Number:");
		lblNewLabel_1.setBounds(50, 83, 58, 13);
		add(lblNewLabel_1);
		
		textField = new JTextField(register.getNumber());
		textField.setBounds(134, 80, 96, 19);
		add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!isAdd && !register.getNumber().equals(textField.getText())) {		//if true then we know we are changing teh key value
					store.removeRegister(register);
					register.setNumber(textField.getText());		//take it out then add it back with teh new key value
					store.addRegister(register);
				}
				else {
					register.setNumber(textField.getText()); //maybe take iut
					if (isAdd) store.addRegister(register);
				}
				
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new RegisterListPanel(currentFrame, store));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnNewButton.setBounds(66, 201, 85, 21);
		add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new RegisterListPanel(currentFrame, store));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnNewButton_1.setBounds(247, 201, 85, 21);
		add(btnNewButton_1);
		
	}
}

