package constant;

public final class Regex {
    public static final String AT_LEAST_ONE_LOWERCASE_LETTER = "(?=.*[a-z])";
    public static final String AT_LEAST_ONE_DEGIT = "(?=.*d)";
    public static final String AT_LEAST_ONE_SPECIAL_CHARACTER = "(?=.*[@#$%])";
    public static final String AT_LEAST_ONE_CAPITAL_LETTER = "((?=.*[A-Z])";
    public static final String MIN_TO_MAX = "{6,16}";
    public static final String VALID_EMAIL = "^[A-Za-z0-9+_.-]+@(.+)$";
}
