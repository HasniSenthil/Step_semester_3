package oop.assignment_problems;
import java.util.HashMap;
import java.util.Map;
public class StopWordFrequencyReport {
    static void printFilteredWordFrequency(String feedback) {
        String cleanedText = feedback.toLowerCase().replace(".", "").replace(",", "");
        String[] words = cleanedText.split("\\s+");
        String[] stopWords = {"the", "was", "and", "a", "is", "of", "in"};
        HashMap<String, Integer> frequency = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            boolean isStopWord = false;
            for (int j = 0; j < stopWords.length; j++) {
                if (words[i].equals(stopWords[j])) {
                    isStopWord = true;
                    break;
                }
            }
            if (!isStopWord) {
                frequency.put(words[i],frequency.getOrDefault(words[i], 0) + 1);
            }
        }
        String[] uniqueWords = frequency.keySet().toArray(new String[0]);
        for (int i = 0; i < uniqueWords.length - 1; i++) {
            for (int j = i + 1; j < uniqueWords.length; j++) {
                if (frequency.get(uniqueWords[j])> frequency.get(uniqueWords[i])) {
                    String temp = uniqueWords[i];
                    uniqueWords[i] = uniqueWords[j];
                    uniqueWords[j] = temp;
                }
            }
        }
        for (int i = 0; i < uniqueWords.length; i++) {
            System.out.println(uniqueWords[i] + ": " + frequency.get(uniqueWords[i]));
        }
    }
    public static void main(String[] args) {
        String feedback ="The mentor was great, the session was great and clear.";
        printFilteredWordFrequency(feedback);
    }
}