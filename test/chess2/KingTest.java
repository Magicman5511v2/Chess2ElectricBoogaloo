/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package chess2;

import java.util.HashSet;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Magicman5511
 */
public class KingTest {
    Board board;
    public KingTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
         board = new Board();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getMoves method, of class King.
     */
    @Test
    public void testGetMoves() {
        System.out.println("getMoves");
        Position pos = new Position(0,4);
        Piece instance = board.getPieceAt(pos);
        HashSet<Move> expResult = new HashSet();
        HashSet<Move> result = instance.getMoves(board);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class King.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Position pos = new Position(0,4);
        Piece instance = board.getPieceAt(pos);
        String expResult = "K";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
}
