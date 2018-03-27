
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author rhythmdiao
 */
public class Quiz {
    private final static char[] ANSWERS = {'A', 'B', 'C', 'D'};

    /**
     * 这道题的答案是：A.A,B.B,C.C,D.D
     *
     * @param answers 答案数组
     * @return 是否正确
     */
    private static boolean checkQ1(char[] answers) {
        return true;
    }

    /**
     * 第5题的答案是：A.C,B.D,C.A,D.B
     *
     * @param answers 答案数组
     * @return 是否正确
     */
    private static boolean checkQ2(char[] answers) {
        return ('C' == answers[4] && 'A' == answers[1]) ||
                ('D' == answers[4] && 'B' == answers[1]) ||
                ('A' == answers[4] && 'C' == answers[1]) ||
                ('B' == answers[4] && 'D' == answers[1]);
    }

    /**
     * 以下选项中哪一题的答案与其他三项不同：A.第3题,B.第6题,C.第2题,D.第4题
     *
     * @param answers 答案数组
     * @return 是否正确
     */
    private static boolean checkQ3(char[] answers) {
        return (answers[2] == answers[5] && answers[5] == answers[1] && answers[1] != answers[3]) ||
                (answers[2] == answers[5] && answers[5] == answers[3] && answers[3] != answers[1]) ||
                (answers[2] == answers[1] && answers[1] == answers[3] && answers[3] != answers[5]) ||
                (answers[5] == answers[1] && answers[1] == answers[3] && answers[3] != answers[2]);
    }

    /**
     * 以下选项中哪两题的答案相同：A.第1，5题,B.第2，7题,C.第1，9题,D.第6，10题
     *
     * @param answers 答案数组
     * @return 是否正确
     */
    private static boolean checkQ4(char[] answers) {
        return (answers[0] == answers[4] && 'A' == answers[3]) ||
                (answers[1] == answers[6] && 'B' == answers[3]) ||
                (answers[0] == answers[8] && 'C' == answers[3]) ||
                (answers[5] == answers[9] && 'D' == answers[3]);
    }

    /**
     * 以下选项中哪一题的答案与本题相同：A.第8题,B.第4题,C.第9题,D.第7题
     *
     * @param answers 答案数组
     * @return 是否正确
     */
    private static boolean checkQ5(char[] answers) {
        return ('A' == answers[7] && answers[7] == answers[4]) ||
                ('B' == answers[3] && answers[3] == answers[4]) ||
                ('C' == answers[8] && answers[8] == answers[4]) ||
                ('D' == answers[6] && answers[6] == answers[4]);
    }

    /**
     * 以下2选项中哪两题的答案与第8题相同：A.第2，4题,B.第1，6题,C.第3，10题,D.第5，9题
     *
     * @param answers 答案数组
     * @return 是否正确
     */
    private static boolean checkQ6(char[] answers) {
        return (answers[1] == answers[3] && answers[3] == answers[7] && 'A' == answers[5]) ||
                (answers[0] == answers[5] && answers[5] == answers[7] && 'B' == answers[5]) ||
                (answers[2] == answers[9] && answers[9] == answers[7] && 'C' == answers[5]) ||
                (answers[4] == answers[8] && answers[8] == answers[7] && 'D' == answers[5]);
    }

    /**
     * 在此十道题中，被选中次数最少的选项字母为：A.C,B.B,C.A,D.D
     *
     * @param answers 答案数组
     * @return 是否正确
     */
    private static boolean checkQ7(char[] answers) {
        Map<Character, Integer> map = new HashMap<>(4);
        map.put('A', 0);
        map.put('B', 0);
        map.put('C', 0);
        map.put('D', 0);
        for (char a : answers) {
            map.put(a, map.get(a) + 1);
        }
        char result = 'A';
        if (map.get('B').compareTo(map.get(result)) < 0) {
            result = 'B';
        }
        if (map.get('C').compareTo(map.get(result)) < 0) {
            result = 'C';
        }
        if (map.get('D').compareTo(map.get(result)) < 0) {
            result = 'D';
        }

        return ('A' == answers[6] && 'C' == result) ||
                ('B' == answers[6] && 'B' == result) ||
                ('C' == answers[6] && 'A' == result) ||
                ('D' == answers[6] && 'D' == result);
    }

