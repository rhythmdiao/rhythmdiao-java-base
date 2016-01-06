import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given two strings s and t, determine if they are isomorphic.
 * Two strings are isomorphic if the characters in s can be replaced to get t.
 * All occurrences of a character must be replaced with another character while preserving the order of characters.
 * No two characters may map to the same character but a character may map to itself.
 * For example,
 * Given "egg", "add", return true.
 * Given "foo", "bar", return false.
 * Given "paper", "title", return true.
 * Note:
 * You may assume both s and t have the same length.
 */
public final class IsomorphicStrings_205 {
    public static boolean isIsomorphic(String s, String t) {
        if (s.length() == 1) return true;
        Map<Character, List<Integer>> sMap = new HashMap<Character, List<Integer>>();
        Map<Character, List<Integer>> tMap = new HashMap<Character, List<Integer>>();
        for (int i = 0; i < s.length(); i++) {
            if (sMap.containsKey(s.charAt(i))) {
                sMap.get(s.charAt(i)).add(i);
            } else {
                List<Integer> list = new ArrayList<Integer>();
                list.add(i);
                sMap.put(s.charAt(i), list);
            }

            if (tMap.containsKey(t.charAt(i))) {
                tMap.get(t.charAt(i)).add(i);
            } else {
                List<Integer> list = new ArrayList<Integer>();
                list.add(i);
                tMap.put(t.charAt(i), list);
            }
        }
        for (Map.Entry<Character, List<Integer>> entry : sMap.entrySet()) {
            if (!tMap.containsValue(entry.getValue())) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(IsomorphicStrings_205.isIsomorphic("paper", "title"));
    }
}
