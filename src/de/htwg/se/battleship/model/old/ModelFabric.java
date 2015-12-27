/**
 *
 */
package de.htwg.se.battleship.model.old;

import java.util.Map;

/**
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 */
@Deprecated
public interface ModelFabric {

   /**
    * Create an instance of an OLDPlayer implementation.
    *
    * @param name String
    * @return OLDPlayer
    */
   OLDPlayer createPlayer(String name);

   /**
    * Create an instance of an OLDGrid implementation.
    *
    * @param player OLDPlayer
    * @return OLDGrid
    */
   OLDGrid createGrid(OLDPlayer player);


   /**
    * Create an instance of an OLDShip implementation.
    *
    * @param player OLDPlayer
    * @param cells  List of cells of the ship.
    * @return OLDShip
    */
   OLDShip createShip(OLDPlayer player, Map<String, OLDCell> cells);

   /**
    * Create an instance of an OLDRound implementation.
    *
    * @param g1 OLDGrid
    * @param g2 OLDGrid
    * @return OLDRound
    */
   OLDRound createRound(OLDGrid g1, OLDGrid g2);

}
