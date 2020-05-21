import exceptions.MapNotSetException;
import files.Director;
import game.Game;
import map.*;
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
        map = new SafeMap();
        int mapSize = tiles.length;
        map.setSize(mapSize, new Random());
        map.setMapTiles(tiles);
        Game.setMap(map);
        Random randomMocked = Mockito.mock(Random.class);
        Mockito.when(randomMocked.nextInt(mapSize)).thenReturn(0,2);
        team = new Team(randomMocked);

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
            Mockito.doNothing().when(player).update();
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
            players[i] = new Player();
            Game.setPlayerToTeam(team, players[i]);
        }

        //move for team
        team.setState(Direction.UP);

        //assert all have UP as a move
        for (Player player: players)
            Assert.assertTrue("Asserting player has an UP move", player.getMoves().contains(Direction.UP));

    }



}