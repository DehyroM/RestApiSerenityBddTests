package co.com.sofka.util;

public enum UserInfoKeys {
    NAME_USER_ONE("Leanne Graham"),
    USERNAME_USER_ONE("Bret"),
    EMAIL_USER_ONE("Sincere@april.biz");

    private final String value;

    UserInfoKeys(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
