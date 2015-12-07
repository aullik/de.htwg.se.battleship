package de.htwg.se.battleship.controller.initgame.impl;

import de.htwg.se.battleship.controller.ThreadSaveController;
import de.htwg.se.battleship.controller.initgame.InitGameControllable;
import de.htwg.se.battleship.controller.initgame.InitGameController;
import de.htwg.se.battleship.model.impl.ShipSize2;
import de.htwg.se.battleship.model.impl.ShipSize3;
import de.htwg.se.battleship.model.impl.ShipSize4;
import de.htwg.se.battleship.model.impl.ShipSize5;
import de.htwg.se.battleship.model.old.OLDGrid;
import de.htwg.se.battleship.model.old.OLDPlayer;
import de.htwg.se.battleship.model.old.OLDPlayerImpl;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by aullik on 28.11.2015.
 */
public class InitGameControllerImpl extends ThreadSaveController<InitGameControllable> implements InitGameController {

   private final String initialName;
   private OLDPlayer player;

   Queue<Runnable> jobs;

   public InitGameControllerImpl(final Platform platform, String initialName) {
      super(platform);
      this.initialName = initialName;
      jobs = new LinkedList<>();

      init();
      nextJob();
   }

   private void init() {
      jobs.add(this::getPlayerName);
      jobs.add(this::get5SizeShip);
      doXTimes(2, () -> jobs.add(this::get4SizeShip));
      doXTimes(3, () -> jobs.add(this::get3SizeShip));
      doXTimes(4, () -> jobs.add(this::get2SizeShip));
      jobs.add(this::finish);

   }

   private void doXTimes(int x, Runnable run) {
      for (int i = 0; i < x; x++)
         run.run();
   }

   private void nextJob() {
      runLater(jobs.poll());
   }


   @Override
   public OLDPlayer getPlayer() {
      return null;
   }

   @Override
   public OLDGrid getGrid() {
      return null;
   }

   private void getPlayerName() {
      executeSingleUse(InitGameControllable::setPlayerName, this::setPlayerName);
   }

   private void setPlayerName(final String name) {
      if (name == null || name.isEmpty())
         player = new OLDPlayerImpl(initialName);
      else
         player = new OLDPlayerImpl(name);
      nextJob();
   }

   private void get5SizeShip() {
      executeSingleUse(InitGameControllable::set5SizeShip, this::set5SizeShip);
   }

   private void set5SizeShip(final ShipSize5 ship) {
      player.addShip(ship);
      nextJob();
   }

   private void get4SizeShip() {
      executeSingleUse(InitGameControllable::set4SizeShip, this::set4SizeShip);
   }

   private void set4SizeShip(final ShipSize4 ship) {
      player.addShip(ship);
      nextJob();
   }

   private void get3SizeShip() {
      executeSingleUse(InitGameControllable::set3SizeShip, this::set3SizeShip);
   }

   private void set3SizeShip(final ShipSize3 ship) {
      player.addShip(ship);
      nextJob();
   }

   private void get2SizeShip() {
      executeSingleUse(InitGameControllable::set2SizeShip, this::set2SizeShip);
   }

   private void set2SizeShip(final ShipSize2 ship) {
      player.addShip(ship);
      nextJob();
   }

   private void finish() {

   }

}
