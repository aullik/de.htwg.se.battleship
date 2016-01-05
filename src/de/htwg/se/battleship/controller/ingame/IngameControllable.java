package de.htwg.se.battleship.controller.ingame;

import de.htwg.se.battleship.model.read.REnemyCell;
import de.htwg.se.battleship.util.controller.Controllable;
import de.htwg.se.battleship.util.platform.SingleUseConsumer;

/**
 * Created by aullik on 27.11.2015.
 */
public interface IngameControllable extends Controllable {

   void shoot(SingleUseConsumer<REnemyCell> cons);
}
