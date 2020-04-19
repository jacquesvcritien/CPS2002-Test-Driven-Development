//package menu;
import menu.Helper;
import menu.MenuValidator;

import java.util.Scanner;

public class Game {
    public static void main(String args[]) {

        Scanner scanner = new Scanner(System.in);

        boolean amtOfPlayerValid;
        boolean mapSizeValid;
        boolean directionValid;
        int players; //amount of players
        int mapSize; //length of map size (square size map)
        int direction; //user direction for moving Up, Down, Left or Right

        do {
            players = Helper.integerVal(scanner, "Enter amount of players [2-8]", "Please input a number");
            amtOfPlayerValid = MenuValidator.amtPlayersValidator(players);
        }while(!amtOfPlayerValid);
        do {
            mapSize = Helper.integerVal(scanner, "Enter length of map size ", "Please input a number");
            mapSizeValid = MenuValidator.mapSize(players, mapSize);
        }while(!mapSizeValid);
        for(int i = 1; i <= players; i++) {
            System.out.println("\nPlayer " + i);
            do {
                direction = Helper.integerVal(scanner, "Enter the next command\n1.Up\n2.Down\n3.Left\n4.Right", "Please input a number");
                directionValid = MenuValidator.directionCheck(direction);
            } while (!directionValid);
            if(i == players){
                i = 0;
            }
        }
    }

}
