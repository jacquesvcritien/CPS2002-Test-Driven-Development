package menu;

/**
 * This is a class to validate menu input
 */
public class MenuValidator {

    /**
     * Method to check the amount of players between 2 & 8
     *
     * @param players amount of players
     * @return if the amount of players is valid
     */
    public static boolean amtPlayersValidator(int players) {
        return players >= 2 && players <= 8;
    }

    /**
     * Method to check map size is set accordingly
     *
     * @param players amount of players
     * @param size    map size
     * @return if the map size is correct
     */
    public static boolean mapSize(int players, int size) {
        if (players >= 2 && players <= 4) {
            if (size >= 5 && size <= 50) {
                return true;
            } else {
                System.out.println("Map size should between between 5-50");
                return false;
            }
        } else {
            if (size >= 8 && size <= 50) {
                return true;
            } else {
                System.out.println("Map size should between between 8-50");
                return false;
            }
        }
    }

    /**
     * Method to check the command entered by the user is acceptable
     *
     * @param direction value which states the direction of the user
     * @return if the direction chosen is correct
     */
    public static boolean directionCheck(int direction) {
        if (direction >= 1 && direction <= 4) {
            return true;
        } else {
            System.out.println("Direction choice should be between 1-4");
            return false;
        }
    }


    /**
     * Method to check the game type
     *
     * @param gameType 1 = Single, 2 = Collaborative
     * @return
     */
    public static boolean checkGameType(int gameType) {
        return gameType == 1 || gameType == 2;
    }
}