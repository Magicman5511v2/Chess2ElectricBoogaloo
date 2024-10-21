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
    private boolean isClone;

    /**
     * constructor for a normal start
     */
    public Board() {
        initializeBoard();
        this.whiteTurn = true;
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
     */
    public void makeMove(Move move) {
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

    /**
     * this is used to check to see if team king will be checked
     *
     * @return true if king will be in check
     */
    public boolean checkForCheck() {
        Position kingPos = null;

        // find the King's Position
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Piece piece = this.getPieceAt(new Position(row, col));
                if (piece != null && piece instanceof King && piece.isWhite() == whiteTurn) {
                    kingPos = new Position(row, col);
                    break;
                }
            }
            if (kingPos != null) {
                break;
            }
        }
        // get all valid moves from enemys
        whiteTurn = !whiteTurn; // Swap to opponent's turn
        HashSet<Move> opponentMoves = new HashSet<>();

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Piece piece = this.getPieceAt(new Position(row, col));
                if (piece != null && piece.isWhite() == whiteTurn) {
                    opponentMoves.addAll(piece.getMoves(this));
                }
            }
        }
        // extract target positions
        HashSet<Position> opponentTargetPos = new HashSet<>();
        opponentMoves.forEach((n) -> {
            opponentTargetPos.add(n.getPos());
        });

        //check if enemy can attack next turn
        return opponentTargetPos.contains(kingPos);

    }

    /**
     * this i used to make sure that the checkForCheck() is not infinitely
     * recursive and was a pain to think about
     */
    public void setIsClone() {
        this.isClone = true;
    }

    /**
     * this i used to make sure that the checkForCheck() is not infinitely
     * recursive and was a pain to think about
     *
     * @return true if clone false if not
     */
    public boolean getIsClone() {
        return this.isClone;
    }

    /**
     * not yet implemented as of time restraints TODO
     */
    public void premotePawn() {
    }

    /**
     *
     * @return true if it is whites turn
     */
    public boolean getWhiteTurn() {
        return this.whiteTurn;
    }

}
