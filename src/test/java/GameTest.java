import exceptions.MapNotSetException;
import files.Director;
import files.Helper;
import game.Game;
import game.GameMode;
import map.Map;
import map.MapFactory;
import map.MapType;
import menu.MenuValidator;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.verification.VerificationMode;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import team.player.Player;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Helper.class, MenuValidator.class, menu.Helper.class, Game.class})
public class GameTest {
    Director director;
    Random randomMocked;
    Game game;
    Map map;

    @Before
    public void setup() throws MapNotSetException {
        game = new Game();
        map = MapFactory.getMap(MapType.SAFE);
        map.setSize(5, new Random());
        Game.setMap(map);
        director = Mockito.mock(Director.class);
    }

    @After
    public void teardown() {
        game = null;
    }


    /**
     * Test set winner
     */
    @Test
    public void testSetWinner() throws MapNotSetException {
        Player player = new Player(new Random(), 1);
        Game.setWinner(player);
        assertTrue("Asserting team.player is in winners", Game.isAWinner(player));
    }

    /**
     * Test for set players
     */
    @Test
    public void testSetPlayersNum() throws MapNotSetException {
        int noplayers = 2;
        Game.setNumPlayers(noplayers);
        Assert.assertEquals("Asserting number of players", noplayers, Game.getPlayers().length);
    }

    /**
     * Test for generateHtmlFiles
     */
    @Test
    public void testGenerateHTMLfiles() throws MapNotSetException, IOException, URISyntaxException {
        PowerMockito.mockStatic(Helper.class);
        Mockito.doNothing().when(director).construct(any(Player.class));

        Game.setDirector(director);
        int noplayers = 2;
        Game.setNumPlayers(noplayers);
        int amtOfPlayers = Game.getPlayers().length;
        int wantedNumberOfInvocations = 5 * amtOfPlayers;
        VerificationMode mode = Mockito.times(wantedNumberOfInvocations);
        Game.generateHTMLfiles();

        PowerMockito.verifyStatic(Helper.class, Mockito.times(amtOfPlayers*1));
        files.Helper.createDirectory(anyString());
        PowerMockito.verifyStatic(Helper.class, Mockito.times(amtOfPlayers*2));
        files.Helper.copyDirectory(anyString(), anyString());
        PowerMockito.verifyStatic(Helper.class, Mockito.times(amtOfPlayers*1));
        files.Helper.copyFile(anyString(), anyString());
        PowerMockito.verifyStatic(Helper.class, Mockito.times(amtOfPlayers*1));
        files.Helper.writeFile(anyString(), anyString(), any());
    }

    /**
     * Test for main - SOLO
     *
     * START AT POS (1,3) PLAYER 1
     * PLAYER 1 WINS
     * TREASURE AT (0,0)
     */
    @Test
    public void testMainSolo() throws Exception {
        Game.reset();
        PowerMockito.mockStatic(MenuValidator.class);
        PowerMockito.mockStatic(menu.Helper.class);
        PowerMockito.mockStatic(Game.class);
        randomMocked = Mockito.mock(Random.class);
        int mapSize = 5;
        PowerMockito.spy(Game.class);
        Mockito.when(randomMocked.nextInt(mapSize)).thenReturn(0,0,0,0,0,4,4,1,1,1,2,2,3,3,2, 2, 1, 3, 1, 2, 3, 1, 2, 3, 1, 0, 0, 1,2,1, 0, 3);

        Game.setRandom(randomMocked);
        PowerMockito.when(menu.Helper.integerVal(any(), anyString(), anyString())).thenReturn(0,1, 1, 2, 0,1, 1, mapSize,1,2,3,4,1,2,1,3,1,3);
        PowerMockito.when(MenuValidator.directionCheck(anyInt())).thenReturn(true, true, true, true, true, false, true);
        PowerMockito.when(MenuValidator.amtPlayersValidator(anyInt(), any(GameMode.class))).thenReturn(false,true);
        PowerMockito.when(MenuValidator.assert1or2(anyInt())).thenReturn(false,true, false, true);
        PowerMockito.when(MenuValidator.amtOfTeamsValid(anyInt(), anyInt())).thenReturn(false,true);
        PowerMockito.when(MenuValidator.mapSize(anyInt(), anyInt())).thenReturn(false, true);
        Mockito.doNothing().when(director).construct(any(Player.class));
        PowerMockito.doCallRealMethod().when(Game.class, "generateHTMLfiles");

        Game.main(new String[0]);

        PowerMockito.verifyStatic(menu.Helper.class, Mockito.times(12));
        menu.Helper.integerVal(any(), anyString(), anyString());
        PowerMockito.verifyStatic(MenuValidator.class, Mockito.times(2));
        MenuValidator.mapSize(anyInt(), anyInt());
        PowerMockito.verifyStatic(MenuValidator.class, Mockito.times(2));
        MenuValidator.amtPlayersValidator(anyInt(), any(GameMode.class));
        PowerMockito.verifyStatic(MenuValidator.class, Mockito.times(4));
        MenuValidator.assert1or2(anyInt());
        PowerMockito.verifyStatic(Game.class, Mockito.times(5));
        Game.generateHTMLfiles();

    }

