package game;//package menu;
import exceptions.MapNotSetException;
import files.Builder;
import files.Director;
import files.MapResultBuilder;
import map.Map;
import menu.Helper;
import menu.MenuValidator;
import player.Direction;
import player.Player;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
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
    //list of winners
    private static ArrayList<Player> winners = new ArrayList<>();

    private static Builder mapResultBuilder = new MapResultBuilder();
    private static Director director = new Director(mapResultBuilder);

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

    /**
     * Method to set a winner
     * @param winner to set
     */
    public static void setWinner(Player winner) {
        Game.winners.add(winner);
    }

    /**
     * Method which generates players html files
     */
    private static void generateHTMLfiles() throws IOException, URISyntaxException {
        for(int i=0; i < players.length; i++)
        {
            String files_name = "map_player_"+(i+1);
            files.Helper.createDirectory(files_name);
            files.Helper.copyDirectory("fonts", files_name+"/fonts");
            files.Helper.copyDirectory("images", files_name+"/images");
            files.Helper.copyFile("game.css", files_name+"/game.css");

            //create page
            director.construct(players[i], i+1);
            files.Helper.writeFile(files_name, "game.html", mapResultBuilder.getPage().getHTML());
        }
    }

    /**
     * Method to check if a player is a winner or not
     * @param player player to check
     * @return
     */
    public static boolean isAWinner(Player player)
    {
        return winners.contains(player);
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
        }while(!amtOfPlayerValid);

        //get map size
        do {
            mapSize = Helper.integerVal(scanner, "Enter length of map size ", "Please input a number");
            mapSizeValid = MenuValidator.mapSize(playersAmt, mapSize);
        }while(!mapSizeValid);

        //set map size
        map.setSize(mapSize, random);

        //init players
        setNumPlayers(playersAmt);

        //generate files
        generateHTMLfiles();

        for(int i = 1; i <= playersAmt && !won; i++) {
            Player player = players[i-1];
            System.out.println("\nPlayer " + i);
            do {
                direction = Helper.integerVal(scanner, "Enter the next direction\n1. UP\n2. DOWN\n3. LEFT\n4. RIGHT", "Please input a number");
                directionValid = MenuValidator.directionCheck(direction);

                Direction actualDirection;

                switch(direction)
                {
                    case 1: actualDirection = Direction.UP;break;
                    case 2: actualDirection = Direction.DOWN;break;
                    case 3: actualDirection = Direction.LEFT;break;
                    default: actualDirection = Direction.RIGHT;break;
                }
                moved = player.move(actualDirection);


            } while (!directionValid || !moved);


            if(i == playersAmt){
                //if there are winners
                if(winners != null && winners.size()!=0)
                {
                    for(int j=0; j < winners.size(); j++)
                    {
                        int index = Arrays.asList(players).indexOf(winners.get(j));
                        System.out.println("Player "+(index+1)+" is a winner!");
                        won = true;
                        break;
                    }
                }

                i = 0;
            }

            //regenerate files
            generateHTMLfiles();
        }
    }

}
