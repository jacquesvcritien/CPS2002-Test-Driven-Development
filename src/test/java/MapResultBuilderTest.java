
import exceptions.MapNotSetException;
import files.Helper;
import files.MapResultBuilder;
import files.Page;
import map.Map;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import player.Direction;
import player.Player;
import player.Position;

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
        Map map = Map.getMap();
        map.setSize(5, new Random());
        mapResultBuilder.buildMapView(new Player(new Random()));
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
        Map map = Map.getMap();
        map.setSize(5, new Random());
        Player player = new Player(new Random());
        player.setPosition(new Position(0, 0));

        player.move(Direction.UP);
        player.move(Direction.UP);
        player.move(Direction.RIGHT);
        player.move(Direction.LEFT);
        player.move(Direction.RIGHT);
        player.move(Direction.DOWN);

        mapResultBuilder.buildMoves(player);
        Page page = mapResultBuilder.getPage();
        Assert.assertEquals("Asserting number of right moves", 2, Helper.getOccurences(page.getHTML(), "p>RIGHT</p"));
        Assert.assertEquals("Asserting number of left moves", 1, Helper.getOccurences(page.getHTML(), "p>LEFT</p"));
    }



}