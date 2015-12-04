package de.htwg.se.battleship.controller.initgame;

import de.htwg.se.battleship.model.impl.ShipSize2;
import de.htwg.se.battleship.model.impl.ShipSize3;
import de.htwg.se.battleship.model.impl.ShipSize4;
import de.htwg.se.battleship.model.impl.ShipSize5;
import de.htwg.se.battleship.util.controller.Controllable;

import java.util.function.Consumer;

/**
 * Created by aullik on 27.11.2015.
 */
public interface InitGameControllable extends Controllable {


   void setPlayerName(final Consumer<String> nameSetter);

   void set5SizeShip(final Consumer<ShipSize5> shipSetter);

   void set4SizeShip(final Consumer<ShipSize4> shipSetter);

   void set3SizeShip(final Consumer<ShipSize3> shipSetter);

   void set2SizeShip(final Consumer<ShipSize2> shipSetter);

}
