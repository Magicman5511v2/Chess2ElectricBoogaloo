package chess2;

import java.util.Objects;

/**
 *
 * @author Christopher Payne
 */
public class Move {

    private final Piece piece;
    private final Position pos;
    private int value = 0;

    /**
     *
     * @param piece is the piece that will be moving
     * @param pos is where the piece wants to go
     */
    public Move(Piece piece, Position pos) {
        this.piece = piece;
        this.pos = pos;
        this.value = 0;
        
    }

    Move(Move move) {
        this.piece = move.getPiece();
        this.pos = move.getPos();
        this.value = move.value;
    }

    public boolean isValid(Board board, Move lastMove) {// TODO implement a check for check

        // Check to make sure the target is on the board
        if (pos.getR() < 0 || pos.getR() > 7 || pos.getC() < 0 || pos.getC() > 7) {
            return false;
        }
        try {
            // Check for own Discover Check
            if (!board.isInTestMove()) {
                if (!board.testMove(this)) {
                    return false;
                }
            }
        } catch (Exception ex) {
            return false;
        }
        // Check if last move was a kill
        if (lastMove != null) {
            if (board.getPieceAt(lastMove.pos) != null) {
                return false;
            }
        }

        //Check if there is a targeted piece
        Piece targetPiece = board.getPieceAt(pos);
        if (targetPiece != null) {
            this.value = targetPiece.getValue();
        }

        //TODO pawn only checks
        if (piece instanceof Pawn) {
            Position piecePos = piece.getPos();
            if (targetPiece == null) { // empty
                if (piecePos.getC() == pos.getC()) { //forward
                    if (piecePos.getR() == pos.getR() + 1 || piecePos.getR() == pos.getR() - 1) {//1 space
                        return true;
                    } else if (piece.hasMoved == false) {//2 space <--Bug Here
                        return true;
                    }

                } else {
                    return false; //cant move diag
                }
            } else {
                if (targetPiece.isWhite == piece.isWhite) {//friendly
                    return false;
                }
                if (piecePos.getC() == pos.getC()) { //forward
                    return false;
                } else {
                    return true;//diag capture
                }
            }
            return false;

        }

        //Check if empty space
        if (targetPiece == null) {
            return true; //TODO Discover check
        }

        //No Team Kills
        if (board.getPieceAt(pos).isWhite == this.piece.isWhite) {
            //TODO Castling goes here //TODO Discover check
            return false;
        }

        return true;

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
     * are 2 objects equal
     *
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

    /**
     * @return the value
     */
    public int getValue() {
        return value;
    }
}
