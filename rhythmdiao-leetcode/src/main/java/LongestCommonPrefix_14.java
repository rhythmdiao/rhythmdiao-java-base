/**
 * Write a function to find the longest common prefix string amongst an array of strings.
 */
public final class LongestCommonPrefix_14 {
    public static String longestCommonPrefix(String[] strs) {
        int length = strs.length;
        String prefix = "";
        if (length == 0) return prefix;
        if (length == 1) return strs[0];
        int prefixLongestLength = strs[0].length();

        for (int i = 1; i < length; i++) {
            prefixLongestLength = strs[i].length() > prefixLongestLength ? prefixLongestLength : strs[i].length();
        }

        for (int i = 1; i <= prefixLongestLength; i++) {
            prefix = strs[0].substring(0, i);
            for (String str : strs) {
                if (!str.startsWith(prefix)) {
                    return prefix.substring(0, prefix.length() - 1);
                }
            }
        }

        return prefix;
    }

    public static void main(String[] args) {
        System.out.print(LongestCommonPrefix_14.longestCommonPrefix(new String[]{"c", "c"}));
    }
}
