/**
 * Given a string s consists of upper/lower-case alphabets and empty space characters ' ',
 * return the length of last word in the string.
 * If the last word does not exist, return 0.
 * Note: A word is defined as a character sequence consists of non-space characters only.
 * For example,
 * Given s = "Hello World",
 * return 5.
 */
public final class LengthofLastWord_58 {
    public static int lengthOfLastWord(String s) {
        if (s == null) return 0;
        int length = s.length();
        if (length == 0) return 0;
        String trimmed = s.trim();
        return trimmed.length() - 1 - trimmed.lastIndexOf(" ");
    }

    public static void main(String[] args) {
        System.out.println(LengthofLastWord_58.lengthOfLastWord(" Hello World "));
    }
}
