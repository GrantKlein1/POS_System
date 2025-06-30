package POSUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import POSPD.Store;
import POSPD.TaxCategory;

public class TaxCategoryListPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	DefaultListModel<TaxCategory> listModel;
	/**
	 * Create the panel.
	 */
	JButton btnNewButton_1;
	JButton btnNewButton_2;
	JButton btnNewButton;
	public TaxCategoryListPanel(JFrame currentFrame, Store store) {
		setLayout(null);

		JLabel lblNewLabel = new JLabel("Tax Category List");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(151, 38, 136, 13);
		add(lblNewLabel);
		
		listModel = new DefaultListModel<TaxCategory>();
		for (TaxCategory taxCat : store.getTaxCategories().values()) {
			listModel.addElement(taxCat);
		}
		
		JList<TaxCategory> list = new JList<TaxCategory>(listModel);
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
			if(list.getSelectedValue() != null) {			//means there is a register in the list that is selected
				btnNewButton_1.setEnabled(true);
				if (list.getSelectedValue().getItems().isEmpty()) {
					btnNewButton_2.setEnabled(true);
				}
				else {
					btnNewButton_2.setEnabled(false);
				}
			}
			else {
				btnNewButton_1.setEnabled(false);
				btnNewButton_2.setEnabled(false);
			}
			}
		});
		
		list.setBounds(151, 61, 136, 133);
		add(list);
		
		btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new TaxCategoryEditPanel(currentFrame, store, new TaxCategory(""), true));
				currentFrame.getContentPane().revalidate();
			}
		});
		
		btnNewButton.setBounds(51, 236, 85, 21);
		add(btnNewButton);
		
		btnNewButton_1 = new JButton("Edit");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new TaxCategoryEditPanel(currentFrame, store, list.getSelectedValue(), false));
				currentFrame.getContentPane().revalidate();
			}
		});
		
		btnNewButton_1.setBounds(178, 236, 85, 21);
		btnNewButton_1.setEnabled(false);
		add(btnNewButton_1);
		
		btnNewButton_2 = new JButton("Delete");
		btnNewButton_2.setEnabled(false);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				store.removeTaxCategory(list.getSelectedValue());
				listModel.removeElement(list.getSelectedValue());
			}
		});
		
		btnNewButton_2.setBounds(310, 236, 85, 21);
		btnNewButton_2.setEnabled(false);
		add(btnNewButton_2);

		
		
		
	//don't put anything past here
	}

}
