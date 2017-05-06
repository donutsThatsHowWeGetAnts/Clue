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
public class Card {

	public Card() {}

	public int type = 0;
	public int value = 0;
	public String desc = null;

	public static final int NUM_SUSPECTS = Suspect.NUMBER_OF_SUSPECTS;
	public static final int NUM_ROOMS = Rooms.NUMBER_OF_ROOMS;
	public static final int NUM_WEAPONS = Weapon.NUMBER_OF_WEAPONS;
	public static final int TOTAL = NUM_ROOMS + NUM_SUSPECTS + NUM_WEAPONS;

	public static final int TYPE_SUSPECT = 0;
	public static final int TYPE_WEAPON = 1;
	public static final int TYPE_ROOM = 2;

	public static final int ROOM_HALL = Rooms.room.Hall.getNumVal();
	public static final int ROOM_LOUNGE = Rooms.room.Lounge.getNumVal();
	public static final int ROOM_DINING = Rooms.room.DiningRoom.getNumVal();
	public static final int ROOM_KITCHEN = Rooms.room.Kitchen.getNumVal();
	public static final int ROOM_BALLROOM = Rooms.room.Ballroom.getNumVal();
	public static final int ROOM_CONSERVATORY = Rooms.room.Conservatory.getNumVal();
	public static final int ROOM_BILLIARD = Rooms.room.BilliardRoom.getNumVal();
	public static final int ROOM_STUDY = Rooms.room.Study.getNumVal();
	public static final int ROOM_LIBRARY = Rooms.room.Library.getNumVal();
	
	public static final Card hall = new Card(TYPE_ROOM, ROOM_HALL);
	public static final Card lounge = new Card(TYPE_ROOM, ROOM_LOUNGE);
	public static final Card dining = new Card(TYPE_ROOM, ROOM_DINING);
	public static final Card kitchen = new Card(TYPE_ROOM, ROOM_KITCHEN);
	public static final Card ballroom = new Card(TYPE_ROOM, ROOM_BALLROOM);
	public static final Card conservatory = new Card(TYPE_ROOM, ROOM_CONSERVATORY);
	public static final Card billiard = new Card(TYPE_ROOM, ROOM_BILLIARD);
	public static final Card study = new Card(TYPE_ROOM, ROOM_STUDY);
	public static final Card library = new Card(TYPE_ROOM, ROOM_LIBRARY);

	public static final int SUSPECT_SCARLET = Suspect.suspect.MissScarlet.getNumVal();
	public static final int SUSPECT_WHITE = Suspect.suspect.MrsWhite.getNumVal();
	public static final int SUSPECT_PLUM = Suspect.suspect.ProjPlum.getNumVal();
	public static final int SUSPECT_MUSTARD = Suspect.suspect.ColMustard.getNumVal();
	public static final int SUSPECT_GREEN = Suspect.suspect.MrGreen.getNumVal();
	public static final int SUSPECT_PEACOCK = Suspect.suspect.MrsPeacock.getNumVal();

	public static final int WEAPON_KNIFE = Weapon.weapon.Knife.getNumVal();
	public static final int WEAPON_ROPE = Weapon.weapon.Rope.getNumVal();
	public static final int WEAPON_REVOLVER = Weapon.weapon.Revolver.getNumVal();
	public static final int WEAPON_WRENCH = Weapon.weapon.Wrench.getNumVal();
	public static final int WEAPON_PIPE = Weapon.weapon.LeadPipe.getNumVal();
	public static final int WEAPON_CANDLE = Weapon.weapon.CandleStick.getNumVal();

	public static final String mustard_desc = "Colonel Mustard: was an old friend of Mr. Boddy's uncle and a frequent guest at the Tudor Mansion.";
	public static final String plum_desc = "Professor Plum: A Professor in Middle Eastern history had many of his archaeological digs funded by Mr. Boddy's uncle.";
	public static final String scarlet_desc = "Miss Scarlett: An aspiring but not very talented actress. Has decided on a career change and is now setting her sights on rich old widowers, which is why she is at Mr.Boddy's dinner party.";
	public static final String green_desc = "Mr. Green: a trickster and conman has become acquainted with Mr. Boddy through his uncle, Sir Hugh Black.";
	public static final String peacock_desc = "Mrs. Peacock: is Miss. Scarlett's mother, a socialite and three time widow, she still dreams of a career on the stage.";
	public static final String white_desc = "Mrs. White: Mr. Boddy's long-term housekeeper and cook.";
	public static final String[] descriptions = {scarlet_desc, white_desc, plum_desc, mustard_desc, green_desc, peacock_desc};

	public static final Card scarlet = new Card(TYPE_SUSPECT, SUSPECT_SCARLET);
	public static final Card mustard = new Card(TYPE_SUSPECT, SUSPECT_MUSTARD);
	public static final Card green = new Card(TYPE_SUSPECT, SUSPECT_GREEN);
	public static final Card plum = new Card(TYPE_SUSPECT, SUSPECT_PLUM);
	public static final Card peacock = new Card(TYPE_SUSPECT, SUSPECT_PEACOCK);
	public static final Card white = new Card(TYPE_SUSPECT, SUSPECT_WHITE);
	
