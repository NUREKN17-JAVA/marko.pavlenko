package com.markopavlenko.usermanagement.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;


public class AddPanel extends JPanel implements ActionListener {
	
		private static final long serialVersionUID = 2371425014873030612L;
		
		protected MainFrame parent;
		protected JPanel buttonPanel;
		protected JPanel fieldPanel;
		protected JButton cancelButton;
	    protected JButton okButton;

	    public AddPanel(MainFrame parent) {
	        this.parent = parent;
	        initialize();
	    }

	    protected void initialize() {
	        this.setLayout(new BorderLayout());
	        this.add(getFieldPanel(), BorderLayout.NORTH);
	        this.add(getButtonPanel(), BorderLayout.SOUTH);
	        
	    }
		
		 protected JPanel getFieldPanel() {
			 return fieldPanel;
	    }
		
	    protected JPanel getButtonPanel() {
	        if (buttonPanel == null) {
	            buttonPanel = new JPanel();
	            buttonPanel.add(getOkButton(), null);
	            buttonPanel.add(getCancelButton(), null);
	        }
	        return buttonPanel;
	    }

	    protected JButton getCancelButton() {
	        if (cancelButton == null) {
	            cancelButton = new JButton();
	            cancelButton.setText("Cancel"); 
	            cancelButton.setName("cancelButton"); 
	            cancelButton.setActionCommand("cancel"); 
	            cancelButton.addActionListener(this);
	        }
	        return cancelButton;
	    }

	    protected JButton getOkButton() {
	        if (okButton == null) {
	            okButton = new JButton();
	            okButton.setText("OK"); 
	            okButton.setName("okButton");
	            okButton.setActionCommand("ok"); 
	            okButton.addActionListener(this);
	        }
	        return okButton;
	    }

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
		}

}
