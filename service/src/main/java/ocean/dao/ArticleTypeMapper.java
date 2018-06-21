package ocean.dao;

import ocean.model.ArticleType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author xieyi
 */
@Mapper
public interface ArticleTypeMapper {
    ArticleType findById(@Param("id") Long id);

    List<ArticleType> getAll(@Param("userId") Integer userId);

    Integer addArticleType(ArticleType articleType);

    Integer deleteByHard(@Param("id") Long id, @Param("userId") Integer userId);

    Integer deleteBySoft(@Param("id") Long id, @Param("userId") Integer userId);

    Integer saveEdit(ArticleType articleType);
}
