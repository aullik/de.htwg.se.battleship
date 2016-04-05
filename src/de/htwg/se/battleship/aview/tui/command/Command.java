package de.htwg.se.battleship.aview.tui.command;

/**
 * @author aullik on 13.01.2016.
 */
public interface Command {

   String COMMAND_PREFIX = "-";

   String getCommand();

   String getCommandStripped();

   /**
    * TUIMenu entry createDescription
    *
    * @return
    */
   String getDescription();

   void doAction();

}
