package Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Games;

class testGame {
	private Games games;
	@BeforeEach
	void setUp() throws Exception {
		games= new Games();
	}

	@AfterEach
	void tearDown() throws Exception {
		games=null;
	}

	@Test
	void testConstructorVacio() {
		assertEquals("", games.getNombre());
	}
	@Test
	void testConstructorParameters() {
		Games gamesP = new Games("Duro");
		assertEquals("Duro", gamesP.getNombre());
	}
	@Test
	void testGetters() {
		games = new Games("Hard");
		assertEquals("Hard", games.getNombre());
		
	}
	@Test
	void testSetters() {
		games.setNombre("Xabi");
		assertEquals("Xabi", games.getNombre());
		
	}

}
