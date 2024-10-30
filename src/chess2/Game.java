/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chess2;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.HashSet;

/**
 *
 * @author Magicman5511
 */
public class Game {

    private Board board;
    private Piece selectedPiece;
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);//chat GPT recomendation for event 

    public Game() {
        board = new Board();
    }

    // Resets the game
    public void newGame() {
        setBoard(new Board());
        selectedPiece = null;
    }

    // Selects a piece and highlights possible moves
    public HashSet<Move> selectPiece(Position pos) {
        Piece piece = getBoard().getPieceAt(pos);
        if (piece != null && piece.isWhite == getBoard().getWhiteTurn()) {
            selectedPiece = piece;
            return selectedPiece.getMoves(getBoard());
        }
        return null;
    }

    // Executes a move if it's valid
    public boolean makeMove(Position target) {
        if (selectedPiece == null) {
            return false;
        }

        Move move = new Move(selectedPiece, target);
        HashSet<Move> validMoves = selectedPiece.getMoves(getBoard());
        if (validMoves.contains(move)) {
            try {
                getBoard().makeMove(move);
                selectedPiece = null; // Clear selection after the move
                return true;
            } catch (Exception ex) {
            }
        }
        return false;
    }

    public boolean makeCpu() {
        HashSet<Move> moves = getBoard().findAllValidMoves();

        if (moves.isEmpty()) {// game over you win
            return false;
        }
        Move bestMove = moves.iterator().next();
        for (Move move : moves) {
            if (move.getValue() > bestMove.getValue()) {
                bestMove = move;
            }
        }
        try {
            getBoard().makeMove(bestMove);
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
        return getBoard().getWhiteTurn() ? "Whites Turn" : "Blacks Turn";
    }

    // Gets the piece at a specific position for display purposes
    public Piece getPieceAt(Position pos) {
        return getBoard().getPieceAt(pos);
    }

    // Provides access to the current game board (e.g., for saving/loading)
    public Board getBoard() {
        return board;
    }

    /**
     * @param board the board to set
     */
    public void setBoard(Board board) {
        Board oldBoard = this.board;//chat GPT
        this.board = board;
        pcs.firePropertyChange("board", oldBoard, this.board);//chat GPT
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {//chat GPT
        pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {//chat GPT
        pcs.removePropertyChangeListener(listener);
    }
}
