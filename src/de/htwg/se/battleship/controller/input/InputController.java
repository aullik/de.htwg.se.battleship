package de.htwg.se.battleship.controller.input;


import de.htwg.se.battleship.controller.IntController;
import de.htwg.se.battleship.util.observer.Event;
import de.htwg.se.battleship.util.observer.Observable;
import de.htwg.se.battleship.util.observer.Events.CloseEvent;
public class InputController  extends Observable implements IntController {

	
	private InputState currentState; 
	
	public InputController() {
		currentState = new SelectState();
	}
	protected Event setState(InputState i){
		currentState = i;
		return currentState.getEvent();
	}
		
	@Override
	public boolean processInputLine(String line){
		Event e;
		String words[] = line.split(" ");
		for (String word :words){
			e=currentState.processInput(this, word);
			notifyObservers(e);
			if(e!=null&& e instanceof CloseEvent){
				return false;
			}
		}
		return true;
	}
	
	
	@Override
	public void updateNotify() {
		notifyObservers(currentState.getEvent());
		
	}
	


	
}
