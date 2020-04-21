package game;//package menu;
import exceptions.MapNotSetException;
import map.Map;
import menu.Helper;
import menu.MenuValidator;
import player.Direction;
import player.Player;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Game {

    //map
    static Map map = Map.getMap();
    //random number to use
    private static Random random = new Random();
    //array of players
    private static Player[] players;

    /**
     * Method to set and init players
     * @param amount number of plauers
     */
    private static void setNumPlayers(int amount)
    {
        players = new Player[amount];

        //init players
        for(int i=0; i < amount; i++)
            players[i] = new Player(random);

    }

    public static void main(String args[]) throws MapNotSetException, IOException, URISyntaxException {

        Scanner scanner = new Scanner(System.in);
        boolean amtOfPlayerValid;
        boolean mapSizeValid;
        boolean directionValid;
        boolean moved;
        boolean won = false;
        int playersAmt; //amount of players
        int mapSize; //length of map size (square size map)
        int direction; //user direction for moving Up, Down, Left or Right

        //read number of players
        do {
            playersAmt = Helper.integerVal(scanner, "Enter amount of players [2-8]", "Please input a number");
            amtOfPlayerValid = MenuValidator.amtPlayersValidator(playersAmt);
        } while (!amtOfPlayerValid);

        //get map size
        do {
            mapSize = Helper.integerVal(scanner, "Enter length of map size ", "Please input a number");
            mapSizeValid = MenuValidator.mapSize(playersAmt, mapSize);
        } while (!mapSizeValid);

        //set map size
        map.setSize(mapSize, random);

        //init players
        setNumPlayers(playersAmt);

        for (int i = 1; i <= playersAmt && !won; i++) {
            Player player = players[i - 1];
            System.out.println("\nPlayer " + i);
            do {
                direction = Helper.integerVal(scanner, "Enter the next direction\n1. UP\n2. DOWN\n3. LEFT\n4.RIGHT", "Please input a number");
                directionValid = MenuValidator.directionCheck(direction);

                Direction actualDirection;

                switch (direction) {
                    case 1:
                        actualDirection = Direction.UP;
                        break;
                    case 2:
                        actualDirection = Direction.DOWN;
                        break;
                    case 3:
                        actualDirection = Direction.LEFT;
                        break;
                    default:
                        actualDirection = Direction.RIGHT;
                        break;
                }
                moved = player.move(actualDirection);


            } while (!directionValid || !moved);


        }
    }
}
