/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clue.GameEngine;

import clue.GamePieces.Player;
import clue.Helpers.Location;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 *
 * @author glender
 */
public class LocationManager {
	
	private HashMap<Location, Boolean> occupied = new HashMap<Location, Boolean>();
	private List<Location> defaultColors = new ArrayList<Location>();	
	
	/**
	 * Singleton instance.
	 */
	private static LocationManager instance = new LocationManager();
	
	/**
	 * Private empty constructor.
	 */
	private LocationManager() {}
	
	/**
	 * Thread safe getInstance.
	 * @return 
	 */
	public static synchronized LocationManager getInstance() {
		if (null == instance) {
			instance = new LocationManager();
		}
		
		return instance;
	}
	
	public void setup(List<Location> list) {
		for (Location loc : list) {
			occupied.put(loc, false);
			if ((loc.getY() == 18 && loc.getX() == 0) || (loc.getY() == 6 && loc.getX() == 0) ||(loc.getY() == 0 && loc.getX() == 14) || (loc.getY() == 6 && loc.getX() == 21) || (loc.getY() == 21 && loc.getX() == 5) || (loc.getY() == 21 && loc.getX() == 13)) {
				loc.setColor(Color.ORANGE);
			}
			defaultColors.add(loc);
		}
		
		printOccupied();
	}
	
	public HashMap<Location, Boolean> getOccupied() {
		return occupied;
	}
	
	public List<Location> getNeighbors(Location l) {
		for (Location loc: defaultColors) {
			if (loc.getX() == l.getX() && loc.getY() == l.getY()) {
				return loc.getNeighbor();
			}
		}
		
		return null;
	}
	
	public boolean available(Location spot) {
		Iterator it = occupied.entrySet().iterator();
		
		while(it.hasNext()) {
			Map.Entry<Location, Boolean> pair = (Map.Entry) it.next();
			
			if (pair.getKey().equals(spot)) {
				return !pair.getValue();
			}
		}
		
		return false;
	}
	
	public boolean moveToLocation(Location location, Player player) {
		Location modified = new Location(0,0);
		boolean taken = false;
		
		Iterator it = occupied.entrySet().iterator();

		while(it.hasNext()) {
			Map.Entry<Location, Boolean> pair = (Map.Entry) it.next();

			if (pair.getKey().equals(location) && !pair.getValue()) {
				taken = pair.getValue();
				it.remove();
				break;
			}
		}
		
		while(it.hasNext()) {
			Map.Entry<Location, Boolean> pair = (Map.Entry) it.next();

			if (pair.getKey().equals(player.getLocation()) && pair.getValue()) {
				it.remove();
				break;
			}
		}

		if (taken) {
			return false;
		} else {
			location.setColor(player.getPlayerColor());
			occupied.put(location, true);
			System.out.println("LENDER -- modified value to " + location.getColor());
			return true;
		}
			
	}
	
	public void printOccupied() {
		Iterator it = occupied.entrySet().iterator();
		
		while(it.hasNext()) {
			Map.Entry<Location, Boolean> pair = (Map.Entry) it.next();
			System.out.println("LENDER -- VALUES -- " + pair.getKey().toString() + " with color " + pair.getKey().getColor());
		}
		
	}

	public void makeDefaultColor(Location location) {
		
		Iterator it = occupied.entrySet().iterator();
		
		System.out.println("LENDER -- location is " + location.toString());

		while(it.hasNext()) {
			Map.Entry<Location, Boolean> pair = (Map.Entry) it.next();

			if (pair.getKey().equals(location)) {
				it.remove();
			}

		}
		
		for (Location lo: defaultColors) {
			if (lo.equals(location)) {
				System.out.println("LENDER -- setting default color " + lo.getColor());
				location.setColor(lo.getColor());
				occupied.put(location, false);
			}
		}
		
	}

}
