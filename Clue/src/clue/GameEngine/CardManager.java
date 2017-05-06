/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clue.GameEngine;

import clue.GamePieces.Card;
import clue.GamePieces.Player;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author glender
 */
public class CardManager {
	
	private List<Player> players = new ArrayList<Player>();
	private List<Card> takenCards = new ArrayList<Card>();
	private List<Card> availableCards = new ArrayList<Card>();
	
	/**
	 * The solution.
	 */
	private Card killer = new Card();
	private Card weapon = new Card();
	private Card room   = new Card();
	
	/**
	 * Singleton instance.
	 */
	private static CardManager instance = new CardManager();
	
	/**
	 * Private empty constructor.
	 */
	private CardManager() {}
	
	/**
	 * Thread safe getInstance.
	 * @return 
	 */
	public static synchronized CardManager getInstance() {
		if (null == instance) {
			instance = new CardManager();
		}
		
		return instance;
	}

	public void takenCard(Card card) {
		
		if (!availableCards.isEmpty()) {
			availableCards.remove(card);
		}
		
		takenCards.add(card);
	}
	
	public void addAvailableCard(Card card) {
		availableCards.add(card);
	}
	
	public void reset() {
		this.availableCards.clear();
		this.killer = new Card();
		this.players.clear();
		this.room = new Card();
		this.takenCards .clear();
		this.weapon = new Card();
	}
	
	public void setKiller(Card card) {
		this.killer = card;

		if (!availableCards.isEmpty()) {
			availableCards.remove(card);
		}
		
		takenCards.add(card);
	}
	
	public void setWeapon(Card card) {
		this.weapon = card;
		
		if (!availableCards.isEmpty()) {
			availableCards.remove(card);
		}
		
		takenCards.add(card);
		
	}
	
	public void setRoom(Card card) {
		this.room = card;
		
		if (!availableCards.isEmpty()) {
			availableCards.remove(card);
		}
		
		takenCards.add(card);
		
	}
	
	public Card getKiller() {
		return this.killer;
	}
	
	public Card getWeapon() {
		return this.weapon;
	}
	
	public Card getRoom() {
		return this.room;
	}
	
	public List<Card> getAvailableCards() {
		return this.availableCards;
	}
	
	@Override
	public String toString() {
		return "Killer: " + this.killer.toString() + " Weapon: " + this.weapon.toString() + " Room: " + this.room.toString();
	}
	
}
