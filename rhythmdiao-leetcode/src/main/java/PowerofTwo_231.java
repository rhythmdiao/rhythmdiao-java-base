/**
 * Given an integer, write a function to determine if it is a power of two.
 */
public final class PowerofTwo_231 {
    public static boolean isPowerOfTwo(int n) {
        if (n < 0) return false;
        String bit = Integer.toBinaryString(n);
        return bit.lastIndexOf("1") == 0;
    }

    public static void main(String[] args) {
        System.out.println(PowerofTwo_231.isPowerOfTwo(-2147483648));
    }
}
