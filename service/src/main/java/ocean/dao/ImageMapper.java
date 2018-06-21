package ocean.dao;

import ocean.model.Image;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author [ ]
 * @date 2018-04-28 16:17:46
 * Created by
 */
@Mapper
public interface ImageMapper {
    Integer addList(List<Image> imageList);

    List<Image> getAll(@Param("userId") Integer userId);
}
