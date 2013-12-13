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
	public Event[] processInput(InputController input, String line) {
		Event[] array = new Event[2];
		String[] in = input.splitInput(line,command);
		if(in[1].equals(command[0])){
			array[0] =  input.setState(new InitGame());	
			if(!in[2].isEmpty()){
				array[1] = new ContinueEvent(in[2]);
			}
		}else if(in[1].equals(command[1])){
			array[0] =  input.setState(new Close()); // ersetze durch manuellen befehl
		} else{
			array[0] = new ErrorEvent("input didnt match: "+line);
		}
		
		
		if(line.startsWith(InitGame.getCommand())){
			array[0] =  input.setState(new InitGame());
			String tmp =line.replace(InitGame.getCommand(),"");
			if(!tmp.isEmpty()){
				array[1] = new ContinueEvent(tmp);
			}
		} else if(line.startsWith(Close.getCommand())){
			array[0] =  input.setState(new Close());
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
