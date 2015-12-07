package de.htwg.se.battleship.model.read;

/**
 * The {@code RGrid} interface contains all public read methods for a grid implementation.
 *
 * @author aullik on 07.12.2015.
 */

public interface RGrid {

   /**
    * Returns the size of the Grid instance
    *
    * @return Number of cells for width/height
    */
   int getWidth();

   /**
    * Get a single {@link de.htwg.se.battleship.model.read.RCell} instance from the Grid.
    *
    * @param x X-Coordinate
    * @param y Y-Coordinate
    * @return Null/Cell instance
    */
   RCell getCell(final int x, final int y);

}