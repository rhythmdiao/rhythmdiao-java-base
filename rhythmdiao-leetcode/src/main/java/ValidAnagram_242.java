import java.util.Arrays;

/**
 * Given two strings s and t, write a function to determine if t is an anagram of s.
 * For example,
 * s = "anagram", t = "nagaram", return true.
 * s = "rat", t = "car", return false.
 */
public final class ValidAnagram_242 {
    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;
        byte[] b1 = s.getBytes();
        byte[] b2 = t.getBytes();
        Arrays.sort(b1);
        Arrays.sort(b2);
        int length = b1.length;
        for (int i = 0; i < length; i++) {
            if (b1[i] != b2[i]) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(ValidAnagram_242.isAnagram("anagram", "nagaram"));
    }
}
