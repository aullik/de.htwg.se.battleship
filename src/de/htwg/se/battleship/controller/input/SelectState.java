package de.htwg.se.battleship.controller.input;

import de.htwg.se.battleship.util.observer.Event;
import de.htwg.se.battleship.util.observer.Events.ContinueEvent;
import de.htwg.se.battleship.util.observer.Events.ErrorEvent;
import de.htwg.se.battleship.util.observer.Events.StandardEvent;

public class SelectState implements InputState{
	
	private final static String opencommand = "selectstate";
	private final Event event;
	private final String[] command;

	protected SelectState(){
		command = new String[]{InitGame.getCommand(),Close.getCommand()};
		event =new StandardEvent("Welcome to Batteleship"
				+ "\nType: "
				+ "\n-close - terminates the instance"
				+"\n-newgame - starts new game");
	}


	@Override
	public Event[] processInput(InputController controller, String line) {
		Event[] array = new Event[2];
		String[] in = controller.splitInput(line,command);
		
		if(in[1].equals(command[0])){
			array[0] =  controller.setState(new InitGame());	
			if(!in[2].isEmpty()){
				array[1] = new ContinueEvent(in[2]);
			}
		}else if(in[1].equals(command[1])){
			array[0] =  controller.setState(new Close()); // ersetze durch manuellen befehl
		} else{
			array[0] = new ErrorEvent("input didnt match: "+line);
		}
		return array;
	}
	protected static String getCommand() {
		return opencommand;
	}
	@Override
	public Event getEvent() {
		return event;
	}

}
