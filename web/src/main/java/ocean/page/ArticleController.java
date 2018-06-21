package ocean.page;

import com.google.common.base.Throwables;
import lombok.extern.slf4j.Slf4j;
import ocean.JedisApi;
import ocean.enums.LoginEnum;
import ocean.model.Article;
import ocean.response.ResponseHelper;
import ocean.response.ServerResponse;
import ocean.service.ArticleService;
import ocean.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

/**
 * @author xieyi
 */
@Slf4j
@Controller
@RequestMapping(value = "/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @Autowired
    private UserUtil userUtil;

    @RequestMapping(value = "/findById/{id}", method = RequestMethod.GET)
    public String  findById(@PathVariable(value = "id") String id, HttpServletRequest request, ModelMap modelMap) {
        modelMap.put("data", ResponseHelper.or500(articleService.findById(Long.valueOf(id))));
        modelMap.put("user", userUtil.getUser(request));
        return "edit";
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public String getAll(HttpServletRequest request, ModelMap modelMap, @RequestParam(value = "index",required = false,defaultValue = "0")Integer index,@RequestParam(value = "index",required = false,defaultValue = "10")Integer pageSize) {
        if (userUtil.isLogin(request)) {
            modelMap.put("user", userUtil.getUser(request));
            modelMap.put("data", ResponseHelper.or500(articleService.getAll(userUtil.getUserId(request),index,pageSize)));
            return "manage";
        }
        return "login";
    }
    @RequestMapping(value = "/toAdd", method = RequestMethod.GET)
    public String toAdd(HttpServletRequest request, ModelMap modelMap) {
        if (userUtil.isLogin(request)) {
            modelMap.put("user", userUtil.getUser(request));
            return "addBlog";
        }
        return "login";
    }
    @ResponseBody
    @RequestMapping(value = "/addArticle", method = RequestMethod.POST)
    public ServerResponse addArticle(@RequestParam("articleContent")String articleContent, @RequestParam("title")String title
            , HttpServletRequest request) {
        if (userUtil.isLogin(request)) {
            Article article = new Article();
            try {
                article.setArticleContent(articleContent.getBytes("UTF-8"));
            } catch (UnsupportedEncodingException e) {
                throw  new RuntimeException(Throwables.getStackTraceAsString(e));
            }
            article.setTitle(title);
            return articleService.addArticle(article, userUtil.getUserId(request));
        }
        return ServerResponse.failed(LoginEnum.UNLOGIN.getDesc());
    }
    @ResponseBody
    @RequestMapping(value = "/saveEdit", method = RequestMethod.POST)
    public ServerResponse saveEdit(@RequestParam("articleContent")String articleContent, @RequestParam("title")String title, @RequestParam("id")Long id, HttpServletRequest request) {
        if (userUtil.isLogin(request)) {
            Article article = new Article();
            article.setArticleContent(articleContent.getBytes());
            article.setTitle(title);
            return articleService.saveEdit(article, userUtil.getUserId(request));
        }
        return ServerResponse.failed(LoginEnum.UNLOGIN.getDesc());
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ServerResponse delete(@RequestParam(value = "id") Long id , @RequestParam(value = "deleteType") Integer deleteType , HttpServletRequest request) {
        if (userUtil.isLogin(request)) {
            return articleService.delete(id, userUtil.getUserId(request),deleteType);
        }
        return ServerResponse.failed(LoginEnum.UNLOGIN.getDesc());
    }
}