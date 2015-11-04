/**
 *
 */
package de.htwg.se.battleship.model.impl;

import de.htwg.se.battleship.model.Cell;
import de.htwg.se.battleship.model.Grid;
import de.htwg.se.battleship.model.ModelFabric;
import de.htwg.se.battleship.model.Player;
import de.htwg.se.battleship.model.Round;
import de.htwg.se.battleship.model.Ship;
import de.htwg.se.battleship.util.singleton.SingletonSupplier;

import javax.inject.Singleton;
import java.util.Map;

/**
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 */
@Singleton
public class ModelFabricImpl implements ModelFabric {

   private static final SingletonSupplier<ModelFabricImpl> INST_SUPP = new SingletonSupplier<>(ModelFabricImpl::new);

   public static ModelFabricImpl getInstance() {
      return INST_SUPP.get();
   }

   private ModelFabricImpl() {
   }


   @Override
   public Player createPlayer(String name) {
      return new PlayerImpl(name);
   }

   @Override
   public Grid createGrid(Player player) {
      return new GridImpl(GridImpl.DEFAULT_SIZE, player);
   }

   @Override
   public Ship createShip(Player player, Map<String, Cell> cells) {
      return new ShipImpl(player, cells);
   }

   @Override
   public Round createRound(Grid g1, Grid g2) {
      return new RoundImpl(g1, g2);
   }

}
