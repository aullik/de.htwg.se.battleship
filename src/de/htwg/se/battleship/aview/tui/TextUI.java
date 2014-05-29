package de.htwg.se.battleship.aview.tui;

import java.io.IOException;

import com.google.inject.Inject;
import com.google.inject.Singleton;


/**
 * Text User Interface is an Observer
 * 
 * @author Philipp Daniels <philipp.daniels@gmail.com>
 */
@Singleton
public class TextUI  {

    private UserInterface ui;

    /**
     * Default constructor.
     */
    public TextUI() {}

    /**
     * @param controller controller to observe
     */
    @Inject
    public TextUI(Input input, UserInterface ui) {
        this.ui = ui;
        processInput(input);
    }

    private void processInput(Input input) {
        try {
            tryProcessing(input);
        } catch (IOException e) {
            input.close();
        }
    }

    private void tryProcessing(Input input) throws IOException {
        do {
            ui = executeUserInterface(input);
        } while (ui.getProcess());
        input.close();
    }

    private UserInterface executeUserInterface(Input input) throws IOException {
        ui.showText();
        String text = input.get();
        return ui.executeInput(text);
    }
}
