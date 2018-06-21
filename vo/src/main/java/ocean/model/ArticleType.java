package ocean.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author
 * @date 2018-05-24 14:54:36
 * Created by xieyi
 */
@Data
public class ArticleType implements Serializable {

    private static final long serialVersionUID = 3386506501192857123L;
    /**
     * id
     */
    private Long id;

    /**
     * ${item.comment}
     */
    private Integer userId;

    /**
     * type
     */
    private Integer type;

    /**
     * ${item.comment}
     */
    private String name;

    /**
     * ${item.comment}
     */
    private Date createTime;

    /**
     * ${item.comment}
     */
    private Integer status;


}