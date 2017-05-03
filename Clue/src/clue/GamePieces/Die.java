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
	
	/**
	 * 
	 */
	Die() {}
	
	/**
	 * 
	 * @return 
	 */
	public int rollDie() {
		Random random = new Random();
		return random.nextInt(max - min + 1) + min;
	}
	
}
