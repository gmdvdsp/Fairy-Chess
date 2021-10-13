package model;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

public class KingTest {
    King king = new King("white");

    @Test
    public void testPrintPiece() {
        assertTrue(king.printPiece().equals("K"));
    }
}
