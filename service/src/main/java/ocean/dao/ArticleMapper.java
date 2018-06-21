package ocean.dao;

import ocean.model.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author xieyi
 */
@Mapper
public interface ArticleMapper {
    Article findById(@Param("id") Long id);

    List<Article> getByType(@Param("type") Long type, @Param("userId") Integer userId);

    Integer deleteByHard(@Param("id") Long id, @Param("userId") Integer userId);

    Integer deleteBySoft(@Param("id") Long id, @Param("userId") Integer userId);

    Integer addArticle(Article article);

    Integer count(@Param("userId")Integer userId);

    List<Article> getAll(Map<String,Object> map);

    Integer saveEdit(Article article);
}
