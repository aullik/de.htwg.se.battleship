package de.htwg.se.battleship.model.read;

/**
 *
 * The {@code REnemyGrid} interface contains all public read methods for a grid implementation, as the enemy can see them.
 * @author aullik on 04.01.2016.
 */
public interface REnemyGrid {

   /**
    * Returns the size of the Grid instance
    *
    * @return Number of cells for width/height
    */
   int getSize();

   /**
    * Get a single {@link de.htwg.se.battleship.model.read.REnemyCell} instance from the Grid.
    *
    * @param x X-Coordinate
    * @param y Y-Coordinate
    * @return Null/Cell instance
    */
   REnemyCell getCell(final int x, final int y);


   /**
    * Get a single {@link de.htwg.se.battleship.model.read.REnemyCell} instance from the Grid.
    *
    * @param cell similar cell instance, might or might not be of another grid
    * @return Null/Cell instance
    */
   REnemyCell getCell(RCell cell);
}
