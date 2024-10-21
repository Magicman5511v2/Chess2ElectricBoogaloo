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
        imagePath = (isWhite ? "white" : "black") + "-rook.png";
    }

    @Override
    public HashSet<Move> getMoves(Board board) {
        HashSet<Move> moves = new HashSet<>();
        // Rook's moves
        // Check all squares in the vertical direction (up and down)
        for (int i = this.pos.getR() + 1; i < 8; i++) {
            Move move = new Move(this, new Position(i, this.pos.getC()));
            if (!move.isValid(board)) {
                break;
            }
            moves.add(move);
        }
        for (int i = this.pos.getR() - 1; i >= 0; i--) {
            Move move = new Move(this, new Position(i, this.pos.getC()));
            if (!move.isValid(board)) {
                break;
            }
            moves.add(move);
        }

        // Check all squares in the horizontal direction (left and right)
        for (int j = this.pos.getC() + 1; j < 8; j++) {
            Move move = new Move(this, new Position(this.pos.getR(), j));
            if (!move.isValid(board)) {
                break;
            }
            moves.add(move);
        }
        for (int j = this.pos.getC() - 1; j >= 0; j--) {
            Move move = new Move(this, new Position(this.pos.getR(), j));
            if (!move.isValid(board)) {
                break;
            }
            moves.add(move);
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
