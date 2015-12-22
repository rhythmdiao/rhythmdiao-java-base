/**
 * Related to question Excel Sheet Column Title
 * Given a column title as appear in an Excel sheet, return its corresponding column number.
 * For example:
 * A -> 1
 * B -> 2
 * C -> 3
 * ...
 * Z -> 26
 * AA -> 27   26+1
 * AB -> 28   26+2
 */
public final class ExcelSheetColumnNumber_171 {
    public static int titleToNumber(String s) {
        int result = 0;
        int length = s.length();
        for (int i = 0; i < length; i++) {
            result = (s.charAt(i) - 'A' + 1) + result * 26;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(ExcelSheetColumnNumber_171.titleToNumber("NOTATALL"));
    }
}
