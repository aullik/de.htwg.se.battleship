package de.htwg.se.battleship.aview.tui.impl;

import de.htwg.se.battleship.aview.tui.TuiFactory;
import de.htwg.se.battleship.util.singleton.SingletonSupplier;

import java.io.IOException;


/**
 * Text User Interface is an Observer
 *
 * @author Philipp Daniels <philipp.daniels@gmail.com>
 */

public class TextUI {

   private static final SingletonSupplier<TextUI> INST_SUPP = new SingletonSupplier<>(TextUI::new);

   public static TextUI getInstance() {
      return INST_SUPP.get();
   }

   private UserInterface ui;
   private final ConsoleInput input;


   protected TextUI() {
      this(ConsoleInput.getInstance(),
            TuiFactory.createUserInterface());
   }

   protected TextUI(ConsoleInput input, UserInterface ui) {
      this.ui = ui;
      this.input = input;
      processInput();
   }


   private void processInput() {
      try {
         tryProcessing();
      } catch (IOException e) {
         input.close();
      }
   }

   private void tryProcessing() throws IOException {
      while (ui.getProcess()) {
         ui = executeUserInterface();
      }
      input.close();
   }

   private UserInterface executeUserInterface() throws IOException {
      ui.showText();
      String text = input.getInput();
      return ui.executeInput(text);
   }
}
