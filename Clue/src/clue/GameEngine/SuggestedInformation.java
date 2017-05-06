/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clue.GameEngine;

import clue.GamePieces.Card;

/**
 *
 * @author glender
 */
public class SuggestedInformation {
	
	/**
	 * Singleton instance.
	 */
	private static SuggestedInformation instance = new SuggestedInformation();
	
	/**
	 * Private empty constructor.
	 */
	private SuggestedInformation() {}
	
	/**
	 * Thread safe getInstance.
	 * @return 
	 */
	public static synchronized SuggestedInformation getInstance() {
		if (null == instance) {
			instance = new SuggestedInformation();
		}
		
		return instance;
	}
	
	public Card killer = new Card();
	public Card weapon = new Card();
	public Card room = new Card();
	public boolean win = false;
	
}
