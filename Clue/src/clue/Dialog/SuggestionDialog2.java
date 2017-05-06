package clue.Dialog;

import clue.GamePieces.Card;
import clue.GamePieces.Notebook;
import static clue.GamePieces.Card.SUSPECT_GREEN;
import static clue.GamePieces.Card.SUSPECT_MUSTARD;
import static clue.GamePieces.Card.SUSPECT_PEACOCK;
import static clue.GamePieces.Card.SUSPECT_PLUM;
import static clue.GamePieces.Card.SUSPECT_SCARLET;
import static clue.GamePieces.Card.SUSPECT_WHITE;
import static clue.GamePieces.Card.TYPE_SUSPECT;
import static clue.GamePieces.Card.TYPE_WEAPON;
import static clue.GamePieces.Card.WEAPON_CANDLE;
import static clue.GamePieces.Card.WEAPON_KNIFE;
import static clue.GamePieces.Card.WEAPON_PIPE;
import static clue.GamePieces.Card.WEAPON_REVOLVER;
import static clue.GamePieces.Card.WEAPON_ROPE;
import static clue.GamePieces.Card.WEAPON_WRENCH;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class SuggestionDialog2 extends javax.swing.JDialog {

    int selected_suspect = -1;
    int selected_weapon = -1;
    Card room_card = null;

    Notebook notebook;

    String suspect_label = "[SUSPECT]";
    String weapon_label = "[WEAPON]";

    BufferedImage checked_icon = null;
    BufferedImage lock_icon = null;

    ArrayList<Card> suggestion = new ArrayList<Card>();

    private BackgroundImagePanel bgPanel;
    private javax.swing.JButton candle;
    private javax.swing.JButton green;
    private javax.swing.JButton gun;
    private javax.swing.JButton knife;
    private javax.swing.JButton mustard;
    private SuggestionButton okButton;
    private javax.swing.JButton peacock;
    private javax.swing.JButton pipe;
    private javax.swing.JButton plum;
    private javax.swing.JButton rope;
    private javax.swing.JButton scarlet;
    private SuggestionTextArea suggestion_ta;
    private javax.swing.JButton white;
    private javax.swing.JButton wrench;

    public SuggestionDialog2(Frame owner, Card room_card, Notebook notebook) {
        super(owner, true);

        setUndecorated(true);

        this.room_card = room_card;
        this.notebook = notebook;

        initComponents();
    }

    private void initComponents() {

        bgPanel = new BackgroundImagePanel();

        String text = String.format(notebook.getPlayer().toString(), suspect_label, weapon_label, room_card.toString());
        suggestion_ta = new SuggestionTextArea(text, 5, 10);

        okButton = new SuggestionButton("OK");
        okButton.setEnabled(false);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        scarlet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                scarletActionPerformed(evt);
            }
        });

        white.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                whiteActionPerformed(evt);
            }
        });

        plum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                plumActionPerformed(evt);
            }
        });

        green.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                greenActionPerformed(evt);
            }
        });

        mustard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mustardActionPerformed(evt);
            }
        });

        knife.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                knifeActionPerformed(evt);
            }
        });

        peacock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                peacockActionPerformed(evt);
            }
        });

        pipe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pipeActionPerformed(evt);
            }
        });

        rope.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ropeActionPerformed(evt);
            }
        });

        wrench.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wrenchActionPerformed(evt);
            }
        });

        candle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                candleActionPerformed(evt);
            }
        });

        gun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gunActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout bgPanelLayout = new javax.swing.GroupLayout(bgPanel);
        bgPanel.setLayout(bgPanelLayout);
        bgPanelLayout.setHorizontalGroup(
                bgPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(bgPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(bgPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(bgPanelLayout.createSequentialGroup()
                                        .addComponent(scarlet, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(mustard, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(green, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(plum, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(white, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(peacock, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(bgPanelLayout.createSequentialGroup()
                                        .addGroup(bgPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addGroup(bgPanelLayout.createSequentialGroup()
                                                        .addComponent(knife, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(pipe, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(rope, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(wrench, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addComponent(suggestion_ta, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(candle, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(bgPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(gun, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(okButton, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        bgPanelLayout.setVerticalGroup(
                bgPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(bgPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(bgPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(scarlet, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(mustard, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(green, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(plum, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(white, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(peacock, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(bgPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(candle, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(wrench, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(knife, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(pipe, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(rope, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(gun, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(bgPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(suggestion_ta, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(okButton))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(bgPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(bgPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void scarletActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_scarletActionPerformed
        buttonClicked(SUSPECT_SCARLET, TYPE_SUSPECT);
    }//GEN-LAST:event_scarletActionPerformed

    private void whiteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_whiteActionPerformed
        buttonClicked(SUSPECT_WHITE, TYPE_SUSPECT);
    }//GEN-LAST:event_whiteActionPerformed

    private void plumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_plumActionPerformed
        buttonClicked(SUSPECT_PLUM, TYPE_SUSPECT);
    }//GEN-LAST:event_plumActionPerformed

    private void greenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_greenActionPerformed
        buttonClicked(SUSPECT_GREEN, TYPE_SUSPECT);
    }//GEN-LAST:event_greenActionPerformed

    private void mustardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mustardActionPerformed
        buttonClicked(SUSPECT_MUSTARD, TYPE_SUSPECT);
    }//GEN-LAST:event_mustardActionPerformed

    private void knifeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_knifeActionPerformed
        buttonClicked(WEAPON_KNIFE, TYPE_WEAPON);
    }//GEN-LAST:event_knifeActionPerformed

    private void peacockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_peacockActionPerformed
        buttonClicked(SUSPECT_PEACOCK, TYPE_SUSPECT);
    }//GEN-LAST:event_peacockActionPerformed

    private void pipeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pipeActionPerformed
        buttonClicked(WEAPON_PIPE, TYPE_WEAPON);
    }//GEN-LAST:event_pipeActionPerformed

    private void ropeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ropeActionPerformed
        buttonClicked(WEAPON_ROPE, TYPE_WEAPON);
    }//GEN-LAST:event_ropeActionPerformed

    private void wrenchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wrenchActionPerformed
        buttonClicked(WEAPON_WRENCH, TYPE_WEAPON);
    }//GEN-LAST:event_wrenchActionPerformed

    private void candleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_candleActionPerformed
        buttonClicked(WEAPON_CANDLE, TYPE_WEAPON);
    }//GEN-LAST:event_candleActionPerformed

    private void gunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gunActionPerformed
        buttonClicked(WEAPON_REVOLVER, TYPE_WEAPON);
    }//GEN-LAST:event_gunActionPerformed

    public void buttonClicked(int index, int type) {

        if (type == TYPE_SUSPECT) {
            selected_suspect = index;
            suspect_label = new Card(TYPE_SUSPECT, index).toString();
        } else {
            selected_weapon = index;
            weapon_label = new Card(TYPE_WEAPON, index).toString();
        }

        if (selected_weapon != -1 && selected_suspect != -1) {
            okButton.setEnabled(true);
        }

        String text = String.format(notebook.getPlayer().toString(), suspect_label, weapon_label, room_card.toString());
        suggestion_ta.setText(text);

    }

    //return the data after clicking OK
    public Object showDialog() {
        setVisible(true);
        return suggestion;
    }

    class BackgroundImagePanel extends JPanel {

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
        }
    }

    class SuggestionButton extends JButton implements ActionListener {

        SuggestionButton(String text) {
            super(text);
            addActionListener(this);
        }

        public void actionPerformed(ActionEvent arg0) {

            if (selected_suspect == -1 || selected_weapon == -1) {
                return;
            }

            suggestion.add(room_card);
            suggestion.add(new Card(TYPE_SUSPECT, selected_suspect));
            suggestion.add(new Card(TYPE_WEAPON, selected_weapon));

            dispose();
        }
    }

    class SuggestionTextArea extends JTextArea {

        SuggestionTextArea(String text, int rows, int cols) {
            super(text, rows, cols);
            setForeground(Color.white);
            setEditable(false); //uneditable
            setLineWrap(true);
            setHighlighter(null); //unselectable
            setOpaque(false);
        }
    }

    enum ButtonIcon {

        SCARLET("MsScarlett1.png"),
        MUSTARD("ColMustard1.png"),
        GREEN("MrGreen1.png"),
        WHITE("MrsWhite1.png"),
        PLUM("ProfPlum1.png"),
        PEACOCK("MrsPeacock1.png"),
        KNIFE("knife-icon.png"),
        ROPE("rope-icon.png"),
        GUN("gun-icon.png"),
        PIPE("pipe-icon.png"),
        CANDLE("candle-icon.png"),
        WRENCH("wrench-icon.png");

        private BufferedImage image;

        ButtonIcon(String filename) {
            try {
                URL url = this.getClass().getClassLoader().getResource(filename);
                image = ImageIO.read(url);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public BufferedImage get() {
            return image;
        }

        public static void init() {
            values(); // calls the constructor for all the elements
        }
    }
}
