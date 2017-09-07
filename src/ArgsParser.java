import java.util.ArrayList;
import java.util.List;

/**
 * Created by LaneBoy on 28.08.17.
 */
public class ArgsParser {
    private int num;

    public boolean consoleOutput() {
        return (outputFile == null);
    }

    public boolean consoleInput() {
        return (inputFiles == null);
    }

    public int getNum() {
        return num;
    }

    public boolean getCountLines() {
        return countLines;
    }

    public String getOutputFile() {
        return outputFile;
    }

    public List<String> getInputFiles() {
        return inputFiles;
    }

    private boolean countLines;
    private String outputFile;
    private List<String> inputFiles;

    ArgsParser(String[] args) throws IllegalArgumentException {
        countLines = true;
        num = 10;
        outputFile = null;
        inputFiles = null;

        if (args == null || args.length < 1) {
            return;
        }

        //we get here if there are some args
        boolean cOrNIsSet = false;
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-c")) {
                //попыткa одновременно устоновить флаги c и n
                if (cOrNIsSet) {
                    throw new IllegalArgumentException("You set -n and -c flags at the same time!");
                }
                cOrNIsSet = true;
                countLines = false;//отсчитываем символы
                num = Integer.parseInt(args[++i]);
            } else if (args[i].equals("-n")) {
                //попыткa одновременно устоновить флаги c и n
                if (cOrNIsSet) {
                    throw new IllegalArgumentException("You set -n and -c flags at the same time!");
                }
                cOrNIsSet = true;
                countLines = true;//отсчитываем строки (а не символы)
                num = Integer.parseInt(args[++i].toString()); //запоминеам кол-во строк
            } else if (args[i].equals("-o")) {
                outputFile = args[++i];
            } else if (args[i].charAt(0) == '-') {
                throw new IllegalArgumentException("Unknown option name: " + args[i] + "!");
            } else {
                if (inputFiles == null) {
                    inputFiles = new ArrayList<String>();
                }
                while (i < args.length) {
                    inputFiles.add(args[i++]);
                }
            }
        }

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Writing " + num + " last ");
        if (countLines) {
            sb.append("lines ");
        } else {
            sb.append("chars ");
        }
        sb.append("from ");
        if (inputFiles == null) {
            sb.append("console (keyboard) ");
        } else {
            sb.append("files: " + inputFiles.toString() + " ");
        }
        sb.append("to ");
        if (outputFile == null) {
            sb.append("console (screen).");
        } else {
            sb.append("file " + outputFile + ".");
        }
        return sb.toString();
    }
}
