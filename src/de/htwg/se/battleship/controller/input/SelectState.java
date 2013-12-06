package de.htwg.se.battleship.controller.input;

import de.htwg.se.battleship.util.observer.Event;
import de.htwg.se.battleship.util.observer.Events.StandardEvent;

public class SelectState implements InputState{
	
	private final static String command = "selectstate";
	private final Event event;

	public SelectState(){
		event =new StandardEvent("Welcome to Batteleship\n Type: close-terminates the instance");
	}


	@Override
	public Event processInput(InputController input, String word) { 
		if(word.equals(InitGame.getCommand())){
			return input.setState(new InitGame());	
		} else if(word.equals(Close.getCommand())){
			return input.setState(new Close());
		} else{
			return null;//new ErrorEvent();
		}
	}
	protected static String getCommand() {
		return command;
	}
	@Override
	public Event getEvent() {
		return event;
	}

}
