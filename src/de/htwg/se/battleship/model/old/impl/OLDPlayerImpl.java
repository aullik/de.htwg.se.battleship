/**
 *
 */
package de.htwg.se.battleship.model.old.impl;

import de.htwg.se.battleship.model.old.OLDGrid;
import de.htwg.se.battleship.model.old.OLDPlayer;
import de.htwg.se.battleship.model.old.OLDShip;

import java.util.ArrayList;
import java.util.List;

/**
 * This class contains all data for an OLDPlayer
 *
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 */
@Deprecated
public class OLDPlayerImpl implements OLDPlayer {

   private final String name;
   private final List<OLDShip> ships;
   private OLDGrid grid;

   /**
    * Create a new OLDPlayer instance with name and Map for OLDCell instances
    *
    * @param name OLDPlayer name
    */
   public OLDPlayerImpl(final String name) {
      this.name = name;
      this.ships = new ArrayList<>();
   }

   @Override
   public String getName() {
      return name;
   }

   @Override
   public void addShip(final OLDShip ship) {
      if (!containsShip(ship)) {
         ships.add(ship);
      }
   }

   @Override
   public boolean containsShip(final OLDShip ship) {
      return ships.contains(ship);
   }

   @Override
   public void setGrid(final OLDGrid grid) {
      this.grid = grid;
   }

   @Override
   public OLDGrid getGrid() {
      return grid;
   }

   @Override
   public int getNumberOfShipCells() {
      int sum = 0;

      for (OLDShip ship : ships) {
         sum += ship.getNumberOfCells();
      }
      return sum;
   }

   @Override
   public boolean isHuman() {
      return true;
   }

   @Override
   public List<OLDShip> getShips() {
      return ships;
   }

}
