package menu;

public class MenuValidator {

    /**
     * Method to check the amount of players between 2 & 8
     * @param players amount of players
     * @return
     */
    public static boolean amtPlayersValidator(int players){
        return players >= 2 && players <= 8;
    }

    /**
     * Method to check map size is set accordingly
     * @param players amount of players
     * @param size map size
     * @return
     */
    public static boolean mapSize(int players, int size){
        if(players >= 2 && players <= 4){
            if(size >= 5 && size <= 50){
                return true;
            }
            else{
                System.out.println("Map size should between between 5-50");
                return false;
            }
        }
        else{
            if(size >= 8 && size <= 50){
                return true;
            }
            else{
                System.out.println("Map size should between between 8-50");
                return false;
            }
        }
    }

}
