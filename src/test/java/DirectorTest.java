
import exceptions.MapNotSetException;
import files.Builder;
import files.Director;
import game.Game;
import map.Map;
import map.MapFactory;
import map.MapType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.verification.VerificationMode;
import team.player.Player;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Random;

import static org.mockito.ArgumentMatchers.*;


public class DirectorTest {



    Player[] players = new Player[4];
    Map map;

    Builder builder = Mockito.mock(Builder.class);
    @InjectMocks
    Director director;
    int numOfPlayers;
    @Before
    public void setup() throws MapNotSetException {
        MockitoAnnotations.initMocks(this);
        numOfPlayers= 4;
        map = MapFactory.getMap(MapType.SAFE);
        map.setSize(5, new Random());
        Game.setMap(map);

        for(int i=0; i < numOfPlayers; i++)
            players[i] = new Player(new Random(), i+1);

        director = new Director(builder);
    }

    @After
    public void teardown()
    {
        director = null;
    }

    /**
     * Test for construct
     */
    @Test
    public void testConstruct() throws MapNotSetException, IOException, URISyntaxException {
        Mockito.doNothing().when(builder).init();
        Mockito.doNothing().when(builder).buildTitle(any(Player.class));
        Mockito.doNothing().when(builder).buildMapView(any(Player.class));
        Mockito.doNothing().when(builder).buildMoves(any(Player.class));

        int amtOfPlayers = players.length;
        int wantedNumberOfInvocations = 1 * amtOfPlayers;
        VerificationMode mode = Mockito.times(wantedNumberOfInvocations);

        for(int i=0; i < amtOfPlayers; i++)
        {
            director.construct(players[i]);

        }

        Mockito.verify(builder, mode).init();
        Mockito.verify(builder, mode).buildTitle(any(Player.class));
        Mockito.verify(builder, mode).buildMapView(any(Player.class));
        Mockito.verify(builder, mode).buildMoves(any(Player.class));

    }



}