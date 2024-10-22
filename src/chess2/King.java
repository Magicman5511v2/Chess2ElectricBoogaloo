package chess2;

import java.util.HashSet;

/**
 *
 * @author Christopher Payne
 */
public class King extends Piece {

    public King(boolean isWhite, Position pos) {
        super(isWhite, pos);
        this.value = Integer.MAX_VALUE;
        imagePath = (isWhite ? "white" : "black") + "-king.png";
    }

    @Override
    public HashSet<Move> getMoves(Board board) {
        HashSet<Move> moves = new HashSet<>();
        int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1}; // All possible x directions
        int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1}; // All possible y directions

        for (int i = 0; i < 8; i++) {
            int newX = this.pos.getR() + dx[i];
            int newY = this.pos.getC() + dy[i];
            Position newPosition = new Position(newX, newY);
            Move move = new Move(this, newPosition);
            if (move.isValid(board)) {
                moves.add(move);
            }
            if (board.getPieceAt(move.getPos()).isWhite != this.isWhite) {
                moves.add(move);
            }
        }

        return moves;
    }

    @Override
    public String toString() {
        if (this.isWhite) {//set foreground colour
            return "K";
        } else {
            return "k";
        }
    }

}
