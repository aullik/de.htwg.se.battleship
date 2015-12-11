package de.htwg.se.battleship.model.readwrite;

import de.htwg.se.battleship.model.read.RShip;

import java.util.List;

/**
 * The RWShip interface contains all public methods for a Ship implementation.
 *
 * @author aullik on 07.12.2015.
 */
public interface RWShip extends RShip {


   /**
    * Returns List of Cell.
    *
    * @return List<Cell>
    */
   List<? extends RWCell> getCells();

}
