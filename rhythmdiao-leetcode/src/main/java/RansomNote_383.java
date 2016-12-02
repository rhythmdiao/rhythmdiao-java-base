import java.util.HashMap;
import java.util.Map;

/**
 * Given an arbitrary ransom note string and another string containing letters from all the magazines,
 * write a function that will return true if the ransom note can be constructed from the magazines ;
 * otherwise, it will return false.
 * Each letter in the magazine string can only be used once in your ransom note.
 * Note:
 * You may assume that both strings contain only lowercase letters.
 * canConstruct("a", "b") -> false
 * canConstruct("aa", "ab") -> false
 * canConstruct("aa", "aab") -> true
 */
public final class RansomNote_383 {
    private static boolean canConstruct(String ransomNote, String magazine) {
        if (ransomNote.length() > magazine.length()) return false;
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < magazine.length(); i++) {
            if (map.containsKey(magazine.charAt(i))) {
                map.put(magazine.charAt(i), map.get(magazine.charAt(i)) + 1);
            } else {
                map.put(magazine.charAt(i), 1);
            }
        }

        for (int i = 0; i < ransomNote.length(); i++) {
            if(map.containsKey(ransomNote.charAt(i)) && map.get(ransomNote.charAt(i)) > 0){
                map.put(ransomNote.charAt(i),map.get(ransomNote.charAt(i)) -1);
            }else{
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println(canConstruct("aa", "aab"));
    }
}
