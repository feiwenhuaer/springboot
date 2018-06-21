package ocean.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author
 * @date 2018-05-17 15:47:28
 * Created by xieyi
 */
@Data
public class Article implements Serializable {

    private static final long serialVersionUID = 1971532358419285431L;
    /**
     * id
     */
    private Long id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private byte[]  articleContent;

    /**
     * 分类
     */
    private String type;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 有效状态
     */
    private Integer status;

    /**
     * 头图url
     */
    private String headPic;

}