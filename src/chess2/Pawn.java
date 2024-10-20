package chess2;

import java.io.IOException;
import java.util.HashSet;

/**
 *
 * @author Christopher Payne
 */
public class Pawn extends Piece {

    public Pawn(boolean isWhite, Position pos) {
        super(isWhite, pos);
        this.value = 1;
    }

    @Override
    public Boolean isValidMove(Board board, Position target) {
        try {
            if (!board.getIsClone()) {
                //chatGPT created this deep copy for me ↓↓↓↓
                byte[] boardBytes = SerializationUtils.serialize(board);
                Board clone = (Board) SerializationUtils.deserialize(boardBytes);
                //chatGPT created this deep copy for me ↑↑↑
                clone.setIsClone();
                boolean check = clone.checkForCheck();
                if (check) {
                    return clone.checkForCheck();
                }
            }

        } catch (IOException | ClassNotFoundException e) {
            System.out.print(e.getMessage());

        }
        // Check to make sure the target is on the board
        if (target.getR() < 0 || target.getR() > 7 || target.getC() < 0 || target.getC() > 7) {
            return false;
        }

        Piece piece = board.getPieceAt(target);
        if (piece == null) { // empty
            if (target.getC() == pos.getC()) { //forward
                if (target.getR() + 1 == pos.getR() || target.getR() - 1 == pos.getR()) {//1 space
                    return true;
                } else if (hasMoved == false) {
                    return true;
                }

            } else {
                return false; //cant move diag
            }
        } else {
            if (piece.isWhite == this.isWhite) {//friendly
                return false;
            }
            if (target.getC() == pos.getC()) { //forward
                return false;
            } else {
                return true;//diag capture
            }
        }
        return false;
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
            if (isValidMove(board, newPosition)) {
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
