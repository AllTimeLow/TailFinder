import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.Buffer;
import java.nio.BufferOverflowException;

import static org.junit.Assert.*;

/**
 * Created by LaneBoy on 03.09.17.
 */
public class TailWriterTest {
    String[] testValues;
    String[] results;

    @Before
    public void setUp() throws Exception {
        testValues = "-c 3 -o output.txt 1.txt 2.txt 3.txt".split(" ");
        results = new String[]{
                        "1.txt",
                        "ый.",
                        "2.txt",
                        "ем.",
                        "3.txt",
                        "оры"};

    }
    @Test
    public void writeTail() throws Exception {
        ArgsParser argsParser = new ArgsParser(testValues);
        TailWriter writer = new TailWriter(argsParser);
        writer.writeTail();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("src/" + argsParser.getOutputFile()));
        } catch (Exception ex) {
            Assert.assertTrue(false);
        }
        String line;
        int i = 0;
        while ((line = br.readLine()) != null) {
            Assert.assertEquals(line, results[i++]);
        }
        br.close();
    }
}