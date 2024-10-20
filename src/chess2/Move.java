package chess2;

import java.util.Objects;

/**
 *
 * @author Christopher Payne
 */
public class Move {

    private final Piece piece;
    private final Position pos;

    /**
     *
     * @param piece is the piece that will be moving
     * @param pos is where the piece wants to go
     */
    public Move(Piece piece, Position pos) {
        this.piece = piece;
        this.pos = pos;
    }

    /**
     *
     * @return Position pos
     */
    public Position getPos() {
        return pos;
    }

    /**
     *
     * @return Piece piece
     */
    public Piece getPiece() {
        return piece;
    }

    /**
     * used for getting strings like c2c3
     *
     * @return the position of the piece and target pos is the same format as
     * given by users
     */
    public String getStr() {
        return piece.pos.getStr() + pos.getStr();
    }

    /**
     *are 2 objects equal
     * @param o
     * @return true if they are equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Move move = (Move) o;
        return this.piece.equals(move.getPiece()) && this.pos.equals(move.getPos());
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + Objects.hashCode(this.piece);
        hash = 17 * hash + Objects.hashCode(this.pos);
        return hash;
    }

    @Override
    public String toString() {
        return piece + " at pos " + piece.pos + " to " + pos;
    }
}