package Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Trophy;

class testTrophy {
	private Trophy trophy;
	@BeforeEach
	void setUp() throws Exception {
		trophy = new Trophy();
	}

	@AfterEach
	void tearDown() throws Exception {
		trophy = null;
	}

	@Test
	void testConstructorVacio() {
		assertEquals("", trophy.getNombre());
		assertEquals(0, trophy.getPrice());
	}
	@Test
	void testConstructorParameters() {
		Trophy trophyP = new Trophy("Copa Piston", 300);
		assertEquals("Copa Piston",trophyP.getNombre());
		assertEquals(300, trophyP.getPrice());
		
	}
	@Test
	void testGetters() {
		trophy= new Trophy("Copa", 200);
		assertEquals("Copa", trophy.getNombre());
		assertEquals(200, trophy.getPrice());
	}
	@Test
	void testSetters() {
		trophy.setNombre("Copa del Rey");
		trophy.setPrice(250);
		
		assertEquals("Copa del Rey", trophy.getNombre());
		assertEquals(250, trophy.getPrice());
	}

}
