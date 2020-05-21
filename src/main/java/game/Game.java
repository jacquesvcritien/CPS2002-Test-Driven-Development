package game;
import exceptions.MapNotSetException;
import files.Builder;
import files.Director;
import files.MapResultBuilder;
import map.Map;
import menu.Helper;
import menu.MenuValidator;
import team.player.Direction;
import team.player.Player;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * This is the runner class which contains the runner main method for the game
 */
public class Game {
    //map
    static Map map;
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
    public static void setNumPlayers(int amount) throws MapNotSetException {
        players = new Player[amount];

        //init players
        for(int i=0; i < amount; i++)
            players[i] = new Player(random);

    }

    /**
     * Method to return players
     * @return players
     */
    public static Player[] getPlayers() {
        return players;
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
    public static void generateHTMLfiles() throws IOException, URISyntaxException {
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
     * Method to check if a team.player is a winner or not
     * @param player team.player to check
     * @return
     */
    public static boolean isAWinner(Player player)
    {
        return winners.contains(player);
    }

    /**
     * Get map
     * @return
     */
    public static Map getMap()
    {
        return map;
    }


    /**
     * setter for director (FOR TESTING)
     * @param director to set
     */
    public static void setDirector(Director director) {
        Game.director = director;
    }

    /**Setter for random (JUST FOR TESTING)
     * @param random random to set
     */
    public static void setRandom(Random random) {
        Game.random = random;
    }

    /**
     * For Testing purposes;
     */
    public static void reset()
    {
        map.reset();
        //random number to use
        random = new Random();
        //array of players
        players = null;
        //list of winners
        winners = new ArrayList<>();

        mapResultBuilder = new MapResultBuilder();
        director = new Director(mapResultBuilder);
    }

    /**
     * Setter for map
     * @param map map to set
     */
    public static void setMap(Map map) {
        Game.map = map;
    }

    public static void main(String args[]) throws MapNotSetException, IOException, URISyntaxException {
        Scanner scanner = new Scanner(System.in);
        boolean amtOfPlayerValid;
        boolean mapSizeValid;
        boolean directionValid;
        boolean gameTypeValid;
        boolean mapTypeValid;
        boolean amtOfTeamsValid;
        boolean moved;
        int playersAmt; //amount of players
        int mapSize; //length of map size (square size map)
        int direction; //user direction for moving Up, Down, Left or Right
        int gameType; //game type; 1.single, 2.collaborative
        int numOfTeams; //number of teams
        int mapType; //map type; 1.safe, 2.hazardous
        boolean won = false;

        ///game type
        do{
            gameType = Helper.integerVal(scanner, "Game Type:\n1. Single\n2. Collaborative\nEnter your choice: ","Please input a number");
            gameTypeValid = MenuValidator.assert1or2(gameType);
        }while(!gameTypeValid);

        //read number of players
        do {
            playersAmt = Helper.integerVal(scanner, "Enter amount of players [2-8]", "Please input a number");
            amtOfPlayerValid = MenuValidator.amtPlayersValidator(playersAmt);
        }while(!amtOfPlayerValid);

        //if collaborative mode, check amount of players
        if (gameType == 2) {
            do {
                numOfTeams = Helper.integerVal(scanner, "Enter amount of teams", "Please input a number");
                amtOfTeamsValid = MenuValidator.amtOfTeamsValid(playersAmt, numOfTeams);
            }while(!amtOfTeamsValid);
        }

        //map type (safe or hazardous)
        do{
            mapType = Helper.integerVal(scanner, "Map Type:\n1. Safe\n2. Hazardous\nEnter your choice: ","Please input a number");
            mapTypeValid = MenuValidator.assert1or2(mapType);
        }while(!mapTypeValid);

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

        for(int i = 1; !won; i++) {
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
                if( winners.size()!=0)
                {
                    for(int j=0; j < winners.size(); j++)
                    {
                        int index = Arrays.asList(players).indexOf(winners.get(j));
                        System.out.println("Player "+(index+1)+" is a winner!");
                        won = true;
                    }
                }

                i = 0;
            }

            //regenerate files
            generateHTMLfiles();
        }
    }

}
