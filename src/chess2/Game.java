/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chess2;

import java.util.HashSet;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Magicman5511
 */
public class Game {

    private Board board;
    private Piece selectedPiece;

    public Game() {
        board = new Board();
    }

    // Resets the game
    public void newGame() {
        board = new Board();
        selectedPiece = null;
    }

    // Selects a piece and highlights possible moves
    public HashSet<Move> selectPiece(Position pos) {
        Piece piece = board.getPieceAt(pos);
        if (piece != null && piece.isWhite == board.getWhiteTurn()) {
            selectedPiece = piece;
            return selectedPiece.getMoves(board);
        }
        return null;
    }

    // Executes a move if it's valid
    public boolean makeMove(Position target) {
        if (selectedPiece == null) {
            return false;
        }

        Move move = new Move(selectedPiece, target);
        HashSet<Move> validMoves = selectedPiece.getMoves(board);
        if (validMoves.contains(move)) {
            try {
                board.makeMove(move);
                selectedPiece = null; // Clear selection after the move
                return true;
            } catch (Exception ex) {
            }
        }
        return false;
    }

    public boolean makeCpu() {
        HashSet<Move> moves = board.findAllValidMoves();
        
        if (moves.isEmpty()){// game over you win
            return false;
        }
        Move bestMove = moves.iterator().next();
        for (Move move : moves) {
            if (move.getValue() > bestMove.getValue()) {
                bestMove = move;
            }
        }
        try {
            board.makeMove(bestMove);
        } catch (Exception ex) {
        }
        selectedPiece = null;
        return true; 
    }
    
    public boolean moreMoves() {
        return !board.findAllValidMoves().isEmpty();
    }

    // Deselects the current piece
    public void deselectPiece() {
        selectedPiece = null;
    }

    // Checks whose turn it is
    public String getTurn() {
        return board.getWhiteTurn() ? "Whites Turn" : "Blacks Turn";
    }

    // Gets the piece at a specific position for display purposes
    public Piece getPieceAt(Position pos) {
        return board.getPieceAt(pos);
    }

    // Provides access to the current game board (e.g., for saving/loading)
    public Board getBoard() {
        return board;
    }
}
