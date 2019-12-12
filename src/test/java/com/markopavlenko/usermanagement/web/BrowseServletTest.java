package com.markopavlenko.usermanagement.web;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.mockrunner.mock.web.MockServletInputStream;

import  com.markopavlenko.usermanagement.User;
import  com.markopavlenko.usermanagement.web.BrowseServlet;

public class BrowseServletTest extends MockServletInputStream {

	public BrowseServletTest(byte[] arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public void setUp() throws Exception {
        super.setUp();
        createServlet(BrowseServlet.class);
    }

    public void testBrowse() {
        User user = new User(new Long(1000), "John", "Doe", new Date());
        List list = Collections.singletonList(user);
        getMockUserDao().expectAndReturn("findAll", list);
        doGet();
        Collection collection = (Collection) getWebMockObjectFactory().getMockSession().getAttribute("users");
        assertNotNull("Could not find list of users in session", collection);
        assertSame(list, collection);
    }

    public void testEdit() {
        User expectedUser = new User(1000L, "John", "Doe", new Date());
        getMockUserDao().expectAndReturn("find", 1000L, expectedUser);
        addRequestParameter("edit", "Edit");
        addRequestParameter("id", "1000");
        doPost();
        User returnedUser = (User) getWebMockObjectFactory().getMockSession().getAttribute("user");
        assertNotNull(returnedUser);
        assertSame(expectedUser, returnedUser);
    }

}
