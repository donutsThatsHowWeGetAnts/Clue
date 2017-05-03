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
public class Suspect {
	
	private static final int NUMBER_OF_SUSPECTS = 6;
	
	/**
	 * Suspects of the game.
	 */
	public enum suspect {

		/**
		 *
		 */
		ColMustard(1),

		/**
		 *
		 */
		MissScarlet(2),

		/**
		 *
		 */
		ProjPlum(3),

		/**
		 *
		 */
		MrGreen(4),

		/**
		 *
		 */
		MrsWhite(5),

		/**
		 *
		 */
		MrsPeacock(6);

		private int numVal;

		suspect(int numVal) {
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
