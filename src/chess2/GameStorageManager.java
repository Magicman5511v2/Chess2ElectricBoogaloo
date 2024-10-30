package chess2;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Christopher Payne
 */
public class GameStorageManager {

    private String url = "jdbc:derby:games;create=true";

    public void initializeDatabase() {
        try ( Connection conn = DriverManager.getConnection(url)) {

            // Check if the 'games' table exists
            ResultSet tables = conn.getMetaData().getTables(null, null, "GAMES", null);
            if (!tables.next()) { // Table does not exist, so create it.
                String sql = "CREATE TABLE games (id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,name VARCHAR(255) NOT NULL, board BLOB NOT NULL)";
                try ( PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.executeUpdate();
                }
            }
        } catch (SQLException ex) {
            System.out.print(ex.getMessage());
        }
    }

    public boolean saveGame(Board board, String name) {
        initializeDatabase();
        String sql = "INSERT INTO games (name,board) VALUES(?,?)";
        try ( Connection conn = DriverManager.getConnection(url)) {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            byte[] serialBoard = SerializationUtils.serialize(board);

            pstmt.setString(1, name);
            pstmt.setBytes(2, serialBoard);

            pstmt.executeUpdate();

            return true;
        } catch (SQLException | IOException ex) {
            return false;
        }
    }

    public Board loadGame(int id) {
        initializeDatabase();
        String sql = "SELECT board FROM games WHERE id = ?";
        try ( Connection conn = DriverManager.getConnection(url)) {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return (Board) SerializationUtils.deserialize(rs.getBytes("board"));
            }

        } catch (SQLException | IOException | ClassNotFoundException ex) {
            return null;
        }
        return null;

    }

    public List<String> listAllGames() {
        List<String> games = new ArrayList<>();
        String sql = "SELECT id, name FROM games";

        try ( Connection conn = DriverManager.getConnection(url);  PreparedStatement pstmt = conn.prepareStatement(sql);  ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                games.add("ID: " + id + ", Name: " + name);
            }
        } catch (SQLException ex) {
        }
        return games;
    }
}
