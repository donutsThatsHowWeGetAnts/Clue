/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clue.GamePieces;

/**
 *
 * @author glender
 */
public class Weapon {
	
	/**
	 *
	 */
	public static final int NUMBER_OF_WEAPONS = 6;
	
	/**
	 * Weapons of the game.
	 */
	public enum weapon {

		/**
		 *
		 */
		CandleStick(1),

		/**
		 *
		 */
		Dagger(2),

		/**
		 *
		 */
		LeadPipe(3),

		/**
		 *
		 */
		Revolver(4),

		/**
		 *
		 */
		Rope(5),

		/**
		 *
		 */
		Spanner(6);

		private int numVal;

		weapon(int numVal) {
			this.numVal = numVal;
		}

		/**
		 *
		 * @return
		 */
		public int getNumVal() {
			return numVal;
		}
	}
	
}
