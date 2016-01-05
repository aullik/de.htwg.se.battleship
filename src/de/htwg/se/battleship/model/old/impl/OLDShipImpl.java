/**
 *
 */
package de.htwg.se.battleship.model.old.impl;

import de.htwg.se.battleship.model.old.OLDCell;
import de.htwg.se.battleship.model.old.OLDPlayer;
import de.htwg.se.battleship.model.old.OLDShip;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * This class contains all data of a single OLDShip from a player
 *
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 */
@Deprecated
public class OLDShipImpl implements OLDShip {

   public static final int NUMBER_OF_CELLS = 2;

   private final Map<String, OLDCell> cells;
   private OLDPlayer player;

   /**
    * Create an instance of OLDShip and add a Map for OLDCell instances
    */
   public OLDShipImpl(final OLDPlayer player, Map<String, OLDCell> cells) {
      this.cells = new HashMap<String, OLDCell>();

      for (OLDCell cell : cells.values()) {
         addCell(cell);
      }

      setPlayer(player);
   }

   private void setPlayer(final OLDPlayer player) {
      this.player = player;
      player.addShip(this);
   }

   private void addCell(final OLDCell cell) {
      if (!cells.containsKey(cell.getKey())) {
         cells.put(cell.getKey(), cell);
         cell.setShip(this);
      }
   }

   @Override
   public OLDPlayer getPlayer() {
      return player;
   }


   @Override
   public int getNumberOfCells() {
      return cells.size();
   }

   @Override
   public List<OLDCell> getCells() {
      List<OLDCell> list = new LinkedList<OLDCell>();
      for (OLDCell cell : cells.values()) {
         list.add(cell);
      }
      return list;
   }
}