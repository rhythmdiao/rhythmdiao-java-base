package utils;

public enum ConstResult {
    STRING(""), JSON("{}"), XML("<response></response>");
    private String empty;

    ConstResult(String s) {
        this.empty = s;
    }

    public String getEmpty() {
        return empty;
    }
}
