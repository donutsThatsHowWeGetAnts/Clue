/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clue.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

/**
 *
 * @author glender
 */
public class GUI {
	
	
    private final JPanel gui = new JPanel(new BorderLayout(3, 3));
    private JButton[][] gameBoardSquares = new JButton[22][22];
    private JPanel gameBoard;
	private final int GAME_BOARD_DIM = 22;
	private static final int WIDTH = 700;
	private static final int HEIGHT = 700;
	
	jj >= 0 && jj <= 4 && ii >= 0 && ii <= 4

    GUI() {
        initialiseGUI();
    }
	
	public void initialiseGUI() {
        // setup the main GUI
        gui.setBorder(new EmptyBorder(5, 5, 5, 5));
        JToolBar tools = new JToolBar();
        tools.setFloatable(false);
        gui.add(tools, BorderLayout.PAGE_START);
        tools.add(new JButton("Join Game")); // TODO - add functionality!
		tools.addSeparator();
        tools.add(new JButton("Exit Game")); // TODO - add functionality!
        tools.addSeparator();

        gui.add(new JLabel(""), BorderLayout.LINE_START);

        gameBoard = new JPanel(new GridLayout(0, GAME_BOARD_DIM + 1));
        gameBoard.setBorder(new LineBorder(Color.BLACK));
        gui.add(gameBoard);

        // create the board squares
        Insets buttonMargin = new Insets(0,0,0,0);
        for (int ii = 0; ii < gameBoardSquares.length; ii++) {
            for (int jj = 0; jj < gameBoardSquares[ii].length; jj++) {
                JButton b = new JButton();
                b.setMargin(buttonMargin);
				
				// create the different rooms
				if ( jj >= 0 && jj <= 4 && ii >= 0 && ii <= 4 ) {
					
					// STUDY ROOM
					b.setBackground(Color.RED);
					if (ii == 2 && jj >= 0 && jj <= 5 ) {
						switch (jj) {
							case 0:
								b.setText("S");
								break;
							case 1:
								b.setText("T");
								break;
							case 2:
								b.setText("U");
								break;
							case 3:
								b.setText("D");
								break;
							case 4:
								b.setText("Y");
								break;
							default:
								break;
						}
					}
							
				} else if ( jj >= 7 && jj <= 12 && ii >= 0 && ii <= 6 ) {
					
					// HALL ROOM
					b.setBackground(Color.RED);
					if (ii == 3 && jj >= 7 && jj <= 12 ) {
						switch (jj) {
							case 8:
								b.setText("H");
								break;
							case 9:
								b.setText("A");
								break;
							case 10:
								b.setText("L");
								break;
							case 11:
								b.setText("L");
								break;
							default:
								break;
						}
					}
					
				} else if( jj >= 15 && jj <= 21 && ii >= 0 && ii <= 5 ) {
					
					// LOUNGE ROOM
					b.setBackground(Color.RED);
					if (ii == 3 && jj >= 15 && jj <= 21 ) {
						switch (jj) {
							case 15:
								b.setText("L");
								break;
							case 16:
								b.setText("O");
								break;
							case 17:
								b.setText("U");
								break;
							case 18:
								b.setText("N");
								break;
							case 19:
								b.setText("G");
								break;
							case 20:
								b.setText("E");
								break;
							default:
								break;
						}
					}
					
				} else {
					b.setBackground(Color.YELLOW);
				}
                gameBoardSquares[jj][ii] = b;
            }
        }

        // fill the game board
        gameBoard.add(new JLabel(""));
		
        // fill the top row
        for (int ii = 0; ii < GAME_BOARD_DIM; ii++) {
            gameBoard.add(
                    new JLabel("",
                    SwingConstants.CENTER));
        }
		
        // fill the rest of the board
        for (int ii = 0; ii < GAME_BOARD_DIM; ii++) {
            for (int jj = 0; jj < GAME_BOARD_DIM; jj++) {
                switch (jj) {
                    case 0:
                        gameBoard.add(new JLabel("",
                                SwingConstants.CENTER));
                    default:
                        gameBoard.add(gameBoardSquares[jj][ii]);
                }
            }
        }
	}
	
    public final JComponent getChessBoard() {
        return gameBoard;
    }

    public final JComponent getGui() {
        return gui;
    }

    public static void main(String[] args) {
        Runnable r = new Runnable() {

            @Override
            public void run() {
                GUI gui = new GUI();

                JFrame f = new JFrame("Clue Board Game");
				Dimension dimension = new Dimension();
				dimension.height = HEIGHT;
				dimension.width = WIDTH;
				f.setMinimumSize(dimension);
                f.add(gui.getGui());
                f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                f.setLocationByPlatform(true);

                // ensures the frame is the minimum size it needs to be
                // in order display the components within it
                f.pack();
                // ensures the minimum size is enforced.
                f.setMinimumSize(f.getSize());
                f.setVisible(true);
            }
        };
        SwingUtilities.invokeLater(r);
    }
}
