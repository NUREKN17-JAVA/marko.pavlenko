package com.markopavlenko.usermanagement.db;

import java.io.IOException;
import java.util.Properties;

public class DaoFactory {
			
	    private static final String USER_DAO = null;
		private final Properties properties;

	    private DaoFactory() {
	        properties = new Properties();
	        try {
	            properties.load(getClass().getClassLoader().getResourceAsStream("settings.properties"));
	        } catch (IOException e) {
	            throw new RuntimeException(e);
	        }
	    }
	    
	    public UserDao getUserDao() {
	        try {
	            Class daoClass = Class.forName(properties.getProperty(USER_DAO));
	            UserDao userDao = (UserDao) daoClass.newInstance();
	            userDao.setConnectionFactory(getConnectionFactory());
	            return userDao;
	        } catch (Exception e) {
	            throw new RuntimeException(e);
	        }
	    }
	    
	    private ConnectionFactory getConnectionFactory() {
	        String driver = properties.getProperty("connection.driver");
	        String url = properties.getProperty("connection.url");
	        String user = properties.getProperty("connection.user");
	        String password = properties.getProperty("connection.password");
	        return new ConnectionFactoryImpl(driver, url, user, password);
	    }
	}
