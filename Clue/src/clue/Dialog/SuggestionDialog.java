package clue.Dialog;

import clue.GamePieces.Card;
import clue.GamePieces.Notebook;
import static clue.GamePieces.Card.TYPE_SUSPECT;
import static clue.GamePieces.Card.TYPE_WEAPON;
import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;

public class SuggestionDialog extends JDialog {

    int selected_suspect = -1;
    int selected_weapon = -1;
    Card room_card = null;

    Notebook notebook;

    SuggestionTextArea suggestion_text;
    String suspect_label = "[SUSPECT]";
    String weapon_label = "[WEAPON]";
    SuggestionButton suggest_button;

    BufferedImage checked_icon = null;
    BufferedImage lock_icon = null;

    ArrayList<Card> suggestion = new ArrayList<Card>();

    public SuggestionDialog(Frame owner, Card room_card, Notebook notebook) {
        super(owner, "Suggestion Dialog", true);

        setUndecorated(true);

        this.room_card = room_card;
        this.notebook = notebook;

        JPanel suggestion_panel = new JPanel();
        suggestion_panel.setLayout(new FlowLayout());
        suggestion_panel.setOpaque(false);

        String text = String.format(notebook.getPlayer().toString(), suspect_label, weapon_label, room_card.toString());

        suggestion_text = new SuggestionTextArea(text, 3, 10);
        suggestion_text.setPreferredSize(new Dimension(350, 100));
        suggestion_panel.add(suggestion_text);

        JPanel button_panel = new JPanel();
        suggest_button = new SuggestionButton("Make Suggestion", this);
        suggest_button.setEnabled(false);
        button_panel.add(suggest_button);
        button_panel.setOpaque(false);

        JPanel top = new JPanel(new BorderLayout());
        top.setOpaque(false);
        top.add(suggestion_panel, BorderLayout.SOUTH);

        BackgroundImagePanel main = new BackgroundImagePanel();
        main.setLayout(new BorderLayout());
        main.add(top, BorderLayout.CENTER);
        main.add(button_panel, BorderLayout.SOUTH);

        this.add(main, BorderLayout.CENTER);

        setSize(800, 450);
        setLocationRelativeTo(null);

    }

    //return the data after clicking OK
    public Object showDialog() {
        setVisible(true);
        return suggestion;
    }

    class BackgroundImagePanel extends JPanel {}

    class SuggestionButton extends JButton implements ActionListener {

        JDialog dialog = null;

        SuggestionButton(String text, JDialog dialog) {
            super(text);
            this.dialog = dialog;
            addActionListener(this);
        }

        public void actionPerformed(ActionEvent arg0) {

            if (selected_suspect == -1 || selected_weapon == -1) {
                return;
            }

            suggestion.add(room_card);
            suggestion.add(new Card(TYPE_SUSPECT, selected_suspect));
            suggestion.add(new Card(TYPE_WEAPON, selected_weapon));

            dialog.dispose();
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

    class ImagePanel extends JPanel implements MouseListener {

        private double m_zoomPercentage;
        private BufferedImage m_image;
        int index = 0;
        int type = 0;

        ImagePanel(BufferedImage image, int type, int index, double zoom) {
            this.index = index;
            this.type = type;
            addMouseListener(this);
        }

        public void paintComponent(Graphics grp) {
            Graphics2D g2D = (Graphics2D) grp;
            g2D.fillRect(0, 0, getWidth(), getHeight());

            g2D.setComposite(AlphaComposite.Src);
            g2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            BufferedImage image = m_image;
            g2D.drawImage(image, 0, 0, this);

        }

        public Dimension getPreferredSize() {
            return new Dimension(m_image.getWidth(), m_image.getHeight());
        }

        public void mouseClicked(MouseEvent e) {

            if (type == TYPE_SUSPECT) {
                selected_suspect = index;
                suspect_label = new Card(TYPE_SUSPECT, index).toString();
            } else {
                selected_weapon = index;
                weapon_label = new Card(TYPE_WEAPON, index).toString();
            }

            if (selected_weapon != -1 && selected_suspect != -1) {
                suggest_button.setEnabled(true);
            }

            String text = String.format(notebook.getPlayer().toString(), suspect_label, weapon_label, room_card.toString());
            if (suggestion_text != null) {
                suggestion_text.setText(text);
            }

        }

        public void mousePressed(MouseEvent e) {
        }

        public void mouseReleased(MouseEvent e) {
        }

        public void mouseEntered(MouseEvent e) {
            setBorder(new BevelBorder(BevelBorder.RAISED));

            Cursor cursor = Cursor.getDefaultCursor();
            cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
            setCursor(cursor);

        }

        public void mouseExited(MouseEvent e) {
            setBorder(new BevelBorder(BevelBorder.LOWERED));

            Cursor cursor = Cursor.getDefaultCursor();
            cursor = Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);
            setCursor(cursor);
        }
    }

}
