package ocean.controller;

import ocean.annotation.CacheOpen;
import ocean.enums.LoginEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ocean.response.ServerResponse;
import ocean.service.UserService;
import ocean.UserUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @author
 * @date 2018-04-28 16:17:46
 * Created by
 */
@Slf4j
@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserUtil userUtil;

    @CacheOpen(prefix = "user", end = "#{uid}")
    @RequestMapping(value = "/getProfile", method = RequestMethod.GET)
    public ServerResponse getProfile(@RequestParam(value = "uid", required = false) Integer uid, HttpServletRequest request) {
        if (Objects.nonNull(uid)) {
            return userService.getProfile(uid);
        } else if (userUtil.isLogin(request)) {
            uid = userUtil.getUserId(request);
            return userService.getProfile(uid);
        }
        return ServerResponse.failed(LoginEnum.UNLOGIN.getDesc());
    }
}
