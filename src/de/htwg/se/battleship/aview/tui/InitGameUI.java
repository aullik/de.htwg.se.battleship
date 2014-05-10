/**
 * 
 */
package de.htwg.se.battleship.aview.tui;


import com.google.inject.Inject;

import de.htwg.se.battleship.controller.IInitGameController;
import de.htwg.se.battleship.controller.event.SetPlayer;
import de.htwg.se.battleship.controller.event.SetPlayerSuccess;
import de.htwg.se.battleship.controller.event.SetShip;
import de.htwg.se.battleship.controller.event.SetShipSuccess;
import de.htwg.se.battleship.controller.impl.InitGameController;
import de.htwg.se.battleship.model.Cell;
import de.htwg.se.battleship.model.Player;
import de.htwg.se.battleship.util.observer.Event;

/**
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 *
 */
public class InitGameUI extends UserInterface implements IInitGameUI {

    private final IInitGameController controller;
    private Player player;

    public static final String MSG_INPUT_NOTE   = "Name for player %s (default: Player %s): ";
    public static final String MSG_NAME_NOTE    = "Great player %s your name is '%s'!%n%n";
    public static final String DEFAULT_NAME     = "Player %d";
    public static final String MSG_PLAYER_ADD   = "Added successfull player '%s' and '%s'";
    public static final String MSG_WELCOME      = "Hallo '%s'. It's your turn!";
    public static final String MSG_SHIP         = "Next step is to add a ship (start- and end-point).";
    public static final String MSG_SHIP_START_X = "Start-point x-coordinate:";
    public static final String MSG_SHIP_START_Y = "Start-point y-coordinate:";
    public static final String MSG_SHIP_END_X   = "End-point x-coordinate:";
    public static final String MSG_SHIP_END_Y   = "End-point y-coordinate:";
    public static final String MSG_SHIP_SUCCESS = "Added successfull a new ship: %s";

    private static final int ZERO = 0;
    private static final int FIRST = 1;
    private static final int SECOND = 2;
    private static final int THIRD = 3;
    private static final int FOURTH = 4;



    private Event lastEvent;
    private String output;
    private int playerIndex;
    private String playerNo;
    private String playerName;
    private int shipIndex;
    private final Integer[] shipCoords;

    /**
     * 
     */
    @Inject
    public InitGameUI(IInitGameController controller) {
        this.controller = controller;
        controller.addObserver(this);

        player = null;
        lastEvent = null;
        output = "";
        playerIndex = 0;
        playerName = "";
        shipIndex = 0;
        shipCoords = new Integer[FOURTH];
    }

    @Override
    public void update(SetPlayer e) {
        lastEvent = e;
        playerIndex = 1;
        playerNo = InitGameController.P1;
        output = header() + String.format(MSG_INPUT_NOTE, playerNo, playerIndex);
    }


    @Override
    public void update(SetPlayerSuccess e) {
        Player p1 = e.getPlayer();
        e.getRound().next();
        Player p2 = e.getPlayer();
        e.getRound().next();

        getLogger().info(String.format(MSG_PLAYER_ADD, p1.getName(), p2.getName()));
    }

    @Override
    public void update(SetShip e) {

        lastEvent = e;
        shipIndex = ZERO;
        output = header();
        shipOut(MSG_SHIP_START_X);
        player = e.getPlayer();
    }

    private void shipOut(String s) {
        if (!((SetShip)lastEvent).getPlayer().equals(player)) {
            output += String.format(MSG_WELCOME, ((SetShip)lastEvent).getPlayer().getName());
        }
        output += "\n" + MSG_SHIP;
        output += "\n" + s;
    }

    @Override
    public void update(SetShipSuccess e) {
        StringBuilder sb = new StringBuilder();

        for (Cell cell : e.getShip().getCells()) {
            sb.append("(").append(cell.getKey()).append("),");
        }

        getLogger().info(String.format(MSG_SHIP_SUCCESS, sb.toString()));
    }

    @Override
    public void showText() {
        getLogger().info(output);

    }

    @Override
    public boolean executeInput(String input) {

        if (lastEvent instanceof SetPlayer) {
            String pName = input;

            if (pName.equals("")) {
                pName = String.format(DEFAULT_NAME, playerIndex);
            }

            getLogger().info(String.format(MSG_NAME_NOTE, playerNo, pName));

            playerIndex++;
            playerNo = InitGameController.P2;
            output = String.format(MSG_INPUT_NOTE, playerNo, playerIndex);

            if (playerIndex > 2) {
                lastEvent = null;
                controller.player(playerName, pName);
            } else {
                playerName = pName;
            }
        } else if (lastEvent instanceof SetShip) {

            try {
                shipCoords[shipIndex] = Integer.parseInt(input);
            } catch(NumberFormatException e) {
                shipCoords[shipIndex] = null;
            }
            shipIndex++;

            output = "";
            if (shipIndex == FIRST) {
                shipOut(MSG_SHIP_START_Y);
            }
            if (shipIndex == SECOND) {
                shipOut(MSG_SHIP_END_X);
            }
            if (shipIndex == THIRD) {
                shipOut(MSG_SHIP_END_Y);
            }

            if (shipIndex > THIRD) {
                lastEvent = null;
                controller.ship(shipCoords[ZERO], shipCoords[FIRST], shipCoords[SECOND], shipCoords[THIRD]);
            }
        }
        return true;
    }

    @Override
    public UserInterface getUI() {
        return this;
    }

}
