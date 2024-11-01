/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package chess2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.DefaultListModel;

/**
 *
 * @author Magicman5511
 */
public class LoadJFrame extends javax.swing.JFrame {

    private Game game;
    private GameStorageManager gameStorage;
    private Chess2JFrame chess2JFrame; //chat GPT
    private Map<String, Integer> gameMap = new HashMap<>(); //map of game names to id //chat GPT

    /**
     * Creates new form SaveJFrame
     *
     * @param game
     * @param gameStorage
     */
    public LoadJFrame(Game game, GameStorageManager gameStorage, Chess2JFrame chess2JFrame) {
        this.game = game;
        this.gameStorage = gameStorage;
        this.chess2JFrame = chess2JFrame;  //chat GPT
        initComponents(); // Initialize UI components
        updateGameList();

        game.addPropertyChangeListener(evt -> { //chat GPT
        if ("board".equals(evt.getPropertyName())) { //chat GPT
            if (this.chess2JFrame != null) { //chat GPT
                this.chess2JFrame.drawBoard(); //chat GPT
            } //chat GPT
        } //chat GPT
    }); //chat GPT

    }

    //chat GPT implemented  this for me 
    private void updateGameList() {
        List<String> games = gameStorage.listAllGames();

        DefaultListModel<String> listModel = new DefaultListModel<>();
        gameMap.clear(); // Clear any existing entries to avoid duplicates

        for (String gameInfo : games) {
            try {
                String[] parts = gameInfo.split(", Name: ");
                if (parts.length != 2) {
                    continue;
                }
                int id = Integer.parseInt(parts[0].replace("ID: ", "").trim());
                String name = parts[1].trim();

                listModel.addElement(name);
                gameMap.put(name, id);
            } catch (Exception e) {
            }
        }

        // Update the model of jList1 directly
        jList1.setModel(listModel);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jLabel3 = new javax.swing.JLabel();
        LoadjButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);
        setMinimumSize(new java.awt.Dimension(400, 400));
        setResizable(false);

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jList1.setSelectedIndex(0);
        jScrollPane1.setViewportView(jList1);

        jLabel3.setText("Please Select a game to load");

        LoadjButton.setText("Load");
        LoadjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoadjButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(LoadjButton))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LoadjButton)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void LoadjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LoadjButtonActionPerformed
        //chat GPT help to convert the selected list item in to the game id
        String selectedGame = jList1.getSelectedValue();//gpt
        if (selectedGame != null) {
            // Retrieve the ID associated with the selected game name
            int selectedGameId = gameMap.get(selectedGame);
            game.setBoard(gameStorage.loadGame(selectedGameId));
            this.dispose();
        }

    }//GEN-LAST:event_LoadjButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton LoadjButton;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
