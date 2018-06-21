package ocean.enums;

/**
 * 操作响应枚举类
 * @author xieyi
 */

public enum LoginEnum {
    UNLOGIN(1000,"当前用户未登录!"),
    LOGIN_SUCCESS(1001,"登录成功"),
    LOGIN_FAILED(1002,"登录失败"),
    PASSWORD_WRONG(1003,"密码错误"),
    USER_NOTEXIST(1004,"用户不存在");

    private final int code;
    private final String desc;

    LoginEnum(int code, String desc) {
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
