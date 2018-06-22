package ocean.controller;

import lombok.extern.slf4j.Slf4j;
import ocean.response.ResponseHelper;
import ocean.service.ArticleService;
import ocean.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import javax.servlet.http.HttpServletRequest;

/**
 * @author xieyi
 */
@Slf4j
@Controller
@RequestMapping("/index")
public class IndexController {
    @Autowired
    private ArticleService articleService;

    @Autowired
    private UserUtil userUtil;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String getAll(HttpServletRequest request, ModelMap modelMap, @RequestParam(value = "index",required = false,defaultValue = "0")Integer index, @RequestParam(value = "index",required = false,defaultValue = "10")Integer pageSize) {
        modelMap.put("data", ResponseHelper.or500(articleService.getAll(null,index,pageSize)));
        modelMap.put("user", userUtil.getUser(request));
        return "index";
    }

    @RequestMapping(value = "/findById/{id}", method = RequestMethod.GET)
    public String  findById(@PathVariable(value = "id") String id, HttpServletRequest request, ModelMap modelMap) {
        modelMap.put("data",ResponseHelper.or500(articleService.findById(Long.valueOf(id))));
        modelMap.put("user",userUtil.getUser(request));
        log.error(articleService.toString());
        return "detail";
    }

}
