import java.util.HashMap;
import java.util.Map;

/**
 * Initially, there is a Robot at position (0, 0).
 * Given a sequence of its moves, judge if this robot makes a circle, which means it moves back to the original place.
 * The move sequence is represented by a string. And each move is represent by a character.
 * The valid robot moves are R (Right), L (Left), U (Up) and D (down).
 * The output should be true or false representing whether the robot makes a circle.
 * Example 1:
 * Input: "UD"
 * Output: true
 * Example 2:
 * Input: "LL"
 * Output: false
 */

public class JudgeRouteCircle_657 {
    private static boolean judgeCircle(String moves) {
        Map<Character, Integer> map = new HashMap<>(4);
        map.put('U', 1);
        map.put('D', 1);
        map.put('L', 1);
        map.put('R', 1);

        for (int i = 0; i < moves.length(); i++) {
            map.put(moves.charAt(i), map.get(moves.charAt(i)) + 1);
        }
        return map.get('U').equals(map.get('D')) && map.get('L').equals(map.get('R'));
    }

    public static void main(String[] args) {
        System.out.println(judgeCircle("UD"));
    }
}
