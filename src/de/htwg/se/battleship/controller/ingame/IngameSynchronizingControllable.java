package de.htwg.se.battleship.controller.ingame;

import de.htwg.se.battleship.model.read.RPlayer;
import de.htwg.se.battleship.util.controller.Controllable;
import de.htwg.se.battleship.util.platform.SingleUseRunnable;

import java.util.Optional;

/**
 * @author aullik on 05.01.2016.
 */
public interface IngameSynchronizingControllable extends Controllable {

   RPlayer getPlayer();

   Optional<RPlayer> checkGameLost();

   void awaitCellShot(SingleUseRunnable runnable);

}
