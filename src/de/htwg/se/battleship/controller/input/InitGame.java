package de.htwg.se.battleship.controller.input;


import de.htwg.se.battleship.util.observer.Event;
import de.htwg.se.battleship.util.observer.Events.ContinueEvent;
import de.htwg.se.battleship.util.observer.Events.ErrorEvent;
import de.htwg.se.battleship.util.observer.Events.StandardEvent;


public class InitGame implements InputState{

	private final static String opencommand = "-newgame";
	private final Event event;
	private String player1;
	private String player2;
	private int fieldsize;
	private final String[] command;	
	
	protected InitGame(){
		player1 = "Player 1";
		player2 = "Player 2";
		fieldsize = 10;
		command = new String[]{"-p1","-p2","-field",InGame.getCommand()};
		
		event = new StandardEvent("Type:"
				+ "\n"+command[0]+" ... - set name " + player1
				+ "\n"+command[1]+" ... - set name " + player2 
				+ "\n"+command[2]+" ... - set field size(standart: " + fieldsize +")"
				+ "\n"+command[3]+" - starts game");
	}
	

	@Override
	public Event[] processInput(InputController controller, String line) {
		Event[] array=new Event[2];
		String[] in = controller.splitInput(line,command);
		
		if(in[1].equals(command[0])){
			in = controller.splitInput(in[2],command);
			if(in[0].isEmpty()){
				array[0]=new ErrorEvent("couldn't rename Player 1");
			}else{
				array[0] = new StandardEvent(player1+" changed to " + in[0]);
				player1 = in[0];
			}
			if(in[1]!=null&&!in[1].isEmpty()){
				array[1] = new ContinueEvent(in[1]+in[2]); 
			}
		}else if(in[1].equals(command[1])){		
			in = controller.splitInput(in[2],command);
			if(in[0].isEmpty()){
				array[0]=new ErrorEvent("couldn't rename Player 2");
			}else{
				array[0] = new StandardEvent(player2+" changed to " + in[0]);
				player2 = in[0];
			}
			if(in[1]!=null&&!in[1].isEmpty()){
				array[1] = new ContinueEvent(in[1]+in[2]); 
			}
		}else if(in[1].equals(command[2])){
			in = controller.splitInput(in[2],command);
			if(in[0].isEmpty()){
				array[0] = new ErrorEvent("couldn't change fieldsize");
			}else if(in[0].matches("[0-9]+$")){
				fieldsize=Integer.parseInt(in[0]);
				array[0] = new StandardEvent("changed fieldsize to "+fieldsize);
			}else{
				array[0] = new ErrorEvent(in[0]+"is no number");
			}
			if(in[1]!=null&&!in[1].isEmpty()){
				array[1] = new ContinueEvent(in[1]+in[2]); 
			}
		}else if(in[1].equals(command[3])){
			array[0] = controller.setState(new InGame(fieldsize, player1, player2));
			if(!in[2].isEmpty()){
				array[1] = new ContinueEvent(in[2]);
			}
		}else{
			array[0] =  new ErrorEvent("input didnt match: "+line);
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
