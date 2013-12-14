package de.htwg.se.battleship.controller.input;

import de.htwg.se.battleship.util.observer.Event;
import de.htwg.se.battleship.util.observer.events.ContinueEvent;
import de.htwg.se.battleship.util.observer.events.ErrorEvent;
import de.htwg.se.battleship.util.observer.events.StandardEvent;

public class SelectState implements InputState {

    private static final String OPENCOMMAND = "selectstate";
    private final Event         event;

    enum Command {
        INIT(InitGame.getCommand()), CLOSE(Close.getCommand());

        private String name;

        private Command(String s) {
            this.name = s;
        }
    }

    protected SelectState() {
        event = new StandardEvent("Welcome to Batteleship" + "\nType: " + "\n"
                + Command.INIT.name + " - terminates the instance" + "\n"
                + Command.INIT.name + " - starts new game");
    }

    @Override
    public Event[] processInput(InputController controller, String line) {
        Event[] array = new Event[2];
        String[] in = controller.splitInput(line, this);

        if (in[1].equals(Command.INIT.name)) {
            array[0] = controller.setState(new InitGame());
            if (!in[2].isEmpty()) {
                array[1] = new ContinueEvent(in[2]);
            }
        } else if (in[1].equals(Command.CLOSE.name)) {
            array[0] = controller.setState(new Close());
        } else {
            array[0] = new ErrorEvent("input didnt match: " + line);
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
