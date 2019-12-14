package com.markopavlenko.usermanagement.agent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.StringTokenizer;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;

import com.markopavlenko.usermanagement.agent.behaviour.RequestServer;


import com.markopavlenko.usermanagement.User;
import com.markopavlenko.usermanagement.agent.exception.SearchException;
import com.markopavlenko.usermanagement.db.DaoFactory;
import com.markopavlenko.usermanagement.db.DatabaseException;

public class SearchAgent extends Agent {


    private AID[] aids = new AID[0];

   // private SearchGui gui = null;

    @Override
    protected void setup() {
        super.setup();
 //       gui = new SearchGui(this);
 //       gui.setVisible(true);
        addBehaviour(new TickerBehaviour(this, 60000) {
            @Override
            protected void onTick() {
                DFAgentDescription description = new DFAgentDescription();
                description.setName(getAID());
                ServiceDescription serviceDescription = new ServiceDescription();
                serviceDescription.setName("JADE-Searching");
                serviceDescription.setType("searching");
                description.addServices(serviceDescription);
                try {
                    DFAgentDescription[] descriptions = DFService.search(myAgent, description);
                    AID[] newAids = new AID[descriptions.length];
                    for (int i = 0; i < newAids.length; i++) {
                        newAids[i] = descriptions[i].getName();
                    }
                    aids = newAids;
                } catch (FIPAException e) {
                    e.printStackTrace();
                }
            }
        });
        addBehaviour(new RequestServer());
        DFAgentDescription description = new DFAgentDescription();
        description.setName(getAID());
        ServiceDescription serviceDescription = new ServiceDescription();
        serviceDescription.setName("JADE-Searching");
        serviceDescription.setType("searching");
        description.addServices(serviceDescription);
        try {
            DFService.register(this, description);
        } catch (FIPAException e) {
            e.printStackTrace();
        }
        System.out.println("Agent " + getAID().getName() + " is ready! Awaiting orders!");
    }

    @Override
    protected void takeDown() {
        super.takeDown();
        try {
            DFService.deregister(this);
        } catch (FIPAException e) {
            e.printStackTrace();
        }
   //     gui.setVisible(false);
   //     gui.dispose();
        System.out.println("Agent " + getAID().getName() + " has finished working!");
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
