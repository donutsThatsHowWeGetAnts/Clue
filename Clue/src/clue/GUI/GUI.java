/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clue.GUI;

import clue.Utilities.BoardGameManager;
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
	private BoardGameManager boardGameManager = null;
	
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
				if (jj >= 0 && jj <= 3 && ii >= 0 && ii <= 4) {
					
					// STUDY ROOM
					b.setBackground(Color.RED);
					
					// set the door(s)
					if (ii == 4 && jj == 3) {
						b.setBackground(Color.BLACK);
						BoardGameManager.getInstance().setCanClickButton(ii, jj, true);
						BoardGameManager.getInstance().setIsDoorButton(ii, jj, true);
					} else {
						b.enableInputMethods(false);
						b.setSelected(false);
						b.setBorderPainted(false);
						b.setFocusPainted(false);
						BoardGameManager.getInstance().setCanClickButton(ii, jj, false);
						BoardGameManager.getInstance().setIsDoorButton(ii, jj, false);
					}
					
					if (ii == 2 && jj >= 0 && jj <= 4) {
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
								b.setText("DY");
								break;
							case 4:
								b.setText("Y");
								break;
							default:
								break;
						}
					}
							
				} else if (jj >= 6 && jj <= 12 && ii >= 0 && ii <= 6) {
					
					// HALL ROOM
					b.setBackground(Color.RED);
					
					// set the door(s)
					if ((ii == 6 && (jj == 8 || jj == 9)) || (ii == 3 && jj == 6)) {
						b.setBackground(Color.BLACK);
						BoardGameManager.getInstance().setCanClickButton(ii, jj, true);
						BoardGameManager.getInstance().setIsDoorButton(ii, jj, true);
					} else {
						b.enableInputMethods(false);
						b.setSelected(false);
						b.setBorderPainted(false);
						b.setFocusPainted(false);
						BoardGameManager.getInstance().setCanClickButton(ii, jj, false);
						BoardGameManager.getInstance().setIsDoorButton(ii, jj, false);
					}
					
					if (ii == 3 && jj >= 7 && jj <= 12 ) {
						switch (jj) {
							case 7:
								b.setText("H");
								break;
							case 8:
								b.setText("A");
								break;
							case 9:
								b.setText("L");
								break;
							case 10:
								b.setText("L");
								break;
							default:
								break;
						}
					}
					
				} else if(jj >= 15 && jj <= 21 && ii >= 0 && ii <= 5) {
					
					// LOUNGE ROOM
					b.setBackground(Color.RED);
					
					// set the door(s)
					if (ii == 5 && jj == 15) {
						b.setBackground(Color.BLACK);
						BoardGameManager.getInstance().setCanClickButton(ii, jj, true);
						BoardGameManager.getInstance().setIsDoorButton(ii, jj, true);
					} else {
						b.enableInputMethods(false);
						b.setSelected(false);
						b.setBorderPainted(false);
						b.setFocusPainted(false);
						BoardGameManager.getInstance().setCanClickButton(ii, jj, false);
						BoardGameManager.getInstance().setIsDoorButton(ii, jj, false);
					}
					
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
					
				} else if (jj >= 0 && jj <= 3 && ii >= 7 && ii <= 10) {
					
					// LIBRARY ROOM
					b.setBackground(Color.RED);
					
					// set the door(s)
					if ((ii == 8 && jj == 3) || (ii == 10 && jj == 1)) {
						b.setBackground(Color.BLACK);
						BoardGameManager.getInstance().setCanClickButton(ii, jj, true);
						BoardGameManager.getInstance().setIsDoorButton(ii, jj, true);
					} else {
						b.enableInputMethods(false);
						b.setSelected(false);
						b.setBorderPainted(false);
						b.setFocusPainted(false);
						BoardGameManager.getInstance().setCanClickButton(ii, jj, false);
						BoardGameManager.getInstance().setIsDoorButton(ii, jj, false);
					}
					
					if (ii == 9 && jj >= 0 && jj <= 4) {
						switch (jj) {
							case 0:
								b.setText("LI");
								break;
							case 1:
								b.setText("BR");
								break;
							case 2:
								b.setText("AR");
								break;
							case 3:
								b.setText("Y");
								break;
							case 4:
								break;
							default:
								break;
						}
					}
				} else if(jj >= 0 && jj <= 3 && ii >= 13 && ii <= 16) {
					
					// BILLARDS ROOM
					b.setBackground(Color.RED);
					
					// set the door(s)
					if ((ii == 13 && jj == 0) || (ii == 15 && jj == 3)) {
						b.setBackground(Color.BLACK);
						BoardGameManager.getInstance().setCanClickButton(ii, jj, true);
						BoardGameManager.getInstance().setIsDoorButton(ii, jj, true);
					} else {
						b.enableInputMethods(false);
						b.setSelected(false);
						b.setBorderPainted(false);
						b.setFocusPainted(false);
						BoardGameManager.getInstance().setCanClickButton(ii, jj, false);
						BoardGameManager.getInstance().setIsDoorButton(ii, jj, false);
					}
					
					if (ii == 14 && jj >= 0 && jj <= 3) {
						switch (jj) {
							case 0:
								b.setText("BI");
								break;
							case 1:
								b.setText("LL");
								break;
							case 2:
								b.setText("AR");
								break;
							case 3:
								b.setText("DS");
								break;
							case 4:
								break;
							default:
								break;
						}
					}
					
				} else if (jj >= 0 && jj <= 3 && ii >= 19 && ii <= 21) {
					
					// CONSERVATORY ROOM
					b.setBackground(Color.RED);
					
					// set the door(s)
					if (ii == 19 && jj == 3) {
						b.setBackground(Color.BLACK);
						BoardGameManager.getInstance().setCanClickButton(ii, jj, true);
						BoardGameManager.getInstance().setIsDoorButton(ii, jj, true);
					} else {
						b.enableInputMethods(false);
						b.setSelected(false);
						b.setBorderPainted(false);
						b.setFocusPainted(false);
						BoardGameManager.getInstance().setCanClickButton(ii, jj, false);
						BoardGameManager.getInstance().setIsDoorButton(ii, jj, false);
					}
					
					if (ii == 20 && jj >= 0 && jj <= 3) {
						switch (jj) {
							case 0:
								b.setText("CON");
								break;
							case 1:
								b.setText("SER");
								break;
							case 2:
								b.setText("VAT");
								break;
							case 3:
								b.setText("ORY");
								break;
							case 4:
								break;
							default:
								break;
						}
					}
					
				} else if (jj >= 6 && jj <= 12 && ii >= 15 && ii <= 21) {
					
					// BALLROOM ROOM
					b.setBackground(Color.RED);
					
					// set the door(s)
					if ((ii == 18 && jj == 6) || (ii == 18 && jj == 12) || (ii == 15 && jj == 7) || (ii == 15 && jj == 11)) {
						b.setBackground(Color.BLACK);
						BoardGameManager.getInstance().setCanClickButton(ii, jj, true);
						BoardGameManager.getInstance().setIsDoorButton(ii, jj, true);
					} else {
						b.enableInputMethods(false);
						b.setSelected(false);
						b.setBorderPainted(false);
						b.setFocusPainted(false);
						BoardGameManager.getInstance().setCanClickButton(ii, jj, false);
						BoardGameManager.getInstance().setIsDoorButton(ii, jj, false);
					}
					
					if (ii == 18 && jj >= 7 && jj <= 10 ) {
						switch (jj) {
							case 7:
								b.setText("BA");
								break;
							case 8:
								b.setText("LL");
								break;
							case 9:
								b.setText("RO");
								break;
							case 10:
								b.setText("OM");
								break;
							default:
								break;
						}
					}
					
				} else if(jj >= 15 && jj <= 21 && ii >= 16 && ii <= 21) {
					
					// KITCHEN ROOM
					b.setBackground(Color.RED);
					
					// set the door(s)
					if (ii == 16 && jj == 16) {
						b.setBackground(Color.BLACK);
						BoardGameManager.getInstance().setCanClickButton(ii, jj, true);
						BoardGameManager.getInstance().setIsDoorButton(ii, jj, true);
					} else {
						b.enableInputMethods(false);
						b.setSelected(false);
						b.setBorderPainted(false);
						b.setFocusPainted(false);
						BoardGameManager.getInstance().setCanClickButton(ii, jj, false);
						BoardGameManager.getInstance().setIsDoorButton(ii, jj, false);
					}
					
					if (ii == 18 && jj >= 16 && jj <= 19) {
						switch (jj) {
							case 16:
								b.setText("KI");
								break;
							case 17:
								b.setText("TC");
								break;
							case 18:
								b.setText("HE");
								break;
							case 19:
								b.setText("N");
								break;
							case 4:
								break;
							default:
								break;
						}
					}
					
				} else if(jj >= 15 && jj <= 21 && ii >= 9 && ii <= 13) {
					
					// DINING ROOM
					b.setBackground(Color.RED);
					
					// set the door(s)
					if ((ii == 11 && jj == 15) || (ii == 9 && jj == 17)) {
						b.setBackground(Color.BLACK);
						BoardGameManager.getInstance().setCanClickButton(ii, jj, true);
						BoardGameManager.getInstance().setIsDoorButton(ii, jj, true);
					} else {
						b.enableInputMethods(false);
						b.setSelected(false);
						b.setBorderPainted(false);
						b.setFocusPainted(false);
						BoardGameManager.getInstance().setCanClickButton(ii, jj, false);
						BoardGameManager.getInstance().setIsDoorButton(ii, jj, false);
					}
					
					if (ii == 11 && jj >= 16 && jj <= 20) {
						switch (jj) {
							case 16:
								b.setText("DI");
								break;
							case 17:
								b.setText("NI");
								break;
							case 18:
								b.setText("NG");
								break;
							case 19:
								b.setText("RO");
								break;
							case 20:
								b.setText("OM");
								break;
							default:
								break;
						}
					}
					
				} else if (jj >= 7 && jj <= 11 && ii >= 8 && ii <= 12) {
					
					// CONFIDENTIAL
					b.setBackground(Color.GRAY);
					b.enableInputMethods(false);
					b.setSelected(false);
					b.setBorderPainted(false);
					b.setFocusPainted(false);
					BoardGameManager.getInstance().setCanClickButton(ii, jj, false);
					BoardGameManager.getInstance().setIsDoorButton(ii, jj, false);
					
				} else {
					b.setBackground(Color.YELLOW);
					BoardGameManager.getInstance().setCanClickButton(ii, jj, true);
					BoardGameManager.getInstance().setIsDoorButton(ii, jj, false);
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
	
    public final JComponent getGameBoard() {
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
