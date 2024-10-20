package chess2;

import java.util.HashSet;

/**
 *
 * @author Christopher Payne
 */
public class Bishop extends Piece {

    /**
     * this is the constructor for Bishops
     *
     * @param isWhite
     * @param pos
     */
    public Bishop(boolean isWhite, Position pos) {
        super(isWhite, pos);
        this.value = 3;
    }

    @Override
    public HashSet<Move> getMoves(Board board) {
        HashSet<Move> moves = new HashSet<>();

        // Check all diagonals (4 directions)
        for (int i = 1; this.pos.getR() + i < 8 && this.pos.getC() + i < 8; i++) {
            if (!isValidMove(board, new Position(this.pos.getR() + i, this.pos.getC() + i))) {
                break;
            }
            moves.add(new Move(this, new Position(this.pos.getR() + i, this.pos.getC() + i)));
        }
        for (int i = 1; this.pos.getR() + i < 8 && this.pos.getC() - i >= 0; i++) {
            if (!isValidMove(board, new Position(this.pos.getR() + i, this.pos.getC() - i))) {
                break;
            }
            moves.add(new Move(this, new Position(this.pos.getR() + i, this.pos.getC() - i)));
        }
        for (int i = 1; this.pos.getR() - i >= 0 && this.pos.getC() + i < 8; i++) {
            if (!isValidMove(board, new Position(this.pos.getR() - i, this.pos.getC() + i))) {
                break;
            }
            moves.add(new Move(this, new Position(this.pos.getR() - i, this.pos.getC() + i)));
        }
        for (int i = 1; this.pos.getR() - i >= 0 && this.pos.getC() - i >= 0; i++) {
            if (!isValidMove(board, new Position(this.pos.getR() - i, this.pos.getC() - i))) {
                break;
            }
            moves.add(new Move(this, new Position(this.pos.getR() - i, this.pos.getC() - i)));
        }

        return moves;
    }

    /**
     *
     * @return the string representation of a Bishop
     */
    @Override
    public String toString() {
        if (this.isWhite) {//set foreground colour
            return "B";
        } else {
            return "b";
        }
    }
}
