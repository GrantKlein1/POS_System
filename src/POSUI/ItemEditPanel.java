package POSUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import POSPD.*;
import javax.swing.JList;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;

public class ItemEditPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_1;
	DefaultComboBoxModel<TaxCategory> taxCatList;
	JComboBox<TaxCategory> comboBox;
	DefaultListModel<UPC> upcListModel;
	DefaultListModel<Price> priceListModel;	//should be either taxCat or taxRate, whatever the bottom list box 
	JList<UPC> upcList;
	JList<Price> priceList;
	JButton btnNewButton_2;
	JButton btnNewButton_3;
	JButton btnNewButton_4;
	JButton btnNewButton_5;
	JButton btnNewButton_6;
	JButton btnNewButton_7;
	
	/**
	 * Create the panel.
	 */
	public ItemEditPanel(JFrame currentFrame, Store store, Item item, Boolean isAdd) {
		JPanel currentPanel = this;
		setLayout(null);
		JLabel lblNewLabel = new JLabel("Edit Item");
		lblNewLabel.setBounds(201, 10, 68, 13);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Item Number:");
		lblNewLabel_1.setBounds(10, 41, 89, 13);
		add(lblNewLabel_1);
		
		textField = new JTextField(item.getNumber());
		textField.setBounds(94, 38, 96, 19);
		add(textField);
		textField.setColumns(10);
		
		taxCatList = new DefaultComboBoxModel<TaxCategory>();
		for (TaxCategory taxCat : store.getTaxCategories().values()) {
			taxCatList.addElement(taxCat);
		}
		
		comboBox = new JComboBox<TaxCategory>(taxCatList);
		if (!isAdd && item.getTaxCategory().getCategory() == "Food") {comboBox.setSelectedIndex(1);}
		comboBox.setBounds(94, 94, 96, 21);
		add(comboBox);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.setEnabled(false);
		if (item.getPrices() == null || item.getUpcs() == null) {
			btnNewButton.setEnabled(false);
		}
		else {
			btnNewButton.setEnabled(true);
		}
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				item.setNumber(textField.getText());
				item.setDescription(textField_1.getText());
				item.setTaxCategory(comboBox.getItemAt(comboBox.getSelectedIndex()));
				store.addItem(item);
				
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new ItemListPanel(currentFrame, store));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnNewButton.setBounds(31, 248, 76, 21);
		add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new ItemListPanel(currentFrame, store));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnNewButton_1.setBounds(142, 248, 80, 21);
		add(btnNewButton_1);
		
		JLabel lblNewLabel_2 = new JLabel("Description:");
		lblNewLabel_2.setBounds(10, 68, 89, 13);
		add(lblNewLabel_2);
		
		textField_1 = new JTextField(item.getDescription());
		textField_1.setBounds(94, 65, 96, 19);
		add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Tax Category:");
		lblNewLabel_3.setBounds(10, 91, 76, 13);
		add(lblNewLabel_3);
		
		upcListModel = new DefaultListModel<UPC>();
		for (UPC upc : item.getUpcs().values()) {
			upcListModel.addElement(upc);
		}
		
		upcList = new JList<UPC>(upcListModel);
		upcList.addAncestorListener(new AncestorListener() {
			public void ancestorAdded(AncestorEvent event) {
				upcListModel = new DefaultListModel<UPC>();
				for (UPC upc : item.getUpcs().values()) {
					upcListModel.addElement(upc);
				}
				upcList.setModel(upcListModel);
			}
			public void ancestorMoved(AncestorEvent event) {
			}
			public void ancestorRemoved(AncestorEvent event) {
			}
		});
		upcList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if(upcList.getSelectedValue() != null) {
					btnNewButton_2.setEnabled(true);
					btnNewButton_3.setEnabled(true);
				}
				else {
					btnNewButton_2.setEnabled(false);
					btnNewButton_3.setEnabled(false);
				}
				if(item.getUpcs().size() > 1 && upcList.getSelectedValue() != null) {
					btnNewButton_4.setEnabled(true);
				}
				else {
					btnNewButton_4.setEnabled(false);
				}
				
			}
			
		});
		upcList.setBounds(272, 40, 168, 66);
		add(upcList);
		
		btnNewButton_2 = new JButton("Edit");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new UpcEditPanel(currentFrame, store, item, upcList.getSelectedValue(), false, currentPanel));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnNewButton_2.setBounds(294, 116, 68, 21);
		btnNewButton_2.setEnabled(false);
		add(btnNewButton_2);
		
		btnNewButton_3 = new JButton("Add");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new UpcEditPanel(currentFrame, store, item, new UPC(), true, currentPanel));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnNewButton_3.setBounds(372, 116, 68, 21);
		add(btnNewButton_3);
		
		btnNewButton_4 = new JButton("Delete");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				store.removeUPC(upcList.getSelectedValue());
				item.removeUPC(upcList.getSelectedValue());
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new ItemEditPanel(currentFrame, store, item, false));
				currentFrame.getContentPane().revalidate();
			}
		});
		
		btnNewButton_4.setBounds(334, 140, 76, 21);
		btnNewButton_4.setEnabled(false);
		add(btnNewButton_4);
		
		priceListModel = new DefaultListModel<Price>();
		for (Price price : item.getPrices()) {
			priceListModel.addElement(price);
		}
		priceList = new JList<Price>(priceListModel);
		priceList.addAncestorListener(new AncestorListener() {
			public void ancestorAdded(AncestorEvent event) {
				priceListModel = new DefaultListModel<Price>();
				for (Price price : item.getPrices()) {
					priceListModel.addElement(price);
				}
				priceList.setModel(priceListModel);
			}
			public void ancestorMoved(AncestorEvent event) {
			}
			public void ancestorRemoved(AncestorEvent event) {
			}
		});
		priceList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if(priceList.getSelectedValue() != null) {
					btnNewButton_5.setEnabled(true);
					btnNewButton_6.setEnabled(true);
				}
				else {
					btnNewButton_5.setEnabled(false);
					btnNewButton_6.setEnabled(false);
				}
				if(priceList.getSelectedValue() != null && priceList.getSelectedValue().getEffectiveDate().isAfter(LocalDate.now())) {
					btnNewButton_7.setEnabled(true);
				}
				else {
					btnNewButton_7.setEnabled(false);
				}
				
			}
			
		});
		priceList.setBounds(272, 171, 168, 66);
		add(priceList);

		btnNewButton_5 = new JButton("Edit");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				if (priceList.getSelectedValue().getClass() == PromoPrice.class) {
					PromoPrice p = (PromoPrice) priceList.getSelectedValue();
					currentFrame.getContentPane().add(new PriceEditPanel(currentFrame, store, item, p , false, currentPanel, true, p));
				}
				else {
					currentFrame.getContentPane().add(new PriceEditPanel(currentFrame, store, item, priceList.getSelectedValue() , false, currentPanel, false, new PromoPrice()));
				}
				currentFrame.getContentPane().revalidate();
			}
		});
		btnNewButton_5.setBounds(294, 248, 68, 21);
		btnNewButton_5.setEnabled(false);
		add(btnNewButton_5);
		
		btnNewButton_6 = new JButton("Add");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new PriceEditPanel(currentFrame, store, item, new Price(), true, currentPanel, false, new PromoPrice()));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnNewButton_6.setBounds(372, 248, 68, 21);
		add(btnNewButton_6);
		
		btnNewButton_7 = new JButton("Delete");
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				item.removePrice(priceList.getSelectedValue());
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new ItemEditPanel(currentFrame, store, item, false));
				currentFrame.getContentPane().revalidate();
			}
			
		});
		btnNewButton_7.setBounds(334, 273, 76, 21);
		btnNewButton_7.setEnabled(false);
		add(btnNewButton_7);
		
		
		
	}
}
