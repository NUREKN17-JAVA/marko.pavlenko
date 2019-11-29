package com.markopavlenko.usermanagement.gui;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.markopavlenko.usermanagement.User;

import java.awt.Component;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import junit.extensions.jfcunit.JFCTestCase;
import junit.extensions.jfcunit.JFCTestHelper;
import junit.extensions.jfcunit.eventdata.MouseEventData;
import junit.extensions.jfcunit.finder.NamedComponentFinder;

public class MainFrameTest extends JFCTestCase {

	private MainFrame mainFrame;

	@Before
	protected void setUp() throws Exception {
		super.setUp();
		setHelper(new JFCTestHelper());
		mainFrame = new MainFrame();
		mainFrame.setVisible(true);
			
		
	}

	@After
	protected void tearDown() throws Exception {
		mainFrame.setVisible(false);
		getHelper().cleanUp(this);
		super.tearDown();
	}

	  private Component find(Class componentClass, String name) {
	        NamedComponentFinder finder;
	        finder = new NamedComponentFinder(componentClass, name);
	        finder.setWait(0);
	        Component component = finder.find(mainFrame, 0);
	        assertNotNull("Could not find component '" + name + "'", component);
	        return component;
	    }
	
	  public void testBrowseControls() {
	        find(JPanel.class, "browsePanel");
	        JTable table = (JTable) find(JTable.class, "userTable");
	        assertEquals(3, table.getColumnCount());
	        assertEquals("ID", table.getColumnName(0));
	        assertEquals("Имя", table.getColumnName(1));
	        assertEquals("Фамилия", table.getColumnName(2));
	        find(JButton.class, "addButton");
	        find(JButton.class, "editButton");
	        find(JButton.class, "deleteButton");
	        find(JButton.class, "detailsButton");      
	    }
	  
	  public void testAddUser() {
	        try {
	            String firstName = "George";
	            String lastName = "Bush";

	            User user = new User("George", "Bush", DATE);

	            User expectedUser = new User(new Long(1), "George", "Bush", DATE);
	            mockUserDao.expectAndReturn("create", user, expectedUser);
	            System.out.println("1a" + expectedUser + user);

	            
	            ArrayList<User> users = new ArrayList<User>(this.users);
	            users.add(expectedUser);
	            mockUserDao.expectAndReturn("findAll", users);
	            
	            JTable table = (JTable) find(JTable.class, "userTable");
	            assertEquals(1, table.getRowCount());

	            JButton addButton = (JButton) find(JButton.class, "addButton");
	            getHelper().enterClickAndLeave(new MouseEventData(this, addButton));

	            find(JPanel.class, "addPanel");

	            fillField(firstName, lastName, DATE);

	            JButton okButton = (JButton) find(JButton.class, "okButton");
	            getHelper().enterClickAndLeave(new MouseEventData(this, okButton));

	            find(JPanel.class, "browsePanel");
	            table = (JTable) find(JTable.class, "userTable");
	            assertEquals(2, table.getRowCount());
	        } catch (Exception e) {
	            fail(e.toString());
	        }
	    }
	
}
