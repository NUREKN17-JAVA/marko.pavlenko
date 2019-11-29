package com.markopavlenko.usermanagement.gui;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.awt.Component;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import junit.extensions.jfcunit.JFCTestCase;
import junit.extensions.jfcunit.JFCTestHelper;
import junit.extensions.jfcunit.TestHelper;
import junit.extensions.jfcunit.eventdata.JTableMouseEventData;
import junit.extensions.jfcunit.eventdata.MouseEventData;
import junit.extensions.jfcunit.eventdata.StringEventData;
import junit.extensions.jfcunit.finder.DialogFinder;
import junit.extensions.jfcunit.finder.NamedComponentFinder;

import com.mockobjects.dynamic.Mock;

import com.markopavlenko.usermanagement.User;
import com.markopavlenko.usermanagement.db.DaoFactory;
/**import com.markopavlenko.usermanagement.db.MockDaoFactory; **/
import com.markopavlenko.usermanagement.util.Messages;

public class MainFrameTest extends JFCTestCase {

	private MainFrame mainFrame;

	 protected void setUp() throws Exception {
	        try {
	            super.setUp();
	            Properties properties = new Properties();
	            properties.setProperty("dao.factory", MockDaoFactory.class.getName());
	            DaoFactory.init(properties );
	            mockUserDao = ((MockDaoFactory) DaoFactory.getInstance()).getMockUserDao();
	            User expectedUser = new User(new Long(1001L), "George", "Bush", DATE);
	            users = new ArrayList<User>();
	            users.add(expectedUser);
	            mockUserDao.expectAndReturn("findAll",users);
	            setHelper(new JFCTestHelper());
	            mainFrame = new MainFrame();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
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
