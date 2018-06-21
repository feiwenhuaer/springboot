package ocean.service;

import com.google.common.base.Throwables;
import ocean.dao.ImageMapper;
import lombok.extern.slf4j.Slf4j;
import ocean.model.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import ocean.response.ServerResponse;

import java.util.List;


/**
 * @author
 * @date 2018-04-28 16:17:46
 * Created by
 */
@Slf4j
@Service
public class ImageService {
    @Autowired
    ImageMapper imageMapper;
    @Transactional
    public ServerResponse addList(List<Image> imageList) {
        try {
            if (!CollectionUtils.isEmpty(imageList)) {
                return ServerResponse.ok(imageMapper.addList(imageList));
            }
            return ServerResponse.failed("图片不能为空!");
        }catch (Exception e){
            log.error("addList failed. reson:{}", Throwables.getStackTraceAsString(e));
            throw new RuntimeException(Throwables.getStackTraceAsString(e));
        }
    }

    public ServerResponse getAll(Integer userId) {
        try {
            return ServerResponse.ok(imageMapper.getAll(userId));
        } catch (Exception e) {
            log.error("getAll failed. reson:{}", Throwables.getStackTraceAsString(e));
            throw new RuntimeException(Throwables.getStackTraceAsString(e));
        }
    }
}