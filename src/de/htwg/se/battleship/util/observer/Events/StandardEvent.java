package de.htwg.se.battleship.util.observer.Events;

import de.htwg.se.battleship.util.observer.Event;

public class StandardEvent implements Event {
	
	private final String message;

	public StandardEvent(String msg) {
		this.message = msg;

	}
	
	@Override
	public String getMessage() {
		return message;
	}

}
