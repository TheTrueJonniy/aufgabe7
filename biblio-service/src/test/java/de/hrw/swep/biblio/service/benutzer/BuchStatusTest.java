package de.hrw.swep.biblio.service.benutzer;

import static org.junit.Assert.*;

import org.junit.Test;

import de.hrw.swep.biblio.service.IllegalStateTransition;
import de.hrw.swep.biblio.service.gegenstaende.Ausgeliehen;
import de.hrw.swep.biblio.service.gegenstaende.Buch;
import de.hrw.swep.biblio.service.gegenstaende.Frei;
import de.hrw.swep.biblio.service.gegenstaende.Verloren;

public class BuchStatusTest {

	@Test
	public void testAusgeliehen() {
		Benutzer x = new Benutzer(1, "TestUser");
		Buch b = new Buch("Test Titel", "Test  Author");
		b.setState(new Frei(b));
		
		b.ausleihen(x);
		assertEquals("de.hrw.swep.biblio.service.gegenstaende.Ausgeliehen", b.getState().getClass().getName());
	}
	
	@Test
	public void testFrei() {
		Benutzer x = new Benutzer(1, "TestUser");
		Buch b = new Buch("Test Titel", "Test  Author");
		b.setState(new Ausgeliehen(b));
		
		b.zurueckgeben();
		assertEquals("de.hrw.swep.biblio.service.gegenstaende.Frei", b.getState().getClass().getName());
	}
	
	@Test
	public void testverloren() {
		Benutzer x = new Benutzer(1, "TestUser");
		Buch b = new Buch("Test Titel", "Test  Author");
		b.setState(new Ausgeliehen(b));
		
		b.verloren();
		assertEquals("de.hrw.swep.biblio.service.gegenstaende.Verloren", b.getState().getClass().getName());
	}
	
	@Test
	public void testwiederfinden() {
		Benutzer x = new Benutzer(1, "TestUser");
		Buch b = new Buch("Test Titel", "Test  Author");
		b.setState(new Verloren(b));
		
		b.zurueckgeben();
		assertEquals("de.hrw.swep.biblio.service.gegenstaende.Frei", b.getState().getClass().getName());
	}

	@Test(expected = IllegalStateTransition.class)
	public void testIllegalTransitionNormal() {
		Benutzer x = new Benutzer(1, "TestUser");
		Buch b = new Buch("Test Titel", "Test  Author");
		b.setState(new Verloren(b));
		
		b.ausleihen(x);
	}
	
}
