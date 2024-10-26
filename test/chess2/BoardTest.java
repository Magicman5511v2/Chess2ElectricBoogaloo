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
public class BoardTest {
    Board board;
    public BoardTest() {
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
     * Test of getPieceAt method, of class Board.
     */
    @Test
    public void testGetPieceAt() {
        System.out.println("getPieceAt (0,4)");
        Position pos = new Position(0,4);
        Piece expResult = new King(true,pos);
        Piece result = board.getPieceAt(pos);
        assertEquals(expResult, result);
    }

    /**
     * Test of findAllValidMoves method, of class Board.
     */
    @Test
    public void testFindAllValidMoves() {
        System.out.println("findAllValidMoves");
        int expResult = 20;
        int result = board.findAllValidMoves().size();
        assertEquals(expResult, result);
    }

    /**
     * Test of makeMove method, of class Board.
     * @throws java.lang.Exception
     */
    @Test(expected = Exception.class)
    public void testMakeMove() throws Exception {
        System.out.println("makeMove");
        Position kingPos = new Position(0,4);
        Position Targetpos = new Position(1,4);
        Piece piece = board.getPieceAt(kingPos);
        Move move = new Move(piece,Targetpos);
        board.makeMove(move);
    }

    /**
     * Test of checkForCheck method, of class Board.
     */
    @Test
    public void testCheckForCheck() {
        System.out.println("checkForCheck");
        boolean expResult = false;
        boolean result = board.checkForCheck();
        assertEquals(expResult, result);
    }

    /**
     * Test of getWhiteTurn method, of class Board.
     */
    @Test
    public void testGetWhiteTurn() {
        System.out.println("getWhiteTurn");
        Board instance = new Board();
        boolean expResult = true;
        boolean result = instance.getWhiteTurn();
        assertEquals(expResult, result);
    }
    
}
