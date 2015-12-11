package de.htwg.se.battleship.model;

import de.htwg.se.battleship.model.impl.Grid;
import de.htwg.se.battleship.model.impl.Player;
import de.htwg.se.battleship.model.impl.Ship;
import de.htwg.se.battleship.model.readwrite.RWCell;
import de.htwg.se.battleship.model.readwrite.RWGrid;
import de.htwg.se.battleship.model.readwrite.RWPlayer;
import de.htwg.se.battleship.model.readwrite.RWShip;

import java.util.List;

/**
 * Factory for Model elements
 *
 * @author niwehrle
 */

public abstract class ModelFactory {

   protected static ModelFactory instance;

   private static ModelFactory getInstance() {
      if (instance == null)
         instance = new DefaultImpl();

      return instance;
   }


   protected abstract RWGrid _createGrid();

   public static RWGrid createGrid() {
      return getInstance()._createGrid();
   }

   protected abstract RWShip _createShip(final List<RWCell> cells);

   public static RWShip createShip(final List<RWCell> cells) {
      return getInstance()._createShip(cells);
   }

   protected abstract RWPlayer _createPlayer(final String name);

   public static RWPlayer createPlayer(final String name) {
      return getInstance()._createPlayer(name);
   }


   public static class DefaultImpl extends ModelFactory {

      @Override
      protected RWGrid _createGrid() {
         return new Grid(Grid.DEFAULT_SIZE);
      }

      @Override
      protected RWShip _createShip(final List<RWCell> cells) {
         return new Ship(cells);
      }

      @Override
      protected RWPlayer _createPlayer(final String name) {
         return new Player(name);
      }
   }

}
