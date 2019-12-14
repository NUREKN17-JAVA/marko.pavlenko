package com.markopavlenko.usermanagement.agent;

import java.util.Collection;

import jade.core.Agent;
import com.markopavlenko.usermanagement.User;
import com.markopavlenko.usermanagement.agent.exception.SearchException;
import com.markopavlenko.usermanagement.db.DaoFactory;
import com.markopavlenko.usermanagement.db.DatabaseException;

public class SearchAgent extends Agent {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void setup() {
        super.setup();
        System.out.println("Agent " + getAID().getName() + " is ready! Awaiting orders!");
    }

    @Override
    protected void takeDown() {
        super.takeDown();
        System.out.println("Agent " + getAID().getName() + " has finished working!");
    }
    
    public void search(String firstName, String lastName) throws SearchException {
        try {
            Collection<User> users = DaoFactory.getInstance().getUserDao().find(firstName, lastName);
            if (users.size() > 0) {
                showUsers(users);
            } else {
               
            }
        } catch (DatabaseException e) {
            throw new SearchException(e);
        }
    }

    public void showUsers(Collection<User> users) {
        
    }
}
