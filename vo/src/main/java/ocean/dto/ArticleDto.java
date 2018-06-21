package ocean.dto;

import ocean.model.Article;
import lombok.Data;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * @author xieyi
 */
@Data
public class ArticleDto implements Serializable{
    private static final long serialVersionUID = 3399595913517495673L;
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
    private String  articleContent;

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

    public ArticleDto(Article article) {
        this.id = article.getId();
        this.userId = article.getUserId();
        this.title = article.getTitle();
        try {
            this.articleContent = new String(article.getArticleContent(),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        this.type = article.getType();
        this.createTime = article.getCreateTime();
        this.updateTime = article.getUpdateTime();
        this.status = article.getStatus();
        this.headPic = article.getHeadPic();
    }
}
