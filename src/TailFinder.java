import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by LaneBoy on 29.08.17.
 */
public class TailFinder {
    private ArgsParser argsParser; //хранит информацию о том, что надо делать (откуда читать, сколько и чего с конца отделять, куда писать)

    public TailFinder(ArgsParser argsParser) {
        this.argsParser = argsParser;
        readFiles();
    }

    private List<ArrayList<String>> filesText;

    private void readFiles() {
        filesText = new ArrayList<ArrayList<String>>();
        //чтение с консоли
        if (argsParser.consoleInput()) {

            ArrayList<String> text = new ArrayList<String>();

            Scanner scanner = new Scanner(System.in);
            String line;
            while (scanner.hasNextLine() && !(line = scanner.nextLine()).equals("")) {
                text.add(line);
            }
            filesText.add(text);
            return;
        }
        //чтение из входных файлов
        for (String filename: argsParser.getInputFiles()) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader("src/" + filename));
                ArrayList<String> text = new ArrayList<String>(); //all lines from current input file
                String line;
                while ((line = reader.readLine()) != null) {
                    text.add(line);
                }
                reader.close();
                filesText.add(text);//adding current text to all texts from all input files
            } catch (IOException ex) {
                System.out.println(ex);
            }
        }
    }

    public String getTail() {
        StringBuilder sb = new StringBuilder();
        int fileNum = 0;
        for (ArrayList<String> strs: filesText) {
            if (!argsParser.consoleInput()) {
                sb.append(argsParser.getInputFiles().get(fileNum++));
                sb.append(System.lineSeparator());
            }
            if (argsParser.getCountLines()) {
                int linesToWrite = Math.min(argsParser.getNum(), strs.size()); //требуемое значение строк не должно превышать фактическое
                for (int i = strs.size() - linesToWrite; i < strs.size(); i++) {
                    sb.append(strs.get(i));
                    sb.append(System.lineSeparator());
                }
            } else {
                int charsFound = 0;
                int strToStartWith = -1; //номер строки, с которой надо начинать читать требуемый хвост из argsParser.getNum() символов
                int posToStartWith = -1; //номер позиции в данной строке, с которой надо начинать читать этот хвост

                for (int i = strs.size() - 1; i >= 0; i--) {
                    charsFound += strs.get(i).length();
                    if (charsFound >= argsParser.getNum()) {
                        strToStartWith = i;
                        posToStartWith = charsFound - argsParser.getNum();

                        break;
                    }
                }
                if (strToStartWith == -1) {//сюда попадем, если длина хвоста больше, чем кол-во символов в тексте
                    strToStartWith = 0;
                    posToStartWith = 0;
                }


                sb.append(strs.get(strToStartWith).substring(posToStartWith));
                //sb.append(System.lineSeparator());
                for (int i = strToStartWith + 1; i < strs.size(); i++) {
                    sb.append(strs.get(i));
                    //sb.append(System.lineSeparator());
                }

                if (!argsParser.consoleInput())
                    sb.append(System.lineSeparator());

            }
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return getTail();
    }
}
