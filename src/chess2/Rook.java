package chess2;

import java.util.HashSet;

/**
 *
 * @author Christopher Payne
 */
public class Rook extends Piece {

    /**
     * this is the constructor for Rooks
     *
     * @param isWhite
     * @param pos
     */
    public Rook(boolean isWhite, Position pos) {
        super(isWhite, pos);
        this.value = 5;
    }

    @Override
    public HashSet<Move> getMoves(Board board) {
        HashSet<Move> moves = new HashSet<>();

        for (int i = this.pos.getR() + 1; i < 8; i++) {
            if (!isValidMove(board, new Position(i, this.pos.getC()))) {
                break;
            }
            moves.add(new Move(this, new Position(i, this.pos.getC())));
        }
        for (int i = this.pos.getR() - 1; i >= 0; i--) {
            if (!isValidMove(board, new Position(i, this.pos.getC()))) {
                break;
            }
            moves.add(new Move(this, new Position(i, this.pos.getC())));
        }

        // Check all squares in the horizontal direction (left and right)
        for (int j = this.pos.getC() + 1; j < 8; j++) {
            if (!isValidMove(board, new Position(this.pos.getR(), j))) {
                break;
            }
            moves.add(new Move(this, new Position(this.pos.getR(), j)));
        }
        for (int j = this.pos.getC() - 1; j >= 0; j--) {
            if (!isValidMove(board, new Position(this.pos.getR(), j))) {
                break;
            }
            moves.add(new Move(this, new Position(this.pos.getR(), j)));
        }

        return moves;
    }

    @Override
    public String toString() {
        if (this.isWhite) {//set foreground colour
            return "R";
        } else {
            return "r";
        }
    }

}
