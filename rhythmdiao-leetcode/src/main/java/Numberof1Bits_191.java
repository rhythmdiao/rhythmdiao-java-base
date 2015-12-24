/**
 * Write a function that takes an unsigned integer and returns the number of ’1' bits it has (also known as the Hamming weight).
 * For example, the 32-bit integer ’11' has binary representation 00000000000000000000000000001011, so the function should return 3.
 */
public class Numberof1Bits_191 {
    public static int hammingWeight(int n) {
        int result = 0;
        String s = Integer.toBinaryString(n);
        char[] array = s.toCharArray();
        for (char c : array) {
            if (c == 49) result++;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(Numberof1Bits_191.hammingWeight(11));
    }
}
