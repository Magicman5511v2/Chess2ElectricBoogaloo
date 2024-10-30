package chess2;

import java.util.HashSet;

/**
 *
 * @author Christopher Payne
 */
public class Queen extends Piece {

    public Queen(boolean isWhite, Position pos) {
        super(isWhite, pos);
        this.value = 9;
        imagePath = (isWhite ? "white" : "black") + "-queen.png";
    }

    @Override
    public HashSet<Move> getMoves(Board board) {
        HashSet<Move> moves = new HashSet<>();
        Move lastMove = null;

        // Rook's moves
        // Check all squares in the vertical direction (up and down)
        for (int i = this.pos.getR() + 1; i < 8; i++) {
            Move move = new Move(this, new Position(i, this.pos.getC()));
            if (!move.isValid(board, lastMove)) {
                lastMove = null;
                break;
            }
            moves.add(move);
            lastMove = new Move(move);
        }
        for (int i = this.pos.getR() - 1; i >= 0; i--) {
            Move move = new Move(this, new Position(i, this.pos.getC()));
            if (!move.isValid(board, lastMove)) {
                lastMove = null;
                break;
            }
            moves.add(move);
            lastMove = new Move(move);
        }

        // Check all squares in the horizontal direction (left and right)
        for (int j = this.pos.getC() + 1; j < 8; j++) {
            Move move = new Move(this, new Position(this.pos.getR(), j));
            if (!move.isValid(board, lastMove)) {
                lastMove = null;
                break;
            }
            moves.add(move);
            lastMove = new Move(move);
        }
        for (int j = this.pos.getC() - 1; j >= 0; j--) {
            Move move = new Move(this, new Position(this.pos.getR(), j));
            if (!move.isValid(board, lastMove)) {
                lastMove = null;
                break;
            }
            moves.add(move);
            lastMove = new Move(move);
        }

        // Bishop's moves
        for (int i = 1; this.pos.getR() + i < 8 && this.pos.getC() + i < 8; i++) {
            Move move = new Move(this, new Position(this.pos.getR() + i, this.pos.getC() + i));
            if (!move.isValid(board, lastMove)) {
                lastMove = null;
                break;
            }
            moves.add(move);
            lastMove = new Move(move);

        }
        for (int i = 1; this.pos.getR() + i < 8 && this.pos.getC() - i >= 0; i++) {
            Move move = new Move(this, new Position(this.pos.getR() + i, this.pos.getC() - i));
            if (!move.isValid(board, lastMove)) {
                lastMove = null;
                break;
            }
            moves.add(move);
            lastMove = new Move(move);
        }
        for (int i = 1; this.pos.getR() - i >= 0 && this.pos.getC() + i < 8; i++) {
            Move move = new Move(this, new Position(this.pos.getR() - i, this.pos.getC() + i));
            if (!move.isValid(board, lastMove)) {
                lastMove = null;
                break;
            }
            moves.add(move);
            lastMove = new Move(move);
        }
        for (int i = 1; this.pos.getR() - i >= 0 && this.pos.getC() - i >= 0; i++) {
            Move move = new Move(this, new Position(this.pos.getR() - i, this.pos.getC() - i));
            if (!move.isValid(board, lastMove)) {
                break;
            }
            moves.add(move);
            lastMove = new Move(move);
        }

        return moves;
    }

    @Override
    public String toString() {
        if (this.isWhite) {//set foreground colour
            return "Q";
        } else {
            return "q";
        }
    }
}
