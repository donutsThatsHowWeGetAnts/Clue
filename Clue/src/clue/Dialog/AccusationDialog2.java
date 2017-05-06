package clue.Dialog;

import clue.GamePieces.Card;
import static clue.GamePieces.Card.ROOM_BALLROOM;
import static clue.GamePieces.Card.ROOM_BILLIARD;
import static clue.GamePieces.Card.ROOM_CONSERVATORY;
import static clue.GamePieces.Card.ROOM_DINING;
import static clue.GamePieces.Card.ROOM_HALL;
import static clue.GamePieces.Card.ROOM_KITCHEN;
import static clue.GamePieces.Card.ROOM_LIBRARY;
import static clue.GamePieces.Card.ROOM_LOUNGE;
import static clue.GamePieces.Card.ROOM_STUDY;
import static clue.GamePieces.Card.SUSPECT_GREEN;
import static clue.GamePieces.Card.SUSPECT_MUSTARD;
import static clue.GamePieces.Card.SUSPECT_PEACOCK;
import static clue.GamePieces.Card.SUSPECT_PLUM;
import static clue.GamePieces.Card.SUSPECT_SCARLET;
import static clue.GamePieces.Card.SUSPECT_WHITE;
import static clue.GamePieces.Card.TYPE_ROOM;
import static clue.GamePieces.Card.TYPE_SUSPECT;
import static clue.GamePieces.Card.TYPE_WEAPON;
import static clue.GamePieces.Card.WEAPON_CANDLE;
import static clue.GamePieces.Card.WEAPON_KNIFE;
import static clue.GamePieces.Card.WEAPON_PIPE;
import static clue.GamePieces.Card.WEAPON_REVOLVER;
import static clue.GamePieces.Card.WEAPON_ROPE;
import static clue.GamePieces.Card.WEAPON_WRENCH;
import clue.GamePieces.Notebook;
import java.awt.Frame;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;


public class AccusationDialog2 extends javax.swing.JDialog {

    int selected_suspect = -1;
    int selected_weapon = -1;
    int selected_room = -1;

    Notebook notebook;

    String suspect_label = "[SUSPECT]";
    String weapon_label = "[WEAPON]";
    String room_label = "[ROOM]";

    BufferedImage checked_icon = null;
    BufferedImage lock_icon = null;

    ArrayList<Card> accusation = new ArrayList<Card>();

    public AccusationDialog2(Frame owner, Notebook notebook) {
        super(owner, true);

        setUndecorated(true);

        this.notebook = notebook;

        initComponents();
    }

    //return the data after clicking OK
    public Object showDialog() {
        setVisible(true);
        return accusation;
    }

    public ImageIcon getIcon(BufferedImage image, int type, int value) {
        ImageIcon icon = null;
		icon = new ImageIcon(image);
        return icon;
    }

    private void setButtonInfo(javax.swing.JButton button, String text) {
        //button.setForeground(java.awt.SystemColor.textInactiveText);
        //button.setText(text);
        button.setToolTipText(text);
    }

    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        bg = new javax.swing.JPanel();
        fgPanel = new javax.swing.JPanel();
        scarlet = new javax.swing.JButton();
        white = new javax.swing.JButton();
        plum = new javax.swing.JButton();
        green = new javax.swing.JButton();
        mustard = new javax.swing.JButton();
        knife = new javax.swing.JButton();
        peacock = new javax.swing.JButton();
        pipe = new javax.swing.JButton();
        rope = new javax.swing.JButton();
        wrench = new javax.swing.JButton();
        candle = new javax.swing.JButton();
        gun = new javax.swing.JButton();

        String text = String.format(notebook.getPlayer().toString(), suspect_label, weapon_label, room_label);
        suggestion_ta = new SuggestionTextArea(text, 5, 10);

        cancelButton = new javax.swing.JButton();

        okButton = new javax.swing.JButton();
        okButton.setEnabled(false);

        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        bgLabel = new javax.swing.JLabel();

        bg.setMinimumSize(new java.awt.Dimension(1, 1));
        bg.setLayout(new java.awt.GridBagLayout());

        fgPanel.setOpaque(false);
        fgPanel.setPreferredSize(new java.awt.Dimension(600, 500));

