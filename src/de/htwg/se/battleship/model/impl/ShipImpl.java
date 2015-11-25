/**
 *
 */
package de.htwg.se.battleship.model.impl;

import de.htwg.se.battleship.model.Cell;
import de.htwg.se.battleship.model.Player;
import de.htwg.se.battleship.model.Ship;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * This class contains all data of a single Ship from a player
 *
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 */
public class ShipImpl implements Ship {

   public static final int NUMBER_OF_CELLS = 2;

   private final Map<String, Cell> cells;
   private Player player;

   /**
    * Create an instance of Ship and add a Map for Cell instances
    */
   public ShipImpl(final Player player, Map<String, Cell> cells) {
      this.cells = new HashMap<String, Cell>();

      for (Cell cell : cells.values()) {
         addCell(cell);
      }

      setPlayer(player);
   }

   private void setPlayer(final Player player) {
      this.player = player;
      player.addShip(this);
   }

   private void addCell(final Cell cell) {
      if (!cells.containsKey(cell.getKey())) {
         cells.put(cell.getKey(), cell);
         cell.setShip(this);
      }
   }

   @Override
   public Player getPlayer() {
      return player;
   }

   @Override
   public Cell getCell(final int x, final int y) {
      String key = CellImpl.createKey(x, y);
      Cell cell = null;

      if (cells.containsKey(key)) {
         cell = cells.get(key);
      }

      return cell;
   }

   @Override
   public int getNumberOfCells() {
      return cells.size();
   }

   @Override
   public List<Cell> getCells() {
      List<Cell> list = new LinkedList<Cell>();
      for (Cell cell : cells.values()) {
         list.add(cell);
      }
      return list;
   }
}
