import java.io.FileWriter;
import java.util.Arrays;

/**
 * Created by LaneBoy on 28.08.17.
 */

public class Tail {
    public static void main(String[] args) {
        //System.out.println("Command args: " + Arrays.toString(args));
        try {
            ArgsParser argsParser = new ArgsParser(args);
            System.out.println(argsParser);

            TailWriter writer = new TailWriter(argsParser);
            writer.writeTail();

            System.out.println("Done.");

        } catch(Exception e) {
            System.out.println(e);//"Exception: " + e.getMessage()
            System.out.println("Usage: tail [-c|-n lines_num|char_num] [-o output_file] input_files");
        }

    }
}
