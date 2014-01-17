/**
 * 
 */
package de.htwg.se.battleship.controller.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import de.htwg.se.battleship.controller.IInitGameController;
import de.htwg.se.battleship.controller.event.IsShot;
import de.htwg.se.battleship.controller.event.SetPlayer;
import de.htwg.se.battleship.controller.event.SetPlayerSuccess;
import de.htwg.se.battleship.controller.event.SetShip;
import de.htwg.se.battleship.controller.event.SetShipSuccess;
import de.htwg.se.battleship.controller.event.SetShot;
import de.htwg.se.battleship.controller.event.Winner;
import de.htwg.se.battleship.controller.event.WrongCoordinate;
import de.htwg.se.battleship.model.ICell;
import de.htwg.se.battleship.model.IGrid;
import de.htwg.se.battleship.model.IModelFabric;
import de.htwg.se.battleship.model.IPlayer;
import de.htwg.se.battleship.model.IRound;
import de.htwg.se.battleship.model.IShip;
import de.htwg.se.battleship.model.impl.Ship;
import de.htwg.se.battleship.util.observer.Observable;

/**
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 *
 */
@Singleton
public class InitGameController extends Observable implements IInitGameController {

    public static final String MSG_PLAYER_EMPTY = "Player %s name is empty";
    public static final String P1               = "one";
    public static final String P2               = "two";

    public static final String ERROR_INPUT       = "Your input must be a NUMBER";
    public static final String ERROR_COORDS_GRID = "One or both coordinates are not within the grid";
    public static final String ERROR_COORDS      = "Coordinates can only be set horizontal or vertical";
    public static final String ERROR_SAME_COORDS = "Coordinates can not be the same";
    public static final String ERROR_TO_MANY     = "Sorry you only have %d cells left (not %d cells)";

    private final IModelFabric fabric;
    private IRound round;
    private int shipPlayerCount;

    /**
     * Create InitGameController with an instance of IModelFabric.
     * @param fabric IModelFabric
     */
    @Inject
    public InitGameController(IModelFabric fabric) {
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
        IPlayer player1 = fabric.createPlayer(p1);
        IPlayer player2 = fabric.createPlayer(p2);

        IGrid g1 = fabric.createGrid(player1);
        IGrid g2 = fabric.createGrid(player2);
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

            ICell start = round.getGrid().getCell(startX, startY);
            ICell end   = round.getGrid().getCell(endX, endY);

            shipValidateSimple(start, end);
            shipValidateComplex(start, end);

            IShip ship = fabric.createShip(round.getGrid().getPlayer(), getCells(start, end));
            notifyObservers(new SetShipSuccess(round, ship));
        } catch(IllegalArgumentException e) {
            notifyObservers(new WrongCoordinate(round, e.getMessage()));
        } finally {

            if (round.getGrid().getPlayer().getNumberOfShipCells() == Ship.NUMBER_OF_CELLS) {
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

    private void shipValidateSimple(ICell start, ICell end) {
        if (start == null || end == null) {
            throw new IllegalArgumentException(ERROR_COORDS_GRID);
        }

        if (start.getX() != end.getX() && start.getY() != end.getY()) {
            throw new IllegalArgumentException(ERROR_COORDS);
        }
    }

    private void shipValidateComplex(ICell start, ICell end) {
        if (start.getX() == end.getX() && start.getY() == end.getY()) {
            throw new IllegalArgumentException(ERROR_SAME_COORDS);
        }

        int rest = Ship.NUMBER_OF_CELLS - round.getGrid().getPlayer().getNumberOfShipCells();
        int diff = diff(start, end);
        if (diff > rest) {
            throw new IllegalArgumentException(String.format(ERROR_TO_MANY, rest, diff));
        }
    }

    private int diff(ICell start, ICell end) {
        int diff = 0;
        diff += Math.abs(start.getX() - end.getX());
        diff += Math.abs(start.getY() - end.getY());
        diff += 1;
        return diff;
    }

    private Map<String, ICell> getCells(ICell start, ICell end) {

        Map<String, ICell> map;

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

    private Map<String, ICell> getCellsX(ICell start, ICell end) {
        Map<String, ICell> map = new HashMap<String, ICell>();
        ICell cell;

        for (int i = start.getY(); i <= end.getY(); i++) {
            cell = round.getGrid().getCell(start.getX(), i);
            map.put(cell.getKey(), cell);
        }
        return map;
    }

    private Map<String, ICell> getCellsY(ICell start, ICell end) {
        Map<String, ICell> map = new HashMap<String, ICell>();
        ICell cell;

        for (int i = start.getX(); i <= end.getX(); i++) {
            cell = round.getGrid().getCell(i, start.getY());
            map.put(cell.getKey(), cell);
        }
        return map;
    }

    @Override
    public void shot(Integer x, Integer y) {

        ICell cell = round.getOpponentGrid().getCell(x, y);

        if(cell.getShip() != null) {
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
        List<IShip> list = round.getGrid().getPlayer().getShips();
        boolean check = true;

        for(IShip ship: list) {
            for (ICell cell: ship.getCells()) {
                if (!cell.isShot()) {
                    check = false;
                }
            }
        }

        return check;
    }
}