    /**
     * 以下选项中哪一题的答案与第1题的答案在字母中不相邻：A.第7题,B.第5题,C.第2题,D.第10题
     *
     * @param answers 答案数组
     * @return 是否正确
     */
    private static boolean checkQ8(char[] answers) {
        return (Math.abs(answers[6] - answers[0]) > 1 && 'A' == answers[7]) ||
                (Math.abs(answers[4] - answers[0]) > 1 && 'B' == answers[7]) ||
                (Math.abs(answers[1] - answers[0]) > 1 && 'C' == answers[7]) ||
                (Math.abs(answers[9] - answers[0]) > 1 && 'D' == answers[7]);
    }

    /**
     * 已知“第1题与第6题的答案相同”与“第X题与第5题的答案相同的真假性相反，那么X为”：A.第6题,B.第10题,C.第2题,D.第9题
     *
     * @param answers 答案数组
     * @return 是否正确
     */
    private static boolean checkQ9(char[] answers) {
        return ((answers[0] == answers[5] && answers[5] != answers[4]) || (answers[0] != answers[5] && answers[5] == answers[4]) && 'A' == answers[8]) ||
                ((answers[0] == answers[5] && answers[9] != answers[4]) || (answers[0] != answers[5] && answers[9] == answers[4]) && 'B' == answers[8]) ||
                ((answers[0] == answers[5] && answers[1] != answers[4]) || (answers[0] != answers[5] && answers[1] == answers[4]) && 'C' == answers[8]) ||
                ((answers[0] == answers[5] && answers[8] != answers[4]) || (answers[0] != answers[5] && answers[8] == answers[4]) && 'D' == answers[8]);
    }

    /**
     * 在此10道题中，ABCD四个字母出现次数最多与最少者的差为：A.3,B.2,C.4,D.1
     *
     * @param answers 答案数组
     * @return 是否正确
     */
    private static boolean checkQ10(char[] answers) {
        List<Integer> list = new ArrayList<>(4);
        list.add(0, 0);
        list.add(1, 0);
        list.add(2, 0);
        list.add(3, 0);

        for (char a : answers) {
            if ('A' == a) {
                list.set(0, list.get(0) + 1);
            } else if ('B' == a) {
                list.set(1, list.get(1) + 1);

            } else if ('C' == a) {
                list.set(2, list.get(2) + 1);

            } else {
                list.set(3, list.get(3) + 1);
            }
        }
        int result = 1;
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = 1; j < list.size(); j++) {
                result = Math.max(result, Math.abs(list.get(i) - list.get(j)));
            }
        }
        return (result == 1 && answers[9] == 'D') ||
                (result == 2 && answers[9] == 'B') ||
                (result == 3 && answers[9] == 'A') ||
                (result == 4 && answers[9] == 'C');
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        for (char a1 : ANSWERS) {
            for (char a2 : ANSWERS) {
                for (char a3 : ANSWERS) {
                    for (char a4 : ANSWERS) {
                        for (char a5 : ANSWERS) {
                            for (char a6 : ANSWERS) {
                                for (char a7 : ANSWERS) {
                                    for (char a8 : ANSWERS) {
                                        for (char a9 : ANSWERS) {
                                            for (char a10 : ANSWERS) {
                                                char[] answers = {a1, a2, a3, a4, a5, a6, a7, a8, a9, a10};
                                                if (checkQ2(answers) &&
                                                        checkQ3(answers) &&
                                                        checkQ4(answers) &&
                                                        checkQ5(answers) &&
                                                        checkQ6(answers) &&
                                                        checkQ7(answers) &&
                                                        checkQ8(answers) &&
                                                        checkQ9(answers) &&
                                                        checkQ10(answers)) {
                                                    System.out.println(answers);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        System.out.println("运算耗时:" + (System.currentTimeMillis() - start) + "ms");
    }
}
