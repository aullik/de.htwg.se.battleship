/**
 * 
 */
package de.htwg.se.battleship.aview.tui;

import org.apache.log4j.Logger;

import de.htwg.se.battleship.util.observer.Event;
import de.htwg.se.battleship.util.observer.Observer;

/**
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 */
public abstract class UserInterface implements Observer {

    private final Logger logger = Logger.getLogger("de.htwg.se.battleship.aview.tui");
    private boolean process = true;

    @Override
    public void update(Event e) {}

    /**
     * Return process flag.
     * 
     * @return
     */
    public boolean getProcess() {
        return process;
    }

    /**
     * Stop processing
     */
    protected void deactivateProcess() {
        process = false;
    }

    /**
     * Returns Logger instance
     * 
     * @return Logger
     */
    protected Logger getLogger() {
        return logger;
    }

    /**
     * Game name as ASCII-Art:
     * http://patorjk.com/software/taag/#p=display&f=Big&t=Battleship
     */
    protected String header() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append("***********************************************************\n");
        sb.append("*      ____        _   _   _           _     _            *\n");
        sb.append("*     |  _ \\      | | | | | |         | |   (_)           *\n");
        sb.append("*     | |_) | __ _| |_| |_| | ___  ___| |__  _ _ __       *\n");
        sb.append("*     |  _ < / _` | __| __| |/ _ \\/ __| '_ \\| | '_ \\      *\n");
        sb.append("*     | |_) | (_| | |_| |_| |  __/\\__ \\ | | | | |_) |     *\n");
        sb.append("*     |____/ \\__,_|\\__|\\__|_|\\___||___/_| |_|_| .__/      *\n");
        sb.append("*                                             | |         *\n");
        sb.append("*                                             |_|         *\n");
        sb.append("***********************************************************\n");
        return sb.toString();
    }

    protected void output(String string) {
        getLogger().info(string);
    }

    /**
     * Show Some Hints for the user
     */
    public abstract void showText();

    /**
     * Send input to UI.
     * 
     * @param input String
     * @return boolean
     */
    public abstract UserInterface executeInput(String input);

}
