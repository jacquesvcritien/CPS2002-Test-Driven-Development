import map.Map;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import player.Direction;
import player.Player;
import player.Position;

import static org.junit.Assert.*;

public class PlayerTest {

    Player player;

    Map map = Map.getMap();
    @Before
    public void setup() {
        player = new Player();
        map.setSize(5);
    }

    @After
    public void teardown() {
        player = null;
    }

    /**
     * Test for getter and setter
     */
    @Test
    public void testPositionGetterAndSetter() {
        Position position = new Position(1, 0);
        player.setPosition(position);

        //getting player actual coordinates
        Position playerPos = player.getPosition();

        //checking x coordinate from get
        assertEquals("Asserting x position coordinate", 1, playerPos.getxCoordinate());
        //checking y coordinate from get
        assertEquals("Asserting  y coordinate", 0, playerPos.getyCoordinate());
    }

    /**
     * Test for getter and setter for start Position
     */
    @Test
    public void testStartPositionGetterAndSetter() {
        Position position = new Position(1, 0);
        player.setStart(position);

        //getting player actual coordinates
        Position playerPos = player.getStart();

        //checking x coordinate from get
        assertEquals("Asserting x position coordinate", 1, playerPos.getxCoordinate());
        //checking y coordinate from get
        assertEquals("Asserting  y coordinate", 0, playerPos.getyCoordinate());
    }

    /**
     * Test for move up
     */
    @Test
    public void testMoveUp(){

        Position position = new Position(0, 0);
        player.setPosition(position);

        //move up
        player.move(Direction.UP);
        //checking y coordinate after move up
        assertEquals("Asserting y position after move up", 1, player.getPosition().getyCoordinate());

    }

    /**
     * Test for move down
     */
    @Test
    public void testMoveDown(){
        Position position = new Position(0, 1);
        player.setPosition(position);

        //move down
        player.move(Direction.DOWN);
        //checking y coordinate after move down
        assertEquals("Asserting y position after move down", 0, player.getPosition().getyCoordinate());
    }

    /**
     * Test for move right
     */
    @Test
    public void testMoveRight(){
        Position position = new Position(0, 0);
        player.setPosition(position);

        //move right
        player.move(Direction.RIGHT);
        //checking x coordinate after move right
        assertEquals("Asserting x position after move right", 1, player.getPosition().getxCoordinate());
    }

    /**
     * Test for move left
     */
    @Test
    public void testMoveLeft(){
        Position position = new Position(1, 0);
        player.setPosition(position);

        //move left
        player.move(Direction.LEFT);
        //checking x coordinate after move left
        assertEquals("Asserting y position after move left", 0, player.getPosition().getxCoordinate());
    }


    /**
     * Test for move up when cannot
     */
    @Test
    public void testMoveUpBad(){
        Position position = new Position(0, 5);
        player.setPosition(position);

        //checking if false is returned
        assertFalse("Asserting false on move up", player.move(Direction.UP));

    }

    /**
     * Test for move down when cannot
     */
    @Test
    public void testMoveDownBad(){
        Position position = new Position(0, 0);
        player.setPosition(position);

        //checking if false is returned
        assertFalse("Asserting false on move down", player.move(Direction.DOWN));
    }

    /**
     * Test for move right when cannot
     */
    @Test
    public void testMoveRightBad(){
        Position position = new Position(5, 6);
        player.setPosition(position);

        //checking if false is returned
        assertFalse("Asserting false on move right", player.move(Direction.RIGHT));

    }

    /**
     * Test for move left when cannot
     */
    @Test
    public void testMoveLeftBad(){
        Position position = new Position(0, 0);
        player.setPosition(position);

        //checking if false is returned
        assertFalse("Asserting false on move left", player.move(Direction.LEFT));

    }

}