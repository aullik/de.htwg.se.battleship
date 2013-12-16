package de.htwg.se.battleship.controller;

import java.util.Map.Entry;
import java.util.Set;

import de.htwg.se.battleship.controller.input.MainMenu;
import de.htwg.se.battleship.util.observer.Event;
import de.htwg.se.battleship.util.observer.Observable;
import de.htwg.se.battleship.util.observer.events.CloseEvent;
import de.htwg.se.battleship.util.observer.events.ContinueEvent;
import de.htwg.se.battleship.util.observer.events.ErrorEvent;
import de.htwg.se.battleship.util.observer.events.StandardEvent;

public class InputController extends Observable implements IntController {

    private INTInputState currentState;

    public InputController() {
        currentState = new MainMenu();
    }

    public Event setState(INTInputState i) {
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
                continue;
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

    public static String[] splitInput(String line, Set<String> set) {
        final int maxSplitNo = 3;
        String[] split = new String[maxSplitNo];
        int first = -1;
        String tmp = null;

        for (String s : set) {
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

    public static Event welcomeEvent(INTInputState state) {
        StringBuilder sb = new StringBuilder(state.getMenuName());
        sb.append(":\n");
        for (Entry<String, INTCommands> e : state.getEntrySet()) {
            sb.append(e.getValue().getCommand());
            sb.append("\t- ");
            sb.append(e.getValue().getDescription());
            sb.append("\n");
        }
        return new StandardEvent(sb.toString());
    }

}
