package de.htwg.se.battleship.model.readwrite;

import de.htwg.se.battleship.model.read.REnemyCell;
import de.htwg.se.battleship.model.read.RGrid;

/**
 * The {@code RWGrid} interface contains all public methods for a grid implementation.
 *
 * @author aullik on 07.12.2015.
 */
public interface RWGrid extends RGrid {


   @Override
   RWCell getCell(final int x, final int y);


   @Override
   RWCell getCell(REnemyCell cell);
}
