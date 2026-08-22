package Java_Collections;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Word_Frequency {
    static void main() {
        String str = "java spring java sql spring java";
        displayFrequency(str);
    }

    public static void displayFrequency(String str) {
        List<String> words = Arrays.asList(str.split(" "));
        Map<String, Long> freq = words.stream().collect(Collectors.groupingBy(
                Function.identity(),
                Collectors.counting()
        ));

        System.out.println(freq);
    }
}
