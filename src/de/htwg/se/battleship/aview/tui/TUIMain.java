package de.htwg.se.battleship.aview.tui;

import de.htwg.se.battleship.aview.tui.command.Command;
import de.htwg.se.battleship.aview.tui.command.impl.ExitGame;
import de.htwg.se.battleship.aview.tui.command.impl.NewSharedScreenGame;
import de.htwg.se.battleship.aview.tui.view.TUIView;
import de.htwg.se.battleship.aview.tui.view.TuiJob;
import de.htwg.se.battleship.controller.ControllerFactory;
import de.htwg.se.battleship.controller.GameStateControllable;
import de.htwg.se.battleship.controller.GameStateController;
import de.htwg.se.battleship.controller.gamemode.GamemodeControllable;
import de.htwg.se.battleship.util.platform.ThreadPlatform;
import de.htwg.se.battleship.util.singleton.SingletonSupplier;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author aullik on 18.01.2016.
 */
public class TUIMain implements GameStateControllable {

   private final static String NAME = "TUI";

   private final static SingletonSupplier<TUIMain> INSTANCE = new SingletonSupplier<>(TUIMain::new);
   private final TuiJob mainMenu;

   public static TUIMain getInstance() {
      return INSTANCE.get();
   }

   private final GameStateController gsController;
   private final MyThreadPlatform platform;
   private final TUIView tuiView;



   private TUIMain() {
      this.gsController = ControllerFactory.getController();
      gsController.registerControllable(this);
      //gsController.registerControllable(this);
      platform = new MyThreadPlatform();
      tuiView = new TUIView(platform);
      this.mainMenu = createMainMenu();
      platform.run();
      runMainMenu();
   }

   private void resetGame() {       ;
      tuiView.clearJobs();
      runMainMenu();
   }

   private void runMainMenu() {
      tuiView.setJob(mainMenu);
   }

   private TuiJob createMainMenu() {
      final List<Command> list = new LinkedList<>();

      list.add(new NewSharedScreenGame(gsController));
      list.add(new ExitGame(this::exitGame));

      return new TuiJob.CommandOnlyJob(list);
   }


   @Override
   public void startNewGame(final Consumer<GamemodeControllable> consumer) {
      consumer.accept(new GameTUI(platform, tuiView, this::resetGame));
   }

   @Override
   public String getName() {
      return NAME;
   }


   private void exitGame() {
      tuiView.close();
      gsController.unregisterControllable(this);
      gsController.unregisterControllable(this);
      platform.closeInputQueue();
   }

   private class MyThreadPlatform extends ThreadPlatform {

      @Override
      protected synchronized void closeInputQueue() {
         super.closeInputQueue();
      }
   }
}
