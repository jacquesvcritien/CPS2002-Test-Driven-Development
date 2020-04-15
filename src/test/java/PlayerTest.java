import com.sun.media.jfxmedia.events.PlayerEvent;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
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
}