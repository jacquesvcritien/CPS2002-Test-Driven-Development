package menu;

public class MenuValidator {

    /**
     * Method to check the amount of players between 2 & 8
     * @param players
     * @return
     */
    public static boolean amtPlayersValidator(int players){
        return players >= 2 && players <= 8;
    }
}
