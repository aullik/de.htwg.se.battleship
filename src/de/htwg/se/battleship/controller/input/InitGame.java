package de.htwg.se.battleship.controller.input;

import de.htwg.se.battleship.util.observer.Event;


public class InitGame implements InputState{

	private final static String command = "selectstate";
	

	@Override
	public Event processInput(InputController input, String word) {
		// TODO Auto-generated method stub
		return null;
	}

	protected static String getCommand() {
		return command;
	}

	@Override
	public Event getEvent() {
		// TODO Auto-generated method stub
		return null;
	}



}
