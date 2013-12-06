package de.htwg.se.battleship.aview.tui;

import org.apache.log4j.Logger;

import de.htwg.se.battleship.controller.IntController;
import de.htwg.se.battleship.util.observer.Event;
import de.htwg.se.battleship.util.observer.IObserver;

/**
 * Text User Interface is an Observer
 * 
 * @author aullik
 */
public class TextUI implements IObserver {

    private final String        newLine    = System.getProperty("line.separator");
    private final Logger        logger     = Logger.getLogger("de.htwg.se.battleship.aview.tui");
    private final IntController mycontroller;

    public TextUI(IntController controller) {
        this.mycontroller = controller;
        mycontroller.addObserver(this);
        mycontroller.updateNotify();

    }

    /**
     * switches thought input to get commands
     * 
     * @param in input from console
     */
//    public boolean processInput(final String in) {
//    	boolean toReturn = true;
//        switch (in) {
//        case "close":
//            logger.info(newLine + "terminated");
//            toReturn = false;
//            break;
//        case "start":
//            mycontroller.newgame();
//            mycontroller.newgame(10,"hans","berta");
//            break;
//        default:
//            System.out.println("Unknown Input: " + in);
//
//        }
//        return toReturn;
//
//    }

    @Override
    public void update(Event e) {
    	if(e==null){
    		System.out.println("error");
    	}else{
    		logger.info(newLine + e.getMessage());	
    	}
    	
    }

    /**
     * prints status to stdout and logger
     */
//    private void printTUI() {
//    	System.out.println(HELLOWORLD);
//    	logger.info(newLine + HELLOWORLD);
//        
//    }

    
	}
