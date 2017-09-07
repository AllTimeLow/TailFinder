import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.io.*;
import java.util.Scanner;

import static org.junit.Assert.*;

/**
 * Created by LaneBoy on 03.09.17.
 */
public class TailFinderTest {
    String testValues[][];
    String results[][];

    @Before
    public void setUp() throws Exception {
        testValues = new String[][]{
                "-c 3".split(" "),
                "-n 3 -c 3 -o output.txt 1.txt 2.txt 3.txt".split(" "),
                "-c 3 -o output.txt 1.txt 2.txt 3.txt".split(" "),
                "-n 3 -o output.txt 1.txt 2.txt 3.txt".split(" "),
                "-o output.txt 1.txt 2.txt 3.txt".split(" "),
                };
        results = new String[][]{
                new String[]{"аму"},
                new String[]{"You set -n and -c flags at the same time!"},
                new String[]{
                        "1.txt",
                        "ый.",
                        "2.txt",
                        "ем.",
                        "3.txt",
                        "оры"},
                new String[]{
                        "1.txt",
                        "Ты песен Грузии печальной:",
                        "Напоминают мне оне",
                        "Другую жизнь и берег дальный.",
                        "2.txt",
                        "В душе моей угасла не совсем;",
                        "Но пусть она вас больше не тревожит;",
                        "Я не хочу печалить вас ничем.",
                        "3.txt",
                        "Еще ты дремлешь, друг прелестный –",
                        "Пора, красавица, проснись;",
                        "Открой сомкнуты негой взоры"
                },
                new String[]{
                        "1.txt",
                        "Не пой, красавица, при мне",
                        "Ты песен Грузии печальной:",
                        "Напоминают мне оне",
                        "Другую жизнь и берег дальный.",
                        "2.txt",
                        "Я вас любил: любовь еще, быть может ",
                        "В душе моей угасла не совсем;",
                        "Но пусть она вас больше не тревожит;",
                        "Я не хочу печалить вас ничем.",
                        "3.txt",
                        "Мороз и солнце; день чудесный!",
                        "Еще ты дремлешь, друг прелестный –",
                        "Пора, красавица, проснись;",
                        "Открой сомкнуты негой взоры"
                },
        };
    }

    @Test
    public void getTail() throws Exception {
        //Console input/console output test
        ArgsParser p = new ArgsParser(testValues[0]);
        System.setIn(new ByteArrayInputStream("Мама мыла раму".getBytes()));
        TailFinder tail = new TailFinder(p);
        Assert.assertEquals(results[0][0], tail.getTail());

        //File input/file ouptput tests
        for (int i = 1; i < testValues.length; i++) {
            try {
                p = new ArgsParser(testValues[i]);
                tail = new TailFinder(p);
                String[] tailLines = tail.getTail().split(System.lineSeparator());
                Assert.assertEquals(tailLines.length, results[i].length);
                for (int j = 0; j < tailLines.length; j++) {
                    Assert.assertEquals(results[i][j], tailLines[j]);
                }
            } catch (Exception ex) {
                Assert.assertEquals(results[i][0], ex.getMessage());
            }
        }
    }

}