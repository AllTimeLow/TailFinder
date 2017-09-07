import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by LaneBoy on 03.09.17.
 */
public class TailWriter {
    private TailFinder tail;
    private boolean consoleOutput;
    private String outputFile;

    public TailWriter(ArgsParser argsParser) {
        tail = new TailFinder(argsParser);
        consoleOutput = argsParser.consoleOutput();
        outputFile = argsParser.getOutputFile();
    }

    public void writeTail() throws IOException {
        if (consoleOutput) {
            System.out.println(tail);
        }
        else {
            FileWriter writer = new FileWriter("src/" + outputFile);
            writer.write(tail.toString());
            writer.close();
        }
    }
}
