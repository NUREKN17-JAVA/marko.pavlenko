package com.markopavlenko.usermanagement.web;

import java.util.Calendar;
import java.util.Properties;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mockobjects.dynamic.Mock;
import com.mockrunner.servlet.BasicServletTestCaseAdapter;

import com.markopavlenko.usermanagement.db.DaoFactory;
import com.markopavlenko.usermanagement.db.MockDaoFactory;

public class MockServletTestCase extends BasicServletTestCaseAdapter {
	
	private Mock mockUserDao; 
	
	@Override
    protected void setUp() throws Exception {
        super.setUp();
        Properties properties = new Properties();
        properties.setProperty("dao.factory", MockDaoFactory.class.getName());
        DaoFactory.init(properties);
        setMockUserDao(((MockDaoFactory) DaoFactory.getInstance()).getMockUserDao());
    }

    @Override
    protected void tearDown() throws Exception {
        getMockUserDao().verify();
        super.tearDown();
    }

	public Mock getMockUserDao() {
		return mockUserDao;
	}

	public void setMockUserDao(Mock mockUserDao) {
		this.mockUserDao = mockUserDao;
	}

	
	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
