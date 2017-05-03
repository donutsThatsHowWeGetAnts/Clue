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

    public static final int NUM_SUSPECTS = Characters.NUMBER_OF_CHARACTERS;
    public static final int NUM_ROOMS = Rooms.NUMBER_OF_ROOMS;
    public static final int NUM_WEAPONS = Weapon.NUMBER_OF_WEAPONS;
    public static final int TOTAL = NUM_ROOMS + NUM_SUSPECTS + NUM_WEAPONS;

    public static final int TYPE_SUSPECT = 0;
    public static final int TYPE_WEAPON = 1;
    public static final int TYPE_ROOM = 2;

    public static final String MUSTARD = "Colonel Mustard: was an old friend of Mr. Boddy's uncle and a frequent guest at the Tudor Mansion.";
    public static final String PLUM = "Professor Plum: A Professor in Middle Eastern history had many of his archaeological digs funded by Mr. Boddy's uncle.";
    public static final String SCARLET = "Miss Scarlett: An aspiring but not very talented actress. Has decided on a career change and is now setting her sights on rich old widowers, which is why she is at Mr.Boddy's dinner party.";
    public static final String GREEN = "Mr. Green: a trickster and conman has become acquainted with Mr. Boddy through his uncle, Sir Hugh Black.";
    public static final String PEACOCK = "Mrs. Peacock: is Miss. Scarlett's mother, a socialite and three time widow, she still dreams of a career on the stage.";
    public static final String WHITE = "Mrs. White: Mr. Boddy's long-term housekeeper and cook.";
    public static final String[] descriptions = {SCARLET, WHITE, PLUM, MUSTARD, GREEN, PEACOCK};

    public static final Card scarlet = new Card(TYPE_SUSPECT, SUSPECT_SCARLET);
    public static final Card mustard = new Card(TYPE_SUSPECT, SUSPECT_MUSTARD);
    public static final Card green = new Card(TYPE_SUSPECT, SUSPECT_GREEN);
    public static final Card plum = new Card(TYPE_SUSPECT, SUSPECT_PLUM);
    public static final Card peacock = new Card(TYPE_SUSPECT, SUSPECT_PEACOCK);
    public static final Card white = new Card(TYPE_SUSPECT, SUSPECT_WHITE);

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

    public String getDescription() {
        return descriptions[value];
    }

    public String toString() {
        String desc = null;

        switch (type) {
            case TYPE_SUSPECT:
                switch (value) {
                    case Suspect.suspect.MissScarlet.getNumVal():
                        desc = "Miss Scarlet";
                        break;
                    case SUSPECT_PLUM:
                        desc = "Professor Plum";
                        break;
                    case SUSPECT_WHITE:
                        desc = "Mrs. White";
                        break;
                    case SUSPECT_MUSTARD:
                        desc = "Colonel Mustard";
                        break;
                    case SUSPECT_GREEN:
                        desc = "Mr. Green";
                        break;
                    case SUSPECT_PEACOCK:
                        desc = "Mrs. Peacock";
                        break;
                }
                break;
            case TYPE_ROOM:
                switch (value) {
                    case ROOM_HALL:
                        desc = "Hall";
                        break;
                    case ROOM_LOUNGE:
                        desc = "Lounge";
                        break;
                    case ROOM_DINING:
                        desc = "Dining Room";
                        break;
                    case ROOM_KITCHEN:
                        desc = "Kitchen";
                        break;
                    case ROOM_BALLROOM:
                        desc = "Ballroom";
                        break;
                    case ROOM_CONSERVATORY:
                        desc = "Conservatory";
                        break;
                    case ROOM_BILLIARD:
                        desc = "Billiard Room";
                        break;
                    case ROOM_STUDY:
                        desc = "Study";
                        break;
                    case ROOM_LIBRARY:
                        desc = "Library";
                        break;
                }
                break;
            case TYPE_WEAPON:
                switch (value) {
                    case WEAPON_KNIFE:
                        desc = "Knife";
                        break;
                    case WEAPON_ROPE:
                        desc = "Rope";
                        break;
                    case WEAPON_REVOLVER:
                        desc = "Revolver";
                        break;
                    case WEAPON_WRENCH:
                        desc = "Wrench";
                        break;
                    case WEAPON_PIPE:
                        desc = "Pipe";
                        break;
                    case WEAPON_CANDLE:
                        desc = "Candlestick";
                        break;
                }
                break;
        }

        return desc;

    }


}