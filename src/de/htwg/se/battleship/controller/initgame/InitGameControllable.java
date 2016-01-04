package de.htwg.se.battleship.controller.initgame;

import de.htwg.se.battleship.model.impl.ShipSize2;
import de.htwg.se.battleship.model.impl.ShipSize3;
import de.htwg.se.battleship.model.impl.ShipSize4;
import de.htwg.se.battleship.model.impl.ShipSize5;
import de.htwg.se.battleship.model.read.RPlayer;
import de.htwg.se.battleship.util.controller.Controllable;
import de.htwg.se.battleship.util.controller.impl.SingleUseConsumer;

/**
 * Created by aullik on 27.11.2015.
 */
public interface InitGameControllable extends Controllable {

   void setPlayer(RPlayer player);

   void setPlayerName(final SingleUseConsumer<String> nameSetter);

   void set5SizeShip(final SingleUseConsumer<ShipSize5> shipSetter);

   void set4SizeShip(final SingleUseConsumer<ShipSize4> shipSetter);

   void set3SizeShip(final SingleUseConsumer<ShipSize3> shipSetter);

   void set2SizeShip(final SingleUseConsumer<ShipSize2> shipSetter);

}
