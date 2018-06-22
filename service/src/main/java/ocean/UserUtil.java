package ocean;


import lombok.extern.slf4j.Slf4j;
import ocean.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @author xieyi
 */
@Component
@Slf4j
public class UserUtil {
    @Autowired
    private JedisApi jedisApi;

    public  void setUser(HttpServletRequest request, User user){
        jedisApi.saveObject(request.getSession().getId(),user);
    }
    /**
     * 从session获取用户id
     * @param request
     * @return
     */
    public  Integer getUserId(HttpServletRequest request){
        Object object = getUser(request);
        if(Objects.nonNull(object)){
            User user = (User)object;
            return user.getId();
        }
        return null;
    }
    /**
     * 获取用户详情
     */
    public  User getInfo(HttpServletRequest request){
        Object object = getUser(request);
        if(Objects.nonNull(object)){
            return (User)object;
        }
        return null;
    }

    /**
     * 检查用户是否登录
     * @param request
     * @return
     */
    public  boolean isLogin(HttpServletRequest request){
        if(Objects.nonNull(getUser(request))){
            return true;
        }
        return false;
    }

    public  Object getUser(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        Object object = null;
        for(Cookie cookie:cookies){
            object = jedisApi.getObject(cookie.getValue());
            if(Objects.nonNull(object)){
                return object;
            }
        }
        return object;
    }
}
