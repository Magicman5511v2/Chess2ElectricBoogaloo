package chess2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

/**
 *
 * @author Christopher Payne
 */
public class FileHandler {

    private static final Scanner scanner = new Scanner(System.in);

    /**
     *
     * @return Board if successful null if not
     */
    public Board load(String filename) {
        if (!this.listAllSaves()) {
            return null;
        }
        if ("exit".equalsIgnoreCase(filename)) {
            return null;
        }
        try {
            return loadBoard(filename);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading the board. Please check the filename and try again.");
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * this is for the user to decide on how to save
     *
     * @param board to save
     * @param filename
     * @return true = continue false = exit
     */
    public boolean save(Board board, String filename) {
        try {
            saveBoard(filename, board);
        } catch (IOException e) {
            System.out.println("Error saving the board. Please try again.");
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    private static Board loadBoard(String boardSTR) throws FileNotFoundException, IOException, ClassNotFoundException {
        String fileName = "saves/" + boardSTR + ".txt";
        FileInputStream fis = new FileInputStream(fileName);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Board board = (Board) ois.readObject();
        ois.close();
        return board;

    }

    private static void saveBoard(String boardSTR, Board board) throws FileNotFoundException, IOException {
        String directoryName = "saves";
        File directory = new File(directoryName);

        // Create the directory if it doesn't exist
        if (!directory.exists()) {
            directory.mkdir();
        }
        String fileName = "saves/" + boardSTR + ".txt";
        FileOutputStream fos = new FileOutputStream(fileName);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(board);
        oos.flush();
        oos.close();
    }

    /**
     * will print to console all saves
     *
     * @return true if there are saves false if not
     */
    public boolean listAllSaves() {
        File directory = new File("saves");

        // Check if the directory exists and list files
        if (directory.exists() && directory.isDirectory()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) {
                        System.out.println((file.getName().replace(".txt", "")));
                    }
                }
                return true;
            }
        } else {
            System.out.println("Save directory does not exist.");
        }
        return false;
    }

}
