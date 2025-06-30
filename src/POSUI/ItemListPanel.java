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

import POSPD.*;

public class ItemListPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	DefaultListModel<Item> listModel;
	/**
	 * Create the panel.
	 */
	JButton btnNewButton_1;
	JButton btnNewButton_2;
	Item holder = new Item("", "");
	public ItemListPanel(JFrame currentFrame, Store store) {
		JPanel currentPanel = this;
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Item List");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(178, 38, 85, 13);
		add(lblNewLabel);
		
		listModel = new DefaultListModel<Item>();
		for (Item item : store.getItems().values()) {
			listModel.addElement(item);
		}
		
		JList<Item> list = new JList<Item>(listModel);
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if(list.getSelectedValue() != null) {
					btnNewButton_1.setEnabled(true);
					btnNewButton_2.setEnabled(true);
				}
				else {
					btnNewButton_1.setEnabled(false);
					btnNewButton_2.setEnabled(false);
				}
				
			}
			
		});
		list.setBounds(129, 61, 189, 133);
		add(list);
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new ItemEditPanel(currentFrame, store, new Item(), true));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnNewButton.setBounds(51, 236, 85, 21);
		add(btnNewButton);
		
		btnNewButton_1 = new JButton("Edit");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new ItemEditPanel(currentFrame, store, list.getSelectedValue(), false));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnNewButton_1.setBounds(178, 236, 85, 21);
		btnNewButton_1.setEnabled(false);
		add(btnNewButton_1);
		
		btnNewButton_2 = new JButton("Delete");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				store.removeItem(list.getSelectedValue());
				listModel.removeElement(list.getSelectedValue());
			}
		});
		btnNewButton_2.setBounds(310, 236, 85, 21);
		btnNewButton_2.setEnabled(false);
		add(btnNewButton_2);

		
		
	}

}
