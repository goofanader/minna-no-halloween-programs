/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package animehalloweenprograms.animetrivia;

import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Phyllis
 */
public class TriviaPlayers extends javax.swing.JPanel {
    private JTable playerList;
    
    /**
     * Creates new form TriviaPlayers
     */
    public TriviaPlayers() {
        initComponents();
        
        String[] colNames = {"", "Player Name", "Points"};
        Object[][] data = new Object[1][3];

        //data[0][0] = image;
        data[0][1] = "Player 1";
        data[0][2] = 0;
        
        playerList = new JTable(data, colNames);
        playerList.setFillsViewportHeight(true);
        playerList.getTableHeader().setReorderingAllowed(false);
        
        //======Get the Row Selected======//
        playerList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        ListSelectionModel rowSM = playerList.getSelectionModel();
        rowSM.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                //Ignore extra messages.
                if (e.getValueIsAdjusting()) {
                    return;
                }

                ListSelectionModel lsm = (ListSelectionModel) e.getSource();
                
                int selectedRow = lsm.getMinSelectionIndex();
                System.out.println("Row " + selectedRow
                        + " is now selected.");
                /*currRow = selectedRow;
                
                if (currRow > -1 && currCol > -1 && above != null) {
                    above.switchToQuestionAnswer(currCol, currRow);
                    lsm.clearSelection();
                }*/
            }
        });
        
        playerListPanel.setLayout(new BorderLayout());
        playerListPanel.add(playerList.getTableHeader(), BorderLayout.PAGE_START);
        playerListPanel.add(playerList, BorderLayout.CENTER);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        playerListPanel = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jSpinner1 = new javax.swing.JSpinner();
        jLabel1 = new javax.swing.JLabel();

        javax.swing.GroupLayout playerListPanelLayout = new javax.swing.GroupLayout(playerListPanel);
        playerListPanel.setLayout(playerListPanelLayout);
        playerListPanelLayout.setHorizontalGroup(
            playerListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        playerListPanelLayout.setVerticalGroup(
            playerListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 343, Short.MAX_VALUE)
        );

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/animehalloweenprograms/animetrivia/images/Plus.png"))); // NOI18N

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/animehalloweenprograms/animetrivia/images/Minus.png"))); // NOI18N

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/animehalloweenprograms/animetrivia/images/ButtonSet.png"))); // NOI18N
        jButton3.setPreferredSize(new java.awt.Dimension(77, 77));

        jTextField1.setText("Player 1");

        jLabel1.setText("Player Name");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addComponent(jTextField1))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(playerListPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(playerListPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JPanel playerListPanel;
    // End of variables declaration//GEN-END:variables
}