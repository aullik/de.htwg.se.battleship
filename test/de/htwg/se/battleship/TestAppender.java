/**
 * 
 */
package de.htwg.se.battleship;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LoggingEvent;

/**
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 *
 */
public class TestAppender extends AppenderSkeleton {

    private final List<LoggingEvent> log = new ArrayList<LoggingEvent>();

    @Override
    public void close() {
    }

    @Override
    public boolean requiresLayout() {
        return false;
    }

    @Override
    protected void append(LoggingEvent loggingEvent) {
        log.add(loggingEvent);
    }

    public String getLog() {
        StringBuilder sb = new StringBuilder();

        for (LoggingEvent e : log) {
            sb.append(e.getMessage());
        }

        return sb.toString();
    }

}
