package com.markopavlenko.usermanagement.agent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.StringTokenizer;

import jade.core.Agent;
import jade.lang.acl.ACLMessage;

import com.markopavlenko.usermanagement.User;
import com.markopavlenko.usermanagement.agent.exception.SearchException;
import com.markopavlenko.usermanagement.db.DaoFactory;
import com.markopavlenko.usermanagement.db.DatabaseException;

public class SearchAgent extends Agent {


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
            //    addBehaviour(new SearchRequestBehaviour(firstName, lastName, aids));
            }
        } catch (DatabaseException e) {
            throw new SearchException(e);
        }
    }

    private ACLMessage createReply(ACLMessage message) {
        ACLMessage reply = message.createReply();
        String content = message.getContent();
        StringTokenizer tokenizer = new StringTokenizer(content, ",");
        if (tokenizer.countTokens() == 2) {
            String firstName = tokenizer.nextToken();
            String lastName = tokenizer.nextToken();
            Collection<User> collection = null;
            try {
                collection = DaoFactory.getInstance().getUserDao().find(firstName, lastName);
            } catch (DatabaseException e) {
                e.printStackTrace();
                collection = new ArrayList<>();
            }
            StringBuffer buffer = new StringBuffer();
            for (User user : collection) {
                buffer.append(user.getId()).append(",");
                buffer.append(user.getFirstName()).append(",");
                buffer.append(user.getLastName()).append(";");
            }
            reply.setContent(buffer.toString());
        }
        return reply;
    }

	public void showUsers(Collection<User> users) {
		// TODO Auto-generated method stub
		
	}
}
