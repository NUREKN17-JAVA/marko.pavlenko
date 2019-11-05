package com.markopavlenko.usermanagement.db;

import java.io.IOException;
import java.util.Properties;

public class DaoFactory {
			
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
	        UserDao result = null;
	        return result;
	    }
	    
	    private ConnectionFactory getConnectionFactory() {
	        String driver = properties.getProperty("connection.driver");
	        String url = properties.getProperty("connection.url");
	        String user = properties.getProperty("connection.user");
	        String password = properties.getProperty("connection.password");
	        return new ConnectionFactoryImpl(driver, url, user, password);
	    }
	}
