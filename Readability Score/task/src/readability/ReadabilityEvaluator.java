package readability;

import java.text.DecimalFormat;

public class ReadabilityEvaluator {
    private DecimalFormat df = new DecimalFormat("0.00");

    public int countWordsIn(String text) {
        int count = 0;
        String[] sentenceUnits = text.split(" ");

        String numberPattern = "(\\d{1,3},)*\\d{1,3}";
        String wordPattern = "\\(?\\w+[!.?,]?\\)?[!.?,\n]?";
        String regex = "(" + numberPattern + "|" + wordPattern + ")";

        for (String word : sentenceUnits) {
            if(word.matches(regex)) {
                count++;
            }
        }
        return count;
    }

    public int countSentencesIn(String text) {
        String[] sentences = text.split("[!.?\n]");
        return sentences.length;
    }

    public int countCharactersIn(String text) {
        String[] chars = text.split("");
        int count = 0;
        for (String charString : chars) {
            if (charString.matches("(\\S)")) {
                count++;
            }
        }
        return count;
    }

    public String calculateScore(String text) {
        int characters = countCharactersIn(text);
        int words = countWordsIn(text);
        int sentences = countSentencesIn(text);

        double score = (4.71 *  characters / words ) + (0.5 *  words / sentences ) - 21.43;
        score = Math.floor(score * 100) / 100;
        return  df.format(score);
    }

    public String evaluate(String text) {
        int score = (int) Math.ceil(
                Double.parseDouble(
                        calculateScore(text)));
        String age = "";
        switch (score) {
            case 1:
                age = "5-6";
                break;
            case 2:
                age = "6-7";
                break;
            case 3:
                age = "7-9";
                break;
            case 4:
                age = "9-10";
                break;
            case 5:
                age = "10-11";
                break;
            case 6:
                age = "11-12";
                break;
            case 7:
                age = "12-13";
                break;
            case 8:
                age = "13-14";
                break;
            case 9:
                age = "14-15";
                break;
            case 10:
                age = "15-16";
                break;
            case 11:
                age = "16-17";
                break;
            case 12:
                age = "17-18";
                break;
            case 13:
                age = "18-24";
                break;
            case 14:
                age = "24+";
        }

        return String.format("This text should be understood by %s year olds.", age);
    }
}
