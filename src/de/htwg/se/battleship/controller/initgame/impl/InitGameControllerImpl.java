package de.htwg.se.battleship.controller.initgame.impl;

import de.htwg.se.battleship.controller.initgame.InitGameControllable;
import de.htwg.se.battleship.controller.initgame.InitGameController;
import de.htwg.se.battleship.model.ModelFactory;
import de.htwg.se.battleship.model.impl.ShipSize2;
import de.htwg.se.battleship.model.impl.ShipSize3;
import de.htwg.se.battleship.model.impl.ShipSize4;
import de.htwg.se.battleship.model.impl.ShipSize5;
import de.htwg.se.battleship.model.readwrite.RWPlayer;
import de.htwg.se.battleship.util.controller.impl.GamePlatform;
import de.htwg.se.battleship.util.controller.impl.ThreadSaveController;

import java.util.function.Consumer;

/**
 * @author aullik on 28.11.2015.
 */
public class InitGameControllerImpl extends ThreadSaveController<InitGameControllable> implements InitGameController {

   private final static int NUM_SIZE_2_SHIPS = 4;
   private final static int NUM_SIZE_3_SHIPS = 3;
   private final static int NUM_SIZE_4_SHIPS = 2;
   private final static int NUM_SIZE_5_SHIPS = 1;

   private final String initialName;
   private final Consumer<RWPlayer> onfinished;
   private RWPlayer player;
   private int size_2_ships;
   private int size_3_ships;
   private int size_4_ships;
   private int size_5_ships;


   public InitGameControllerImpl(final GamePlatform platform, String initialName, Consumer<RWPlayer> onfinished) {
      super(platform);
      this.initialName = initialName;
      this.onfinished = onfinished;

      this.size_2_ships = 0;
      this.size_3_ships = 0;
      this.size_4_ships = 0;
      this.size_5_ships = 0;
      getPlayerName();
   }



   private void getPlayerName() {
      executeSingleUse(InitGameControllable::setPlayerName, this::setPlayerName);
   }

   private void setPlayerName(final String name) {
      if (name == null || name.isEmpty())
         player = ModelFactory.createPlayer(initialName);
      else
         player = ModelFactory.createPlayer(name);

      initSetShips();
   }


   private void initSetShips() {
      platform.runLater(this::get5SizeShip);
      platform.runLater(this::get4SizeShip);
      platform.runLater(this::get3SizeShip);
      platform.runLater(this::get2SizeShip);
   }

   private void checkFinished() {
      if(checkSize5Ships()
         && checkSize4Ships()
         && checkSize3Ships()
            && checkSize2Ships())
         finish();
   }

   private void get5SizeShip() {
      executeSingleUse(InitGameControllable::set5SizeShip, this::set5SizeShip);
   }

   private void set5SizeShip(final ShipSize5 ship) {
      player.addShip(ship);
      checkFinished();
   }

   private boolean checkSize5Ships(){
      return size_5_ships >= NUM_SIZE_5_SHIPS;
   }


   private void get4SizeShip() {
      executeSingleUse(InitGameControllable::set4SizeShip, this::set4SizeShip);
   }

   private void set4SizeShip(final ShipSize4 ship) {
      player.addShip(ship);

      if(checkSize4Ships())
         checkFinished();
      else
         platform.runLater(this::get4SizeShip);
   }
   private boolean checkSize4Ships(){
      return size_4_ships >= NUM_SIZE_4_SHIPS;
   }

   private void get3SizeShip() {
      executeSingleUse(InitGameControllable::set3SizeShip, this::set3SizeShip);
   }

   private void set3SizeShip(final ShipSize3 ship) {
      player.addShip(ship);

      if(checkSize3Ships())
         checkFinished();
      else
         platform.runLater(this::get3SizeShip);
   }
   private boolean checkSize3Ships(){
      return size_3_ships >= NUM_SIZE_3_SHIPS;
   }

   private void get2SizeShip() {
      executeSingleUse(InitGameControllable::set2SizeShip, this::set2SizeShip);
   }

   private void set2SizeShip(final ShipSize2 ship) {
      player.addShip(ship);

      if(checkSize2Ships())
         checkFinished();
      else
         platform.runLater(this::get2SizeShip);
   }
   private boolean checkSize2Ships(){
      return size_2_ships >= NUM_SIZE_2_SHIPS;
   }

   private void finish() {
      onfinished.accept(player);
   }

}
