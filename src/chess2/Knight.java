package chess2;

import java.util.HashSet;

/**
 *
 * @author Christopher Payne
 */
public class Knight extends Piece {

    public Knight(boolean isWhite, Position pos) {
        super(isWhite, pos);
        this.value = 3;
    }

    @Override
    public HashSet<Move> getMoves(Board board) {
        HashSet<Move> moves = new HashSet<>();
        int[] dx = {-2, -1, 1, 2, 2, 1, -1, -2}; // All possible x directions for a knight
        int[] dy = {1, 2, 2, 1, -1, -2, -2, -1}; // All possible y directions for a knight

        for (int i = 0; i < 8; i++) {
            int newX = this.pos.getR() + dx[i];
            int newY = this.pos.getC() + dy[i];
            Position newPosition = new Position(newX, newY);

            if (isValidMove(board, newPosition)) {
                moves.add(new Move(this, newPosition));
            }
        }

        return moves;
    }

    @Override
    public String toString() {
        if (this.isWhite) {//set foreground colour
            return "N";
        } else {
            return "n";
        }
    }
}
