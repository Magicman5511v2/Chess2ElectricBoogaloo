package chess2;

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
    protected String imagePath;

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

    public void makeMove(Position pos) {
        this.hasMoved = true;
        this.pos = pos;
    }

    public Position getPos() {
        return this.pos;
    }

    public String getImagePath() {
        return imagePath;
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

    /**
     * @return the value
     */
    public int getValue() {
        return value;
    }
}
