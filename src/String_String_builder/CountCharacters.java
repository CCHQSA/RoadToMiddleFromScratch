package String_String_builder;

import java.util.HashMap;
import java.util.Map;

public class CountCharacters {
    static void main() {
        String text = "Some text yada yada ...!!?";
        System.out.println(countCharacters(text));


    }

    public static Map<Character, Integer> countCharacters(String text) {
        if (text == null) {
            return new HashMap<>();
        }
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        return map;
    }
}
