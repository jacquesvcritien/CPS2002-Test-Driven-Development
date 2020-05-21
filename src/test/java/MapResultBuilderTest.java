
import exceptions.MapNotSetException;
import files.Helper;
import files.MapResultBuilder;
import files.Page;
import game.Game;
import map.Map;
import map.MapFactory;
import map.MapType;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import team.player.Direction;
import team.player.Player;
import team.player.Position;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Random;


public class MapResultBuilderTest {

    MapResultBuilder mapResultBuilder;

    @Before
    public void setup(){
        mapResultBuilder = new MapResultBuilder();
    }

    @After
    public void teardown()
    {
        mapResultBuilder = null;
    }

    /**
     * Test for get page when not initialised
     */
    @Test
    public void testGetPageNoInit(){
        Assert.assertNotNull("Asserting page not null", mapResultBuilder.getPage());
    }

    /**
     * Test for get page
     */
    @Test
    public void testGetPage() throws IOException, URISyntaxException {
        mapResultBuilder.init();
        Assert.assertNotNull("Asserting page not null", mapResultBuilder.getPage());
    }

    /**
     * Test for build title
     */
    @Test
    public void testBuildTitle() throws IOException, URISyntaxException {
        mapResultBuilder.init();
        mapResultBuilder.buildTitle(1);
        Page page = mapResultBuilder.getPage();
        Assert.assertTrue("Asserting page html contains title as it should", page.getHTML().contains("<div class=\"table-title\">Player 1</div>"));
    }

    /**
     * Test for build map by checking that it has as much cell end tags as should be
     */
    @Test
    public void testBuildMap() throws IOException, URISyntaxException, MapNotSetException {
        mapResultBuilder.init();
        Map map = MapFactory.getMap(MapType.SAFE);
        map.setSize(5, new Random());
        Game.setMap(map);

        mapResultBuilder.buildMapView(new Player(new Random(), 1));
        Page page = mapResultBuilder.getPage();
        String html = mapResultBuilder.getPage().getHTML();
        Assert.assertEquals("Asserting number of </td> (table data cells ending tags)", 25, Helper.getOccurences(html, "/td"));
    }

    /**
     * Test for build moves
     */
    @Test
    public void testBuildMoves() throws IOException, URISyntaxException, MapNotSetException {
        mapResultBuilder.init();
        Map map = MapFactory.getMap(MapType.SAFE);
        int mapSize =5;
        Random randomMocked = Mockito.mock(Random.class);
        Mockito.when(randomMocked.nextInt(mapSize)).thenReturn(0,0,0,0,0,1,1,1,2,2,3,3,2, 2, 1, 3, 1, 2, 3, 1, 2, 3, 1, 0, 0, 1,2,1, 0, 3);
        map.setSize(mapSize, randomMocked);
        Game.setMap(map);

        Player player = new Player(randomMocked, 1);
        player.setPosition(new Position(2, 4));
        player.move(Direction.LEFT);
        player.move(Direction.LEFT);
        player.move(Direction.RIGHT);
        player.move(Direction.UP);
        player.move(Direction.RIGHT);
        player.move(Direction.DOWN);

        mapResultBuilder.buildMoves(player);
        Page page = mapResultBuilder.getPage();
        Assert.assertEquals("Asserting number of right moves", 2, Helper.getOccurences(page.getHTML(), "p>RIGHT</p"));
        Assert.assertEquals("Asserting number of left moves", 2, Helper.getOccurences(page.getHTML(), "p>LEFT</p"));
    }

    /**
     * Test for build winner
     */
    @Test
    public void testBuildWinner() throws IOException, URISyntaxException, MapNotSetException {
        mapResultBuilder.init();
        Map map = MapFactory.getMap(MapType.SAFE);
        int mapSize =5;
        Random randomMocked = Mockito.mock(Random.class);
        Mockito.when(randomMocked.nextInt(mapSize)).thenReturn(0,0,0,0,0,1,1,1,2,2,3,3,2, 2, 1, 3, 1, 2, 3, 1, 2, 3, 1, 0, 0, 1,2,1, 0, 3);
        map.setSize(mapSize, randomMocked);
        Game.setMap(map);
        Player player = new Player(randomMocked, 1);
        player.setPosition(new Position(0, 1));

        player.move(Direction.UP);
        mapResultBuilder.buildWinner(player);
        Page page = mapResultBuilder.getPage();
        Assert.assertTrue("Asserting Congratulations", page.getHTML().contains("Congratulations"));
    }

    /**
     * Test for build winner when nnot a winner
     */
    @Test
    public void testBuildWinnerNoWinner() throws IOException, URISyntaxException, MapNotSetException {
        mapResultBuilder.init();
        Map map = MapFactory.getMap(MapType.SAFE);
        int mapSize =5;
        Random randomMocked = Mockito.mock(Random.class);
        Mockito.when(randomMocked.nextInt(mapSize)).thenReturn(0,0,0,0,0,1,1,1,2,2,3,3,2, 2, 1, 3, 1, 2, 3, 1, 2, 3, 1, 0, 0, 1,2,1, 0, 3);
        map.setSize(mapSize, randomMocked);
        Game.setMap(map);
        Player player = new Player(randomMocked, 1);
        player.setPosition(new Position(0, 1));

        player.move(Direction.DOWN);
        mapResultBuilder.buildWinner(player);
        Page page = mapResultBuilder.getPage();
        Assert.assertFalse("Asserting no Congratulations", page.getHTML().contains("Congratulations"));
    }



}