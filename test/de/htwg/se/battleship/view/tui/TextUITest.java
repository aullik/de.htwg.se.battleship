package de.htwg.se.battleship.view.tui;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import org.junit.Before;
import org.junit.Test;

public class TextUITest {

    private TextUI t;
    private InputStream in;
    private OutputStream out;

    class TestTextUI extends TextUI {

    }

    @Before
    public void setUp() throws Exception {
        t = new TestTextUI();
        in = new ByteArrayInputStream("".getBytes("UTF-8"));
        out = new ByteArrayOutputStream();
        t.initInOut(in, out);
    }


    @Test
    public void testOutputError() throws UnsupportedEncodingException {

        String s = "test";
        assertTrue(out.toString().isEmpty());
        t.outputError(s);
        assertEquals(TextUI.ERROR + s + System.lineSeparator(), out.toString());
    }

    @Test
    public void testnextInt() throws UnsupportedEncodingException {
        Integer i = 1337;
        String s = i + System.lineSeparator();
        t.initInOut(new ByteArrayInputStream(s.getBytes("UTF-8")), out);

        assertTrue(out.toString().isEmpty());
        assertEquals(i, t.nextInt());
        assertTrue(out.toString().isEmpty());
    }

    @Test
    public void testnextIntWithString() throws UnsupportedEncodingException {
        String s = "Hallo Welt!" + System.lineSeparator();
        t.initInOut(new ByteArrayInputStream(s.getBytes("UTF-8")), out);

        assertTrue(out.toString().isEmpty());
        assertNull(t.nextInt());
        assertTrue(out.toString().isEmpty());
    }

    @Test
    public void testOutputHeader() {
        assertTrue(out.toString().isEmpty());
        t.outputHeader();
        assertFalse(out.toString().isEmpty());
    }

    @Test
    public void testOutputln() {
        String s = "test";
        assertTrue(out.toString().isEmpty());
        t.outputln(s);
        assertEquals(s + System.lineSeparator(), out.toString());
    }

    @Test
    public void testOutput() {
        String s = "test";
        assertTrue(out.toString().isEmpty());
        t.output(s);
        assertEquals(s, out.toString());
    }

    @Test(expected=IllegalArgumentException.class)
    public void testCheckString() {
        assertTrue(out.toString().isEmpty());
        t.output(null);
    }


}
