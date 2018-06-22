package ocean.controller;

import lombok.extern.slf4j.Slf4j;
import ocean.enums.LoginEnum;
import ocean.model.User;
import ocean.response.ServerResponse;
import ocean.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
public class LoginController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Object toLogin(User user, HttpServletRequest request) {
        if(Objects.nonNull(user)) {
            return userService.checkUser(user, request);
        }
        return ServerResponse.failed(LoginEnum.UNLOGIN.getDesc());
    }

    @RequestMapping(value = "/toRegis",method = RequestMethod.POST)
    public ServerResponse toRegis(User user){
        //TODO
        return userService.toRegis(user);
    }


}