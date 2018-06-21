package ocean.service;

import com.google.common.base.Throwables;

import ocean.dao.ArticleTypeMapper;
import ocean.enums.DeleteTypeEnum;
import lombok.extern.slf4j.Slf4j;
import ocean.model.ArticleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ocean.response.ServerResponse;

import java.util.Objects;

/**
 * @author xieyi
 */
@Slf4j
@Service
public class ArticleTypeService {
    @Autowired
    private ArticleTypeMapper articleTypeMapper;

    public ServerResponse findById(Long id) {
        try {
            return ServerResponse.ok(articleTypeMapper.findById(id));
        } catch (Exception e) {
            log.error("findById failed. reson:{}", Throwables.getStackTraceAsString(e));
            return ServerResponse.failed("findById failed");
        }
    }

    public ServerResponse getAll(Integer userId) {
        try {
            return ServerResponse.ok(articleTypeMapper.getAll(userId));
        } catch (Exception e) {
            log.error("getAll failed. reson:{}", Throwables.getStackTraceAsString(e));
            return ServerResponse.failed("getAll failed");
        }
    }

    @Transactional
    public ServerResponse addArticleType(ArticleType articleType, Integer userId) {
        try {
            if(Objects.nonNull(articleType)) {
                articleType.setUserId(userId);
                articleType.setStatus(1);
                return ServerResponse.ok(articleTypeMapper.addArticleType(articleType));
            }
            return ServerResponse.failed("文章分类不能为空!");
        } catch (Exception e) {
            log.error("addArticleType failed. reson:{}", Throwables.getStackTraceAsString(e));
            return ServerResponse.failed("addArticleType failed");
        }
    }

    @Transactional
    public ServerResponse delete(Long id, Integer userId, Integer deleteType) {
            try {
                if(Objects.equals(DeleteTypeEnum.DELETE_HARD.getCode(),deleteType)) {
                    return ServerResponse.ok(articleTypeMapper.deleteByHard(id, userId));
                }
                return ServerResponse.ok(articleTypeMapper.deleteBySoft(id, userId));
            } catch (Exception e) {
                log.error("delete failed. reson:{}", Throwables.getStackTraceAsString(e));
                throw new RuntimeException("delete failed");
            }
    }

    @Transactional
    public ServerResponse saveEdit(ArticleType articleType, Integer userId) {
        try {
            if(Objects.nonNull(articleType)) {
                articleType.setUserId(userId);
                return ServerResponse.ok(articleTypeMapper.saveEdit(articleType));
            }
            return ServerResponse.failed("类型不能为空!");
        } catch (Exception e) {
            log.error("delete failed. reson:{}", Throwables.getStackTraceAsString(e));
            throw new RuntimeException("delete failed");
        }
    }
}