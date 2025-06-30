package POSUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

import POSPD.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;

public class TaxCategoryEditPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	DefaultListModel<TaxRate> listModel;
	JList<TaxRate> list;
	JButton btnNewButton_2;
	JButton btnNewButton_3;
	JButton btnNewButton_4;
	
	/**
	 * Create the panel.
	 */
	public TaxCategoryEditPanel(JFrame currentFrame, Store store, TaxCategory taxCat, Boolean isAdd) {
		JPanel currentPanel = this;
		addAncestorListener(new AncestorListener() {
			public void ancestorAdded(AncestorEvent event) {
				listModel = new DefaultListModel<TaxRate>();
				for (TaxRate taxRate : taxCat.getTaxRates()) {
					listModel.addElement(taxRate);
				}
				list.setModel(listModel);
			}
			public void ancestorMoved(AncestorEvent event) {
			}
			public void ancestorRemoved(AncestorEvent event) {
			}
		});
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Edit Tax Category");
		lblNewLabel.setBounds(185, 31, 85, 13);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Category: ");
		lblNewLabel_1.setBounds(50, 83, 58, 13);
		add(lblNewLabel_1);
		
		textField = new JTextField(taxCat.getCategory());
		textField.setBounds(134, 80, 96, 19);
		add(textField);
		textField.setColumns(10);
		
		
		listModel = new DefaultListModel<TaxRate>();
		if (!isAdd) {
			for (TaxRate taxRate : taxCat.getTaxRates()) {
				listModel.addElement(taxRate);
			}
		}
		list = new JList<TaxRate>(listModel);
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (list.getSelectedValue() != null) {
					btnNewButton_3.setEnabled(true);
				}
				else {
					btnNewButton_3.setEnabled(false);
				}
				if (list.getSelectedValue() != null && taxCat.getTaxRates().size() > 1 ){
					btnNewButton_4.setEnabled(true);
				}
				else {
					btnNewButton_4.setEnabled(false);
				}
			}
		});
		list.setBounds(269, 60, 136, 102);
		add(list);
		
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!isAdd && !taxCat.getCategory().equals(textField.getText())) {
					store.removeTaxCategory(taxCat);
					taxCat.setCategory(textField.getText());
					store.addTaxCategory(taxCat);
				}
				else {
					taxCat.setCategory(textField.getText()); //maybe take out
					if (isAdd) store.addTaxCategory(taxCat);
				}
				
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new TaxCategoryListPanel(currentFrame, store));
				currentFrame.getContentPane().revalidate();
			}
		});
		
		btnNewButton.setBounds(68, 233, 85, 21);
		add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new TaxCategoryListPanel(currentFrame, store));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnNewButton_1.setBounds(248, 233, 85, 21);
		add(btnNewButton_1);
		
		
		btnNewButton_2 = new JButton("Add");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new TaxRateEditPanel(currentFrame, store, new TaxRate(), taxCat, true, currentPanel));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnNewButton_2.setBounds(216, 183, 71, 21);
		add(btnNewButton_2);
		
		btnNewButton_3 = new JButton("Edit");
		btnNewButton_3.setEnabled(false);
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new TaxRateEditPanel(currentFrame, store, list.getSelectedValue(), taxCat, false, currentPanel));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnNewButton_3.setBounds(290, 183, 69, 21);
		add(btnNewButton_3);
		
		btnNewButton_4 = new JButton("Delete");
		btnNewButton_4.setEnabled(false);
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				taxCat.removeTaxRate(list.getSelectedValue());
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new TaxCategoryEditPanel(currentFrame, store, taxCat, isAdd));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnNewButton_4.setBounds(363, 183, 77, 21);
		add(btnNewButton_4);
		
		
		//nothing past here
	}
}
