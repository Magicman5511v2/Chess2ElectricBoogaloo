package chess2;

import java.util.HashSet;

/**
 *
 * @author Christopher Payne
 */
public class Pawn extends Piece {

    public Pawn(boolean isWhite, Position pos) {
        super(isWhite, pos);
        this.value = 1;
        imagePath = (isWhite ? "white" : "black") + "-pawn.png";
    }

    @Override
    public HashSet<Move> getMoves(Board board) {
        HashSet<Move> moves = new HashSet<>();
        int direction = this.isWhite ? 1 : -1; // White pawns move up, black pawns move down

        // Define all potential move directions
        // forward, double forward, capture right, capture left
        int[] dx = {direction, direction * 2, direction, direction};
        int[] dy = {0, 0, -1, 1};

        for (int i = 0; i < 4; i++) {
            int r = this.pos.getR() + dx[i];
            int c = this.pos.getC() + dy[i];
            Position newPosition = new Position(r, c);
            Move move = new Move(this, newPosition);
            if (move.isValid(board,null)) {
                moves.add(new Move(this, newPosition));
            }
        }

        return moves;
    }

    @Override
    public String toString() {
        if (this.isWhite) {//set foreground colour
            return "P";
        } else {
            return "p";
        }
    }

}
