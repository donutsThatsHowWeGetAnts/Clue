/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clue.GamePieces;

import clue.Helpers.Colors;

/**
 *
 * @author glender
 */
public class Characters {
	
	/**
	 *
	 */
	public static final int NUMBER_OF_CHARACTERS = 6;

	/**
	 *
	 */
	public static final int NUMBER_OF_COLORS = 6;
	
	/**
	 * 
	 */
	private Colors colors = new Colors();

	/**
	 * Characters of the game.
	 */
	public enum characters {

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

		characters(int numVal) {
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
	
	/**
	 * Colors of the characters of the game.
	 */
	public enum colors {

		/**
		 *
		 */
		YELLOW(1),

		/**
		 *
		 */
		RED(2),

		/**
		 *
		 */
		PURPLE(3),

		/**
		 *
		 */
		GREEN(4),

		/**
		 *
		 */
		WHITE(5),

		/**
		 *
		 */
		BLUE(6);

		private int numVal;

		colors(int numVal) {
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
	
	/**
	 * 
	 * @param c
	 * @return 
	 */
	public java.awt.Color getCharacterColor(characters c) {
		switch (c.getNumVal()) {
			case 1:
				return colors.getColor("YELLOW");
			case 2:
				return colors.getColor("RED");
			case 3:
				return colors.getColor("PURPLE");
			case 4:
				return colors.getColor("GREEN");
			case 5:
				return colors.getColor("WHITE");
			case 6:
				return colors.getColor("BLUE");
			default: 
				return colors.getColor("YELLOW");
		}
	}
	
}
