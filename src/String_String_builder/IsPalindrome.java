package String_String_builder;

public class IsPalindrome {
    static void main() {
    String str1 = "abc";
    String str2 = "aba";

    System.out.println(isPalindrome(str1));
    System.out.println(isPalindrome(str2));
    }

    public static boolean isPalindrome(String str) {
        if (str == null) {
            return false;
        }
        str = str.toLowerCase();
        for (int i = 0; i < str.length()/2 ; i++) {
            if (str.charAt(i) != str.charAt(str.length()-i-1)) {
                return false;
            }
        }
        return true;
    }
}
