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
import clue.GamePieces.Player;
import java.util.ArrayList;

public class PickCardsToShowDialog2 extends javax.swing.JDialog {

    public PickCardsToShowDialog2(ArrayList<Card> suggestion, String suggestion_text, Player player) {

        this.suggestion = suggestion;
        this.player = player;

        setModal(true);
        setUndecorated(true);

        initComponents();

        suggestion_ta.setText(player.getPlayerName() + ", pick a card to show\naccording to the suggestion.\n\n" + suggestion_text);

        ArrayList<Card> cards_in_hand = player.getCardsInHand();

        boolean has_a_card = false;

        for (Card card : cards_in_hand) {

            if (!suggestion.contains(card)) {
                continue;
            }

            has_a_card = true;

            int type = card.getType();
            int value = card.getValue();

            if (type == TYPE_SUSPECT) {
                if (value == SUSPECT_SCARLET) {
                    scarlet_rb.setEnabled(true);
                }
                if (value == SUSPECT_MUSTARD) {
                    mustard_rb.setEnabled(true);
                }
                if (value == SUSPECT_GREEN) {
                    green_rb.setEnabled(true);
                }
                if (value == SUSPECT_PLUM) {
                    plum_rb.setEnabled(true);
                }
                if (value == SUSPECT_WHITE) {
                    white_rb.setEnabled(true);
                }
                if (value == SUSPECT_PEACOCK) {
                    peacock_rb.setEnabled(true);
                }
            } else if (type == TYPE_WEAPON) {
                if (value == WEAPON_REVOLVER) {
                    revolver_rb.setEnabled(true);
                }
                if (value == WEAPON_PIPE) {
                    pipe_rb.setEnabled(true);
                }
                if (value == WEAPON_ROPE) {
                    rope_rb.setEnabled(true);
                }
                if (value == WEAPON_CANDLE) {
                    candlestick_rb.setEnabled(true);
                }
                if (value == WEAPON_WRENCH) {
                    wrench_rb.setEnabled(true);
                }
                if (value == WEAPON_KNIFE) {
                    knife_rb.setEnabled(true);
                }
            } else {
                if (value == ROOM_KITCHEN) {
                    kitchen_rb.setEnabled(true);
                }
                if (value == ROOM_BALLROOM) {
                    ballroom_rb.setEnabled(true);
                }
                if (value == ROOM_CONSERVATORY) {
                    conservatory_rb.setEnabled(true);
                }
                if (value == ROOM_BILLIARD) {
                    billiard_rb.setEnabled(true);
                }
                if (value == ROOM_LIBRARY) {
                    library_rb.setEnabled(true);
                }
                if (value == ROOM_STUDY) {
                    study_rb.setEnabled(true);
                }
                if (value == ROOM_HALL) {
                    hall_rb.setEnabled(true);
                }
                if (value == ROOM_LOUNGE) {
                    lounge_rb.setEnabled(true);
                }
                if (value == ROOM_DINING) {
                    dining_rb.setEnabled(true);
                }
            }

        }

        //let them click OK if they have no cards to show
        if (!has_a_card) {
            okButton.setEnabled(true);
        }

    }

