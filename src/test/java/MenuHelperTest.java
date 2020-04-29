import map.Map;
import menu.Helper;
import menu.MenuValidator;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Random;
import java.util.Scanner;

import static org.junit.Assert.*;


public class MenuHelperTest {

    InputStream sysInBackup = System.in;
    ByteArrayInputStream in;
    Scanner scanner;
    Helper helper;

    private final ByteArrayOutputStream output = new ByteArrayOutputStream();
    private final PrintStream original = System.out;
    @Before
    public void setup()
    {
        helper = new Helper();
        System.setOut(new PrintStream(output));
    }

    @After
    public void teardown()
    {
        System.setIn(in);
        sysInBackup = null;
        scanner = null;
        System.setOut(original);
    }

    /**
     * Test for reading integer value good on first time
     */
    @Test
    public void testIntegerValCorrectNum(){
        in = new ByteArrayInputStream("10\n".getBytes());
        System.setIn(in);
        scanner = new Scanner(System.in);
        String expected = "Please enter a number".replaceAll("\\n|\\r\\n", System.getProperty("line.separator"));
        int value = Helper.integerVal(scanner, "Please enter a number", "This is not a number, please enter a correct number");
        Assert.assertEquals("Asserting console output", expected, output.toString().trim());
        assertEquals(10,value);
    }

    /**
     * Test for reading integer value when first input is not a number
     */
    @Test
    public void testIntegerValNotNum(){
        in = new ByteArrayInputStream("aaa\n10\n".getBytes());
        System.setIn(in);
        scanner = new Scanner(System.in);
        int value = Helper.integerVal(scanner, "Please enter a number", "This is not a number, please enter a correct number");
        String expected = ("Please enter a number\n" +
                "This is not a number, please enter a correct number\n" +
                "Please enter a number").replaceAll("\\n|\\r\\n", System.getProperty("line.separator"));
        Assert.assertEquals("Asserting console output",expected, output.toString().trim());
        assertEquals(10,value);
    }

}
