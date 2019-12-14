package com.markopavlenko.usermanagement.agent;

import jade.core.Agent;

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

}
