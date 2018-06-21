package ocean.enums;

/**
 * 用户类型枚举类
 * @author xieyi
 */
public enum UserTypeEnum {
    ADMIN(0,"管理员"),
    COMMON_USER(1,"普通用户"),
    PASSENGER(2,"游客");

    private final int code;
    private final String desc;

    UserTypeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
