/**
 *
 */
package de.htwg.se.battleship.model.old.impl;

import de.htwg.se.battleship.model.old.ModelFabric;
import de.htwg.se.battleship.model.old.OLDCell;
import de.htwg.se.battleship.model.old.OLDGrid;
import de.htwg.se.battleship.model.old.OLDPlayer;
import de.htwg.se.battleship.model.old.OLDRound;
import de.htwg.se.battleship.model.old.OLDShip;
import de.htwg.se.battleship.util.singleton.SingletonSupplier;

import java.util.Map;

/**
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 */
@Deprecated
public class ModelFabricImpl implements ModelFabric {

   private static final SingletonSupplier<ModelFabricImpl> INST_SUPP = new SingletonSupplier<>(ModelFabricImpl::new);

   public static ModelFabricImpl getInstance() {
      return INST_SUPP.get();
   }

   private ModelFabricImpl() {
   }


   @Override
   public OLDPlayer createPlayer(String name) {
      return new OLDPlayerImpl(name);
   }

   @Override
   public OLDGrid createGrid(OLDPlayer player) {
      return new OLDGridImpl(OLDGridImpl.DEFAULT_SIZE, player);
   }

   @Override
   public OLDShip createShip(OLDPlayer player, Map<String, OLDCell> cells) {
      return new OLDShipImpl(player, cells);
   }

   @Override
   public OLDRound createRound(OLDGrid g1, OLDGrid g2) {
      return new OLDRoundImpl(g1, g2);
   }

}
