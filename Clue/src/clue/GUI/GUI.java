/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clue.GUI;

import clue.Clue;
import clue.GameEngine.CardManager;
import clue.GamePieces.Card;
import clue.GamePieces.Die;
import clue.GamePieces.Player;
import clue.Helpers.Location;
import clue.Network.Client;
import clue.Network.ClientInstance;
import clue.Network.ClientListener;
import clue.Network.Server;
import clue.Network.ServerListener;
import clue.Utilities.BoardGameManager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.io.PrintWriter;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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

	
	Player scarletPlayer = new Player();
	Player greenPlayer = new Player();
	Player whitePlayer = new Player();
	Player profPlumPlayer = new Player();
	Player mrsPeacock = new Player();
	Player colMustardPlayer = new Player();
	
	private Clue clue = new Clue();
	
    private final JPanel gui = new JPanel(new BorderLayout(3, 3));
    private JButton[][] gameBoardSquares = new JButton[22][22];
    private JPanel gameBoard;
	private final int GAME_BOARD_DIM = 22;
	private static final int WIDTH = 700;
	private static final int HEIGHT = 700;
	private BoardGameManager boardGameManager = null;
	JButton ready = new JButton("Ready");
	JButton roll = new JButton("Roll Die");
	JButton viewCards = new JButton("View Cards");
	JButton takeNotes = new JButton("Take Notes");
	private List<ClientInstance> playerList = new ArrayList<ClientInstance>();
	private String serverIP = "";
	private String clientIP = "";
	private Server server = null;
	private ServerListener serverListener = new ServerListener() {

		@Override
		public void clientConncted(ClientInstance client, PrintWriter out) {
			System.out.println("LENDER -- client connected: " + client.ip);
			playerList.add(new ClientInstance(client.ip, client.port));
		}

		@Override
		public void clientDisconnected(ClientInstance client) {
			playerList.remove(new ClientInstance(client.ip, client.port));
		}

		@Override
		public void recivedInput(ClientInstance client, String msg) {
			System.out.println("LENDER -- received message from " + client.ip + " with message: " + msg);
		}

		@Override
		public void serverClosed() {
			System.out.println("LENDER -- server closed");
		}
	};
	private final int defaultServerPort = 6666;
	private final int defaultClientPort = 7777;
	
	private Client client = null;
	private ClientListener clientListener = new ClientListener() {

		@Override
		public void unknownHost() {
			System.out.println("LENDER -- unknown host!");
		}

		@Override
		public void couldNotConnect() {
			System.out.println("LENDER -- could not connect to server");
		}

		@Override
		public void recivedInput(String msg) {
			System.out.println("LENDER -- received message: " + msg);
		}

		@Override
		public void serverClosed() {
			System.out.println("LENDER -- server is closed");
		}

		@Override
		public void disconnected() {
			System.out.println("LENDER -- server is disconnected");
		}

		@Override
		public void connectedToServer() {
			System.out.println("We have an incomming player!");
		}
	};
	
    GUI() {
        initialiseGUI();
    }
	
	public void enableReadyButton() {
		ready.setVisible(true);
	}
	
	public void initialiseServer() {
		try(final DatagramSocket socket = new DatagramSocket()){
			socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
			serverIP = socket.getLocalAddress().getHostAddress();
			System.out.println("LENDER -- server ip is + " + serverIP);
			server = new Server(defaultServerPort, serverListener);
			System.out.println("LENDER -- server ip is now " + server.getIp());
		} catch (UnknownHostException ex) {
			Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
		} catch (SocketException ex) {
			Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	public void initialiseDefaultPlayers() {
		
		// give each player their card
		scarletPlayer.addCard(Card.scarlet);
		greenPlayer.addCard(Card.green);
		whitePlayer.addCard(Card.white);
		profPlumPlayer.addCard(Card.plum);
		mrsPeacock.addCard(Card.peacock);
		colMustardPlayer.addCard(Card.mustard);
		
		clue.addPlayer(Card.green, "MrGreen", Color.GREEN, false);
		clue.addPlayer(Card.scarlet, "MissScarlet", Color.RED, false);
		clue.addPlayer(Card.white, "MrsWhite", Color.WHITE, false);
		clue.addPlayer(Card.plum, "ProjPlum", Color.MAGENTA, false);
		clue.addPlayer(Card.peacock, "MrsPeacock", Color.BLUE, false);
		clue.addPlayer(Card.mustard, "ColMustard", Color.YELLOW, false);
		
		// cards are taken
		CardManager.getInstance().takenCard(Card.scarlet);
		CardManager.getInstance().takenCard(Card.green);
		CardManager.getInstance().takenCard(Card.white);
		CardManager.getInstance().takenCard(Card.plum);
		CardManager.getInstance().takenCard(Card.peacock);
		CardManager.getInstance().takenCard(Card.mustard);
		
		// available cards
		CardManager.getInstance().addAvailableCard(Card.ballroom);
		CardManager.getInstance().addAvailableCard(Card.billiard);
		CardManager.getInstance().addAvailableCard(Card.candle);
		CardManager.getInstance().addAvailableCard(Card.conservatory);
		CardManager.getInstance().addAvailableCard(Card.dining);
		CardManager.getInstance().addAvailableCard(Card.hall);
		CardManager.getInstance().addAvailableCard(Card.kitchen);
		CardManager.getInstance().addAvailableCard(Card.knife);
		CardManager.getInstance().addAvailableCard(Card.library);
		CardManager.getInstance().addAvailableCard(Card.lounge);
		CardManager.getInstance().addAvailableCard(Card.pipe);
		CardManager.getInstance().addAvailableCard(Card.revolver);
		CardManager.getInstance().addAvailableCard(Card.rope);
		CardManager.getInstance().addAvailableCard(Card.study);
		CardManager.getInstance().addAvailableCard(Card.wrench);
		
		determineSolution();
		distributeCards();
		System.out.println("LENDER -- the solution is " + CardManager.getInstance().toString());
		
	}
	
	private void distributeCards() {
		
		boolean done = false;
		
		while (!CardManager.getInstance().getAvailableCards().isEmpty()) {
			int size = CardManager.getInstance().getAvailableCards().size();
			
			int pick = new Die().rollBetweenValues(0, size-1);
			
			if (size == 0) {
				pick = 0;
			}
			
			Card c = CardManager.getInstance().getAvailableCards().get(pick);
			
			boolean weapon = false;
			boolean room = false;

			switch (c.type) {
				case Card.TYPE_ROOM:
					CardManager.getInstance().takenCard(c);
					room = scarletPlayer.addRoom(c, true);
					if (room) {
						break;
					}
					room = greenPlayer.addRoom(c, true);
					if (room) {
						break;
					}
					room = whitePlayer.addRoom(c, true);
					if (room) {
						break;
					}
					room = profPlumPlayer.addRoom(c, true);
					if (room) {
						break;
					}
					room = mrsPeacock.addRoom(c, true);
					if (room) {
						break;
					}
					room = colMustardPlayer.addRoom(c, true);
					break;
				case Card.TYPE_WEAPON:
					CardManager.getInstance().takenCard(c);
					weapon = scarletPlayer.addWeapon(c, true);
					if (weapon) {
						break;
					}
					weapon = greenPlayer.addWeapon(c, true);
					if (weapon) {
						break;
					}
					weapon = whitePlayer.addWeapon(c, true);
					if (weapon) {
						break;
					}
					weapon = profPlumPlayer.addWeapon(c, true);
					if (weapon) {
						break;
					}
					weapon = mrsPeacock.addWeapon(c, true);
					if (weapon) {
						break;
					}
					weapon = colMustardPlayer.addWeapon(c, true);
					break;
			}
		}
		
		printPlayerCards();
		
	}
	
	private void printPlayerCards() {
		String all = "";
		all += scarletPlayer.printCards();
		all += mrsPeacock.printCards();
		all += colMustardPlayer.printCards();
		all += greenPlayer.printCards();
		all += profPlumPlayer.printCards();
		all += whitePlayer.printCards();
		System.out.println("LENDER -- all is " + all);
	}
	
	private void determineSolution() {
		
		Die die = new Die();
		int number = die.rollDie();
		
		switch (number) {
			case 1:
				CardManager.getInstance().setKiller(Card.green);
				break;
			case 2:
				CardManager.getInstance().setKiller(Card.mustard);
				break;
			case 3:
				CardManager.getInstance().setKiller(Card.peacock);
				break;
			case 4:
				CardManager.getInstance().setKiller(Card.plum);
				break;
			case 5:
				CardManager.getInstance().setKiller(Card.scarlet);
				break;
			case 6:
				CardManager.getInstance().setKiller(Card.white);
				break;
			default:
				CardManager.getInstance().setKiller(Card.green);
				break;
		}
		
		number = die.rollDie();
		switch (number) {
			case 1:
				CardManager.getInstance().setWeapon(Card.candle);
				break;
			case 2:
				CardManager.getInstance().setWeapon(Card.knife);
				break;
			case 3:
				CardManager.getInstance().setWeapon(Card.pipe);
				break;
			case 4:
				CardManager.getInstance().setWeapon(Card.revolver);
				break;
			case 5:
				CardManager.getInstance().setWeapon(Card.rope);
				break;
			case 6:
				CardManager.getInstance().setWeapon(Card.wrench);
				break;
			default:
				CardManager.getInstance().setWeapon(Card.revolver);
				break;
		}
		
		number = die.rollBetweenRooms();
		switch (number) {
			case 1:
				CardManager.getInstance().setRoom(Card.ballroom);
				break;
			case 2:
				CardManager.getInstance().setRoom(Card.billiard);
				break;
			case 3:
				CardManager.getInstance().setRoom(Card.conservatory);
				break;
			case 4:
				CardManager.getInstance().setRoom(Card.dining);
				break;
			case 5:
				CardManager.getInstance().setRoom(Card.kitchen);
				break;
			case 6:
				CardManager.getInstance().setRoom(Card.library);
				break;
			case 7:
				CardManager.getInstance().setRoom(Card.lounge);
				break;
			case 8:
				CardManager.getInstance().setRoom(Card.study);
				break;
			case 9:
				CardManager.getInstance().setRoom(Card.hall);
				break;
			default:
				CardManager.getInstance().setRoom(Card.kitchen);
				break;
		}
		
	}
	
	public void initialiseClient() {
		try(final DatagramSocket socket = new DatagramSocket()){
			socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
			clientIP = socket.getLocalAddress().getHostAddress();
			System.out.println("LENDER -- client ip is + " + serverIP);
			client = new Client(clientIP, defaultServerPort, clientListener);
			System.out.println("LENDER -- client ip is connected " + client.isConnected());
		} catch (UnknownHostException ex) {
			Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
		} catch (SocketException ex) {
			Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
		}
		
	}
	
	public void disposeClientServer() {
		
		CardManager.getInstance().reset();
		ready.setVisible(false);
		
		if (null != client) {
			
			for (ClientInstance ci : playerList) {
				this.server.kickClient(ci);
			}
			
			this.client.dispose();
			System.out.println("LENDER -- client connection is " + this.client.isConnected());
		}
	}
	
	public void initialiseGUI() {
        // setup the main GUI
        gui.setBorder(new EmptyBorder(5, 5, 5, 5));
        JToolBar tools = new JToolBar();
        tools.setFloatable(false);
        gui.add(tools, BorderLayout.PAGE_START);
		JButton join = new JButton("Join Game");
		
		// add the listener to the jbutton to handle the "pressed" event
		join.addActionListener((ActionEvent e) -> {
			// join the game
			if (null == server) {
				initialiseServer();
			}
			initialiseClient();
			initialiseDefaultPlayers();
			enableReadyButton();
		});
		
        tools.add(join);
		tools.addSeparator();
		
		JButton exit = new JButton("Exit Game");
		
		// add the listener to the jbutton to handle the "pressed" event
		exit.addActionListener((ActionEvent e) -> {
			// join the game
			disposeClientServer();
		});
		
        tools.add(exit);
        tools.addSeparator();
		
		ready.setVisible(false);
		// add the listener to the jbutton to handle the "pressed" event
		ready.addActionListener((ActionEvent e) -> {
			// ready for the game
			readyForGameToBegin();
		});
		
		tools.add(ready);
        tools.addSeparator();
		
		roll.setVisible(false);
		// add the listener to the jbutton to handle the "pressed" event
		roll.addActionListener((ActionEvent e) -> {
			// roll die
			rollDie();
		});
		
		tools.add(roll);
        tools.addSeparator();
		
		viewCards.setVisible(false);
		// add the listener to the jbutton to handle the "pressed" event
		viewCards.addActionListener((ActionEvent e) -> {
			// view your cards
			viewMyCards();
		});
		
		tools.add(viewCards);
        tools.addSeparator();
		
		takeNotes.setVisible(false);
		// add the listener to the jbutton to handle the "pressed" event
		takeNotes.addActionListener((ActionEvent e) -> {
			// take notes
			takeNotes();
		});
		
		tools.add(takeNotes);
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

	private void viewMyCards() {
		
	}

	private void takeNotes() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	private void rollDie() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	private void readyForGameToBegin() {
		ready.setVisible(false);
		viewCards.setVisible(true);
		roll.setVisible(true);
		takeNotes.setVisible(true);
		
		if (playerList.size() == 1) {
			client.send("KILL " + CardManager.getInstance().getKiller());
			client.send("WEA " + CardManager.getInstance().getWeapon());
			client.send("ROO " + CardManager.getInstance().getRoom());
			
		} else {
			
		}
	}
	
	public static void sendMoveEvent(Player player, int x, int y, int x0, int y0, Color playerColor, boolean b) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	public static void sendSetSuggestionEvent(Card suspect, Card weapon, Card room, Player suggesting_player) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	public static void addPlayerIcon(int roomId, int suspectNumber) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	public static void setLocationColor(Location location, Color gray) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	public static void removePlayerIcon(int suspectNumber) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
	
}
