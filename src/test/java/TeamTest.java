import exceptions.MapNotSetException;
import files.Director;
import game.Game;
import map.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.verification.VerificationMode;
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
    



}