    //return the data after clicking OK
    public Card showDialog() {
        setVisible(true);
        return picked_card;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        buttonGroup1 = new javax.swing.ButtonGroup();
        bg = new javax.swing.JPanel();
        fgPanel = new javax.swing.JPanel();
        scarlet_rb = new javax.swing.JRadioButton();
        mustard_rb = new javax.swing.JRadioButton();
        green_rb = new javax.swing.JRadioButton();
        plum_rb = new javax.swing.JRadioButton();
        white_rb = new javax.swing.JRadioButton();
        peacock_rb = new javax.swing.JRadioButton();
        revolver_rb = new javax.swing.JRadioButton();
        candlestick_rb = new javax.swing.JRadioButton();
        rope_rb = new javax.swing.JRadioButton();
        pipe_rb = new javax.swing.JRadioButton();
        wrench_rb = new javax.swing.JRadioButton();
        knife_rb = new javax.swing.JRadioButton();
        kitchen_rb = new javax.swing.JRadioButton();
        ballroom_rb = new javax.swing.JRadioButton();
        conservatory_rb = new javax.swing.JRadioButton();
        billiard_rb = new javax.swing.JRadioButton();
        library_rb = new javax.swing.JRadioButton();
        study_rb = new javax.swing.JRadioButton();
        hall_rb = new javax.swing.JRadioButton();
        lounge_rb = new javax.swing.JRadioButton();
        dining_rb = new javax.swing.JRadioButton();
        okButton = new javax.swing.JButton();
        suggestion_ta = new javax.swing.JTextArea();
        bgLabel = new javax.swing.JLabel();

        bg.setMinimumSize(new java.awt.Dimension(1, 1));
        bg.setLayout(new java.awt.GridBagLayout());

        fgPanel.setOpaque(false);

        scarlet_rb.addItemListener(new PickItemListener());
        mustard_rb.addItemListener(new PickItemListener());
        green_rb.addItemListener(new PickItemListener());
        plum_rb.addItemListener(new PickItemListener());
        white_rb.addItemListener(new PickItemListener());
        peacock_rb.addItemListener(new PickItemListener());

        revolver_rb.addItemListener(new PickItemListener());
        candlestick_rb.addItemListener(new PickItemListener());
        rope_rb.addItemListener(new PickItemListener());
        pipe_rb.addItemListener(new PickItemListener());
        wrench_rb.addItemListener(new PickItemListener());
        knife_rb.addItemListener(new PickItemListener());

        kitchen_rb.addItemListener(new PickItemListener());
        ballroom_rb.addItemListener(new PickItemListener());
        conservatory_rb.addItemListener(new PickItemListener());
        billiard_rb.addItemListener(new PickItemListener());
        library_rb.addItemListener(new PickItemListener());
        study_rb.addItemListener(new PickItemListener());
        hall_rb.addItemListener(new PickItemListener());
        lounge_rb.addItemListener(new PickItemListener());
        dining_rb.addItemListener(new PickItemListener());

        scarlet_rb.setEnabled(false);
        mustard_rb.setEnabled(false);
        green_rb.setEnabled(false);
        plum_rb.setEnabled(false);
        white_rb.setEnabled(false);
        peacock_rb.setEnabled(false);

        revolver_rb.setEnabled(false);
        candlestick_rb.setEnabled(false);
        rope_rb.setEnabled(false);
        pipe_rb.setEnabled(false);
        wrench_rb.setEnabled(false);
        knife_rb.setEnabled(false);

        kitchen_rb.setEnabled(false);
        ballroom_rb.setEnabled(false);
        conservatory_rb.setEnabled(false);
        billiard_rb.setEnabled(false);
        library_rb.setEnabled(false);
        study_rb.setEnabled(false);
        hall_rb.setEnabled(false);
        lounge_rb.setEnabled(false);
        dining_rb.setEnabled(false);

        okButton.setEnabled(false);

        buttonGroup1.add(scarlet_rb);
        scarlet_rb.setText("Miss Scarlet");
        scarlet_rb.setOpaque(false);

        buttonGroup1.add(mustard_rb);
        mustard_rb.setText("Colonel Mustard");
        mustard_rb.setOpaque(false);

        buttonGroup1.add(green_rb);
        green_rb.setText("Mr. Green");
        green_rb.setOpaque(false);

        buttonGroup1.add(plum_rb);
        plum_rb.setText("Professor Plum");
        plum_rb.setOpaque(false);

        buttonGroup1.add(white_rb);
        white_rb.setText("Mrs. White");
        white_rb.setOpaque(false);

        buttonGroup1.add(peacock_rb);
        peacock_rb.setText("Mrs. Peacock");
        peacock_rb.setOpaque(false);

        buttonGroup1.add(revolver_rb);
        revolver_rb.setText("Revolver");
        revolver_rb.setOpaque(false);

        buttonGroup1.add(candlestick_rb);
        candlestick_rb.setText("Candlelabra");
        candlestick_rb.setOpaque(false);

        buttonGroup1.add(rope_rb);
        rope_rb.setText("Rope");
        rope_rb.setOpaque(false);

        buttonGroup1.add(pipe_rb);
        pipe_rb.setText("Lead Pipe");
        pipe_rb.setOpaque(false);

        buttonGroup1.add(wrench_rb);
        wrench_rb.setText("Wrench");
        wrench_rb.setOpaque(false);

        buttonGroup1.add(knife_rb);
        knife_rb.setText("Knife");
        knife_rb.setOpaque(false);

        buttonGroup1.add(kitchen_rb);
        kitchen_rb.setText("Kitchen");
        kitchen_rb.setOpaque(false);

        buttonGroup1.add(ballroom_rb);
        ballroom_rb.setText("Ballroom");
        ballroom_rb.setOpaque(false);

        buttonGroup1.add(conservatory_rb);
        conservatory_rb.setText("Conservatory");
        conservatory_rb.setOpaque(false);

        buttonGroup1.add(billiard_rb);
        billiard_rb.setText("Billiard Room");
        billiard_rb.setOpaque(false);

        buttonGroup1.add(library_rb);
        library_rb.setText("Library");
        library_rb.setOpaque(false);

        buttonGroup1.add(study_rb);
        study_rb.setText("Study");
        study_rb.setOpaque(false);

        buttonGroup1.add(hall_rb);
        hall_rb.setText("Hall");
        hall_rb.setOpaque(false);

        buttonGroup1.add(lounge_rb);
        lounge_rb.setText("Lounge");
        lounge_rb.setOpaque(false);

        buttonGroup1.add(dining_rb);
        dining_rb.setText("Dining Room");
        dining_rb.setOpaque(false);

        okButton.setText("OK");
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        suggestion_ta.setColumns(20);
        suggestion_ta.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 14)); // NOI18N
        suggestion_ta.setRows(5);
        suggestion_ta.setText("sample text");
        suggestion_ta.setBorder(null);
        suggestion_ta.setOpaque(false);

        bg.add(fgPanel, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        bg.add(bgLabel, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void okButtonActionPerformed(java.awt.event.ActionEvent evt) {

        if (scarlet_rb.isSelected()) {
            picked_card = new Card(TYPE_SUSPECT, SUSPECT_SCARLET);
        }
        if (mustard_rb.isSelected()) {
            picked_card = new Card(TYPE_SUSPECT, SUSPECT_MUSTARD);
        }
        if (green_rb.isSelected()) {
            picked_card = new Card(TYPE_SUSPECT, SUSPECT_GREEN);
        }
        if (plum_rb.isSelected()) {
            picked_card = new Card(TYPE_SUSPECT, SUSPECT_PLUM);
        }
        if (white_rb.isSelected()) {
            picked_card = new Card(TYPE_SUSPECT, SUSPECT_WHITE);
        }
        if (peacock_rb.isSelected()) {
            picked_card = new Card(TYPE_SUSPECT, SUSPECT_PEACOCK);
        }

        if (revolver_rb.isSelected()) {
            picked_card = new Card(TYPE_WEAPON, WEAPON_REVOLVER);
        }
        if (candlestick_rb.isSelected()) {
            picked_card = new Card(TYPE_WEAPON, WEAPON_CANDLE);
        }
        if (rope_rb.isSelected()) {
            picked_card = new Card(TYPE_WEAPON, WEAPON_ROPE);
        }
        if (pipe_rb.isSelected()) {
            picked_card = new Card(TYPE_WEAPON, WEAPON_PIPE);
        }
        if (wrench_rb.isSelected()) {
            picked_card = new Card(TYPE_WEAPON, WEAPON_WRENCH);
        }
        if (knife_rb.isSelected()) {
            picked_card = new Card(TYPE_WEAPON, WEAPON_KNIFE);
        }

        if (kitchen_rb.isSelected()) {
            picked_card = new Card(TYPE_ROOM, ROOM_KITCHEN);
        }
        if (ballroom_rb.isSelected()) {
            picked_card = new Card(TYPE_ROOM, ROOM_BALLROOM);
        }
        if (conservatory_rb.isSelected()) {
            picked_card = new Card(TYPE_ROOM, ROOM_CONSERVATORY);
        }
        if (billiard_rb.isSelected()) {
            picked_card = new Card(TYPE_ROOM, ROOM_BILLIARD);
        }
        if (library_rb.isSelected()) {
            picked_card = new Card(TYPE_ROOM, ROOM_LIBRARY);
        }
        if (study_rb.isSelected()) {
            picked_card = new Card(TYPE_ROOM, ROOM_STUDY);
        }
        if (hall_rb.isSelected()) {
            picked_card = new Card(TYPE_ROOM, ROOM_HALL);
        }
        if (lounge_rb.isSelected()) {
            picked_card = new Card(TYPE_ROOM, ROOM_LOUNGE);
        }
        if (dining_rb.isSelected()) {
            picked_card = new Card(TYPE_ROOM, ROOM_DINING);
        }

        dispose();
    }

    class PickItemListener implements java.awt.event.ItemListener {

        public void itemStateChanged(java.awt.event.ItemEvent evt) {
            okButton.setEnabled(true);
        }
    }

    private ArrayList<Card> suggestion;
    private Player player;
    private Card picked_card = null;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton ballroom_rb;
    private javax.swing.JPanel bg;
    private javax.swing.JLabel bgLabel;
    private javax.swing.JRadioButton billiard_rb;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JRadioButton candlestick_rb;
    private javax.swing.JRadioButton conservatory_rb;
    private javax.swing.JRadioButton dining_rb;
    private javax.swing.JPanel fgPanel;
    private javax.swing.JRadioButton green_rb;
    private javax.swing.JRadioButton hall_rb;
    private javax.swing.JRadioButton kitchen_rb;
    private javax.swing.JRadioButton knife_rb;
    private javax.swing.JRadioButton library_rb;
    private javax.swing.JRadioButton lounge_rb;
    private javax.swing.JRadioButton mustard_rb;
    private javax.swing.JButton okButton;
    private javax.swing.JRadioButton peacock_rb;
    private javax.swing.JRadioButton pipe_rb;
    private javax.swing.JRadioButton plum_rb;
    private javax.swing.JRadioButton revolver_rb;
    private javax.swing.JRadioButton rope_rb;
    private javax.swing.JRadioButton scarlet_rb;
    private javax.swing.JRadioButton study_rb;
    private javax.swing.JTextArea suggestion_ta;
    private javax.swing.JRadioButton white_rb;
    private javax.swing.JRadioButton wrench_rb;
    // End of variables declaration//GEN-END:variables

}
