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
public class Rooms {
	
	public void Rooms() {}
	
	/**
	 *
	 */
	public static final int NUMBER_OF_ROOMS = 9;

	/**
	 * Rooms of the game.
	 */
	public enum room {
		
		/**
		 * 
		 */
		None(0),
		
		/**
		 *
		 */
		Kitchen(1),

		/**
		 *
		 */
		Conservatory(2),

		/**
		 *
		 */
		DiningRoom(3),

		/**
		 *
		 */
		Ballroom(4),

		/**
		 *
		 */
		BilliardRoom(5),

		/**
		 *
		 */
		Library(6),

		/**
		 *
		 */
		Hall(7),

		/**
		 *
		 */
		Lounge(8),

		/**
		 *
		 */
		Study(9);

		private int numVal;

		room(int numVal) {
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