        scarlet = new javax.swing.JButton();
        scarlet.setIcon(getIcon(ButtonIcon.SCARLET.get(), TYPE_SUSPECT, SUSPECT_SCARLET));
        white = new javax.swing.JButton();
        white.setIcon(getIcon(ButtonIcon.WHITE.get(), TYPE_SUSPECT, SUSPECT_WHITE));
        plum = new javax.swing.JButton();
        plum.setIcon(getIcon(ButtonIcon.PLUM.get(), TYPE_SUSPECT, SUSPECT_PLUM));
        green = new javax.swing.JButton();
        green.setIcon(getIcon(ButtonIcon.GREEN.get(), TYPE_SUSPECT, SUSPECT_GREEN));
        mustard = new javax.swing.JButton();
        mustard.setIcon(getIcon(ButtonIcon.MUSTARD.get(), TYPE_SUSPECT, SUSPECT_MUSTARD));
        peacock = new javax.swing.JButton();
        peacock.setIcon(getIcon(ButtonIcon.PEACOCK.get(), TYPE_SUSPECT, SUSPECT_PEACOCK));

        knife = new javax.swing.JButton();
        knife.setIcon(getIcon(ButtonIcon.KNIFE.get(), TYPE_WEAPON, WEAPON_KNIFE));
        pipe = new javax.swing.JButton();
        pipe.setIcon(getIcon(ButtonIcon.PIPE.get(), TYPE_WEAPON, WEAPON_PIPE));
        rope = new javax.swing.JButton();
        rope.setIcon(getIcon(ButtonIcon.ROPE.get(), TYPE_WEAPON, WEAPON_ROPE));
        wrench = new javax.swing.JButton();
        wrench.setIcon(getIcon(ButtonIcon.WRENCH.get(), TYPE_WEAPON, WEAPON_WRENCH));
        candle = new javax.swing.JButton();
        candle.setIcon(getIcon(ButtonIcon.CANDLE.get(), TYPE_WEAPON, WEAPON_CANDLE));
        gun = new javax.swing.JButton();
        gun.setIcon(getIcon(ButtonIcon.GUN.get(), TYPE_WEAPON, WEAPON_REVOLVER));

        library = new javax.swing.JButton();
        library.setIcon(getIcon(ButtonIcon.LIBRARY.get(), TYPE_ROOM, ROOM_LIBRARY));
        study = new javax.swing.JButton();
        study.setIcon(getIcon(ButtonIcon.STUDY.get(), TYPE_ROOM, ROOM_STUDY));
        kitchen = new javax.swing.JButton();
        kitchen.setIcon(getIcon(ButtonIcon.KITCHEN.get(), TYPE_ROOM, ROOM_KITCHEN));
        hall = new javax.swing.JButton();
        hall.setIcon(getIcon(ButtonIcon.HALL.get(), TYPE_ROOM, ROOM_HALL));
        lounge = new javax.swing.JButton();
        lounge.setIcon(getIcon(ButtonIcon.LOUNGE.get(), TYPE_ROOM, ROOM_LOUNGE));
        billiard = new javax.swing.JButton();
        billiard.setIcon(getIcon(ButtonIcon.BILLIARD.get(), TYPE_ROOM, ROOM_BILLIARD));
        conservatory = new javax.swing.JButton();
        conservatory.setIcon(getIcon(ButtonIcon.CONSERVATORY.get(), TYPE_ROOM, ROOM_CONSERVATORY));
        ballroom = new javax.swing.JButton();
        ballroom.setIcon(getIcon(ButtonIcon.BALLROOM.get(), TYPE_ROOM, ROOM_BALLROOM));
        dining = new javax.swing.JButton();
        dining.setIcon(getIcon(ButtonIcon.DINING.get(), TYPE_ROOM, ROOM_DINING));

        setButtonInfo(conservatory, "Conservatory");
        setButtonInfo(library, "Library");
        setButtonInfo(hall, "Hall");
        setButtonInfo(study, "Study");
        setButtonInfo(lounge, "Lounge");
        setButtonInfo(kitchen, "Kitchen");
        setButtonInfo(billiard, "Billiard");
        setButtonInfo(dining, "Dining");
        setButtonInfo(ballroom, "Ballroom");

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

        suggestion_ta.setColumns(20);
        suggestion_ta.setRows(5);

