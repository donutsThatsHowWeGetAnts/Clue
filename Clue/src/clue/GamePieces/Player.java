/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clue.GamePieces;

import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author glender
 */
public class Player {
	
    private int suspectNumber = 0;
    private String suspectName = null;
    private String playerName = "";
    private Card playerCard = null;
    private ArrayList<Card> cardsInHand = new ArrayList<Card>();
    private boolean computerPlayer = false;
    private Location playerLocation = null;
    private Notebook notebook = null;
    private Color playerColor = Color.BLUE;
    private boolean hasMadeFalseAccusation = false;

    public Player() {}

    public Player(Card pick, String name, Color color, boolean computer) {
        setPlayerName(name);
        setPlayerCard(pick);
        setSuspectNumber(pick.getValue());
        setSuspectName(getPlayerCard().toString());
        setComputerPlayer(computer);
        setPlayerColor(color);
    }

    public void setLocation(Location location) {
        playerLocation = location;
    }

    public Location getLocation() {
        return playerLocation;
    }

    public void setNotebook(Notebook notebook) {
        this.notebook = notebook;
    }

    public Notebook getNotebook() {
        return notebook;
    }

    public void addCard(Card card) {
        cardsInHand.add(card);
    }

    public ArrayList<Card> getCardsInHand() {
        return cardsInHand;
    }

    public boolean isCardInHand(Card card) {
        return cardsInHand.contains(card);
    }

    public boolean isCardInHand(int type, int id) {
        Card card = new Card(type, id);
        return cardsInHand.contains(card);
    }

    public boolean isHoldingCardInSuggestion(ArrayList<Card> suggestion) {
        boolean hasCards = false;
        for (Card card : cardsInHand) {
            if (suggestion.contains(card)) {
                hasCards = true;
            }
        }
        return hasCards;
    }

    public String toString() {
        return getPlayerCard().toString();
    }

    public String toLongString() {
        String location = (playerLocation != null && playerLocation.getRoomId() != -1 ? "in the " + playerLocation.getRoomCard().toString() : "outside of a room");
        return getPlayerCard().toString() + ", played by " + (isComputerPlayer() ? "computer" : getPlayerName()) + " is currently " + location + ".";
    }

    public String getSuspectName() {
        return suspectName;
    }

    public void setSuspectName(String suspectName) {
        this.suspectName = suspectName;
    }

    public Card getPlayerCard() {
        return playerCard;
    }

    public void setPlayerCard(Card playerCard) {
        this.playerCard = playerCard;
    }

    public Color getPlayerColor() {
        return playerColor;
    }

    public void setPlayerColor(Color playerColor) {
        this.playerColor = playerColor;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getSuspectNumber() {
        return suspectNumber;
    }

    public void setSuspectNumber(int suspectNumber) {
        this.suspectNumber = suspectNumber;
    }

    public boolean isComputerPlayer() {
        return computerPlayer;
    }

    public void setComputerPlayer(boolean computerPlayer) {
        this.computerPlayer = computerPlayer;
    }

    public boolean hasMadeFalseAccusation() {
        return hasMadeFalseAccusation;
    }

    public void setHasMadeFalseAccusation() {
        hasMadeFalseAccusation = true;
    }
	
}
