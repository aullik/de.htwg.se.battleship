package de.htwg.se.battleship.aview.tui.command;

/**
 * @author aullik on 13.01.2016.
 */
public abstract class CommandBase implements Command {

   private final String commandStripped;
   private final String description;


   public CommandBase(String commandStripped, String description) {
      this.commandStripped = commandStripped;
      this.description = description;
   }

   @Override
   public String getCommand() {
      return COMMAND_PREFIX + commandStripped;
   }

   @Override
   public String getCommandStripped() {
      return commandStripped;
   }

   @Override
   public String getDescription() {
      return description;
   }

}
