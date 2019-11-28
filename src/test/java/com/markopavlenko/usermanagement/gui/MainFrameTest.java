package com.markopavlenko.usermanagement.gui;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.awt.Component;

import javax.swing.JPanel;

import junit.extensions.jfcunit.JFCTestCase;
import junit.extensions.jfcunit.JFCTestHelper;
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
		   find(JPanel.class, "UserTable");
		   find(JPanel.class, "addButton");
		   find(JPanel.class, "editButton");
		   find(JPanel.class, "deleteButton");
		   find(JPanel.class, "detailsButton");
	  }
	  
	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
