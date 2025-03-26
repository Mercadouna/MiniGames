package Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.Player;

import model.Player;

class testPlayer {
	private Player player;

	@BeforeEach
	void setUp() throws Exception {
		player= new Player();
	}

	@AfterEach
	void tearDown() throws Exception {
		player=null;
	}

	@Test
	void testConstructorVacio() {
		assertEquals("", player.getName());
		assertEquals("", player.getPassword());
		assertEquals(0, player.getPoints());
	}
	@Test
	void testConstructorParametro() {
		Player playerP= new Player("Mikel", "1234", 20);
		assertEquals("Mikel", playerP.getName());
		assertEquals("1234", playerP.getPassword());
		assertEquals(20, playerP.getPoints());
		
	}
	@Test
	void testGetters() {
		player = new Player("bob", "duro", 30);
		assertEquals("bob", player.getName());
		assertEquals("duro", player.getPassword());
		assertEquals(30, player.getPoints());
		
	}
	@Test
	void testSetters() {
		player.setName("Bobi");
		player.setPassword("ordenador");
		player.setPoints(90);
		
		assertEquals("Bobi", player.getName());
		assertEquals("ordenador", player.getPassword());
		assertEquals(90, player.getPoints());
		
	}

}
