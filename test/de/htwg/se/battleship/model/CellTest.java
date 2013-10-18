/**
 * 
 */
package de.htwg.se.battleship.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * @author phdaniel
 *
 */
public class CellTest {

	private Cell c1;
	private Cell c2;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		c1 = new Cell(1, 2);
		c2 = new Cell(12, 8);
	}

	@Test
	public void testGetX() {
		assertEquals(1, c1.getX());
		assertEquals(12, c2.getX());
	}

	@Test
	public void testGetY() {
		assertEquals(2, c1.getY());
		assertEquals(8, c2.getY());
	}

}
