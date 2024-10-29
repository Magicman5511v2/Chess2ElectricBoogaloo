package chess2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 *
 * @author Christopher Payne
 */
public class Board implements Serializable {

    private List<List<Piece>> board;
    private boolean whiteTurn;
    private boolean inTestMove;

    /**
     * constructor for a normal start
     */
    public Board() {
        initializeBoard();
        this.whiteTurn = true;
        this.inTestMove = false;
        for (int c = 0; c < 8; c++) {
            board.get(1).set(c, new Pawn(true, new Position(1, c)));
            board.get(6).set(c, new Pawn(false, new Position(6, c)));
        }

        board.get(0).set(0, new Rook(true, new Position(0, 0)));
        board.get(7).set(0, new Rook(false, new Position(7, 0)));
        board.get(0).set(7, new Rook(true, new Position(0, 7)));
        board.get(7).set(7, new Rook(false, new Position(7, 7)));

        board.get(0).set(1, new Knight(true, new Position(0, 1)));
        board.get(7).set(1, new Knight(false, new Position(7, 1)));
        board.get(0).set(6, new Knight(true, new Position(0, 6)));
        board.get(7).set(6, new Knight(false, new Position(7, 6)));

        board.get(0).set(2, new Bishop(true, new Position(0, 2)));
        board.get(7).set(2, new Bishop(false, new Position(7, 2)));
        board.get(0).set(5, new Bishop(true, new Position(0, 5)));
        board.get(7).set(5, new Bishop(false, new Position(7, 5)));

        board.get(0).set(4, new King(true, new Position(0, 4)));
        board.get(7).set(3, new King(false, new Position(7, 3)));

        board.get(0).set(3, new Queen(true, new Position(0, 3)));
        board.get(7).set(4, new Queen(false, new Position(7, 4)));
    }

    private void initializeBoard() {
        board = new ArrayList<>();
        for (int r = 0; r < 8; r++) {
            List<Piece> row = new ArrayList<>();
            for (int c = 0; c < 8; c++) {
                row.add(null);
            }
            board.add(row);
        }

    }

    /**
     *
     * @param p Position to find on board
     * @return the Piece at Position p or null if empty
     */
    public Piece getPieceAt(Position p) {
        return this.board.get(p.getR()).get(p.getC());
    }

    /**
     * finds all valid move available to the Pieces of the board
     *
     * @return HashSet of Moves or null if none found
     */
    public HashSet<Move> findAllValidMoves() {
        // Initialize a list to store all valid moves
        HashSet<Move> validMoves = new HashSet<>();
        HashSet<Move> moves;
        // Loop through all the pieces on the board
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                Piece piece = board.get(r).get(c);
                if (piece != null) {
                    if (piece.isWhite == this.whiteTurn) {
                        moves = piece.getMoves(this);

                        if (!moves.isEmpty()) {
                            validMoves.addAll(piece.getMoves(this));
                        }
                    }
                }
            }
        }
        return validMoves;
    }

    /**
     * correctly complete a move on the board
     *
     * @param move to perform
     * @throws java.lang.Exception
     */
    public void makeMove(Move move) throws Exception {
        if (!move.isValid(this, null)) {
            throw new Exception("Move is Invalid");
        }
        Position target = move.getPos();

        Piece piece = move.getPiece();
        Position start = piece.getPos();

        //update board
        this.board.get(target.getR()).set(target.getC(), piece);
        this.board.get(start.getR()).set(start.getC(), null);

        //update pieces
        piece.makeMove(target);
        this.whiteTurn = !this.whiteTurn;
    }

    public boolean testMove(Move move) throws Exception {
 // Set the inTestMove flag to prevent recursion
    this.inTestMove = true;

    Position start = move.getPiece().getPos();
    Position target = move.getPos();
    Piece movedPiece = move.getPiece();
    Piece targetPiece = getPieceAt(target); // Get any piece at the target position

    // Prevent friendly captures: if the target position has a piece of the same color, move is invalid
    if (targetPiece != null && targetPiece.isWhite == movedPiece.isWhite) {
        this.inTestMove = false; // Reset the inTestMove flag before returning
        return false;
    }

    // Save the `hasMoved` states
    boolean movedPieceHasMoved = movedPiece.hasMoved;
    boolean targetPieceHasMoved = (targetPiece != null) ? targetPiece.hasMoved : false;

    // Temporarily make the move
    board.get(start.getR()).set(start.getC(), null); // Remove piece from the start
    board.get(target.getR()).set(target.getC(), movedPiece); // Place piece at target
    movedPiece.makeMove(target); // Update piece's internal position to the target
    movedPiece.hasMoved = true;  // Mark the moved piece as having moved

    // Check if this move results in the king being in check
    boolean kingInCheck = checkForCheck();

    // Revert the move: restore the original positions and `hasMoved` states
    board.get(start.getR()).set(start.getC(), movedPiece); // Put piece back at the start
    board.get(target.getR()).set(target.getC(), targetPiece); // Restore any captured piece
    movedPiece.makeMove(start); // Reset piece's internal position back to the start
    movedPiece.hasMoved = movedPieceHasMoved; // Revert `hasMoved` status of moved piece

    if (targetPiece != null) {
        targetPiece.hasMoved = targetPieceHasMoved; // Revert `hasMoved` of target piece if it was captured
    }

    // Reset the inTestMove flag after the check
    this.inTestMove = false;

    // Return whether the move does not put the king in check
    return !kingInCheck;
}


    /**
     * this is used to check to see if team king will be checked
     *
     * @return true if king will be in check
     */
    public boolean checkForCheck() {
        // Determine the current player and opponent
        boolean currentPlayerIsWhite = this.whiteTurn;
        Position kingPosition = null;

        //Find the king
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                Piece piece = board.get(r).get(c);
                if (piece instanceof King && piece.isWhite == currentPlayerIsWhite) {
                    kingPosition = piece.getPos();
                    break;
                }
            }
        }

        //where is the king?
        if (kingPosition == null) {
            throw new IllegalStateException("King not found on the board!");
        }

        // Temporarily switch turn to check opponent's moves
        this.whiteTurn = !currentPlayerIsWhite;
        HashSet<Move> opponentMoves = findAllValidMoves();
        this.whiteTurn = currentPlayerIsWhite; // revert to the original turn

        // Check if any opponent move can capture the king's position
        for (Move move : opponentMoves) {
            if (move.getPos().equals(kingPosition)) {
                return true; // King is in check
            }
        }
        return false;
    }
    /**
     *
     * @return true if it is whites turn
     */
    public boolean getWhiteTurn() {
        return this.whiteTurn;
    }

    /**
     * @return the inTestMove
     */
    public boolean isInTestMove() {
        return inTestMove;
    }

}
