/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clue.GUI;

import clue.Clue;
import clue.Dialog.SuggestionDialog;
import clue.GameEngine.CardManager;
import clue.GameEngine.LocationManager;
import clue.GamePieces.Card;
import clue.GamePieces.Die;
import clue.GamePieces.Player;
import clue.Helpers.Colors;
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
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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

	
	private Colors colors = new Colors();
	private Card globalCard = new Card();
	
	private Player scarletPlayer = new Player();
	private Player greenPlayer = new Player();
	private Player whitePlayer = new Player();
	private Player profPlumPlayer = new Player();
	private Player mrsPeacockPlayer = new Player();
	private Player colMustardPlayer = new Player();
	
	private HashMap<String, Integer> playerTurn = new HashMap<String, Integer>();
	private static String currentPlayerTurn = "Miss Scarlet";
	private int takeTurnCounter = 0;
	
	private Clue clue = new Clue();
	
	private List<Location> canMoveTo = new ArrayList<Location>();
	private List<Location> roomPositionMoveTo = new ArrayList<Location>();
	private List<Location> hallwayPositionMoveTo = new ArrayList<Location>();
	private List<Location> freeSpaceMoveTo = new ArrayList<Location>();
	
	private static final String inChargeIP = "10.0.5.15";
	
    private final JPanel gui = new JPanel(new BorderLayout(3, 3));
    private JButton[][] gameBoardSquares = new JButton[22][22];
    private JPanel gameBoard;
	private final int GAME_BOARD_DIM = 22;
	private static final int WIDTH = 700;
	private static final int HEIGHT = 700;
	private BoardGameManager boardGameManager = null;
	JButton ready = new JButton("Ready");
	JButton takeTurn = new JButton("Take Turn");
	JButton viewCards = new JButton("View My Cards");
	JButton makeSuggestion = new JButton("Make Suggestion");
	private List<ClientInstance> playerList = new ArrayList<ClientInstance>();
	private String serverIP = "";
	private String clientIP = "";
	private Server server = null;
	private ServerListener serverListener = new ServerListener() {

		@Override
		public void clientConncted(ClientInstance client, PrintWriter out) {
			System.out.println("Client connected: " + client.ip);
			playerList.add(new ClientInstance(client.ip, client.port));
		}

		@Override
		public void clientDisconnected(ClientInstance client) {
			playerList.remove(new ClientInstance(client.ip, client.port));
		}

		@Override
		public void recivedInput(ClientInstance client, String msg) {
			
			String IP = client.ip.toString();
			IP = IP.replace("/", "");
			
			// handle messages from the master controller
			if (inChargeIP.equalsIgnoreCase(client.ip.toString().replace("/", ""))) {
				if (msg.contains("KILL")) {
					CardManager.getInstance().setKiller(globalCard.getCard(msg.replace("KILL", "")));
				} else if (msg.contains("WEA")) {
					CardManager.getInstance().setWeapon(globalCard.getCard(msg.replace("WEA", "")));
				} else if (msg.contains("ROO")) {
					CardManager.getInstance().setRoom(globalCard.getCard(msg.replace("ROO", "")));
				} else if (msg.contains("MOVE")) {
					Location lo = new Location(0,0);
					String[] split = msg.replace("MOVE", "").split("\\s+");
					//LocationManager.getInstance().moveToLocation(null, greenPlayer)
					System.out.println("Move event is " + msg);
				} else if (msg.contains("SUGG")) {
					String proposed = msg.replace("SUGG", "");
					Card card = globalCard.getCard(proposed);
					System.out.println("Received suggestion of " + card.desc);
					receiveSuggestion(card);
				}
				
			} else {
				if (msg.contains("MOVE")) {
					Location lo = new Location(0,0);
					String[] split = msg.replace("MOVE", "").split("\\s+");
					//LocationManager.getInstance().moveToLocation(null, greenPlayer
					System.out.println("Move event is " + split);
				}
			}
			
		}

		@Override
		public void serverClosed() {
			System.out.println("Server closed");
		}
	};
	private final int defaultServerPort = 6666;
	
	private Client client = null;
	private ClientListener clientListener = new ClientListener() {

		@Override
		public void unknownHost() {
			System.out.println("Unknown host!");
		}

		@Override
		public void couldNotConnect() {
			System.out.println("could not connect to server");
		}

		@Override
		public void recivedInput(String msg) {
			// TODO : add reading of data here
			System.out.println("received message: " + msg);
		}

		@Override
		public void serverClosed() {
			System.out.println("server is closed");
		}

		@Override
		public void disconnected() {
			System.out.println("server is disconnected");
		}

		@Override
		public void connectedToServer() {
			System.out.println("We have an incomming player!");
		}
	};
	
    GUI() {
        initializeGUI();
    }
	
	public void enableReadyButton() {
		ready.setVisible(true);
	}
	
	public void initializeServer() {
		try(final DatagramSocket socket = new DatagramSocket()){
			socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
			serverIP = socket.getLocalAddress().getHostAddress();
			System.out.println("server ip is + " + serverIP);
			server = new Server(defaultServerPort, serverListener);
			System.out.println("server ip is now " + server.getIp());
		} catch (UnknownHostException ex) {
			Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
		} catch (SocketException ex) {
			Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	public void initializeDefaultPlayers() {
		
		// give each player their card
		scarletPlayer.addCard(Card.scarlet);
		greenPlayer.addCard(Card.green);
		whitePlayer.addCard(Card.white);
		profPlumPlayer.addCard(Card.plum);
		mrsPeacockPlayer.addCard(Card.peacock);
		colMustardPlayer.addCard(Card.mustard);
		
		playerTurn.put(Card.scarlet.desc, 0);
		playerTurn.put(Card.green.desc, 0);
		playerTurn.put(Card.white.desc, 0);
		playerTurn.put(Card.plum.desc, 0);
		playerTurn.put(Card.peacock.desc, 0);
		playerTurn.put(Card.mustard.desc, 0);
		
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
		System.out.println("the solution is " + CardManager.getInstance().toString());
		
	}
	
	private void distributeCards() {
		
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
					room = mrsPeacockPlayer.addRoom(c, true);
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
					weapon = mrsPeacockPlayer.addWeapon(c, true);
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
		all += mrsPeacockPlayer.printCards();
		all += colMustardPlayer.printCards();
		all += greenPlayer.printCards();
		all += profPlumPlayer.printCards();
		all += whitePlayer.printCards();
		System.out.println("all is " + all);
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
	
	public void initializeClient() {
		try(final DatagramSocket socket = new DatagramSocket()){
			socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
			clientIP = socket.getLocalAddress().getHostAddress();
			System.out.println("client ip is + " + serverIP);
			client = new Client(clientIP, defaultServerPort, clientListener);
			System.out.println("client ip is connected " + client.isConnected());
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
			System.out.println("client connection is " + this.client.isConnected());
		}
	}
	
	public void initializeGUI() {
		scarletPlayer.setPlayerColor(Color.RED);
		greenPlayer.setPlayerColor(Color.GREEN);
		whitePlayer.setPlayerColor(Color.WHITE);
		profPlumPlayer.setPlayerColor(colors.getColor("PURPLE"));
		mrsPeacockPlayer.setPlayerColor(Color.BLUE);
		colMustardPlayer.setPlayerColor(Color.YELLOW);
		
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
				initializeServer();
			}
			initializeClient();
			initializeDefaultPlayers();
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
		
		takeTurn.setVisible(false);
		// add the listener to the jbutton to handle the "pressed" event
		takeTurn.addActionListener((ActionEvent e) -> {
			// take turn
			takeTurn();
		});
		
		tools.add(takeTurn);
        tools.addSeparator();
		
		viewCards.setVisible(false);
		// add the listener to the jbutton to handle the "pressed" event
		viewCards.addActionListener((ActionEvent e) -> {
			// view your cards
			viewMyCards();
		});
		
		tools.add(viewCards);
        tools.addSeparator();
		
		makeSuggestion.setVisible(false);
		// add the listener to the jbutton to handle the "pressed" event
		makeSuggestion.addActionListener((ActionEvent e) -> {
			// suggestion
			makeSuggestion();
		});
		
		tools.add(makeSuggestion);
        tools.addSeparator();

        gui.add(new JLabel(""), BorderLayout.LINE_START);

        gameBoard = new JPanel(new GridLayout(0, GAME_BOARD_DIM + 1));
        gameBoard.setBorder(new LineBorder(Color.BLACK));
        gui.add(gameBoard);

		Location l = new Location(0,0);
		
        // create the board squares
        Insets buttonMargin = new Insets(0,0,0,0);
        for (int ii = 0; ii < gameBoardSquares.length; ii++) {
            for (int jj = 0; jj < gameBoardSquares[ii].length; jj++) {
                JButton b = new JButton();
                b.setMargin(buttonMargin);
				
				// create the different rooms
				if (jj >= 0 && jj <= 3 && ii >= 0 && ii <= 4) {
					
					// STUDY ROOM
					b.setBackground(Color.LIGHT_GRAY);
					
					// set the door(s)
					if (ii == 4 && jj == 3) {
						b.setBackground(Color.BLACK);
						BoardGameManager.getInstance().setCanClickButton(jj, ii, true);
						BoardGameManager.getInstance().setIsDoorButton(jj, ii, true);
						l = new Location(jj, ii);
						l.setColor(Color.BLACK);
						canMoveTo.add(l);
					} else if (ii == 4 && jj == 2) {
						b.setBackground(Color.BLACK);
						BoardGameManager.getInstance().setCanClickButton(jj, ii, true);
						BoardGameManager.getInstance().setIsDoorButton(jj, ii, true);
						l = new Location(jj, ii);
						l.setColor(Color.BLACK);
						canMoveTo.add(l);
					} else {
						b.enableInputMethods(false);
						b.setSelected(false);
						b.setBorderPainted(false);
						b.setFocusPainted(false);
						BoardGameManager.getInstance().setCanClickButton(jj, ii, false);
						BoardGameManager.getInstance().setIsDoorButton(jj, ii, false);
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
					b.setBackground(Color.LIGHT_GRAY);
					
					// set the door(s)
					if ((ii == 6 && (jj == 8 || jj == 9)) || (ii == 3 && jj == 6)) {
						b.setBackground(Color.BLACK);
						BoardGameManager.getInstance().setCanClickButton(jj, ii, true);
						BoardGameManager.getInstance().setIsDoorButton(jj, ii, true);
						l = new Location(jj, ii);
						l.setColor(Color.BLACK);
						canMoveTo.add(l);
					} else {
						b.enableInputMethods(false);
						b.setSelected(false);
						b.setBorderPainted(false);
						b.setFocusPainted(false);
						BoardGameManager.getInstance().setCanClickButton(jj, ii, false);
						BoardGameManager.getInstance().setIsDoorButton(jj, ii, false);
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
					b.setBackground(Color.LIGHT_GRAY);
					
					// set the door(s)
					if (ii == 5 && jj == 15) {
						b.setBackground(Color.BLACK);
						BoardGameManager.getInstance().setCanClickButton(jj, ii, true);
						BoardGameManager.getInstance().setIsDoorButton(jj, ii, true);
						l = new Location(jj, ii);
						l.setColor(Color.BLACK);
						canMoveTo.add(l);
						
					} else if (ii == 5 && jj == 16) {
						b.setBackground(Color.BLACK);
						BoardGameManager.getInstance().setCanClickButton(jj, ii, true);
						BoardGameManager.getInstance().setIsDoorButton(jj, ii, true);
						l = new Location(jj, ii);
						l.setColor(Color.BLACK);
						canMoveTo.add(l);
					} else {
						b.enableInputMethods(false);
						b.setSelected(false);
						b.setBorderPainted(false);
						b.setFocusPainted(false);
						BoardGameManager.getInstance().setCanClickButton(jj, ii, false);
						BoardGameManager.getInstance().setIsDoorButton(jj, ii, false);
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
					b.setBackground(Color.LIGHT_GRAY);
					
					// set the door(s)
					if ((ii == 8 && jj == 3) || (ii == 10 && jj == 1)) {
						b.setBackground(Color.BLACK);
						BoardGameManager.getInstance().setCanClickButton(jj, ii, true);
						BoardGameManager.getInstance().setIsDoorButton(jj, ii, true);
						l = new Location(jj, ii);
						l.setColor(Color.BLACK);
						canMoveTo.add(l);
					} else {
						b.enableInputMethods(false);
						b.setSelected(false);
						b.setBorderPainted(false);
						b.setFocusPainted(false);
						BoardGameManager.getInstance().setCanClickButton(jj, ii, false);
						BoardGameManager.getInstance().setIsDoorButton(jj, ii, false);
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
					b.setBackground(Color.LIGHT_GRAY);
					
					// set the door(s)
					if ((ii == 13 && jj == 0) || (ii == 15 && jj == 3)) {
						b.setBackground(Color.BLACK);
						BoardGameManager.getInstance().setCanClickButton(jj, ii, true);
						BoardGameManager.getInstance().setIsDoorButton(jj, ii, true);
						l = new Location(jj, ii);
						l.setColor(Color.BLACK);
						canMoveTo.add(l);
					} else {
						b.enableInputMethods(false);
						b.setSelected(false);
						b.setBorderPainted(false);
						b.setFocusPainted(false);
						BoardGameManager.getInstance().setCanClickButton(jj, ii, false);
						BoardGameManager.getInstance().setIsDoorButton(jj, ii, false);
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
					b.setBackground(Color.LIGHT_GRAY);
					
					// set the door(s)
					if (ii == 19 && jj == 3) {
						b.setBackground(Color.BLACK);
						BoardGameManager.getInstance().setCanClickButton(jj, ii, true);
						BoardGameManager.getInstance().setIsDoorButton(jj, ii, true);
						l = new Location(jj, ii);
						l.setColor(Color.BLACK);
						canMoveTo.add(l);
					} else if (ii == 19 && jj == 2) {
						b.setBackground(Color.BLACK);
						BoardGameManager.getInstance().setCanClickButton(jj, ii, true);
						BoardGameManager.getInstance().setIsDoorButton(jj, ii, true);
						l = new Location(jj, ii);
						l.setColor(Color.BLACK);
						canMoveTo.add(l);
					} else {
						b.enableInputMethods(false);
						b.setSelected(false);
						b.setBorderPainted(false);
						b.setFocusPainted(false);
						BoardGameManager.getInstance().setCanClickButton(jj, ii, false);
						BoardGameManager.getInstance().setIsDoorButton(jj, ii, false);
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
					b.setBackground(Color.LIGHT_GRAY);
					
					// set the door(s)
					if ((ii == 18 && jj == 6) || (ii == 18 && jj == 12) || (ii == 15 && jj == 7) || (ii == 15 && jj == 11)) {
						b.setBackground(Color.BLACK);
						BoardGameManager.getInstance().setCanClickButton(jj, ii, true);
						BoardGameManager.getInstance().setIsDoorButton(jj, ii, true);
						l = new Location(jj, ii);
						l.setColor(Color.BLACK);
						canMoveTo.add(l);
					} else {
						b.enableInputMethods(false);
						b.setSelected(false);
						b.setBorderPainted(false);
						b.setFocusPainted(false);
						BoardGameManager.getInstance().setCanClickButton(jj, ii, false);
						BoardGameManager.getInstance().setIsDoorButton(jj, ii, false);
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
					b.setBackground(Color.LIGHT_GRAY);
					
					// set the door(s)
					if (ii == 16 && jj == 16) {
						b.setBackground(Color.BLACK);
						BoardGameManager.getInstance().setCanClickButton(jj, ii, true);
						BoardGameManager.getInstance().setIsDoorButton(jj, ii, true);
						l = new Location(jj, ii);
						l.setColor(Color.BLACK);
						canMoveTo.add(l);
					} else if (ii == 16 && jj == 17) {
						b.setBackground(Color.BLACK);
						BoardGameManager.getInstance().setCanClickButton(jj, ii, true);
						BoardGameManager.getInstance().setIsDoorButton(jj, ii, true);
						l = new Location(jj, ii);
						l.setColor(Color.BLACK);
						canMoveTo.add(l);
					} else {
						b.enableInputMethods(false);
						b.setSelected(false);
						b.setBorderPainted(false);
						b.setFocusPainted(false);
						BoardGameManager.getInstance().setCanClickButton(jj, ii, false);
						BoardGameManager.getInstance().setIsDoorButton(jj, ii, false);
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
					b.setBackground(Color.LIGHT_GRAY);
					
					// set the door(s)
					if ((ii == 11 && jj == 15) || (ii == 9 && jj == 17)) {
						b.setBackground(Color.BLACK);
						BoardGameManager.getInstance().setCanClickButton(jj, ii, true);
						BoardGameManager.getInstance().setIsDoorButton(jj, ii, true);
						l = new Location(jj, ii);
						l.setColor(Color.BLACK);
						canMoveTo.add(l);
					} else {
						b.enableInputMethods(false);
						b.setSelected(false);
						b.setBorderPainted(false);
						b.setFocusPainted(false);
						BoardGameManager.getInstance().setCanClickButton(jj, ii, false);
						BoardGameManager.getInstance().setIsDoorButton(jj, ii, false);
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
					BoardGameManager.getInstance().setCanClickButton(jj, ii, false);
					BoardGameManager.getInstance().setIsDoorButton(jj, ii, false);
					
				} else {
					b.setBackground(Color.ORANGE);
					BoardGameManager.getInstance().setCanClickButton(jj, ii, true);
					BoardGameManager.getInstance().setIsDoorButton(jj, ii, false);
				}
				
				// Default starting positions
				if (ii == 18 && jj == 0) {
					b.setBackground(Color.BLUE);
					mrsPeacockPlayer.setLocation(new Location(jj, ii));
					l = new Location(jj, ii);
					l.setColor(Color.BLUE);
					
					Location neigh = new Location(3, 19);
					l.addNeighbor(neigh);
					neigh = new Location(2, 19);
					l.addNeighbor(neigh);
					
					canMoveTo.add(l);
					hallwayPositionMoveTo.add(l);
				}
				
				if (ii == 6 && jj == 0) {
					b.setBackground(colors.getColor("PURPLE"));
					profPlumPlayer.setLocation(new Location(jj, ii));
					l = new Location(jj, ii);
					l.setColor(colors.getColor("PURPLE"));
					
					Location neigh = new Location(3, 4);
					l.addNeighbor(neigh);
					neigh = new Location(2, 4);
					l.addNeighbor(neigh);
					
					canMoveTo.add(l);
					hallwayPositionMoveTo.add(l);
				}
				
				if (ii == 0 && jj == 14) {
					b.setBackground(Color.RED);
					scarletPlayer.setLocation(new Location(jj, ii));
					l = new Location(jj, ii);
					l.setColor(Color.RED);
					
					Location neigh = new Location(15, 5);
					l.addNeighbor(neigh);
					
					canMoveTo.add(l);
					hallwayPositionMoveTo.add(l);
				}
				
				if (ii == 6 && jj == 21) {
					b.setBackground(Color.YELLOW);
					colMustardPlayer.setLocation(new Location(jj, ii));
					l = new Location(jj, ii);
					l.setColor(Color.YELLOW);
					
					Location neigh = new Location(15, 5);
					l.addNeighbor(neigh);
					neigh = new Location(16, 5);
					l.addNeighbor(neigh);
					neigh = new Location(17, 9);
					l.addNeighbor(neigh);
					
					canMoveTo.add(l);
					hallwayPositionMoveTo.add(l);
				}
				
				if (ii == 21 && jj == 5) {
					b.setBackground(Color.GREEN);
					greenPlayer.setLocation(new Location(jj, ii));
					l = new Location(jj, ii);
					l.setColor(Color.GREEN);

					Location neigh = new Location(3, 19);
					l.addNeighbor(neigh);
					neigh = new Location(2, 19);
					l.addNeighbor(neigh);
					neigh = new Location(6, 18);
					l.addNeighbor(neigh);
					
					canMoveTo.add(l);
					hallwayPositionMoveTo.add(l);
				}
				
				if (ii == 21 && jj == 13) {
					b.setBackground(Color.WHITE);
					whitePlayer.setLocation(new Location(jj, ii));
					l = new Location(jj, ii);
					l.setColor(Color.WHITE);
					
					Location neigh = new Location(12, 18);
					l.addNeighbor(neigh);
					
					canMoveTo.add(l);
					hallwayPositionMoveTo.add(l);
				}
				
				if ((jj == 4 && ii == 0) || (jj == 0 && ii == 11) || (jj == 9 && ii == 7) || (jj == 9 && ii == 13) || (jj == 21 && ii == 15) || (jj == 12 && ii == 9) || (jj == 6 && ii == 9)) {
					
					l = new Location(jj, ii);
					l.setColor(Color.ORANGE);
					
					Location neigh = new Location(0,0);
					
					// setup neighbors
					if (jj == 4 && ii == 0) {
						neigh = new Location(3, 4);
						l.addNeighbor(neigh);
						neigh = new Location(2, 4);
						l.addNeighbor(neigh);
						neigh = new Location(6, 3);
						l.addNeighbor(neigh);
					} else if (jj == 0 && ii == 11) {
						neigh = new Location(1, 10);
						l.addNeighbor(neigh);
						neigh = new Location(0, 13);
						l.addNeighbor(neigh);
					} else if (jj == 9 && ii == 7) {
						neigh = new Location(9, 6);
						l.addNeighbor(neigh);
					} else if (jj == 9 && ii == 13) {
						neigh = new Location(7, 15);
						l.addNeighbor(neigh);
						neigh = new Location(11, 15);
						l.addNeighbor(neigh);
					} else if (jj == 21 && ii == 15) {
						neigh = new Location(16, 16);
						l.addNeighbor(neigh);
						neigh = new Location(16, 17);
						l.addNeighbor(neigh);
					} else if (jj == 12 && ii == 9) {
						neigh = new Location(15, 11);
						l.addNeighbor(neigh);
					} else if (jj == 6 && ii == 9) {
						neigh = new Location(3, 8);
						l.addNeighbor(neigh);
					}
					
					
					
					canMoveTo.add(l);
					hallwayPositionMoveTo.add(l);
				}
				
                gameBoardSquares[jj][ii] = b;
            }
        }
		
		// setup the locations we can move to
		LocationManager.getInstance().setup(canMoveTo);
		LocationManager.getInstance().moveToLocation(whitePlayer.getLocation(), whitePlayer);
		LocationManager.getInstance().moveToLocation(greenPlayer.getLocation(), greenPlayer);
		LocationManager.getInstance().moveToLocation(colMustardPlayer.getLocation(), colMustardPlayer);
		LocationManager.getInstance().moveToLocation(scarletPlayer.getLocation(), scarletPlayer);
		LocationManager.getInstance().moveToLocation(profPlumPlayer.getLocation(), profPlumPlayer);
		LocationManager.getInstance().moveToLocation(mrsPeacockPlayer.getLocation(), mrsPeacockPlayer);

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

                JFrame f = new JFrame("Clueless Board Game");
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
		if (currentPlayerTurn.equalsIgnoreCase(Card.scarlet.desc)) {
			JOptionPane.showMessageDialog(null,
								scarletPlayer.getWeapon().desc + " \n" + scarletPlayer.getRoom().desc);
		} else if (currentPlayerTurn.equalsIgnoreCase(Card.plum.desc)) {
			JOptionPane.showMessageDialog(null,
								profPlumPlayer.getWeapon().desc + " \n" + profPlumPlayer.getRoom().desc);
		} else if (currentPlayerTurn.equalsIgnoreCase(Card.green.desc)) {
			JOptionPane.showMessageDialog(null,
								greenPlayer.getWeapon().desc + " \n" + greenPlayer.getRoom().desc);
		} else if (currentPlayerTurn.equalsIgnoreCase(Card.white.desc)) {
			JOptionPane.showMessageDialog(null,
								whitePlayer.getWeapon().desc + " \n" + whitePlayer.getRoom().desc);
		} else if (currentPlayerTurn.equalsIgnoreCase(Card.mustard.desc)) {
			JOptionPane.showMessageDialog(null,
								colMustardPlayer.getWeapon().desc + " \n" + colMustardPlayer.getRoom().desc);
		} else if (currentPlayerTurn.equalsIgnoreCase(Card.peacock.desc)) {
			JOptionPane.showMessageDialog(null,
								mrsPeacockPlayer.getWeapon().desc + " \n" + mrsPeacockPlayer.getRoom().desc);
		}
	}
	
	public void receiveSuggestion(Card card) {
		
		Location location = new Location(0,0);
		Location previous = new Location(0,0);
		
		boolean scar = false;
		boolean green = false;
		boolean white = false;
		boolean mustard = false;
		boolean plum = false;
		boolean pea = false;
		
		if (currentPlayerTurn.equals(Card.scarlet.desc)) {
			scar = true;
			location = BoardGameManager.getInstance().getClosestDoor(scarletPlayer.getLocation().getX(), scarletPlayer.getLocation().getY());
		} else if (currentPlayerTurn.equals(Card.peacock.desc)) {
			pea = true;
			location = BoardGameManager.getInstance().getClosestDoor(mrsPeacockPlayer.getLocation().getX(), mrsPeacockPlayer.getLocation().getY());
		} else if (currentPlayerTurn.equals(Card.plum.desc)) {
			plum = true;
			location = BoardGameManager.getInstance().getClosestDoor(profPlumPlayer.getLocation().getX(), profPlumPlayer.getLocation().getY());
		} else if (currentPlayerTurn.equals(Card.mustard.desc)) {
			mustard = true;
			location = BoardGameManager.getInstance().getClosestDoor(colMustardPlayer.getLocation().getX(), colMustardPlayer.getLocation().getY());
		} else if (currentPlayerTurn.equals(Card.white.desc)) {
			white = true;
			location = BoardGameManager.getInstance().getClosestDoor(whitePlayer.getLocation().getX(), whitePlayer.getLocation().getY());
		} else if (currentPlayerTurn.equals(Card.green.desc)) {
			green = true;
			location = BoardGameManager.getInstance().getClosestDoor(greenPlayer.getLocation().getX(), greenPlayer.getLocation().getY());
		}
		
		if (card.desc.equals(Card.scarlet.desc)) {
			previous = scarletPlayer.getLocation();
			movePlayer(location, previous, scarletPlayer);
		} else if (card.desc.equals(Card.peacock.desc)) {
			previous = mrsPeacockPlayer.getLocation();
			movePlayer(location, previous, mrsPeacockPlayer);
		} else if (card.desc.equals(Card.plum.desc)) {
			previous = profPlumPlayer.getLocation();
			movePlayer(location, previous, profPlumPlayer);
		} else if (card.desc.equals(Card.mustard.desc)) {
			previous = colMustardPlayer.getLocation();
			movePlayer(location, previous, colMustardPlayer);
		} else if (card.desc.equals(Card.white.desc)) {
			previous = whitePlayer.getLocation();
			movePlayer(location, previous, whitePlayer);
		} else if (card.desc.equals(Card.green.desc)) {
			previous = greenPlayer.getLocation();
			movePlayer(location, previous, greenPlayer);
		}
		
	}
	
	
	private void movePlayer(Location moveToLocation, Location previousLocation, Player player) {

		boolean didMove = LocationManager.getInstance().moveToLocation(moveToLocation, player);

		if (didMove) {
			player.setLocation(moveToLocation);
		}
				
		LocationManager.getInstance().makeDefaultColor(previousLocation);
		Iterator it = LocationManager.getInstance().getOccupied().entrySet().iterator();

		while(it.hasNext()) {
			Map.Entry<Location, Boolean> pair = (Map.Entry) it.next();
			gameBoardSquares[pair.getKey().getX()][pair.getKey().getY()].setBackground(pair.getKey().getColor());
		}
		
		
	}

	private void makeSuggestion() {
		
		boolean scarlet = BoardGameManager.getInstance().getIsDoorButton(scarletPlayer.getLocation().getX(), scarletPlayer.getLocation().getY());
		boolean green = BoardGameManager.getInstance().getIsDoorButton(greenPlayer.getLocation().getX(), greenPlayer.getLocation().getY());
		boolean white = BoardGameManager.getInstance().getIsDoorButton(whitePlayer.getLocation().getX(), whitePlayer.getLocation().getY());
		boolean mustard = BoardGameManager.getInstance().getIsDoorButton(colMustardPlayer.getLocation().getX(), colMustardPlayer.getLocation().getY());
		boolean peacock = BoardGameManager.getInstance().getIsDoorButton(mrsPeacockPlayer.getLocation().getX(), mrsPeacockPlayer.getLocation().getY());
		boolean profplum = BoardGameManager.getInstance().getIsDoorButton(profPlumPlayer.getLocation().getX(), profPlumPlayer.getLocation().getY());
		
		boolean canMakeSuggestion = false;
		
		switch (takeTurnCounter%6) {
			case 0:
				canMakeSuggestion = scarlet;
				
				if (canMakeSuggestion) {
					currentPlayerTurn = Card.scarlet.desc;
					SuggestionDialog s = new SuggestionDialog(Card.scarlet.desc);
					s.setClient(client);
					s.setVisible(true);
					takeTurnCounter++;
				} else {
					JOptionPane.showMessageDialog(null,
								"Sorry, you cannot make a suggestion!");
				}
				break;
			case 1:
				canMakeSuggestion = profplum;
				if (canMakeSuggestion) {
					currentPlayerTurn = Card.plum.desc;
					SuggestionDialog s = new SuggestionDialog(Card.plum.desc);
					s.setClient(client);
					s.setVisible(true);
					takeTurnCounter++;
				} else {
					JOptionPane.showMessageDialog(null,
								"Sorry, you cannot make a suggestion!");
				}
				break;
			case 2:
				canMakeSuggestion = green;
				if (canMakeSuggestion) {
					currentPlayerTurn = Card.green.desc;
					SuggestionDialog s = new SuggestionDialog(Card.green.desc);
					s.setClient(client);
					s.setVisible(true);
					takeTurnCounter++;
				} else {
					JOptionPane.showMessageDialog(null,
								"Sorry, you cannot make a suggestion!");
				}
				break;
			case 3:
				canMakeSuggestion = white;
				if (canMakeSuggestion) {
					currentPlayerTurn = Card.white.desc;
					SuggestionDialog s = new SuggestionDialog(Card.white.desc);
					s.setClient(client);
					s.setVisible(true);
					takeTurnCounter++;
				} else {
					JOptionPane.showMessageDialog(null,
								"Sorry, you cannot make a suggestion!");
				}
				break;
			case 4:
				canMakeSuggestion = mustard;
				if (canMakeSuggestion) {
					currentPlayerTurn = Card.mustard.desc;
					SuggestionDialog s = new SuggestionDialog(Card.mustard.desc);
					s.setClient(client);
					s.setVisible(true);
					takeTurnCounter++;
				} else {
					JOptionPane.showMessageDialog(null,
								"Sorry, you cannot make a suggestion!");
				}
				break;
			case 5:
				canMakeSuggestion = peacock;
				if (canMakeSuggestion) {
					currentPlayerTurn = Card.peacock.desc;
					SuggestionDialog s = new SuggestionDialog(Card.peacock.desc);
					s.setClient(client);
					s.setVisible(true);
					takeTurnCounter++;
				} else {
					JOptionPane.showMessageDialog(null,
								"Sorry, you cannot make a suggestion!");
				}
				break;
				
		}
	}

	private void takeTurn() {
		
		Location pLoc = new Location(0, 0);
		Location de = new Location(0,0);
		List<Location> list = new ArrayList<Location>();
		String[] options;
		int response = 0;
		boolean didMove = false;
		
		switch (takeTurnCounter%6) {
			case 0:
				currentPlayerTurn = Card.scarlet.desc;
				pLoc = scarletPlayer.getLocation();
				
				list = LocationManager.getInstance().getNeighbors(pLoc);
				
				for (Location d: hallwayPositionMoveTo) {
					for (Location e: canMoveTo) {
						if (d.getX() == e.getX() && d.getY() == e.getY()) {
							if (LocationManager.getInstance().available(d)) {
								list.add(d);
							}
						}
					}
				}
				
				options = new String[list.size()];
				
				for (int index = 0; index < list.size(); index++) {
					options[index] = "X: " + list.get(index).getX() + " Y: " + list.get(index).getY();
				}
				
				response = JOptionPane.showOptionDialog(null, "Miss Scarlet, please choose a location to go to", "Move Choice", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
				
				de = scarletPlayer.getLocation();
				
				didMove = LocationManager.getInstance().moveToLocation(list.get(response), scarletPlayer);
				
				if (didMove) {
					scarletPlayer.setLocation(list.get(response));
				}
				
				break;
			case 1:
				currentPlayerTurn = Card.plum.desc;
				
				pLoc = profPlumPlayer.getLocation();
				
				list = LocationManager.getInstance().getNeighbors(pLoc);
				
				for (Location d: hallwayPositionMoveTo) {
					for (Location e: canMoveTo) {
						if (d.getX() == e.getX() && d.getY() == e.getY()) {
							if (LocationManager.getInstance().available(d)) {
								list.add(d);
							}
						}
					}
				}
				
				options = new String[list.size()];
				
				for (int index = 0; index < list.size(); index++) {
					options[index] = "X: " + list.get(index).getX() + " Y: " + list.get(index).getY();
				}
				
				response = JOptionPane.showOptionDialog(null, "Prof Plum, please choose a location to go to", "Move Choice", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
				
				de = profPlumPlayer.getLocation();
				
				didMove = LocationManager.getInstance().moveToLocation(list.get(response), profPlumPlayer);
				
				if (didMove) {
					profPlumPlayer.setLocation(list.get(response));
				}
				
				break;
			case 2:
				currentPlayerTurn = Card.green.desc;
				
				pLoc = greenPlayer.getLocation();
				
				list = LocationManager.getInstance().getNeighbors(pLoc);
				
				for (Location d: hallwayPositionMoveTo) {
					for (Location e: canMoveTo) {
						if (d.getX() == e.getX() && d.getY() == e.getY()) {
							if (LocationManager.getInstance().available(d)) {
								list.add(d);
							}
						}
					}
				}
				
				options = new String[list.size()];
				
				for (int index = 0; index < list.size(); index++) {
					options[index] = "X: " + list.get(index).getX() + " Y: " + list.get(index).getY();
				}
				
				response = JOptionPane.showOptionDialog(null, "Mr. Green, please choose a location to go to", "Move Choice", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
				
				de = greenPlayer.getLocation();
				
				didMove = LocationManager.getInstance().moveToLocation(list.get(response), greenPlayer);
				
				if (didMove) {
					greenPlayer.setLocation(list.get(response));
				}
				
				break;
			case 3:
				currentPlayerTurn = Card.white.desc;
				
				pLoc = whitePlayer.getLocation();
				
				list = LocationManager.getInstance().getNeighbors(pLoc);
				
				for (Location d: hallwayPositionMoveTo) {
					for (Location e: canMoveTo) {
						if (d.getX() == e.getX() && d.getY() == e.getY()) {
							if (LocationManager.getInstance().available(d)) {
								list.add(d);
							}
						}
					}
				}
				
				options = new String[list.size()];
				
				for (int index = 0; index < list.size(); index++) {
					options[index] = "X: " + list.get(index).getX() + " Y: " + list.get(index).getY();
				}
				
				response = JOptionPane.showOptionDialog(null, "Mrs. White, please choose a location to go to", "Move Choice", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
				
				de = whitePlayer.getLocation();
				
				didMove = LocationManager.getInstance().moveToLocation(list.get(response), whitePlayer);
				
				if (didMove) {
					whitePlayer.setLocation(list.get(response));
				}

				break;
			case 4:
				currentPlayerTurn = Card.mustard.desc;
				
				pLoc = colMustardPlayer.getLocation();
				
				list = LocationManager.getInstance().getNeighbors(pLoc);
				
				for (Location d: hallwayPositionMoveTo) {
					for (Location e: canMoveTo) {
						if (d.getX() == e.getX() && d.getY() == e.getY()) {
							if (LocationManager.getInstance().available(d)) {
								list.add(d);
							}
						}
					}
				}
				
				options = new String[list.size()];
				
				for (int index = 0; index < list.size(); index++) {
					options[index] = "X: " + list.get(index).getX() + " Y: " + list.get(index).getY();
				}
				
				response = JOptionPane.showOptionDialog(null, "Col. Mustard, please choose a location to go to", "Move Choice", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
				
				de = colMustardPlayer.getLocation();
				
				didMove = LocationManager.getInstance().moveToLocation(list.get(response), colMustardPlayer);
				
				if (didMove) {
					colMustardPlayer.setLocation(list.get(response));
				}
				
				break;
			case 5:
				currentPlayerTurn = Card.peacock.desc;
				
				pLoc = mrsPeacockPlayer.getLocation();
				
				list = LocationManager.getInstance().getNeighbors(pLoc);
				
				for (Location d: hallwayPositionMoveTo) {
					for (Location e: canMoveTo) {
						if (d.getX() == e.getX() && d.getY() == e.getY()) {
							if (LocationManager.getInstance().available(d)) {
								list.add(d);
							}
						}
					}
				}
				
				options = new String[list.size()];
				
				for (int index = 0; index < list.size(); index++) {
					options[index] = "X: " + list.get(index).getX() + " Y: " + list.get(index).getY();
				}
				
				response = JOptionPane.showOptionDialog(null, "Mrs. Peacock, please choose a location to go to", "Move Choice", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
				
				de = mrsPeacockPlayer.getLocation();
				
				didMove = LocationManager.getInstance().moveToLocation(list.get(response), mrsPeacockPlayer);
				
				if (didMove) {
					mrsPeacockPlayer.setLocation(list.get(response));
				}
				
				break;
		}
		
		LocationManager.getInstance().makeDefaultColor(de);
		Iterator it = LocationManager.getInstance().getOccupied().entrySet().iterator();

		while(it.hasNext()) {
			Map.Entry<Location, Boolean> pair = (Map.Entry) it.next();
			gameBoardSquares[pair.getKey().getX()][pair.getKey().getY()].setBackground(pair.getKey().getColor());
		}
		
		if (playerList.size() == 1) {
			if (currentPlayerTurn.equalsIgnoreCase(Card.scarlet.desc)) {
				client.send("MOVE" + scarletPlayer.getLocation().getX() + " " + scarletPlayer.getLocation().getY() + " " + Card.scarlet.desc);
			} else if (currentPlayerTurn.equalsIgnoreCase(Card.plum.desc)) {
				client.send("MOVE" + profPlumPlayer.getLocation().getX() + " " + profPlumPlayer.getLocation().getY() + " " + Card.plum.desc);
			} else if (currentPlayerTurn.equalsIgnoreCase(Card.green.desc)) {
				client.send("MOVE" + greenPlayer.getLocation().getX() + " " + greenPlayer.getLocation().getY() + " " + Card.green.desc);
			} else if (currentPlayerTurn.equalsIgnoreCase(Card.white.desc)) {
				client.send("MOVE" + whitePlayer.getLocation().getX() + " " + whitePlayer.getLocation().getY() + " " + Card.white.desc);
			} else if (currentPlayerTurn.equalsIgnoreCase(Card.mustard.desc)) {
				client.send("MOVE" + colMustardPlayer.getLocation().getX() + " " + colMustardPlayer.getLocation().getY() + " " + Card.mustard.desc);
			} else if (currentPlayerTurn.equalsIgnoreCase(Card.peacock.desc)) {
				client.send("MOVE" + mrsPeacockPlayer.getLocation().getX() + " " + mrsPeacockPlayer.getLocation().getY() + " " + Card.peacock.desc);
			}
		} else {
			
		}
		
		takeTurnCounter += 1;
	}

	private void readyForGameToBegin() {
		ready.setVisible(false);
		viewCards.setVisible(true);
		takeTurn.setVisible(true);
		makeSuggestion.setVisible(true);
		
		if (playerList.size() == 1) {
			client.send("KILL" + CardManager.getInstance().getKiller());
			client.send("WEA" + CardManager.getInstance().getWeapon());
			client.send("ROO" + CardManager.getInstance().getRoom());
			
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
