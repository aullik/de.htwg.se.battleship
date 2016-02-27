/**
 *
 */
package de.htwg.se.battleship.aview.OLDtui.impl;

import de.htwg.se.battleship.aview.OLDtui.IMenuEntry;
import de.htwg.se.battleship.aview.OLDtui.InitGameUI;
import de.htwg.se.battleship.aview.OLDtui.OLDTuiFactory;
import de.htwg.se.battleship.aview.OLDtui.menu.TUIMenu;
import de.htwg.se.battleship.controller.old.IInitGameController;
import de.htwg.se.battleship.controller.old.IOLDController;
import de.htwg.se.battleship.controller.old.event.CloseProgamm;
import de.htwg.se.battleship.controller.old.event.InitGame;

import java.util.List;
import java.util.function.Supplier;

/**
 * @author Philipp Daniels <philipp.daniels@gmail.com>
 */
public class MainMenuUi extends UserInterface {

   public static final String MSG_INPUT_NOTE = "%s\n%s\nPlease choice a menu: ";
   public static final String MSG_DEFAULT_MENU = "Sorry bro, but '%s' has no menu entry!\n";
   public static final String NEXT_CLOSEST = "\n Interpreted '%s' as '%s'\n";
   public static final String MSG_POSSIBLE_ENTRIES = "Did you mean: \n";
   public static final String MSG_EXIT = "This is the end my friend!";
   public static final String MENU_FORMAT = "%-20s - %s\n";
   public static final String MENU_HEAD = "Main TUIMenu:\n";

   private final TUIMenu menu;
   private final UserInterface initGameUI;
   private final IInitGameController igc;
   private final ConsoleInput input;
   private String inputMessage = null;
   private UserInterface ui;


   public MainMenuUi(ConsoleInput input, IOLDController controller, InitGameUI initGameUI,
                     IInitGameController igc) {
      controller.addObserver(this);

      this.input = input;
      this.menu = OLDTuiFactory.createTUIMenu();
      this.initGameUI = initGameUI;
      this.ui = this;
      this.igc = igc;
   }

   /**
    * Shutdown TUI process input
    *
    * @param ignore CloseProgamm
    */
   public void update(CloseProgamm ignore) {
      deactivateProcess();
      output(header() + MSG_EXIT);
      input.close();
   }

   /**
    * Start TUI for game initialization
    *
    * @param ignore InitGame
    */
   public void update(InitGame ignore) {
      ui = initGameUI;
      igc.init();
   }

   @Override
   public UserInterface executeInput(String input) {
      executeAction(input);
      return ui;
   }

   private void executeAction(String input) {
      if (input == null || input.isEmpty())
         return;

      final List<IMenuEntry> approx = menu.getApproximateCommands(input);

      if (approx.size() == 0)
         output(header() + String.format(MSG_DEFAULT_MENU, input));
      else if (approx.size() == 1) {
         IMenuEntry e = approx.get(0);
         if (!e.command().equals(input))
            output(String.format(NEXT_CLOSEST, input, e.command()));
         e.action();
      } else
         inputMessage = createMessage(() -> MSG_POSSIBLE_ENTRIES + buildMenuFromList(approx).toString());

   }

   @Override
   public void showText() {
      output(getText());
   }

   private String getText() {
      if (inputMessage == null)
         return createMessage(this::buildMainMenu);


      String msg = inputMessage;
      inputMessage = null;
      return msg;
   }

   private String createMessage(Supplier<String> supp) {
      return String.format(MSG_INPUT_NOTE, header(), supp.get());
   }


   private String buildMainMenu() {
      return MENU_HEAD + buildMenuFromList(menu.getAllCommands()).toString();
   }

   private StringBuilder buildMenuFromList(List<IMenuEntry> list) {
      StringBuilder sb = new StringBuilder();
      for (IMenuEntry entry : list) {
         buildMenuEntry(sb, entry);
      }
      return sb;
   }

   private void buildMenuEntry(StringBuilder sb, IMenuEntry entry) {
      String string = String.format(MENU_FORMAT, entry.command(), entry.description());
      sb.append(string);
   }
}
