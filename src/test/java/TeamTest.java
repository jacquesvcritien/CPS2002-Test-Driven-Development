import exceptions.MapNotSetException;
import game.Game;
import map.*;
import map.tile.BlueTile;
import map.tile.GreenTile;
import map.tile.Tile;
import map.tile.TreasureTile;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import team.Team;
import team.player.Direction;
import team.player.Player;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Random;



public class TeamTest {

    Team team;
    SafeMap map;
    Tile[][] tiles = {
            {new GreenTile() , new GreenTile(),  new GreenTile(),  new TreasureTile(), new BlueTile()},
            {new GreenTile() , new BlueTile(),  new GreenTile(),  new GreenTile(), new BlueTile()},
            {new GreenTile() , new GreenTile(),  new GreenTile(),  new GreenTile(), new GreenTile()},
            {new BlueTile() , new GreenTile(),  new GreenTile(),  new BlueTile(), new BlueTile()},
            {new GreenTile() , new BlueTile(),  new BlueTile(),  new BlueTile(), new BlueTile()}
    };

    @Before
    public void setup() throws MapNotSetException {
        map = SafeMap.getMap();
        int mapSize = tiles.length;
        map.setSize(mapSize, new Random());
        map.setMapTiles(tiles);
        Game.setMap(map);
        Random randomMocked = Mockito.mock(Random.class);
        Mockito.when(randomMocked.nextInt(mapSize)).thenReturn(0,2);
        team = new Team(randomMocked, 1);

    }

    @After
    public void teardown()
    {
        team = null;
    }

    /**
     * Test for notifyAllPlayers
     */
    @Test
    public void testNotifyAllPlayers() throws MapNotSetException, IOException, URISyntaxException {

        Player[] players = new Player[3];
        players[0] =  Mockito.mock(Player.class);
        players[1] =  Mockito.mock(Player.class);
        players[2] =  Mockito.mock(Player.class);

        for(Player player: players)
        {
            Mockito.when(player.update()).thenReturn(true);
            team.attach(player);
        }

        team.notifyAllPlayers();

        Mockito.verify(players[0], Mockito.times(1)).update();
        Mockito.verify(players[1], Mockito.times(1)).update();
        Mockito.verify(players[2], Mockito.times(1)).update();

    }

    /**
     * Test for notifyAllPlayers by checking the actual getting of state
     */
    @Test
    public void testNotifyAllPlayersActualAction() throws MapNotSetException, IOException, URISyntaxException {

        int amOfPlayers = 3;
        Player[] players = new Player[amOfPlayers];

        for(int i=0; i < amOfPlayers; i++)
        {
            players[i] = new Player(i+1);
            Game.setPlayerToTeam(team, players[i]);
        }

        //move for team
        team.setState(Direction.UP);

        //assert all have UP as a move
        for (Player player: players)
            Assert.assertTrue("Asserting player has an UP move", player.getMoves().contains(Direction.UP));

    }

    /**
     * Test for notifyAllPlayers when it returns false due to bad move
     */
    @Test
    public void testNotifyAllPlayersBadMove(){
        int amOfPlayers = 3;
        Player[] players = new Player[amOfPlayers];


        Direction direction = Direction.UP;

        //create 3 players and set then to the team
        for(int i=0; i < amOfPlayers; i++)
        {

            players[i] =  Mockito.mock(Player.class);
            //return false when moving
            Mockito.when(players[i].move(direction)).thenReturn(false);
            Game.setPlayerToTeam(team, players[i]);
        }

        //move for team
        team.setState(Direction.UP);

        //assert all have UP as a move
        for (Player player: players)
            Assert.assertTrue("Asserting player has no UP move", !player.getMoves().contains(Direction.UP));
    }

    /**
     * Test for get players by size
     */
    @Test
    public void testGetPlayers(){

        int amOfPlayers = 3;

        for(int i=0; i < amOfPlayers; i++)
        {
            Player[] players = new Player[amOfPlayers];

            //create 3 players and set then to the team
            players[i] =  Mockito.mock(Player.class);
            Game.setPlayerToTeam(team, players[i]);
        }

        Assert.assertEquals("Asserting number of players in the team", 3, team.getPlayers().size());

    }

    /**
     * Test for next player turn when all players have played - i.e. test to make sure after last player, first player plays again
     */
    @Test
    public void testNextPlayerTurn(){
        int amOfPlayers = 3;
        Player[] players = new Player[amOfPlayers];

        Direction direction = Direction.UP;

        //create 3 players and set them to the team
        for(int i=0; i < amOfPlayers; i++)
        {

            players[i] =  Mockito.mock(Player.class);
            //mock ids
            Mockito.when(players[i].getId()).thenReturn(i+1);
            //return false when moving
            Mockito.when(players[i].update()).thenReturn(true);
            Game.setPlayerToTeam(team, players[i]);
        }

        //move for team for every player in team
        for(int i=0; i < amOfPlayers; i++) {
            team.setState(direction);
        }

        //assert that next player is player 1
        Assert.assertEquals("Asserting next player is player 1",1, team.getNextPlayerTurn());
    }



    /**
     * Test for get index
     */
    @Test
    public void testGetIndex(){
        Assert.assertEquals("Asserting index", 1, team.getId());
    }

}