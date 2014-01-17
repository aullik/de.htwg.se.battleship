/**
 * 
 */
package de.htwg.se.battleship.aview.tui;

import org.apache.log4j.Logger;

import de.htwg.se.battleship.util.observer.Event;
import de.htwg.se.battleship.util.observer.IObserver;

/**
 * @author Philipp
 *
 */
public abstract class UserInterface implements IObserver {

    public static final String MSG_EXCEPTION = "%s has no listener for %s";

    private final Logger logger = Logger.getLogger("de.htwg.se.battleship.aview.tui");

    /**
     * Returns Logger instance
     * @return Logger
     */
    protected Logger getLogger() {
        return logger;
    }

    @Override
    public void update(Event e) {
        throw new IllegalArgumentException(String.format(MSG_EXCEPTION, this.getClass().toString(), e.getClass().toString()));
    }

    /**
     * Game name as ASCII-Art:
     * http://patorjk.com/software/taag/#p=display&f=Big&t=Battleship
     */
    protected String header() {

        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append("***********************************************************").append("\n");
        sb.append("*      ____        _   _   _           _     _            *").append("\n");
        sb.append("*     |  _ \\      | | | | | |         | |   (_)           *").append("\n");
        sb.append("*     | |_) | __ _| |_| |_| | ___  ___| |__  _ _ __       *").append("\n");
        sb.append("*     |  _ < / _` | __| __| |/ _ \\/ __| '_ \\| | '_ \\      *").append("\n");
        sb.append("*     | |_) | (_| | |_| |_| |  __/\\__ \\ | | | | |_) |     *").append("\n");
        sb.append("*     |____/ \\__,_|\\__|\\__|_|\\___||___/_| |_|_| .__/      *").append("\n");
        sb.append("*                                             | |         *").append("\n");
        sb.append("*                                             |_|         *").append("\n");
        sb.append("***********************************************************").append("\n");
        return sb.toString();
    }

    /**
     * Show Some Hints for the user
     */
    public abstract void showText();

    /**
     * Send input to UI.
     * @param input String
     * @return boolean
     */
    public abstract boolean executeInput(String input);


    /**
     * Return UserInterface for next input.
     * @return UserInterface
     */
    public abstract UserInterface getUI();

}
