package game;
import exceptions.MapNotSetException;
import files.Builder;
import files.Director;
import files.MapResultBuilder;
import map.Map;
import map.MapFactory;
import map.MapType;
import menu.Helper;
import menu.MenuValidator;
import team.Team;
import team.player.Direction;
import team.player.Observer;
import team.player.Player;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

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
    //array of teams
    private static Team[] teams;
    //list of winners players
    private static ArrayList<Player> winners = new ArrayList<>();
    //list of winners teams
    private static ArrayList<Team> winnersTeams = new ArrayList<>();
    //game mode
    private static GameMode gameMode;


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
            //if collaborative, create empty player
            //if solo, create player with index
            players[i] = (gameMode == GameMode.COLLABORATIVE) ? new Player(i+1) : new Player(random, (i+1));
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
     * Method to set a winning team
     * @param winner team to set
     */
    public static void setWinningTeam(Team winner) {
        //if not already added, add it
        if(!winnersTeams.contains(winner))
            Game.winnersTeams.add(winner);
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
            director.construct(players[i]);
            files.Helper.writeFile(files_name, files_name+".html", mapResultBuilder.getPage().getHTML());
        }
    }

    /**
     * Method to set player to team
     * @param team team to set
     * @param player player to set
     */
    public static void setPlayerToTeam(Team team, Player player)
    {
        //attach player to team
        team.attach(player);
        //setup player with team's starting position
        player.setup(team.getStart(), team);

    }

    /**
     * Method to init teams
     * @param numOfTeams number of teams
     */
    private static void initTeams(int numOfTeams) throws MapNotSetException {
        //init teams
        teams = new Team[numOfTeams];

        for(int i=0; i < numOfTeams; i++)
            teams[i] = new Team(random, (i+1));

        //get all players into an arraylist
        List<Player> playersCopy = new ArrayList(Arrays.asList(players));

        //loop for amount of players
        for(int i=0; i < players.length; i++)
        {
            //get team index for this player
            int teamIndex = i % numOfTeams;

            //get random player index
            int playerIndex = random.nextInt(playersCopy.size());
            //get that player
            Player playerToAdd =playersCopy.get(playerIndex);
            //add player to the team
            setPlayerToTeam(teams[teamIndex], playerToAdd);
            //remove player from that list
            playersCopy.remove(playerToAdd);
        }

        printTeams();
    }

    /**
     * Method to print teams
     */
    public static void printTeams()
    {

        System.out.println("\n-----------------------\n");
        //traverse all teams
        for(int i=0; i < teams.length; i++)
        {
            System.out.println("TEAM "+(i+1)+"\n");
            //get teams players
            ArrayList<Observer> players = (ArrayList<Observer>) teams[i].getObservers();
            for(Observer player : players)
                System.out.println("PLAYER "+ ((Player)player).getId());

            System.out.println("\n-----------------------\n");
        }
    }

    /**
     * Method to check if a team.player is a winner or not
     * @param player team.player to check
     * @return if a player is winner
     */
    public static boolean isAWinner(Player player)
    {
        return winners.contains(player);
    }

    /**
     * Get map
     * @return map
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
     * Method to get game mode form choice
     * @param choice choice of input
     * @return SOLO or COLLABORATIVE
     */
    public static GameMode getGameMode(int choice)
    {
        if(choice == 1)
            return GameMode.SOLO;
        else
            return GameMode.COLLABORATIVE;
    }

    /**
     * Method to get map type form choice
     * @param choice choice of input
     * @return SAFE or HAZARDOUS
     */
    public static MapType getMapType(int choice)
    {
        if(choice == 1)
            return MapType.SAFE;
        else
            return MapType.HAZARDOUS;
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

    /**
     * Method to print winners
     */
    public static void printWinners()
    {
        if(gameMode == GameMode.COLLABORATIVE)
        {
            System.out.println("\n=================================\n");
            for (Team winnersTeam : winnersTeams) {
                int index = Arrays.asList(teams).indexOf(winnersTeam);
                System.out.println("Team " + (index + 1) + " is a winner!");

                //get winning team's players
                ArrayList<Observer> winningPlayers = (ArrayList<Observer>) teams[index].getObservers();
                //print players
                for (Observer winningPlayer : winningPlayers) {
                    System.out.println("Player " + ((Player)winningPlayer).getId() + " is a winner!");
                }
                System.out.println("\n=================================\n");
            }
        }
        else
        {
            for (Player winner : winners) {
                int index = Arrays.asList(players).indexOf(winner);
                System.out.println("Player " + (index + 1) + " is a winner!");
            }
        }

    }

    /**
     * Method to play rounds
     * @param scanner scanner for input
     * @throws IOException thrown when there is problem when reading or writing to a file
     * @throws URISyntaxException thrown when there is a problem with reading from a file
     */
    public static void playRounds(Scanner scanner) throws IOException, URISyntaxException {
        boolean won = false;
        boolean directionValid;
        int direction; //user direction for moving Up, Down, Left or Right
        boolean moved;

        //go on until someone wins
        for(int i = 1; !won; i++) {
            Player player = null;
            Team team = null;

            //if game mode is collaborative
            if(gameMode == GameMode.COLLABORATIVE)
            {
                team = teams[i-1];
                System.out.println("\nTeam " + i + ": Player "+team.getNextPlayerTurn());
            }
            //if solo
            else
            {
                player = players[i-1];
                System.out.println("\nPlayer " + i);
            }

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

                //if collaborative, move team
                //if solo move player
                moved = (gameMode == GameMode.COLLABORATIVE) ? team.setState(actualDirection) : player.move(actualDirection);

            } while (!directionValid || !moved);


            //checker length
            //if collaborative get number of teams
            //if solo get number of players
            int checkerSize = (gameMode == GameMode.COLLABORATIVE) ? teams.length : players.length;

            if(i == checkerSize){
                //if there are winners
                if( winners.size()!=0)
                {
                    printWinners();
                    won = true;
                }

                i = 0;
            }

            //regenerate files
            generateHTMLfiles();
        }
    }

    public static void main(String args[]) throws MapNotSetException, IOException, URISyntaxException {
        Scanner scanner = new Scanner(System.in);
        boolean amtOfPlayerValid;
        boolean mapSizeValid;
        boolean gameTypeValid;
        boolean mapTypeValid;
        boolean amtOfTeamsValid;
        int playersAmt; //amount of players
        int mapSize; //length of map size (square size map)
        int gameType; //game type; 1.single, 2.collaborative
        int numOfTeams = 0; //number of teams
        int mapType; //map type; 1.safe, 2.hazardous

        ///game type
        do{
            //get game type
            gameType = Helper.integerVal(scanner, "Game Type:\n1. Single\n2. Collaborative\nEnter your choice: ","Please input a number");
            gameTypeValid = MenuValidator.assert1or2(gameType);
        }while(!gameTypeValid);

        //set game mode
        gameMode = getGameMode(gameType);

        //read number of players
        do {
            playersAmt = Helper.integerVal(scanner, "Enter amount of players [2-8]", "Please input a number");
            amtOfPlayerValid = MenuValidator.amtPlayersValidator(playersAmt, gameMode);
        }while(!amtOfPlayerValid);

        //if collaborative mode, check amount of players
        if (gameMode == GameMode.COLLABORATIVE) {
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

        //set map
        map = MapFactory.getMap(getMapType(mapType), random);

        //get map size
        do {
            mapSize = Helper.integerVal(scanner, "Enter length of map size ", "Please input a number");
            mapSizeValid = MenuValidator.mapSize(playersAmt, mapSize);
        }while(!mapSizeValid);

        //set map size
        map.setSize(mapSize, random);

        //init players
        setNumPlayers(playersAmt);

        //if collaborative, init teams
        if(gameMode == GameMode.COLLABORATIVE)
            initTeams(numOfTeams);

        //generate files
        generateHTMLfiles();

        playRounds(scanner);

    }

}
