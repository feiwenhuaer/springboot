package ocean.util;

import lombok.Data;

/**
 * @author xieyi
 */
@Data
public class PageInfo {
    public static final String LIMIT = "limit";
    public static final String OFFSET = "offset";
    private Integer offset;
    private Integer limit;

    public PageInfo(Integer pageNo, Integer size) {
        this.limit = size.intValue() > 0 ? size.intValue() : 20;
        this.offset = (pageNo.intValue() - 1) * size.intValue();
        this.offset = this.offset.intValue() > 0 ? this.offset.intValue() : 0;
    }
}
