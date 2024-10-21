package chess2;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.util.HashSet;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author Christopher Payne
 */
public class Chess2JFrame extends javax.swing.JFrame {

    static Board board = new Board();
    private Piece selectedPiece;

    private static final FileHandler fileHandler = new FileHandler();
    private JButton[][] buttons;

    public Chess2JFrame() {
        initComponents();
        CreateGridButtons();
    }

    private void CreateGridButtons() {
        buttons = new JButton[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                JButton button = new JButton();
                buttons[i][j] = button;
                button.setName(i + "" + (j));
                button.addActionListener((ActionEvent e)
                        -> chessButtonClicked(e));

                GamePanel.add(button);

            }
        }
        this.DrawBoard();
    }

    private void chessButtonClicked(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        String name = button.getName();
        int x = name.charAt(0) - 48;
        int y = name.charAt(1) - 48;
        Position pos = new Position(x, y);
        System.out.println(pos);

        if (selectedPiece == null && board.getPieceAt(pos) == null) {
            return;
        }
        if (selectedPiece == null && board.getPieceAt(pos) != null) {
            if (board.getPieceAt(pos).isWhite == board.getWhiteTurn()) {
                selectedPiece = board.getPieceAt(pos);
                highlightMoves(selectedPiece.getMoves(board));
                return;
            }
        }
        if (selectedPiece != null && board.getPieceAt(pos) != null) {
            if (board.getPieceAt(pos).isWhite == board.getWhiteTurn()) {
                selectedPiece = board.getPieceAt(pos);
                highlightMoves(selectedPiece.getMoves(board));
                return;
            } else {
                Move move = new Move(selectedPiece, pos);
                System.out.println(move);
                HashSet<Move> moves = selectedPiece.getMoves(board);
                if (moves.contains(move)) {
                    board.makeMove(move);
                    selectedPiece = null;
                    this.DrawBoard();

                }
                return;
            }
        }

        if (selectedPiece != null && board.getPieceAt(pos) == null) {
            Move move = new Move(selectedPiece, pos);
            System.out.println(move);
            HashSet<Move> moves = selectedPiece.getMoves(board);
            if (moves.contains(move)) {
                board.makeMove(move);
                selectedPiece = null;
                this.DrawBoard();

            }
            return;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        MenuPanel = new javax.swing.JPanel();
        NewButton = new javax.swing.JButton();
        SaveButton = new javax.swing.JButton();
        LoadButton = new javax.swing.JButton();
        TurnTextPane = new javax.swing.JTextPane();
        GamePanel = new javax.swing.JPanel();
        MessagePanel = new javax.swing.JPanel();
        MessageTextPane = new javax.swing.JTextPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Chess 2 Electric Boogaloo");
        setMinimumSize(new java.awt.Dimension(498, 588));
        setPreferredSize(new java.awt.Dimension(498, 588));
        setResizable(false);

        NewButton.setText("New Game");
        NewButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NewButtonActionPerformed(evt);
            }
        });

        SaveButton.setText("Save Game");
        SaveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveButtonActionPerformed(evt);
            }
        });

        LoadButton.setText("Load Game");
        LoadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoadButtonActionPerformed(evt);
            }
        });

        TurnTextPane.setText("No Game");

        javax.swing.GroupLayout MenuPanelLayout = new javax.swing.GroupLayout(MenuPanel);
        MenuPanel.setLayout(MenuPanelLayout);
        MenuPanelLayout.setHorizontalGroup(
            MenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MenuPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(NewButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(SaveButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LoadButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TurnTextPane, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8))
        );
        MenuPanelLayout.setVerticalGroup(
            MenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MenuPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(MenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(MenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(NewButton)
                        .addComponent(SaveButton)
                        .addComponent(LoadButton))
                    .addComponent(TurnTextPane))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        GamePanel.setMaximumSize(new java.awt.Dimension(480, 480));
        GamePanel.setMinimumSize(new java.awt.Dimension(480, 480));
        GamePanel.setName("[512, 512]"); // NOI18N
        GamePanel.setPreferredSize(new java.awt.Dimension(480, 480));
        GamePanel.setRequestFocusEnabled(false);
        GamePanel.setVerifyInputWhenFocusTarget(false);
        GamePanel.setLayout(new java.awt.GridLayout(8, 8));

        MessageTextPane.setText("Welcome to Chess human vs cpu(not good at all)!");

        javax.swing.GroupLayout MessagePanelLayout = new javax.swing.GroupLayout(MessagePanel);
        MessagePanel.setLayout(MessagePanelLayout);
        MessagePanelLayout.setHorizontalGroup(
            MessagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(MessagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(MessagePanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(MessageTextPane, javax.swing.GroupLayout.DEFAULT_SIZE, 474, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        MessagePanelLayout.setVerticalGroup(
            MessagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 32, Short.MAX_VALUE)
            .addGroup(MessagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(MessagePanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(MessageTextPane, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MenuPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                .addComponent(MessagePanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(GamePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(MenuPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(GamePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(MessagePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void SaveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveButtonActionPerformed
        fileHandler.save(board, "test");
        this.DrawBoard();
    }//GEN-LAST:event_SaveButtonActionPerformed

    private void NewButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NewButtonActionPerformed
        board = new Board();
        this.DrawBoard();
    }//GEN-LAST:event_NewButtonActionPerformed

    private void LoadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LoadButtonActionPerformed

    }//GEN-LAST:event_LoadButtonActionPerformed

    private void DrawBoard() {
        TurnTextPane.setText(board.getWhiteTurn() ? "Whites Turn" : "Blacks Turn");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Position pos = new Position(i, j);
                Piece piece = this.board.getPieceAt(pos);
                JButton button = buttons[i][j];
                if (piece != null) {
                    Image image = new ImageIcon("resources/icons/" + piece.imagePath).getImage();
                    Image newimg = image.getScaledInstance(32, 32, java.awt.Image.SCALE_SMOOTH);
                    ImageIcon imageIcon = new ImageIcon(newimg);
                    button.setIcon(imageIcon);
                } else {
                    button.setIcon(null);
                }
                if ((i + j) % 2 == 0) {
                    button.setBackground(Color.WHITE);
                } else {
                    button.setBackground(Color.BLACK);
                }

            }
        }
        if (selectedPiece != null) {
            setBackground(selectedPiece.getPos(), Color.GREEN);
        }

    }

    private void highlightMoves(HashSet<Move> Moves) {
        DrawBoard();
        for (Move move : Moves) {
            if (board.getPieceAt(move.getPos()) != null) {
                setBackground(move.getPos(), Color.RED);
            } else {
                setBackground(move.getPos(), Color.YELLOW);
            }

        }
    }

    private void setBackground(Position pos, Color c) {
        JButton button = buttons[pos.getR()][pos.getC()];
        button.setBackground(c);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Chess2JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Chess2JFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel GamePanel;
    private javax.swing.JButton LoadButton;
    private javax.swing.JPanel MenuPanel;
    private javax.swing.JPanel MessagePanel;
    private javax.swing.JTextPane MessageTextPane;
    private javax.swing.JButton NewButton;
    private javax.swing.JButton SaveButton;
    private javax.swing.JTextPane TurnTextPane;
    // End of variables declaration//GEN-END:variables
}
