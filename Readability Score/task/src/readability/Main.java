package readability;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
    private static String[] sentences;

    public static void main(String[] args) {
        String text = readFile(args[0]);
        ReadabilityEvaluator readabilityEvaluator = new ReadabilityEvaluator();

        int wordCount = readabilityEvaluator.countWordsIn(text);
        int sentencesCount = readabilityEvaluator.countSentencesIn(text);
        int charachtersCount = readabilityEvaluator.countCharactersIn(text);
        String score = readabilityEvaluator.calculateScore(text);
        String evaluation = readabilityEvaluator.evaluate(text);

        //System.out.println(text);

        System.out.printf(
                "Words: %d\n" +
                "Sentences: %d\n" +
                "Characters: %d\n" +
                "The score is: %s\n" +
                "%s",
                wordCount,
                sentencesCount,
                charachtersCount,
                score,
                evaluation
        );
    }


    private static String readFile(String fileName) {
        Path path = Paths.get(fileName);
        String text = null;
        try {
            text = new String(Files.readAllBytes(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return text;
    }

}
