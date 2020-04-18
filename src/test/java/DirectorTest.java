
import exceptions.MapNotSetException;
import files.Builder;
import files.Director;
import map.Map;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import player.Player;

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
        map = Map.getMap();
        map.setSize(5, new Random());
        for(int i=0; i < numOfPlayers; i++)
            players[i] = new Player(new Random());

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
    public void testConstruct() throws MapNotSetException {
        Mockito.doNothing().when(builder).buildTitle(anyInt());
        Mockito.doNothing().when(builder).buildMapView(any(Player.class));

        director.construct(players);

        Mockito.verify(builder, Mockito.times(numOfPlayers)).buildTitle(anyInt());
        Mockito.verify(builder, Mockito.times(numOfPlayers)).buildMapView(any(Player.class));

    }



}