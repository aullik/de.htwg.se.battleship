package de.htwg.se.battleship.model.readwrite;

import de.htwg.se.battleship.model.read.RGrid;

/**
 * The {@code RWGrid} interface contains all public methods for a grid implementation.
 *
 * @author aullik on 07.12.2015.
 */
public interface RWGrid extends RGrid {


   /**
    * {@see RGrid#getCell}
    *
    * @return returns RWCell instead of RCEll
    */
   @Override
   RWCell getCell(final int x, final int y);
}
