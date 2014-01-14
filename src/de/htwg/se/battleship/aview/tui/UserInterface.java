/**
 * 
 */
package de.htwg.se.battleship.aview.tui;

import java.io.InputStream;

import org.apache.log4j.Logger;

import de.htwg.se.battleship.util.observer.Event;
import de.htwg.se.battleship.util.observer.IObserver;

/**
 * @author Philipp
 *
 */
public abstract class UserInterface implements IObserver {

    protected final Logger logger = Logger.getLogger("de.htwg.se.battleship.aview.tui");
    protected final InputStream stream;

    /**
     * Initiate input stream
     * @param stream InputStream
     */
    public UserInterface(InputStream stream) {
        this.stream = stream;
    }

    @Override
    public void update(Event e) {
        throw new IllegalArgumentException("This class has no listener for " + e.getClass().toString());
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

}
