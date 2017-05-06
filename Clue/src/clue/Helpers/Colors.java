/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clue.Helpers;

import java.awt.Color;
import java.util.HashMap;

/**
 *
 * @author glender
 */
public class Colors {
	
	private HashMap<String, Color> colors = new HashMap<String, Color>();
	
	/**
	 *
	 */
	public Colors() {
		setupColors();
	}
	
	/**
	 *
	 */
	public void Colors() {
		setupColors();
	}
	
	/**
	 *
	 */
	public void setupColors() {
		
		colors.put("YELLOW",  Color.YELLOW);
		colors.put("BLUE",   Color.BLUE);
		colors.put("RED",    Color.RED);
		colors.put("WHITE",  Color.WHITE);
		colors.put("PURPLE", new Color(192, 0, 255));
		colors.put("GREEN",  Color.GREEN);
		
	}
	
	/**
	 * 
	 * @param name
	 * @return 
	 */
	
	public Color getColor(String name) {
		return colors.getOrDefault(name, Color.BLUE);
	}
	
}