    /**
     * Test for main - COLLABORATIVE
     *
     * START AT POS (1,3) TEAM 1
     * TEAM 1 WINS
     * TREASURE AT (0,0)
     */
    @Test
    public void testMainCollaborative() throws Exception {
        Game.reset();
        PowerMockito.mockStatic(MenuValidator.class);
        PowerMockito.mockStatic(menu.Helper.class);
        PowerMockito.mockStatic(Game.class);
        randomMocked = Mockito.mock(Random.class);
        int mapSize = 5;
        PowerMockito.spy(Game.class);
        Mockito.when(randomMocked.nextInt(mapSize)).thenReturn(0,0,0,0,0,4,4,1,1,1,2,2,3,3,2, 2, 1, 3, 1, 2, 3, 1, 2, 3, 1, 0, 0, 1,2,1, 0, 3);

        Game.setRandom(randomMocked);
        PowerMockito.when(menu.Helper.integerVal(any(), anyString(), anyString())).thenReturn(0,2, 1, 3, 0,2, 0,1, 1, mapSize,1,2,3,4,1,2,1,3,1,3);
        PowerMockito.when(MenuValidator.directionCheck(anyInt())).thenReturn(true, true, true, true, true, false, true);
        PowerMockito.when(MenuValidator.amtPlayersValidator(anyInt(), any(GameMode.class))).thenReturn(false,true);
        PowerMockito.when(MenuValidator.assert1or2(anyInt())).thenReturn(false,true, false, true);
        PowerMockito.when(MenuValidator.amtOfTeamsValid(anyInt(), anyInt())).thenReturn(false,true);
        PowerMockito.when(MenuValidator.mapSize(anyInt(), anyInt())).thenReturn(false, true);
        Mockito.doNothing().when(director).construct(any(Player.class));
        PowerMockito.doCallRealMethod().when(Game.class, "generateHTMLfiles");

        Game.main(new String[0]);

        PowerMockito.verifyStatic(menu.Helper.class, Mockito.times(14));
        menu.Helper.integerVal(any(), anyString(), anyString());
        PowerMockito.verifyStatic(MenuValidator.class, Mockito.times(2));
        MenuValidator.mapSize(anyInt(), anyInt());
        PowerMockito.verifyStatic(MenuValidator.class, Mockito.times(2));
        MenuValidator.amtPlayersValidator(anyInt(), any(GameMode.class));
        PowerMockito.verifyStatic(MenuValidator.class, Mockito.times(4));
        MenuValidator.assert1or2(anyInt());
        PowerMockito.verifyStatic(Game.class, Mockito.times(5));
        Game.generateHTMLfiles();

    }



}