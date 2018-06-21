package ocean.service;

import com.google.common.base.Throwables;
import com.google.common.collect.Lists;

import com.google.common.collect.Maps;
import ocean.dao.ArticleMapper;
import ocean.dto.ArticleDto;
import ocean.enums.DeleteTypeEnum;
import lombok.extern.slf4j.Slf4j;
import ocean.model.Article;
import ocean.util.Page;
import ocean.util.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import ocean.response.ServerResponse;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author xieyi
 */
@Slf4j
@Service
public class ArticleService {
    @Autowired
    private ArticleMapper articleMapper;

    public ServerResponse findById(Long id) {
        try {
            Article article = articleMapper.findById(id);
            ArticleDto articleDto = null;
            if(Objects.nonNull(article)){
                articleDto = new ArticleDto(article);
            }
            return ServerResponse.ok(articleDto);
        } catch (Exception e) {
            log.error("findById failed. reson:{}", Throwables.getStackTraceAsString(e));
            return ServerResponse.failed("findById failed");
        }
    }
    public ServerResponse getAll(Integer userId,Integer index,Integer pageSize) {
        try {
            PageInfo pageInfo = new PageInfo(index,pageSize);
            Map<String,Object> map = Maps.newHashMap();
            map.put("offset",pageInfo.getOffset());
            map.put("limit",pageInfo.getLimit());
            map.put("userId",userId);
            Page<ArticleDto> page = new Page();
            List<Article> list = articleMapper.getAll(map);
            Integer count = articleMapper.count(userId);
            List<ArticleDto> articleDtos = Lists.newArrayList();
            if(!CollectionUtils.isEmpty(list)) {
                articleDtos = list.stream().map(article ->  new  ArticleDto(article)).collect(Collectors.toList());
            }
            page.setCount(count);
            page.setData(articleDtos);
            return ServerResponse.ok(page);
        } catch (Exception e) {
            log.error("getAll failed. reson:{}", Throwables.getStackTraceAsString(e));
            return ServerResponse.failed("getAll failed");
        }
    }

    public ServerResponse getByType(Long type, Integer userId) {
        try {
            return ServerResponse.ok(articleMapper.getByType(type,userId));
        } catch (Exception e) {
            log.error("getByType failed. reson:{}", Throwables.getStackTraceAsString(e));
            return ServerResponse.failed("getByType failed");
        }
    }

    @Transactional
    public ServerResponse delete(Long id, Integer userId,Integer deleteType) {
        try {
            if(Objects.equals(DeleteTypeEnum.DELETE_HARD.getCode(),deleteType)) {
                return ServerResponse.ok(articleMapper.deleteByHard(id, userId));
            }
            return ServerResponse.ok(articleMapper.deleteBySoft(id, userId));
        } catch (Exception e) {
            throw new RuntimeException("deleteArticle failed");
        }
    }

    @Transactional
    public ServerResponse addArticle(Article article, Integer userId) {
        try {
            if(Objects.nonNull(article)) {
                article.setUserId(userId);
                articleMapper.addArticle(article);
            }
            return ServerResponse.ok(article);
        } catch (Exception e) {
            throw new RuntimeException("addArticle failed");
        }
    }

    @Transactional
    public ServerResponse saveEdit(Article article, Integer userId) {
        try {
            if(Objects.nonNull(article)) {
                article.setUserId(userId);
                articleMapper.saveEdit(article);
            }
            return ServerResponse.ok(article);
        } catch (Exception e) {
            log.error("saveEdit failed. reson:{}", Throwables.getStackTraceAsString(e));
            throw new RuntimeException("saveEdit failed");
        }
    }
}