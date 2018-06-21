package ocean.util;

import lombok.Data;

import java.util.List;

/**
 * @author xieyi
 */
@Data
public class Page<T> {
    private Integer count;
    private List<T> data;

    public Page() {
    }

    public Page(Integer count,List<T> data) {
        this.count = count;
        this.data = data;
    }
}
