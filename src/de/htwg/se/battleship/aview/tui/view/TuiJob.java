package de.htwg.se.battleship.aview.tui.view;

import de.htwg.se.battleship.aview.tui.command.Command;
import de.htwg.se.battleship.util.singleton.SingletonSupplier;
import de.htwg.se.battleship.util.stringmap.StringMatchMap;

import java.util.List;
import java.util.StringJoiner;
import java.util.function.Consumer;

/**
 * @author aullik on 28.02.2016.
 */
public abstract class TuiJob {

   private static final String MENU_FORMAT = "%-20s - %s";
   private static final String MSG_INPUT_NOTE = "Please choose a menu: ";
   private static final String MSG_POSSIBLE_ENTRIES = "\nDid you mean: \n";
   private static final String NEXT_CLOSEST = "\n Interpreted '%s' as '%s'\n";
   private static final String MSG_INVALID_INPUT = "'%s' is no valid input!\n";


   private final SingletonSupplier<String> information;
   private final SingletonSupplier<StringMatchMap<Command>> commandMap;


   public TuiJob() {
      this.commandMap = new SingletonSupplier<>(this::createCommandMap);
      this.information = new SingletonSupplier<>(this::createInformation);
   }

   private StringMatchMap<Command> createCommandMap() {
      final StringMatchMap<Command> map = new StringMatchMap<>();
      populateCommandMap(map);
      return map;
   }


   public void checkCommand(final String inp, Consumer<String> errorHandling) {
      String input = inp;
      while (input.startsWith(Command.COMMAND_PREFIX))
         input = input.substring(1);

      final List<Command> closest = commandMap.get().getApprox(input);
      if (closest.isEmpty())
         errorHandling.accept(String.format(MSG_INVALID_INPUT, input));
      else if (closest.size() == 1)
         executeClosest(input, closest.get(0), errorHandling);
      else
         errorHandling.accept(printClosestCommands(closest));
   }

   private void executeClosest(String input, Command c, Consumer<String> errorHandling) {
      if (!input.equals(c.getCommand()))
         errorHandling.accept(String.format(NEXT_CLOSEST, input, c.getCommandStripped()));

      c.doAction();
   }

   private String printClosestCommands(final List<Command> closest) {
      if (closest.isEmpty())
         return buildAllCommandsDesc();
      else {
         return MSG_POSSIBLE_ENTRIES + buildCommandsDesc(closest);
      }
   }

   private String buildAllCommandsDesc() {
      final List<Command> all = commandMap.get().getApprox("");
      if (!all.isEmpty())
         return buildCommandsDesc(all);
      else
         return "";
   }

   private String buildCommandsDesc(final List<Command> commandList) {
      StringJoiner sj = new StringJoiner("\n");
      commandList.forEach(c -> sj.add(commandToMenuEntry(c)));
      sj.add(MSG_INPUT_NOTE);
      return sj.toString();
   }

   private String commandToMenuEntry(Command c) {
      return String.format(MENU_FORMAT, c.getCommand(), c.getDescription());
   }

   public String getInformation() {
      return information.get();
   }

   private String createInformation() {
      String desc = getDescription();
      boolean descValid = desc != null && !desc.isEmpty();
      StringBuilder sb = new StringBuilder();
      if (commandMap.get().size() > 0) {
         sb.append(buildAllCommandsDesc());
         if (descValid)
            sb.append('\n');
      }

      if (descValid)
         sb.append(desc);

      return sb.toString();
   }

   protected abstract void populateCommandMap(StringMatchMap<Command> commandMap);

   public abstract boolean doJob(String input);

   protected abstract String getDescription();

   protected abstract boolean isExecuted();
}
