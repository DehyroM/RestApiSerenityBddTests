package co.com.sofka.util;

public enum UserJsonKeys {
    NAME("[name]"),
    USERNAME("[username]"),
    EMAIL("[email]");

    private final String value;

    UserJsonKeys(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
