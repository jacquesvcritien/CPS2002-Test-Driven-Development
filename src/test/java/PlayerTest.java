import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import player.Direction;
import player.Player;
import player.Position;

import static org.junit.Assert.*;

public class PlayerTest {

    Player player;

    @Before
    public void setup() {
        player = new Player();
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
     * Test for move
     */
    @Test
    public void testMove(){
        Position position = new Position(0, 0);
        player.setPosition(position);

        //move up
        player.move(Direction.UP);
        //checking x coordinate after move up
        assertEquals("Asserting y position after move up", 1, player.getPosition().getyCoordinate());
        //move down
        player.move(Direction.DOWN);
        //checking x coordinate after move down
        assertEquals("Asserting y position after move down", 0, player.getPosition().getyCoordinate());
        //move right
        player.move(Direction.RIGHT);
        //checking x coordinate after move right
        assertEquals("Asserting x position after move right", 1, player.getPosition().getxCoordinate());
        //move left
        player.move(Direction.LEFT);
        //checking x coordinate after move left
        assertEquals("Asserting y position after move left", 0, player.getPosition().getxCoordinate());

    }

}