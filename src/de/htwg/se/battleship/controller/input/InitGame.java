package de.htwg.se.battleship.controller.input;

import de.htwg.se.battleship.util.observer.Event;
import de.htwg.se.battleship.util.observer.events.ContinueEvent;
import de.htwg.se.battleship.util.observer.events.ErrorEvent;
import de.htwg.se.battleship.util.observer.events.StandardEvent;

public class InitGame implements InputState {

    private static final String OPENCOMMAND  = "-newgame";
    private static final int    STDFIELDSIZE = 10;
    private final Event         event;
    private String[]            player;
    private int                 fieldsize;

    enum Command {
        P1("-p1"), P2("-p2"), FIELD("-field"), START(InGame.getCommand());

        private String name;

        private Command(String s) {
            this.name = s;
        }

    };

    protected InitGame() {
        player = new String[] { "Player 1", "Player 2" };
        fieldsize = STDFIELDSIZE;

        event = new StandardEvent("Type:" + "\n" + Command.P1.name
                + " ... - set name " + player[0] + "\n" + Command.P2.name
                + " ... - set name " + player[1] + "\n" + Command.FIELD.name
                + " ... - set field size(standart: " + fieldsize + ")" + "\n"
                + Command.START.name + " - starts game");
    }

    @Override
    public Event[] processInput(InputController controller, String line) {
        Event[] array = new Event[2];
        String[] in = controller.splitInput(line, this);

        if (in[1].equals(Command.P1.name)) {
            array = renamePlayer(controller, in, 0);
        } else if (in[1].equals(Command.P2.name)) {
            array = renamePlayer(controller, in, 1);
        } else if (in[1].equals(Command.FIELD.name)) {
            array = changeSize(controller, in);
        } else if (in[1].equals(Command.START.name)) {
            array[0] = controller.setState(new InGame(fieldsize, player[0],
                    player[1]));
            if (!in[2].isEmpty()) {
                array[1] = new ContinueEvent(in[2]);
            }
        } else {
            array[0] = new ErrorEvent("input didnt match: " + line);
        }

        return array;
    }

    private Event[] changeSize(InputController controller, String[] in) {
        Event[] array = new Event[2];
        String[] input = controller.splitInput(in[2], this);
        if (input[0].isEmpty()) {
            array[0] = new ErrorEvent("couldn't change fieldsize, currently: "
                    + fieldsize);
        } else if (input[0].matches("[0-9]+$")) {
            fieldsize = Integer.parseInt(input[0]);
            array[0] = new StandardEvent("changed fieldsize to " + fieldsize);
        } else {
            array[0] = new ErrorEvent(input[0] + "is no number");
        }
        if (input[1] != null && !input[1].isEmpty()) {
            array[1] = new ContinueEvent(input[1] + input[2]);
        }
        return array;
    }

    private Event[] renamePlayer(InputController controller, String[] in, int no) {
        Event[] array = new Event[2];
        String[] input = controller.splitInput(in[2], this);
        if (input[0].isEmpty()) {
            array[0] = new ErrorEvent("couldn't rename " + player[no]);
        } else {
            array[0] = new StandardEvent(player[no] + " changed to " + input[0]);
            player[no] = input[0];
        }
        if (input[1] != null && !input[1].isEmpty()) {

            array[1] = new ContinueEvent(input[1] + input[2]);
        }

        return array;

    }

    protected static String getCommand() {
        return OPENCOMMAND;
    }

    @Override
    public Event getEvent() {
        return event;
    }

    @Override
    public String[] getCommands() {
        Command[] com = Command.values();
        String[] strings = new String[com.length];
        for (int i = 0; i < com.length; i++) {
            strings[i] = com[i].name;
        }
        return strings;

    }

}
