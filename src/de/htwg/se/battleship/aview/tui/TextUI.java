package de.htwg.se.battleship.aview.tui;

import java.util.Scanner;

import com.google.inject.Inject;
import com.google.inject.Singleton;


/**
 * Text User Interface is an Observer
 * 
 * @author aullik
 */
@Singleton
public class TextUI  {

    private UserInterface ui;

    /**
     * @param controller controller to observe
     */
    @Inject
    public TextUI(IScannerFactory sf, MainMenuUI ui) {
        this.ui = ui;
        processInput(sf.getScanner());
    }

    private void processInput(Scanner scanner) {
        boolean process = true;

        while (process) {
            ui.showText();
            String input = scanner.nextLine();
            process = ui.executeInput(input);
            ui = ui.getUI();
        }
    }

}
