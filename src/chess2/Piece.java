package chess2;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;

/**
 *
 * @author Christopher Payne
 */
public abstract class Piece implements Serializable {

    protected boolean hasMoved = false;
    protected boolean isWhite;
    protected Position pos;
    protected int value = 0;

    public Piece(boolean isWhite, Position pos) {
        this.isWhite = isWhite;
        this.pos = pos;
    }

    public Piece(Piece p) {
        this.isWhite = p.isWhite;
        this.pos = p.pos;
        this.hasMoved = true;
    }

    /**
     * this will return HashSet of moves available to the piece
     *
     * @param board this i the board the piece is on
     * @return HashSet of Moves or null if no moves are available
     */
    public abstract HashSet<Move> getMoves(Board board);

    public Boolean isValidMove(Board board, Position target) {

        try {
            if (!board.getIsClone()) {
                //chatGPT created this deep copy for me ↓↓↓↓
                byte[] boardBytes = SerializationUtils.serialize(board);
                Board clone = (Board) SerializationUtils.deserialize(boardBytes);
                //chatGPT created this deep copy for me ↑↑↑
                clone.setIsClone();
                boolean check = clone.checkForCheck();
                if (check) {
                    return clone.checkForCheck();
                }
            }

        } catch (IOException | ClassNotFoundException e) {
            System.out.print(e.getMessage());

        }

        // Check to make sure the target is on the board
        if (target.getR() < 0 || target.getR() > 7 || target.getC() < 0 || target.getC() > 7) {
            return false;
        }

        Piece piece = board.getPieceAt(target);// does not acount for Check Mate, but it needs to go here
        // if the target is empty it valid
        if (piece == null) {
            return true;
        }
        return piece.isWhite != this.isWhite;

    }

    public void makeMove(Position pos) {
        this.hasMoved = true;
        this.pos = pos;
    }

    public Position getPos() {
        return this.pos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true; // Check for reference equality first
        }
        if (o == null || getClass() != o.getClass()) {
            return false; // Ensure the object is not null and is of the same class
        }
        Piece piece = (Piece) o;
        return this.pos.equals(piece.getPos()); // Use equals() to compare positions
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + (this.hasMoved ? 1 : 0);
        hash = 89 * hash + (this.isWhite ? 1 : 0);
        hash = 89 * hash + Objects.hashCode(this.pos);
        return hash;
    }

    public boolean isWhite() {
        return isWhite;
    }
}
