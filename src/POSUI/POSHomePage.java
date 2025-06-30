package POSUI;

import javax.swing.JPanel;

import POSPD.Store;

import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class POSHomePage extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public POSHomePage(Store store) {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel(store.getName());
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(98, 143, 254, 13);
		add(lblNewLabel);
		
		
		
	}
}
