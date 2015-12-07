/**
 *
 */
package de.htwg.se.battleship.controller.old.impl;

import de.htwg.se.battleship.controller.old.IInitGameController;
import de.htwg.se.battleship.controller.old.event.IsShot;
import de.htwg.se.battleship.controller.old.event.SetPlayer;
import de.htwg.se.battleship.controller.old.event.SetPlayerSuccess;
import de.htwg.se.battleship.controller.old.event.SetShip;
import de.htwg.se.battleship.controller.old.event.SetShipSuccess;
import de.htwg.se.battleship.controller.old.event.SetShot;
import de.htwg.se.battleship.controller.old.event.Winner;
import de.htwg.se.battleship.controller.old.event.WrongCoordinate;
import de.htwg.se.battleship.model.ModelFactory;
import de.htwg.se.battleship.model.old.ModelFabric;
import de.htwg.se.battleship.model.old.OLDCell;
import de.htwg.se.battleship.model.old.OLDGrid;
import de.htwg.se.battleship.model.old.OLDPlayer;
import de.htwg.se.battleship.model.old.Round;
import de.htwg.se.battleship.model.old.Ship;
import de.htwg.se.battleship.model.old.ShipImpl;
import de.htwg.se.battleship.util._observer.impl.ObservableImpl;
import de.htwg.se.battleship.util.singleton.SingletonSupplier;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 */
@Deprecated
public class OLDInitGameController extends ObservableImpl implements IInitGameController {

   private static final SingletonSupplier<OLDInitGameController> INST_SUPP =
         new SingletonSupplier<>(OLDInitGameController::new);

   public static OLDInitGameController getInstance() {
      return INST_SUPP.get();
   }

   public static final String MSG_PLAYER_EMPTY = "OLDPlayer %s name is empty";
   public static final String P1 = "one";
   public static final String P2 = "two";

   public static final String ERROR_INPUT = "Your input must be a NUMBER";
   public static final String ERROR_COORDS_GRID = "One or both coordinates are not within the grid";
   public static final String ERROR_COORDS = "Coordinates can only be set horizontal or vertical";
   public static final String ERROR_SAME_COORDS = "Coordinates can not be the same";
   public static final String ERROR_TO_MANY = "Sorry you only have %d cells left (not %d cells)";

   private final ModelFabric fabric;
   private Round round;
   private int shipPlayerCount;


   private OLDInitGameController() {
      this(ModelFactory.createModelFabric());
   }

   /**
    * Create OLDInitGameController with an instance of ModelFabric.
    *
    * @param fabric ModelFabric
    */
   protected OLDInitGameController(ModelFabric fabric) {
      this.fabric = fabric;
   }


   @Override
   public void init() {
      notifyObservers(new SetPlayer());
   }

   @Override
   public void player(String p1, String p2) {
      checkEmpty(p1, String.format(MSG_PLAYER_EMPTY, P1));
      checkEmpty(p2, String.format(MSG_PLAYER_EMPTY, P2));

      //TODO throw errorEvent instead of event
      OLDPlayer player1 = fabric.createPlayer(p1);
      OLDPlayer player2 = fabric.createPlayer(p2);

      OLDGrid g1 = fabric.createGrid(player1);
      OLDGrid g2 = fabric.createGrid(player2);
      round = fabric.createRound(g1, g2);

      notifyObservers(new SetPlayerSuccess(round));

      shipPlayerCount = 0;
      notifyObservers(new SetShip(round));
   }

   private void checkEmpty(String s, String message) {
      if (s == null || s.equals("")) {
         //TODO should not be done with an exception, because only requested UI gets it
         throw new IllegalArgumentException(message);
      }
   }

