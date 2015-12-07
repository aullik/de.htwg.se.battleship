/**
 *
 */
package de.htwg.se.battleship.model.old;

/**
 * The OLDGrid interface contains all public methods for a grid implementation.
 *
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 */
@Deprecated
public interface Grid {

   /**
    * Returns the size of the OLDGrid instance
    *
    * @return Number of cells for width/height
    */
   int getWidth();

   /**
    * Get a single OLDCell instance from the OLDGrid.
    *
    * @param x X-Coordinate
    * @param y Y-Coordinate
    * @return Null/OLDCell instance
    */
   OLDCell getCell(final int x, final int y);

}
