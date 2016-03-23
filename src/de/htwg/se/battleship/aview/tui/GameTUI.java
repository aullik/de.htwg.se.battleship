package de.htwg.se.battleship.aview.tui;

import de.htwg.se.battleship.aview.tui.command.impl.SurrenderGame;
import de.htwg.se.battleship.aview.tui.view.TUIView;
import de.htwg.se.battleship.controller.gamemode.GamemodeControllable;
import de.htwg.se.battleship.controller.ingame.IngameController;
import de.htwg.se.battleship.controller.initgame.InitGameController;
import de.htwg.se.battleship.model.read.RPlayer;
import de.htwg.se.battleship.util.platform.AlreadyExecutedException;
import de.htwg.se.battleship.util.platform.NotUIThreadException;
import de.htwg.se.battleship.util.platform.SingleUseRunnable;
import de.htwg.se.battleship.util.platform.ThreadPlatform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 * @author aullik on 18.01.2016.
 */
public class GameTUI implements GamemodeControllable {

   private final static String WINNER_MSG = "Congratulations '%s', you won the game!";


   private final ThreadPlatform platform;
   private final TUIView tuiView;
   private final Runnable onFinish;
   private final BooleanProperty initFinished;
   private final SurrenderGame surrenderGame;

   private SingleUseRunnable gameSurrenderRunnable;


   public GameTUI(final ThreadPlatform platform, final TUIView tuiView, final Runnable onFinish) {
      this.platform = platform;
      this.tuiView = tuiView;
      this.onFinish = onFinish;
      this.initFinished = new SimpleBooleanProperty(false);
      this.surrenderGame = new SurrenderGame(this::surrenderGame);
   }


   @Override
   public void setInitGameController(final InitGameController cont) {
      final InitGameTUI initGameTUI = new InitGameTUI(platform, tuiView, surrenderGame);
      cont.registerControllable(initGameTUI);


      // must be anonymous class or it cant remove itself.
      initFinished.addListener(new InvalidationListener() {
         @Override
         public void invalidated(final Observable observable) {
            if (!initFinished.get())
               return;

            initGameTUI.finish();
            initFinished.removeListener(this);
         }
      });

   }

   @Override
   public void setInGameController(final IngameController cont) {
      final InGameTUI inGameTUI = new InGameTUI(tuiView, surrenderGame);
      cont.registerControllable(inGameTUI);
      initFinished.set(true);
   }

   @Override
   public void setSurrenderGameExecutable(final SingleUseRunnable surrenderGame) {
      this.gameSurrenderRunnable = surrenderGame;
   }

   private void surrenderGame() {
      platform.runOnPlatform(this::_surrenderGame);
   }

   private void _surrenderGame() {
      if (gameSurrenderRunnable == null)
         throw new IllegalStateException("Surrender called while not in game");
      try {
         gameSurrenderRunnable.run();
      } catch (AlreadyExecutedException | NotUIThreadException ignore) {
         //doesnt matter if it is already executed, NotUIThreadException cannot happen due to platform execution
      }
   }

   @Override
   public void setWinner(RPlayer player) {
      tuiView.printFormat(WINNER_MSG, player.getName());
      initFinished.set(true);
      onFinish.run();
   }
}
