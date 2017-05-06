/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clue.GamePieces;

import java.util.Random;

/**
 *
 * @author glender
 */
public class Die {
	
	private final int min = 1;
	private final int max = 6;
	
	private final int oMin = 1;
	private final int oMax = 9;
	
	/**
	 * 
	 */
	public Die() {}
	
	/**
	 * 
	 * @return 
	 */
	public int rollDie() {
		Random random = new Random();
		return random.nextInt(max - min + 1) + min;
	}
	
	/**
	 * 
	 * @return 
	 */
	public int rollBetweenRooms() {
		Random random = new Random();
		return random.nextInt(oMax - oMin + 1) + oMin;
	}
	
	public int rollBetweenValues(int minimum, int maximum) {
		Random random = new Random();
		return random.nextInt(maximum - minimum + 1) + minimum;
	}
	
}
