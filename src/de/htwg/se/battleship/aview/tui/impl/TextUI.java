package de.htwg.se.battleship.aview.tui.impl;

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
    private ConsoleInput input;

    /**
     * Default constructor.
     */
    public TextUI() {}

    /**
     * @param controller controller to observe
     */
    @Inject
    public TextUI(ConsoleInput input, UserInterface ui) {
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
        String text = input.get();
        return ui.executeInput(text);
    }
}
