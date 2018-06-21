package ocean.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author
 * @date 2018-04-28 16:17:46
 * Created by
 */
@Data
public class Image implements Serializable {

    private static final long serialVersionUID = -6427284491449487392L;
    /**
     * id
     */
    private Long id;

    /**
     * ${item.comment}
     */
    private Integer userId;

    /**
     * ${item.comment}
     */
    private String picUrl;

    /**
     * ${item.comment}
     */
    private String type;

    /**
     * ${item.comment}
     */
    private String describe;

    /**
     * ${item.comment}
     */
    private Date uploadTime;

    /**
     * ${item.comment}
     */
    private Integer status;

}