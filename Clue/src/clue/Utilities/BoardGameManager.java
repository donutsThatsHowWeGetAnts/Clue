/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clue.Utilities;

import clue.Helpers.Location;

/**
 *
 * @author glender
 */
public class BoardGameManager {
	
	/**
	 * 
	 */
	private Boolean[][] canClickButton = new Boolean[22][22];
	
	/**
	 * 
	 */
	private Boolean[][] isDoorButton = new Boolean[22][22];
	
	/**
	 * Singleton instance.
	 */
	private static BoardGameManager instance = new BoardGameManager();
	
	/**
	 * Private empty constructor.
	 */
	private BoardGameManager() {}
	
	/**
	 * Thread safe getInstance.
	 * @return 
	 */
	public static synchronized BoardGameManager getInstance() {
		if (null == instance) {
			instance = new BoardGameManager();
		}
		
		return instance;
	}
	
	public void setCanClickButton(int i, int j, boolean b) {
		this.canClickButton[i][j] = b;
	}
	
	public void setIsDoorButton(int i, int j, boolean b) {
		this.isDoorButton[i][j] = b;
	}
	
	public boolean getCanClickButton(int i, int j) {
		return this.canClickButton[i][j];
	}
	
	public boolean getIsDoorButton(int x, int y) {
		return this.isDoorButton[x][y];
	}

	public Location getClosestDoor(int x, int y) {
		double distance = 100000;
		int finalX = 0;
		int finalY = 0;
		
		if (isDoorButton[x+1][y]) {
			return new Location(x+1, y);
		} else if (isDoorButton[x+1][y+1]) {
			return new Location(x+1, y+1);
		} else if (isDoorButton[x+1][y-1]) {
			return new Location(x+1, y-1);
		} else if (isDoorButton[x-1][y]) {
			return new Location(x-1, y);
		} else if (isDoorButton[x-1][y+1]) {
			return new Location(x-1, y+1);
		} else if (isDoorButton[x][y-1]) {
			return new Location(x, y-1);
		} else if (isDoorButton[x][y+1]) {
			return new Location(x, y+1);
		}
		
		return new Location(finalX, finalY);
		
	}
	
}
