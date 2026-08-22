package Java_Collections;

import java.util.*;

public class Unique_Words {
    static void main() {
        String str = "java is great and java is powerful";
        displayUniqueWords(str);
    }

    public static void displayUniqueWords(String str) {
        String[] words = str.split(" ");
        Set<String> uniqueWords = new LinkedHashSet<>(Arrays.asList(words));
        for (String word : uniqueWords) {
            System.out.println(word);
        }

        System.out.println("Unique words are: " + uniqueWords.size());

    }
}
