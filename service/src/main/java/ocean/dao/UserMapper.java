package ocean.dao;

import ocean.model.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author [ ]
 * @date 2018-04-28 16:17:46
 * Created by
 */
@Mapper
public interface UserMapper {
    /**
     * @param user
     * @return
     */
    User checkUser(User user);

    /**
     * @param user
     * @return
     */
    Integer toRegis(User user);

    /**
     * @param uid
     * @return
     */
    User getProfile(Integer uid);

    /**
     * @param user
     * @return
     */
    Integer isExist(User user);
}
