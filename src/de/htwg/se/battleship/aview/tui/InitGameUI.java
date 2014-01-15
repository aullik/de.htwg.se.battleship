/**
 * 
 */
package de.htwg.se.battleship.aview.tui;


import java.util.Scanner;

import de.htwg.se.battleship.controller.IInitGameController;
import de.htwg.se.battleship.controller.event.SetPlayer;

/**
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 *
 */
public class InitGameUI extends UserInterface {

    private final IInitGameController controller;

    public static final String MSG_INPUT_NOTE   = "Name for player %s: ";
    public static final String MSG_INPUT_EMPTY  = "Sorry your input was empty, please try again!";
    public static final String MSG_NAME_NOTE    = "Great player %s your name is '%s'!";
    public static final String P1               = "one";
    public static final String P2               = "two";

    /**
     * 
     */
    public InitGameUI(IInitGameController controller, Scanner scanner) {
        super(scanner);

        this.controller = controller;
        controller.addObserver(this);
    }

    /**
     * Read input for new player
     * @param e SetPlayer
     */
    public void update(SetPlayer e) {

        getLogger().info(header());

        String player1 = playername(P1);
        String player2 = playername(P2);
        controller.player(player1, player2);
    }

    private String playername(String no) {
        String name;

        do {
            getLogger().info(String.format(MSG_INPUT_NOTE, no));
            name = getScanner().nextLine();

            if (name.equals("")) {
                getLogger().info(MSG_INPUT_EMPTY);
            } else {
                break;
            }

        } while(true);

        getLogger().info(String.format(MSG_NAME_NOTE, no, name));
        return name;
    }

}
