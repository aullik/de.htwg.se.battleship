/**
 * 
 */
package de.htwg.se.battleship.aview.tui;


import com.google.inject.Inject;

import de.htwg.se.battleship.controller.IInitGameController;
import de.htwg.se.battleship.controller.event.SetPlayer;
import de.htwg.se.battleship.controller.event.SetPlayerSuccess;
import de.htwg.se.battleship.controller.event.SetShip;
import de.htwg.se.battleship.controller.impl.InitGameController;
import de.htwg.se.battleship.model.IPlayer;

/**
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 *
 */
public class InitGameUI extends UserInterface implements IInitGameUI {

    private final IInitGameController controller;
    private IPlayer player;

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

    /**
     * 
     */
    @Inject
    public InitGameUI(IInitGameController controller, IScannerFactory sf) {
        super(sf.getScanner());

        this.controller = controller;
        controller.addObserver(this);

        player = null;
    }

    @Override
    public void update(SetPlayer e) {

        getLogger().info(header());

        String player1 = playername(InitGameController.P1, 1);
        String player2 = playername(InitGameController.P2, 2);
        controller.player(player1, player2);
    }

    @Override
    public void update(SetPlayerSuccess e) {
        IPlayer p1 = e.getPlayer();
        e.getRound().next();
        IPlayer p2 = e.getPlayer();
        e.getRound().next();

        getLogger().info(String.format(MSG_PLAYER_ADD, p1.getName(), p2.getName()));
    }

    private String playername(String no, int index) {
        String name;

        getLogger().info(String.format(MSG_INPUT_NOTE, no, index));
        name = getScanner().nextLine();

        if (name.equals("")) {
            name = String.format(DEFAULT_NAME, index);
        }

        getLogger().info(String.format(MSG_NAME_NOTE, no, name));
        return name;
    }

    @Override
    public void update(SetShip e) {

        getLogger().info(header());

        if (!e.getPlayer().equals(player)) {
            getLogger().info(String.format(MSG_WELCOME, e.getPlayer().getName()));
        }
        player = e.getPlayer();

        getLogger().info(MSG_SHIP);

        int sx = readCoordinate(MSG_SHIP_START_X);
        int sy = readCoordinate(MSG_SHIP_START_Y);
        int ex = readCoordinate(MSG_SHIP_END_X);
        int ey = readCoordinate(MSG_SHIP_END_Y);

        controller.ship(sx, sy, ex, ey);
    }

    private int readCoordinate(String msg) {
        getLogger().info(msg);
        return getScanner().nextInt();
    }

}
