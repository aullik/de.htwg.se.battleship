package de.htwg.se.battleship.model.impl;

import de.htwg.se.battleship.model.ModelFactory;
import de.htwg.se.battleship.model.read.RCell;
import de.htwg.se.battleship.model.read.RGrid;
import de.htwg.se.battleship.model.read.RShip;
import de.htwg.se.battleship.model.readwrite.RWCell;
import de.htwg.se.battleship.model.readwrite.RWGrid;
import de.htwg.se.battleship.model.readwrite.RWPlayer;
import de.htwg.se.battleship.model.readwrite.RWShip;

import java.util.ArrayList;
import java.util.List;

/**
 * @author aullik on 07.12.2015.
 */
public class Player implements RWPlayer {

   private final String name;
   private final List<RWShip> ships;
   private final RWGrid grid;


   /**
    * Create a new Player instance with name and Map for Cell instances
    *
    * @param name Player name
    */
   public Player(final String name) {
      if (name == null || name.isEmpty())
         throw new IllegalArgumentException("Player must have a name");
      this.name = name;
      this.ships = new ArrayList<>();
      this.grid = ModelFactory.createGrid();
   }


   @Override
   public RWShip addShip(final RShip ship) {
      List<RWCell> cells = new ArrayList<>(ship.getNumberOfCells());
      ship.getCells().forEach(c -> cells.add(grid.getCell(c)));
      for (RCell c : cells) {
         if (c.getShip() != null)
            return null;
      }

      final RWShip ret = ModelFactory.createShip(cells);
      ships.add(ret);
      cells.forEach(c -> c.setShip(ret)); // ship does the Same. This is just to be sure.
      return ret;
   }


   @Override
   public String getName() {
      return name;
   }

   // SuspiciousMethodCalls as RShip is parent of RWShip
   @SuppressWarnings ("SuspiciousMethodCalls")
   @Override
   public boolean containsShip(final RShip ship) {
      return ships.contains(ship);
   }


   @Override
   public RGrid getGrid() {
      return grid;
   }

   @Override
   public List<RWShip> getShips() {
      return ships;
   }
}
