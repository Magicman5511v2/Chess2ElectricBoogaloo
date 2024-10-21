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
        imagePath = "Icons/" + (isWhite ? "white" : "black") + "-queen.png";
    }

    @Override
    public HashSet<Move> getMoves(Board board) {
        HashSet<Move> moves = new HashSet<>();

        // Rook's moves
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

        // Bishop's moves
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

    @Override
    public String toString() {
        if (this.isWhite) {//set foreground colour
            return "Q";
        } else {
            return "q";
        }
    }
}
