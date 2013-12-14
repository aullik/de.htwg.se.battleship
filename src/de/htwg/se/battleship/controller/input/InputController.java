package de.htwg.se.battleship.controller.input;

import de.htwg.se.battleship.controller.IntController;
import de.htwg.se.battleship.util.observer.Event;
import de.htwg.se.battleship.util.observer.Observable;
import de.htwg.se.battleship.util.observer.events.CloseEvent;
import de.htwg.se.battleship.util.observer.events.ContinueEvent;
import de.htwg.se.battleship.util.observer.events.ErrorEvent;
import de.htwg.se.battleship.util.observer.events.StandardEvent;

public class InputController extends Observable implements IntController {

    private InputState currentState;

    public InputController() {
        currentState = new SelectState();
    }

    protected Event setState(InputState i) {
        currentState = i;
        return currentState.getEvent();
    }

    @Override
    public boolean processInputLine(String line) {
        Event[] array;
        boolean out = true;

        array = currentState.processInput(this, line);
        for (Event e : array) {
            if (array == null) {
            } else if (e instanceof CloseEvent) {
                notifyObservers(e);
                return false;
            } else if (e instanceof StandardEvent) {
                notifyObservers(e);
            } else if (e instanceof ErrorEvent) {
                notifyObservers(e);
            } else if (e instanceof ContinueEvent) {
                out &= processInputLine(e.getMessage());
            }
        }
        return out;
    }

    @Override
    public void updateNotify() {
        notifyObservers(currentState.getEvent());

    }

    public String[] splitInput(String line, String[] command) {
        String[] split = new String[3];
        int first = -1;
        String tmp = null;

        for (String s : command) {
            int i;
            i = line.indexOf(s);
            if (i > -1 && (i < first || first == -1)) {
                first = i;
                tmp = s;
            }
        }
        if (first == -1) {
            split[0] = line;
            split[1] = "";
            split[2] = "";
        } else {
            split[0] = line.substring(0, first).trim();
            split[1] = tmp;
            split[2] = line.substring(first + tmp.length()).trim();

        }
        return split;
    }

}
