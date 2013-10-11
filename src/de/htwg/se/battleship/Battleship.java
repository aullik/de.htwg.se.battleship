package de.htwg.se.battleship;

public class Battleship {

	private static Battleship instance = null;

	public static Battleship getInstance() {
		if (instance == null) instance = new Battleship();
		return instance;
	}

	public Battleship() {
	}
	
	public static void main(String[] args) {
		Battleship.getInstance();
	}

}
