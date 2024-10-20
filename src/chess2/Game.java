package chess2;

import java.util.HashSet;
import java.util.Scanner;

/**
 *
 * @author Christopher Payne
 */
public class Game {

    private static Board board;
    private static final String p1 = "White";
    private static final String p2 = "Black";
    private static final Scanner scanner = new Scanner(System.in);
    private static final FileHandler fileHandler = new FileHandler();

    /**
     *constructor for a new game
     */
    public Game() {
        Game.board = new Board();
    }

    /**
     *constructor for a continuing game
     * @param board loaded game
     */
    public Game(Board board) {
        Game.board = board;
    }

    /**
     *this is used to start main game loop
     */
    public void startGame() {
        boolean gameRunning = true;

        while (gameRunning) {
            Move move = getPlayerMove(scanner);
            if (move == null) {
                gameRunning = false;
            } else {
                board.makeMove(move);

            }
        }
    }

    private Move getPlayerMove(Scanner scanner) {
        Move move = null;
        board.displayBoard();
        HashSet<Move> moves = board.findAllValidMoves();
        if (moves.isEmpty()) {
            System.out.println("No more valid moves " + (board.getWhiteTurn() ? p2 : p1) + " Wins");
            System.out.print("\n\n");
            return null;
        }
        Move example = moves.iterator().next();
        String exampleStr = example.getStr();
        while (move == null) {

            if (!board.getWhiteTurn()) {// useless cpu player
                return example;
            }
            System.out.println("Enter your move " + (board.getWhiteTurn() ? p1 : p2) + " (try, " + exampleStr + ") or type exit or save");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Exiting...");
                System.out.print("\n\n");
                return null;
            }
            if (input.equalsIgnoreCase("save")) {
                if (!fileHandler.save(board)) {
                    return null;//exit
                }
                continue;

            }
            try {
                // Parse input into starting and ending positions
                int PieceR = Character.getNumericValue(input.charAt(1) - 1); // Convert '1'-'8' to 0-7
                int PieceC = input.charAt(0) - 'a'; // Convert 'a'-'h' to 0-7
                int targetR = Character.getNumericValue(input.charAt(3) - 1);
                int targetC = input.charAt(2) - 'a';
                Position piecePos = new Position(PieceR, PieceC);
                Position targetPos = new Position(targetR, targetC);

                // Create a move object
                Piece piece = board.getPieceAt(piecePos);
                move = new Move(piece, targetPos);

                if (!moves.contains(move)) {
                    board.displayBoard();
                    System.out.println("Invalid move. Try again.");
                    move = null;
                }

            } catch (Exception e) {
                board.displayBoard();
                System.out.println("Please use the format " + exampleStr);
                move = null;

            }

        }
        return move;

    }

}
