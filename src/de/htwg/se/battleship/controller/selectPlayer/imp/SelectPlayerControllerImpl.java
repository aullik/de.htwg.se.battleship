package de.htwg.se.battleship.controller.selectPlayer.imp;

import com.sun.javafx.collections.ObservableListWrapper;
import de.htwg.se.battleship.controller.GameStateControllable;
import de.htwg.se.battleship.controller.selectPlayer.SelectPlayerControllable;
import de.htwg.se.battleship.controller.selectPlayer.SelectPlayerController;
import de.htwg.se.battleship.util.controller.impl.ThreadSaveControllerBase;
import de.htwg.se.battleship.util.platform.SingleUseBiConsumer;
import de.htwg.se.battleship.util.platform.ThreadPlatform;
import javafx.beans.property.ReadOnlyListProperty;
import javafx.beans.property.ReadOnlyListWrapper;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;

/**
 */
public class SelectPlayerControllerImpl extends ThreadSaveControllerBase<SelectPlayerControllable> implements SelectPlayerController {


   private final ObservableList<UI> availableUis;
   private final ObservableList<UI> player1UIs;
   private final ObservableList<UI> player2UIs;

   private final ReadOnlyListProperty<UI> availableUisWrapper;
   private final ReadOnlyListProperty<UI> player1UIsWrapper;
   private final ReadOnlyListProperty<UI> player2UIsWrapper;

   private final BooleanSupplier setFinished;
   private final Consumer<GameStateControllable> player1Consumer;
   private final Consumer<GameStateControllable> player2Consumer;
   private boolean finished = false;


   public SelectPlayerControllerImpl(final List<GameStateControllable> allUIs, Consumer<GameStateControllable>
         player1Consumer, Consumer<GameStateControllable> player2Consumer) {
      super(new ThreadPlatform());

      this.player1Consumer = player1Consumer;
      this.player2Consumer = player2Consumer;

      this.availableUis = new ObservableListWrapper<>(new ArrayList<>(allUIs.size()));
      this.player1UIs = new ObservableListWrapper<>(new ArrayList<>(allUIs.size()));
      this.player2UIs = new ObservableListWrapper<>(new ArrayList<>(allUIs.size()));
      allUIs.forEach(c -> availableUis.add(new UIWrapper(c)));


      availableUisWrapper = new ReadOnlyListWrapper<>(availableUis);
      player1UIsWrapper = new ReadOnlyListWrapper<>(player1UIs);
      player2UIsWrapper = new ReadOnlyListWrapper<>(player2UIs);
      setFinished = this::checkFinished;

      platform.runLater(this::runLoop);
      runPlatform();
   }

   @Override
   public synchronized void registerControllable(final SelectPlayerControllable cont) {
      cont.initialize(this.setFinished, this.availableUisWrapper, this.player1UIsWrapper, this.player2UIsWrapper);
      super.registerControllable(cont);
   }

   private void runLoop(){
      executeConsumerMethod(c -> c.setUI(new SingleUseBiConsumer<>(this::accept, this.platform)));
   }

   private void accept(PlayerNumber pn, UI ui) {
      if (pn != null && ui != null)
         checkAddUI(pn, ui);

      if (!finished)
         runLoop();
   }

   private void checkAddUI(final PlayerNumber pn, final UI ui) {
      if (!availableUis.remove(ui))
         return;

      if (PlayerNumber.One.equals(pn))
         player1UIs.add(ui);
      else
         player2UIs.add(ui);

   }


   private boolean checkFinished() {
      if (platform.isPlatformThread())
         return false;

      class Future {

         private volatile boolean finished;
         private final CountDownLatch latch = new CountDownLatch(1);

         void run() {
            this.finished = _checkFinished();
            latch.countDown();
         }
      }

      Future f = new Future();
      platform.runLater(f::run);

      try {
         f.latch.await();
      } catch (InterruptedException e) {
         return false;
      }
      return f.finished;
   }

   private boolean _checkFinished() {
      if (finished)
         return true;

      if (player1UIs.isEmpty() || player2UIs.isEmpty())
         return false;

      finished = true;

      player1UIs.forEach(ui -> player1Consumer.accept(((UIWrapper) ui).controller));
      player2UIs.forEach(ui -> player2Consumer.accept(((UIWrapper) ui).controller));

      return true;
   }


   private static class UIWrapper implements UI {


      private final GameStateControllable controller;

      UIWrapper(final GameStateControllable c) {
         controller = c;
      }

      @Override
      public String getName() {
         return controller.getName();
      }
   }

}
