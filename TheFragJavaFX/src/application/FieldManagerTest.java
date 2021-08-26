package application;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FieldManagerTest implements PublicAccess {
	Combatant comb;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		comb = new Combatant("tester");
		comb.setHitpoints(1);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testGetnewCoords() {
		
		int[] testCoords = {0,0};
		
		String direction = "A";
		
		int[] coordsExpected = {-1, 0};

		int[] newCoords = comb.getNewCoords(testCoords, direction);
		
		assertEquals(coordsExpected[0], newCoords[0]);
		assertEquals(coordsExpected[1], newCoords[1]);
	}
	
	@Test
	void testCreateSymbol() {
		String expected = "tes";
		String toTest = comb.createSymbol(comb.getName());
		
		assertEquals(expected, toTest);
	}
	
	
	@Test
	void testGetHit() {
		boolean toTest = comb.getHit();
		
		assertTrue(toTest);
	}
}
