package com.markopavlenko.usermanagement.gui;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junit.extensions.jfcunit.JFCTestCase;
import junit.extensions.jfcunit.JFCTestHelper;

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

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
