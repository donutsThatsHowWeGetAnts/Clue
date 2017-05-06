package clue.Helpers;


import static clue.GamePieces.Card.NUM_ROOMS;
import static clue.GamePieces.Card.NUM_SUSPECTS;
import static clue.GamePieces.Card.ROOM_BALLROOM;
import static clue.GamePieces.Card.ROOM_BILLIARD;
import static clue.GamePieces.Card.ROOM_CONSERVATORY;
import static clue.GamePieces.Card.ROOM_DINING;
import static clue.GamePieces.Card.ROOM_HALL;
import static clue.GamePieces.Card.ROOM_KITCHEN;
import static clue.GamePieces.Card.ROOM_LIBRARY;
import static clue.GamePieces.Card.ROOM_LOUNGE;
import static clue.GamePieces.Card.ROOM_STUDY;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;


public class RoomIconPlacement {

    int icon_width = 70;
    int icon_height = 80;

    Room[] rooms = new Room[9];
    BufferedImage[] icons = new BufferedImage[NUM_SUSPECTS];

    public RoomIconPlacement() {

    }

    public void addPlayerIcon(int roomId, int playerId) {
        if (roomId < 0) {
            return;
        }
        for (int i = 0; i < NUM_SUSPECTS; i++) {
            if (rooms[roomId].slot[i] == -1) {
                rooms[roomId].slot[i] = playerId;
                break;
            }
        }

    }

    public void removePlayerIcon(int playerId) {
        for (int j = 0; NUM_ROOMS >= j; j++) {
            for (int i = 0; i < NUM_SUSPECTS; i++) {
                if (rooms[j].slot[i] == playerId) {
                    rooms[j].slot[i] = -1;
                }
            }
        }
    }

    public void paintIcons(Graphics2D g) {

        for (int i = 0; i < NUM_ROOMS; i++) {
            for (int j = 0; j < NUM_SUSPECTS; j++) {
                if (rooms[i].slot[j] != -1) {
                    g.drawImage(icons[rooms[i].slot[j]],
                            rooms[i].icon_locations[j][0],//x
                            rooms[i].icon_locations[j][1],//y
                            null);
                }
            }

        }

    }

    class Room {

        int id = 0;
        int start_x = 5;
        int start_y = 5;

        int[][] icon_locations;
        int[] slot = {-1, -1, -1, -1, -1, -1};

        Room(int id) {
            this.id = id;

            if (id == ROOM_KITCHEN) {
                start_x = 5;
                start_y = 5;
            }
            if (id == ROOM_BALLROOM) {
                start_x = 261;
                start_y = 69;
            }
            if (id == ROOM_CONSERVATORY) {
                start_x = 548;
                start_y = 5;
            }
            if (id == ROOM_BILLIARD) {
                start_x = 581;
                start_y = 293;
            }
            if (id == ROOM_LIBRARY) {
                start_x = 579;
                start_y = 452;
            }
            if (id == ROOM_STUDY) {
                start_x = 550;
                start_y = 675;
            }
            if (id == ROOM_HALL) {
                start_x = 291;
                start_y = 695;
            }
            if (id == ROOM_LOUNGE) {
                start_x = 5;
                start_y = 619;
            }
            if (id == ROOM_DINING) {
                start_x = 5;
                start_y = 327;
            }

            int[][] temp = {{start_x, start_y},
            {start_x + (icon_width * 1) + 2, start_y},
            {start_x + (icon_width * 2) + 2, start_y},
            {start_x, start_y + (icon_height) + 2},
            {start_x + (icon_width * 1) + 2, start_y + (icon_height) + 2},
            {start_x + (icon_width * 2) + 2, start_y + (icon_height) + 2}};

            this.icon_locations = temp;
        }

    }

}
