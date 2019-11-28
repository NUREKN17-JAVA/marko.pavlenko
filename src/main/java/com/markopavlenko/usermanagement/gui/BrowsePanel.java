package com.markopavlenko.usermanagement.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;




public class BrowsePanel extends JPanel implements ActionListener {

    /**
	 * 
	 */
	private static final long serialVersionUID = 5955495123527812587L;

	private MainFrame parent;

    private JPanel buttonPanel;

    private JButton addButton;

    private JButton detailsButton;

    private JButton deleteButton;

    private JButton editButton;

    private JScrollPane tablePanel;

    private JTable userTable;

    public BrowsePanel(MainFrame frame) {
        parent = frame;
        initialize();
    }

    private void initialize() {
        this.setName("browsePanel"); 
        this.setLayout(new BorderLayout());
        this.add(getTablePanel(), BorderLayout.CENTER);
        this.add(getButtonsPanel(), BorderLayout.SOUTH);
    }

    private JPanel getButtonsPanel() {
        if (buttonPanel == null) {
            buttonPanel = new JPanel();
            buttonPanel.add(getAddButton(), null);
            buttonPanel.add(getEditButton(), null);
            buttonPanel.add(getDeleteButton(), null);
            buttonPanel.add(getDetailsButton(), null);
        }
        return buttonPanel;
    }

    private JButton getDetailsButton() {
        if (detailsButton == null) {
            detailsButton = new JButton();
            detailsButton.setText("Детали");
            detailsButton.setName("detailsButton"); 
            detailsButton.setActionCommand("details");
            detailsButton.addActionListener(this);
        }
        return detailsButton;
    }

    private JButton getDeleteButton() {
        if (deleteButton == null) {
            deleteButton = new JButton();
            deleteButton.setText("Удалить"); 
            deleteButton.setName("deleteButton"); 
            deleteButton.setActionCommand("delete"); 
            deleteButton.addActionListener(this);
        }
        return deleteButton;
    }

    private JButton getEditButton() {
        if (editButton == null) {
            editButton = new JButton();
            editButton.setText("Редактировать"); 
            editButton.setName("editButton");
            editButton.setActionCommand("edit"); 
            editButton.addActionListener(this);
        }
        return editButton;
    }

    private JButton getAddButton() {
        if (addButton == null) {
            addButton = new JButton();
            addButton.setText("Добавить"); //$NON-NLS-1$
            addButton.setName("addButton"); //$NON-NLS-1$
            addButton.setActionCommand("add"); //$NON-NLS-1$
            addButton.addActionListener(this);
        }
        return addButton;
    }

    private JScrollPane getTablePanel() {
        if (tablePanel == null) {
            tablePanel = new JScrollPane(getUserTable());
        }
        return tablePanel;
    }

    private JTable getUserTable() {
        if (userTable == null) {
            userTable = new JTable();
            userTable.setName("userTable"); 
        }
        return userTable;
    }

    public void initTable() {
        
    }

    public void actionPerformed(ActionEvent e) {
    	 String actionCommand = e.getActionCommand();
         if ("add".equalsIgnoreCase(actionCommand)) { //$NON-NLS-1$
             this.setVisible(false);
             parent.showAddPanel();
         }

    }

}
