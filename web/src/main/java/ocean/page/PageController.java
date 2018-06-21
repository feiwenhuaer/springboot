package ocean.page;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用于调试前端请求
 * @author xieyi
 */
@Slf4j
@Controller
@RequestMapping
public class PageController {

    @RequestMapping(value = "/")
    public String login(){
        return "login";
    }

}