	public static final Card candle = new Card(TYPE_WEAPON, WEAPON_CANDLE);
	public static final Card knife = new Card(TYPE_WEAPON, WEAPON_KNIFE);
	public static final Card rope = new Card(TYPE_WEAPON, WEAPON_ROPE);
	public static final Card revolver = new Card(TYPE_WEAPON, WEAPON_REVOLVER);
	public static final Card wrench = new Card(TYPE_WEAPON, WEAPON_WRENCH);
	public static final Card pipe = new Card(TYPE_WEAPON, WEAPON_PIPE);

	public Card(int type, int value) {
		this.type = type;
		this.value = value;
		this.desc = toString();
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public int hashCode() {
		return type + value;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Card) {
			Card c = (Card) obj;
			return (c.type == this.type && c.value == this.value);
		} else {
			return false;
		}
	}
	
	public Card getCard(String desc) {
		
		if (desc.equalsIgnoreCase("Miss Scarlet")) {
			return Card.scarlet;
		}

		if (desc.equalsIgnoreCase("Professor Plum")) {
			return Card.plum;
		}

		if (desc.equalsIgnoreCase("Mrs. White")) {
			return Card.white;
		}
		
		if (desc.equalsIgnoreCase("Colonel Mustard")) {
			return Card.mustard;
		}
		
		if (desc.equalsIgnoreCase("Mr. Green")) {
			return Card.green;
		}
		
		if (desc.equalsIgnoreCase("Mrs. Peacock")) {
			return Card.peacock;
		}
		
		if (desc.equalsIgnoreCase("Hall")) {
			return Card.hall;
		}
		
		if (desc.equalsIgnoreCase("Lounge")) {
			return Card.lounge;
		}
		
		if (desc.equalsIgnoreCase("Dining Room")) {
			return Card.dining;
		}
		
		if (desc.equalsIgnoreCase("Kitchen")) {
			return Card.kitchen;
		}

		if (desc.equalsIgnoreCase("Ballroom")) {
			return Card.ballroom;
		}
		
		if (desc.equalsIgnoreCase("Conservatory")) {
			return Card.conservatory;
		}
		
		if (desc.equalsIgnoreCase("Billiard Room")) {
			return Card.billiard;
		}
		
		if (desc.equalsIgnoreCase("Study")) {
			return Card.study;
		}
		
		if (desc.equalsIgnoreCase("Library")) {
			return Card.library;
		}
		
		if (desc.equalsIgnoreCase("Knife")) {
			return Card.knife;
		}
		
		if (desc.equalsIgnoreCase("Rope")) {
			return Card.rope;
		}
		
		if (desc.equalsIgnoreCase("Revolver")) {
			return Card.revolver;
		}
		
		if (desc.equalsIgnoreCase("Wrench")) {
			return Card.wrench;
		}
		
		if (desc.equalsIgnoreCase("Pipe")) {
			return Card.pipe;
		}
		
		if (desc.equalsIgnoreCase("Candlestick")) {
			return Card.candle;
		}
		
		return Card.ballroom;
		
	}

	public String getDescription() {
		return descriptions[value];
	}

	public String toString() {
		String desc = null;

		switch (type) {
			case TYPE_SUSPECT:
				if (value == SUSPECT_SCARLET) {
					desc = "Miss Scarlet";
				} else if (value == SUSPECT_PLUM) {
					desc = "Professor Plum";
				} else if (value == SUSPECT_WHITE) {
					desc = "Mrs. White";
				} else if (value == SUSPECT_MUSTARD) {
					desc = "Colonel Mustard";
				} else if (value == SUSPECT_GREEN) {
					desc = "Mr. Green";
				} else if (value == SUSPECT_PEACOCK) {
					desc = "Mrs. Peacock";
				}
				break;
			case TYPE_ROOM:
				if (value == ROOM_HALL) {
					desc = "Hall";
				} else if (value == ROOM_LOUNGE) {
					desc = "Lounge";
				} else if (value == ROOM_DINING) {
					desc = "Dining Room";
				} else if (value == ROOM_KITCHEN) {
					desc = "Kitchen";
				} else if (value == ROOM_BALLROOM) {
					desc = "Ballroom";
				} else if (value == ROOM_CONSERVATORY) {
					desc = "Conservatory";
				} else if (value == ROOM_BILLIARD) {
					desc = "Billiard Room";
				} else if (value == ROOM_STUDY) {
					desc = "Study";
				} else if (value == ROOM_LIBRARY) {
					desc = "Library";
				}
				break;
			case TYPE_WEAPON:
				if (value == WEAPON_KNIFE) {
					desc = "Knife";
				} else if (value == WEAPON_ROPE) {
					desc = "Rope";
				} else if (value == WEAPON_REVOLVER) {
					desc = "Revolver";
				} else if (value == WEAPON_WRENCH) {
					desc = "Wrench";
				} else if (value == WEAPON_PIPE) {
					desc = "Pipe";
				} else if (value == WEAPON_CANDLE) {
					desc = "Candlestick";
				}
				break;
		}

		return desc;

	}

}