   @Override
   public void ship(Integer startX, Integer startY, Integer endX, Integer endY) {
      try {
         if (startX == null || startY == null || endX == null || endY == null) {
            throw new IllegalArgumentException(ERROR_INPUT);
         }

         OLDCell start = round.getGrid().getCell(startX, startY);
         OLDCell end = round.getGrid().getCell(endX, endY);

         shipValidateSimple(start, end);
         shipValidateComplex(start, end);

         Ship ship = fabric.createShip(round.getGrid().getPlayer(), getCells(start, end));
         notifyObservers(new SetShipSuccess(round, ship));
      } catch (IllegalArgumentException e) {
         notifyObservers(new WrongCoordinate(round, e.getMessage()));
      } finally {

         if (round.getGrid().getPlayer().getNumberOfShipCells() == ShipImpl.NUMBER_OF_CELLS) {
            round.next();
            shipPlayerCount++;
         }

         if (shipPlayerCount < 2) {
            notifyObservers(new SetShip(round));
         } else {
            notifyObservers(new SetShot(round));
         }
      }
   }

   private void shipValidateSimple(OLDCell start, OLDCell end) {
      if (start == null || end == null) {
         throw new IllegalArgumentException(ERROR_COORDS_GRID);
      }

      if (start.getX() != end.getX() && start.getY() != end.getY()) {
         throw new IllegalArgumentException(ERROR_COORDS);
      }
   }

   private void shipValidateComplex(OLDCell start, OLDCell end) {
      if (start.getX() == end.getX() && start.getY() == end.getY()) {
         throw new IllegalArgumentException(ERROR_SAME_COORDS);
      }

      int rest = ShipImpl.NUMBER_OF_CELLS - round.getGrid().getPlayer().getNumberOfShipCells();
      int diff = diff(start, end);
      System.out.println("rest : " + rest + ", lenght: " + diff);
      if (diff > rest) {
         throw new IllegalArgumentException(String.format(ERROR_TO_MANY, rest, diff));
      }
   }

   private int diff(OLDCell start, OLDCell end) {
      int diff = 0;
      diff += Math.abs(start.getX() - end.getX());
      diff += Math.abs(start.getY() - end.getY());
      diff += 1;
      return diff;
   }

   private Map<String, OLDCell> getCells(OLDCell start, OLDCell end) {

      Map<String, OLDCell> map;

      if (start.getX() == end.getX()) {
         if (start.getY() < end.getY()) {
            map = getCellsX(start, end);
         } else {
            map = getCellsX(end, start);
         }
      } else {
         if (start.getX() < end.getX()) {
            map = getCellsY(start, end);
         } else {
            map = getCellsY(end, start);
         }
      }

      return map;
   }

   private Map<String, OLDCell> getCellsX(OLDCell start, OLDCell end) {
      Map<String, OLDCell> map = new HashMap<>();
      OLDCell cell;

      for (int i = start.getY(); i <= end.getY(); i++) {
         cell = round.getGrid().getCell(start.getX(), i);
         map.put(cell.getKey(), cell);
      }
      return map;
   }

   private Map<String, OLDCell> getCellsY(OLDCell start, OLDCell end) {
      Map<String, OLDCell> map = new HashMap<>();
      OLDCell cell;

      for (int i = start.getX(); i <= end.getX(); i++) {
         cell = round.getGrid().getCell(i, start.getY());
         map.put(cell.getKey(), cell);
      }
      return map;
   }

   @Override
   public void shot(Integer x, Integer y) {

      OLDCell cell = round.getOpponentGrid().getCell(x, y);

      if (cell.getShip() != null) {
         cell.setToHit();
      } else {
         cell.setToShot();
      }

      round.next();

      if (checkWinner()) {
         round.next();
         notifyObservers(new Winner(round));
      } else {
         notifyObservers(new IsShot(round, cell));
         notifyObservers(new SetShot(round));
      }
   }

   private boolean checkWinner() {
      List<Ship> list = round.getGrid().getPlayer().getShips();
      boolean check = true;

      for (Ship ship : list) {
         for (OLDCell cell : ship.getCells()) {
            if (!cell.isShot()) {
               check = false;
            }
         }
      }

      return check;
   }
}
