package de.htwg.se.battleship.aview.tui.view;

import de.htwg.se.battleship.aview.tui.command.Command;
import de.htwg.se.battleship.util.singleton.SingletonSupplier;
import de.htwg.se.battleship.util.stringmap.StringMatchMap;

import java.util.List;
import java.util.StringJoiner;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.Function;

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
      populateCommandMap(c -> map.put(c.getCommandStripped(), c));
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

   protected abstract void populateCommandMap(Consumer<Command> commandMapFiller);

   public abstract boolean doJob(String input);

   protected abstract String getDescription();

   protected abstract boolean isExecuted();


   public static class CommandOnlyJob extends TuiJob {

      private final List<Command> strippedCommands;

      public CommandOnlyJob(List<Command> strippedCommands) {
         this.strippedCommands = strippedCommands;
      }


      @Override
      protected void populateCommandMap(final Consumer<Command> commandMapPutter) {
         strippedCommands.forEach(commandMapPutter::accept);

      }

      //All of this can stay false or null.
      @Override
      public boolean doJob(final String input) {
         return false;
      }


      @Override
      protected String getDescription() {
         return null;
      }

      @Override
      protected boolean isExecuted() {
         return false;
      }
   }

   public static class ActionOnlyJob extends TuiJob {

      private final Function<String, Boolean> action;
      private final String description;
      private final BooleanSupplier alreadyExecuted;

      public ActionOnlyJob(Function<String, Boolean> action, String description, final BooleanSupplier
            alreadyExecuted) {

         this.action = action;
         this.description = description;
         this.alreadyExecuted = alreadyExecuted;
      }

      @Override
      protected void populateCommandMap(final Consumer<Command> commandMapPutter) { // do nothing
      }

      @Override
      public boolean doJob(final String input) {
         return action.apply(input);
      }

      @Override
      protected String getDescription() {
         return description;
      }

      protected boolean isExecuted() {
         return alreadyExecuted.getAsBoolean();
      }
   }

}
