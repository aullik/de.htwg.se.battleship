package de.htwg.se.battleship.aview.tui;

import de.htwg.se.battleship.aview.tui.command.Command;
import de.htwg.se.battleship.aview.tui.command.impl.FinishChooseUIsCoomand;
import de.htwg.se.battleship.aview.tui.view.TUIView;
import de.htwg.se.battleship.aview.tui.view.TuiJob;
import de.htwg.se.battleship.controller.selectPlayer.SelectPlayerControllable;
import de.htwg.se.battleship.controller.selectPlayer.SelectPlayerController.PlayerNumber;
import de.htwg.se.battleship.controller.selectPlayer.SelectPlayerController.UI;
import de.htwg.se.battleship.util.platform.AlreadyExecutedException;
import de.htwg.se.battleship.util.platform.NotUIThreadException;
import de.htwg.se.battleship.util.platform.SingleUseBiConsumer;
import javafx.beans.property.ReadOnlyListProperty;

import java.util.StringJoiner;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;

/**
 */
public class SelectUIsTUI implements SelectPlayerControllable {

   private final TUIView tuiView;

   private Runnable setFinished;
   private BooleanSupplier checkFinished;
   private ReadOnlyListProperty<UI> availableUIs;
   private ReadOnlyListProperty<UI> player1UIs;
   private ReadOnlyListProperty<UI> player2UIs;

   public SelectUIsTUI(final TUIView tuiView) {
      this.tuiView = tuiView;
   }

   @Override
   public void initialize(final Runnable setFinished, final BooleanSupplier checkFinished, final
   ReadOnlyListProperty<UI> availableUIs,
                          final ReadOnlyListProperty<UI> player1UIs, final ReadOnlyListProperty<UI> player2UIs) {

      this.setFinished = setFinished;
      this.checkFinished = checkFinished;
      this.availableUIs = availableUIs;
      this.player1UIs = player1UIs;
      this.player2UIs = player2UIs;

   }

   private PlayerNumber getPlayerNumberFromInput(final String inp) {
      if (inp.equals("1"))
         return PlayerNumber.One;
      if (inp.equals("2"))
         return PlayerNumber.Two;

      return null;
   }

   private UI getUIFromName(final String name) {
      for (UI ui : availableUIs)
         if (ui.getName().equals(name))
            return ui;

      return null;
   }


   @Override
   public void setUI(final SingleUseBiConsumer<PlayerNumber, UI> uiSetter) {
      tuiView.setJob(new Job(uiSetter));
   }

   class Job extends TuiJob {

      private static final String DESC = "available UIs: %s\nUIs set for Player 1: %s\n" +
            "UIs set for Player 2: %s\n\n" +
            "Please set an UI for a Player (Example: 1, TUI):";

      private final SingleUseBiConsumer<PlayerNumber, UI> uiSetter;

      public Job(final SingleUseBiConsumer<PlayerNumber, UI> uiSetter) {
         this.uiSetter = uiSetter;
      }

      @Override
      protected void populateCommandMap(final Consumer<Command> commandMapFiller) {
         commandMapFiller.accept(new FinishChooseUIsCoomand(this::onFinish));
      }

      private void onFinish() {
         setFinished.run();
      }

      @Override
      public boolean doJob(final String input) {
         boolean ret = _doJob(input);
         if (!ret && !isExecuted())
            tuiView.setJob(this);
         return ret;
      }

      private boolean _doJob(final String input) {
         if (!input.matches("\\d[ ]*,[ ]*\\w+"))
            return false;
         final String[] split = input.split(",", 2);
         final PlayerNumber pNo = getPlayerNumberFromInput(split[0].trim());
         final UI ui = getUIFromName(split[1].trim());
         if (ui == null || pNo == null)
            return false;

         try {
            uiSetter.accept(pNo, ui);
         } catch (AlreadyExecutedException | NotUIThreadException e) {
            return false;
         }
         return true;
      }

      @Override
      public String getInformation() {
         return super.createInformation();
      }

      @Override
      protected String createDescription() {
         final StringJoiner sjAvailables = new StringJoiner(", ");
         availableUIs.forEach(ui -> sjAvailables.add(ui.getName()));

         final StringJoiner sjP1 = new StringJoiner(", ");
         player1UIs.forEach(ui -> sjP1.add(ui.getName()));

         final StringJoiner sjP2 = new StringJoiner(", ");
         player2UIs.forEach(ui -> sjP2.add(ui.getName()));

         return String.format(DESC, sjAvailables.toString(), sjP1.toString(), sjP2.toString());
      }

      @Override
      protected boolean isExecuted() {
         return uiSetter.isExecuted() || checkFinished.getAsBoolean();
      }
   }
}
