package de.htwg.se.battleship.controller.selectPlayer;

import de.htwg.se.battleship.controller.selectPlayer.SelectPlayerController.PlayerNumber;
import de.htwg.se.battleship.controller.selectPlayer.SelectPlayerController.UI;
import de.htwg.se.battleship.util.controller.Controllable;
import de.htwg.se.battleship.util.platform.SingleUseBiConsumer;
import javafx.beans.property.ReadOnlyListProperty;

import java.util.function.BooleanSupplier;

/**
 */
public interface SelectPlayerControllable extends Controllable {

   void initialize(final Runnable setFinished, BooleanSupplier checkFinished, ReadOnlyListProperty<UI> availableUIs, ReadOnlyListProperty<UI>
         player1UIs, ReadOnlyListProperty<UI> player2UIs);

   void setUI(SingleUseBiConsumer<PlayerNumber, UI> uiSetter);

}
