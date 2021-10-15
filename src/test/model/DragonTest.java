package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DragonTest {
    Dragon dragon = new Dragon("white");

    @Test
    public void testPrintPiece() {
        assertTrue(dragon.printPiece().equals("D"));
    }

    @Test
    public void testPrintColourOneCharacterWhite() {
        assertTrue(dragon.printColourOneCharacter().equals("w"));
        dragon.setColour("black");
        assertTrue(dragon.printColourOneCharacter().equals("b"));
    }

    @Test
    public void testGetColour() {
        assertEquals(dragon.getColour(), "white");
    }

    @Test
    public void testSetColour() {
        dragon.setColour("black");
        assertTrue(dragon.getColour().equals("black"));
    }
}