        okButton.setText("OK");
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        conservatory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                conservatoryActionPerformed(evt);
            }
        });

        library.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                libraryActionPerformed(evt);
            }
        });

        study.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                studyActionPerformed(evt);
            }
        });

        hall.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hallActionPerformed(evt);
            }
        });

        billiard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                billiardActionPerformed(evt);
            }
        });

        lounge.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loungeActionPerformed(evt);
            }
        });

        kitchen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kitchenActionPerformed(evt);
            }
        });

        ballroom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ballroomActionPerformed(evt);
            }
        });

        dining.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                diningActionPerformed(evt);
            }
        });

        jLabel3.setText("Select a room:");

        jLabel4.setText("Select a weapon:");

        jLabel5.setText("Select a suspect:");

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        bg.add(bgLabel, gridBagConstraints);

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

    private void conservatoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_conservatoryActionPerformed
        buttonClicked(ROOM_CONSERVATORY, TYPE_ROOM);
    }//GEN-LAST:event_conservatoryActionPerformed

    private void libraryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_libraryActionPerformed
        buttonClicked(ROOM_LIBRARY, TYPE_ROOM);
    }//GEN-LAST:event_libraryActionPerformed

    private void studyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_studyActionPerformed
        buttonClicked(ROOM_STUDY, TYPE_ROOM);
    }//GEN-LAST:event_studyActionPerformed

    private void hallActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hallActionPerformed
        buttonClicked(ROOM_HALL, TYPE_ROOM);
    }//GEN-LAST:event_hallActionPerformed

    private void billiardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_billiardActionPerformed
        buttonClicked(ROOM_BILLIARD, TYPE_ROOM);
    }//GEN-LAST:event_billiardActionPerformed

    private void loungeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loungeActionPerformed
        buttonClicked(ROOM_LOUNGE, TYPE_ROOM);
    }//GEN-LAST:event_loungeActionPerformed

    private void kitchenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kitchenActionPerformed
        buttonClicked(ROOM_KITCHEN, TYPE_ROOM);
    }//GEN-LAST:event_kitchenActionPerformed

    private void ballroomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ballroomActionPerformed
        buttonClicked(ROOM_BALLROOM, TYPE_ROOM);
    }//GEN-LAST:event_ballroomActionPerformed

    private void diningActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_diningActionPerformed
        buttonClicked(ROOM_DINING, TYPE_ROOM);
    }//GEN-LAST:event_diningActionPerformed

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        if (selected_suspect == -1 || selected_weapon == -1 || selected_room == -1) {
            return;
        }

        accusation.add(new Card(TYPE_ROOM, selected_room));
        accusation.add(new Card(TYPE_SUSPECT, selected_suspect));
        accusation.add(new Card(TYPE_WEAPON, selected_weapon));

        dispose();
    }//GEN-LAST:event_okButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        accusation = null;
        dispose();

    }//GEN-LAST:event_cancelButtonActionPerformed

    public void buttonClicked(int index, int type) {

        if (type == TYPE_SUSPECT) {
            selected_suspect = index;
            suspect_label = new Card(TYPE_SUSPECT, index).toString();

        } else if (type == TYPE_ROOM) {
            selected_room = index;
            room_label = new Card(TYPE_ROOM, index).toString();
        } else {
            selected_weapon = index;
            weapon_label = new Card(TYPE_WEAPON, index).toString();
        }

        if (selected_weapon != -1 && selected_suspect != -1 && selected_room != -1) {
            okButton.setEnabled(true);
        }

        String text = String.format(notebook.getPlayer().toString(), suspect_label, weapon_label, room_label);
        suggestion_ta.setText(text);

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ballroom;
    private javax.swing.JPanel bg;
    private javax.swing.JLabel bgLabel;
    private javax.swing.JButton billiard;
    private javax.swing.JButton candle;
    private javax.swing.JButton conservatory;
    private javax.swing.JButton dining;
    private javax.swing.JPanel fgPanel;
    private javax.swing.JButton green;
    private javax.swing.JButton gun;
    private javax.swing.JButton hall;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JButton kitchen;
    private javax.swing.JButton knife;
    private javax.swing.JButton library;
    private javax.swing.JButton lounge;
    private javax.swing.JButton mustard;
    private javax.swing.JButton okButton;
    private javax.swing.JButton peacock;
    private javax.swing.JButton pipe;
    private javax.swing.JButton plum;
    private javax.swing.JButton rope;
    private javax.swing.JButton scarlet;
    private javax.swing.JButton study;
    private SuggestionTextArea suggestion_ta;
    private javax.swing.JButton white;
    private javax.swing.JButton wrench;
    private javax.swing.JButton cancelButton;

    // End of variables declaration//GEN-END:variables
    class SuggestionTextArea extends JTextArea {

        SuggestionTextArea(String text, int rows, int cols) {
            super(text, rows, cols);
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
        WRENCH("wrench-icon.png"),
        STUDY("Study-icon.png"),
        HALL("Hall-icon.png"),
        CONSERVATORY("Conservatory-icon.png"),
        BILLIARD("billiard-icon.png"),
        DINING("DiningRoom-icon.png"),
        BALLROOM("Ballroom-icon.png"),
        KITCHEN("Kitchen-icon.png"),
        LOUNGE("Lounge-icon.png"),
        LIBRARY("Library-icon.png");

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
