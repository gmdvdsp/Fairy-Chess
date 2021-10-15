package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QueenTest {
    Queen queen = new Queen("white");

    @Test
    public void testPrintPiece() {
        assertTrue(queen.printPiece().equals("Q"));
    }

    @Test
    public void testPrintColourOneCharacterWhite() {
        assertTrue(queen.printColourOneCharacter().equals("w"));
        queen.setColour("black");
        assertTrue(queen.printColourOneCharacter().equals("b"));
    }

    @Test
    public void testGetColour() {
        assertEquals(queen.getColour(), "white");
    }

    @Test
    public void testSetColour() {
        queen.setColour("black");
        assertTrue(queen.getColour().equals("black"));
    }
}