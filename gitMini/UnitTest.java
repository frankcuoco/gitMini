package gitlet;

import ucb.junit.textui;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

/** The suite of all JUnit tests for the gitlet package.
 *  @author Frank Cuoco
 */
public class UnitTest {

    /** Run the JUnit tests in the loa package. Add xxxTest.class entries to
     *  the arguments of runClasses to run other JUnit tests. */
    public static void main(String[] ignored) {
        System.exit(textui.runClasses(UnitTest.class));
    }

    @Test
    public void initTest() {
        Main.main("init");
        assertTrue(new File(".gitlet").exists());
        assertTrue(new File(".gitlet/blobs").exists());
        assertTrue(new File(".gitlet/branches").exists());
        assertTrue(new File(".gitlet/branches/master").exists());
        assertTrue(new File(".gitlet/commits").exists());
        assertTrue(new File(".gitlet/stage").exists());
    }

    @Test
    public void branchTest1() {
        Main.main("init");
        Main.main("branch", "new");
        assertTrue(new File(".gitlet/branches/new").exists());
        Main.main("rm-branch", "new");
        assertFalse(new File(".gitlet/branches/new").exists());
    }

    @Test
    public void branchTest2() {
        Main.main("init");
        Main.main("branch", "new");
        assertTrue(new File(".gitlet/branches/new").exists());
        Main.main("rm-branch", "neew");
        assertTrue(new File(".gitlet/branches/new").exists());
    }


}


