/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package animehalloweenprograms.animetrivia;

import java.awt.BorderLayout;

/**
 *
 * @author Phyllis
 */
public class TriviaControls extends javax.swing.JPanel {

    /**
     * Creates new form TriviaControls
     */
    public TriviaControls() {
        initComponents();
        
        notPlaying = new ControlsNotPlaying();
        
        categories = new ControlsCategories();
        categories.setTriviaControls(this);
        
        questionAnswer = new ControlsQuestionAnswer();
        
        displayPanel.setLayout(new BorderLayout());
        displayPanel.add(notPlaying, BorderLayout.CENTER);
    }
    
    public void startPlayingGame() {
        notPlaying.setVisible(false);
        displayPanel.remove(notPlaying);
        //displayPanel.removeAll();
        categories.setVisible(true);
        displayPanel.add(categories, BorderLayout.CENTER);
    }
    
    public void stopPlayingGame() {
        removeAllComponents();
        
        notPlaying.setVisible(true);
        displayPanel.add(notPlaying);
    }
    
    public void pausePlayingGame() {
        
    }
    
    public void switchToQuestionAnswer(int category, int points) {
        removeAllComponents();
        
        questionAnswer.setVisible(true);
        displayPanel.add(questionAnswer);
        
        //incorrectButton.setEnabled(true);
        //correctButton.setEnabled(true);
    }
    
    public void setIncorrectEnabled(boolean isOn) {
        incorrectButton.setEnabled(isOn);
    }
    
    public void setCorrectEnabled(boolean isOn) {
        correctButton.setEnabled(isOn);
    }
    
    public void switchToCategories() {
        incorrectButton.setEnabled(false);
        correctButton.setEnabled(false);
    }
    
    private void removeAllComponents() {
        for (int i = 0; i < displayPanel.getComponentCount(); i++) {
            displayPanel.getComponent(i).setVisible(false);
            displayPanel.remove(displayPanel.getComponent(i));
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

        correctButton = new javax.swing.JButton();
        incorrectButton = new javax.swing.JButton();
        playerNameTextField = new javax.swing.JTextField();
        displayPanel = new javax.swing.JPanel();

        correctButton.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        correctButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/animehalloweenprograms/images/Correct.png"))); // NOI18N
        correctButton.setEnabled(false);

        incorrectButton.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        incorrectButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/animehalloweenprograms/images/Incorrect.png"))); // NOI18N
        incorrectButton.setEnabled(false);
        incorrectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                incorrectButtonActionPerformed(evt);
            }
        });

        playerNameTextField.setEditable(false);
        playerNameTextField.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        playerNameTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        playerNameTextField.setText(" ");
        playerNameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playerNameTextFieldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout displayPanelLayout = new javax.swing.GroupLayout(displayPanel);
        displayPanel.setLayout(displayPanelLayout);
        displayPanelLayout.setHorizontalGroup(
            displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        displayPanelLayout.setVerticalGroup(
            displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 196, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(correctButton, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(playerNameTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(incorrectButton, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(displayPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(displayPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(correctButton, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(incorrectButton, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(playerNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void playerNameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playerNameTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_playerNameTextFieldActionPerformed

    private void incorrectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_incorrectButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_incorrectButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton correctButton;
    private javax.swing.JPanel displayPanel;
    private javax.swing.JButton incorrectButton;
    private javax.swing.JTextField playerNameTextField;
    // End of variables declaration//GEN-END:variables

    private ControlsNotPlaying notPlaying;
    private ControlsCategories categories;
    private ControlsQuestionAnswer questionAnswer;
}
