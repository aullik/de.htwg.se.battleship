package de.htwg.se.battleship;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import org.junit.Test;

import de.htwg.se.battleship.view.tui.MainMenu;

public class BattleshipTest {

    @Test
    public void test() {
        InputStream oldIn = System.in;
        PrintStream oldOut = System.out;
        String s =  MainMenu.CLOSE + System.lineSeparator();

        System.setOut(new PrintStream(new ByteArrayOutputStream()));
        try {
            System.setIn(new ByteArrayInputStream( s.getBytes("UTF-8")));
        } catch (UnsupportedEncodingException e) {
            System.setIn(oldIn);
            fail(e.getMessage());
        }

        Battleship.main(new String[0]);

        System.setIn(oldIn);
        System.setOut(oldOut);
    }

}
