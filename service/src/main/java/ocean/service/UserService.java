package ocean.service;

import com.google.common.base.Throwables;
import lombok.extern.slf4j.Slf4j;
import ocean.UserUtil;
import ocean.dao.UserMapper;
import ocean.enums.LoginEnum;
import ocean.model.User;
import ocean.response.ServerResponse;
import ocean.util.CodeMd5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @author
 * @date 2018-04-28 16:17:46
 * Created by
 */
@Service
@Slf4j
public class UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserUtil userUtil;
    public ServerResponse checkUser(User user, HttpServletRequest request) {
        try {
            user.setPassword(CodeMd5.fomateByMd5(user.getPassword()));
            User u = userMapper.checkUser(user);
            if(Objects.nonNull(u)) {
                userUtil.setUser(request, u);
                return ServerResponse.ok(u);
            }
            Integer exist=userMapper.isExist(user);
            if(Objects.nonNull(exist) && Objects.equals(exist,1)){
                return ServerResponse.failed(LoginEnum.PASSWORD_WRONG.getDesc());
            }
            return ServerResponse.failed(LoginEnum.USER_NOTEXIST.getDesc());
        }catch (Exception e){
            log.error("login failed. reson:{}", Throwables.getStackTraceAsString(e));
            return ServerResponse.failed("login failed");
        }
    }

    @Transactional
    public ServerResponse toRegis(User user) {
        try {
            return ServerResponse.ok(userMapper.toRegis(user));
        }catch (Exception e){
            log.error("regis failed. reson:{}", Throwables.getStackTraceAsString(e));
            throw new RuntimeException(Throwables.getStackTraceAsString(e));
        }
    }

    public ServerResponse<User> getProfile(Integer uid) {
        try {
            User u = userMapper.getProfile(uid);
            if(!Objects.nonNull(u)){
                return ServerResponse.failed(LoginEnum.USER_NOTEXIST.getDesc());
            }
            return ServerResponse.ok(u);
        }catch (Exception e){
            log.error("getProfile failed. reson:{}", Throwables.getStackTraceAsString(e));
            return ServerResponse.failed("getProfile failed");
        }
    }
}