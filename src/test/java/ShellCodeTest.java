import jdk.nashorn.tools.Shell;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ShellCodeTest {

    ShellCode shellCode;
    @Before
    public void setup()
    {
        shellCode = new ShellCode();
    }

    @After
    public void teardown()
    {
        shellCode = null;
    }

    @Test
    public void testGetHelloWorld()
    {
        String actual = shellCode.getHelloWorld();
        assertEquals("Asserting getting hello world string", "Hello World", actual);
    }

}
