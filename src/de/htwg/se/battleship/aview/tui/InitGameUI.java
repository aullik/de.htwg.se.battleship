/**
 * 
 */
package de.htwg.se.battleship.aview.tui;


import com.google.inject.Inject;

import de.htwg.se.battleship.controller.IInitGameController;
import de.htwg.se.battleship.controller.event.SetPlayer;
import de.htwg.se.battleship.controller.event.SetShips;
import de.htwg.se.battleship.controller.impl.InitGameController;

/**
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 *
 */
public class InitGameUI extends UserInterface implements IInitGameUI {

    private final IInitGameController controller;

    public static final String MSG_INPUT_NOTE   = "Name for player %s (default: Player %s): ";
    public static final String MSG_NAME_NOTE    = "Great player %s your name is '%s'!";
    public static final String DEFAULT_NAME     = "Player %d";

    /**
     * 
     */
    @Inject
    public InitGameUI(IInitGameController controller, IScannerFactory sf) {
        super(sf.getScanner());

        this.controller = controller;
        controller.addObserver(this);
    }

    @Override
    public void update(SetPlayer e) {

        getLogger().info(header());

        String player1 = playername(InitGameController.P1, 1);
        String player2 = playername(InitGameController.P2, 2);
        controller.player(player1, player2);
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
    public void update(SetShips e) {
        getLogger().info("awesome");
    }

}
