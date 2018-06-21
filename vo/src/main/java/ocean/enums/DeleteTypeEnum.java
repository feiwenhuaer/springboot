package ocean.enums;

/**
 * 删除类型，delete_hard为物理删除，delete_soft为逻辑删除
 * @author xieyi
 */

public enum  DeleteTypeEnum {
    DELETE_HARD(1,"彻底删除!"),
    DELETE_SOFT(2,"放入回收站");

    private final int code;
    private final String desc;

    DeleteTypeEnum(int code, String desc) {
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
