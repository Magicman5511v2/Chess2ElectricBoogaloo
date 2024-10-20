/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package chess2;

import java.util.Scanner;

/**
 *
 * @author Magicman5511
 */
public class Chess2 {

    private static final Scanner scanner = new Scanner(System.in);
    private static final FileHandler fileHandler = new FileHandler();
    private static Game game;

    /**
     * the main program
     *
     * @param args not used
     */
    public static void main(String[] args) {

        boolean exit = false;
        do {
            System.out.println("Welcome to Chess human vs cpu(not good at all)!");
            System.out.println("Please choose an option:");
            System.out.println("1. Start a New Game");
            System.out.println("2. Load an Existing Game");
            System.out.println("Anything else to Exit");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    game = new Game();
                    game.startGame();
                    break;
                case "2":
                    Board board;
                    board = fileHandler.load();
                    if (board == null) {
                        break;
                    }
                    game = new Game(board);
                    game.startGame();
                    break;
                default:
                    exit = true;
                    break;
            }
        } while (!exit);
        scanner.close();
    }
}

