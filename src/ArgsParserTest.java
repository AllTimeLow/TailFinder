import org.junit.Assert;

/**
 * Created by LaneBoy on 29.08.17.
 */
public class ArgsParserTest {
    String testValues[][];
    String results[];

    @org.junit.Before
    public void setUp() throws Exception {
        testValues = new String[][]
                {
                        "-n 3 -c 3 -o output.txt 1.txt 2.txt 3.txt".split(" "),
                        "-c 3 -o output.txt 1.txt 2.txt 3.txt".split(" "),
                        "-n 3 -o output.txt 1.txt 2.txt 3.txt".split(" "),
                        "-o output.txt 1.txt 2.txt 3.txt".split(" "),
                        "1.txt 2.txt 3.txt".split(" "),
                        "-o output.txt".split(" "),
                        "-c 3 1.txt 2.txt 3.txt".split(" "),
                        "-n 3 1.txt 2.txt 3.txt".split(" "),
                        "output.txt 1.txt 2.txt 3.txt".split(" "),
                        "-n 3".split(" "),
                };
        results = new String[]{
                "You set -n and -c flags at the same time!",
                "Writing 3 last chars from files: [1.txt, 2.txt, 3.txt] to file output.txt.",
                "Writing 3 last lines from files: [1.txt, 2.txt, 3.txt] to file output.txt.",
                "Writing 10 last lines from files: [1.txt, 2.txt, 3.txt] to file output.txt.",
                "Writing 10 last lines from files: [1.txt, 2.txt, 3.txt] to console (screen).",
                "Writing 10 last lines from console (keyboard) to file output.txt.",
                "Writing 3 last chars from files: [1.txt, 2.txt, 3.txt] to console (screen).",
                "Writing 3 last lines from files: [1.txt, 2.txt, 3.txt] to console (screen).",
                "Writing 10 last lines from files: [output.txt, 1.txt, 2.txt, 3.txt] to console (screen).",
                "Writing 3 last lines from console (keyboard) to console (screen).",
        };
    }

    @org.junit.Test
    public void ParamsTest() {
        for (int i = 0; i < testValues.length; i++) {
            try {
                ArgsParser ap = new ArgsParser(testValues[i]);
                Assert.assertEquals(ap.toString(), results[i]);

            } catch (Exception ex) {
                Assert.assertEquals(ex.getMessage(), results[i]);
            }
        }
    }
}