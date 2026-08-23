package String_String_builder;

public class Reverse {
    static void main() {
        String str = "someString";
        System.out.println(reverse(str));
    }

    public static String reverse(String str) {
        if (str == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = str.length() - 1; i >= 0; i--) {
            sb.append(str.charAt(i));
        }
        return sb.toString();
    }
}
