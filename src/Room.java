

/**
 * Created by Yannick Meiller in July 2016.
 * Modified by Esma TALHI
 * This class defines and manage a room. The universe of the game is made of interconnected rooms.
 * Each room can have one or several exits. Through each of these exits, we arrive in another room.
 */
public class Room
{
    //Attributes

    private String description;
    private boolean[] exits; // stores the exits of the room :
    // true when the exit exist, false otherwise
    private Room[] connectedRooms; // stores rooms connected through the exits of this room.
    private DirectionLexicon possibleDirections; //possibleDirections is a object of the class DirectionLexicon (which is defined in this project)

    // Methods

    //Public methods


    /**
     * This is a constructor.
     * Creates a room described "description". Initially, it has no exits.
     * "description" is something like "a kitchen" or "an open court yard".
     */
    public Room(String descriptionText, DirectionLexicon directions) {
        int i;
        int numberOfDirections;

        description = descriptionText;
        possibleDirections = directions;
        numberOfDirections = possibleDirections.getNumberOfWords();//the method getNumberOfWords() is defined in class Lexicon
        //possibleDirection is an object of the classe DirectionLexicon, which derives from the class Lexicon.
        exits = new boolean[numberOfDirections]; // we initialize the array of booleans, with the right size
        // For example, we could have :
        // exits[0] : north exit
        // exits[1] : east exit
        // exits[2] : south exit
        // exits[3] : west exit
        connectedRooms = new Room[numberOfDirections]; // we initialize the array of objects of class Room, with the right size
        // For example, we could have :
        // connectedRoom[0] : room connected through the north exit if this exit exists
        // connectedRoom[1] : room connected through the east exit if this exit exists
        // connectedRoom[2] : room connected through the south exit if this exit exists
        // connectedRoom[3] : room connected through the west exit if this exit exists

        // initialization of the exit list : no exit
        for (i = 0; i < numberOfDirections; i = i + 1) {
            exits[i] = false;
        }

        // As we initialized the exit list to no exit,
        // we do not process connectedRooms
    }


    /**
     *
     * This method defines one exit of this room.
     * The direction is given by a text.
     *
     * @param direction - a word recognized as describing a direction.
     * @param nextRoom - an object Room, which wil be reached if we follow this direction
     *
     */
    public void setExit(String direction, Room nextRoom) {

        int dirNum;

        dirNum = possibleDirections.wordToCode(direction);
        // wordToCode(...) is defined in class Lexicon. It translate a word of the lexicon in its digital code (0, 1, 2, etc.).
        //possibleDirection is an object of the classe DirectionLexicon, which derives from the class Lexicon.

        exits[dirNum] = true;
        connectedRooms[dirNum] = nextRoom;
    }


    /**
     * Return the description of the room (the one that was defined in the
     * constructor).
     */
    public String getShortDescription() {

        return description;
    }

    /**
     * Return a long description of this room, on the form:
     * You are in the kitchen.
     * Exits: north west
     */
    public String getLongDescription() {

        return "You are in " + description + ".\n" + exitString();
        // + allows to concatenate Strings (texts)
        // "\n" will be interpreted as Go to the line (return)
    }

    /**
     * Return whether there is an exit in the given direction
     * If the direction does not exist, returns false.
     */
    public boolean isExit(String direction) {
        int directionCode;
        boolean result;

        directionCode = possibleDirections.wordToCode(direction);
        if (possibleDirections.isErrorCode(directionCode)) {
            result = false;
        } else {
            result = exits[directionCode];
        }
        return result;
    }


    /**
     * Returns the room that is reached if we go from this room in direction
     * "direction". There must be a room in this direction.
     */
    public Room nextRoom(String direction) {
        int directionCode;

        directionCode = possibleDirections.wordToCode(direction);
        return connectedRooms[directionCode];
    }

    /**
     *
     * @param otherRoom
     * @return a boolean : true if this room is the same as the one proposed as a parameter
     * This method is necessary becaus we cannot use a simple ==.
     *
     */
    public boolean isSameRoomAs(Room otherRoom){

        return (description.equals(otherRoom.getShortDescription()));
    }

    //Private methods

    /**
     * Return a string describing the room's exits (separated by comas ans spaces), for example
     * "Exits: north, south ".
     */
    private String exitString() {
        int i;
        String returnString;

        returnString = "Exits:";
        for (i = 0; i < possibleDirections.getNumberOfWords(); i = i + 1) {
            if (exits[i]) {
                returnString = returnString + ", " + connectedRooms[i].getShortDescription();
            }
        }
        return returnString;
    }
}
