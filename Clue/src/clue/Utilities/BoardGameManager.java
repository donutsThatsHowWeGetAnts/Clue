/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clue.Utilities;

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
	
	public boolean getIsDoorButton(int i, int j) {
		return this.isDoorButton[i][j];
	}
	
}
