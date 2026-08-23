package String_String_builder;

import java.util.Arrays;

public class CountWords {
    static void main() {
        String str = "Some very            long text about nothing";
        System.out.println(Arrays.toString(countWords(str)));
    }

    public static String[] countWords(String text){
        if (text == null || text.isEmpty()){
            return new String[0];
        }

        return text.trim().split("\\s+");
    }
}
