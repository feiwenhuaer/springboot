package ocean.config.qiNiu;

import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class QiNiuService {
    // 设置好账号的ACCESS_KEY和SECRET_KEY
    private String ACCESS_KEY = QiNiuInfo.QINIU_ACCESS_KEY;
    private String SECRET_KEY = QiNiuInfo.QINIU_SECRET_KEY;
    // 要上传的空间
    private String BUCKET_NAME = QiNiuInfo.QINIU_BUCKET_NAME;
    // 密钥配置
    private Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
    // 创建上传对象
    private UploadManager uploadManager = new UploadManager();

    // 简单上传，使用默认策略，只需要设置上传的空间名就可以了
    public String getUpToken() {
        return auth.uploadToken(BUCKET_NAME);
    }

    public void upload(byte[] localData, String remoteFileName){
        try {
            Response res = uploadManager.put(localData, remoteFileName, getUpToken());
        }catch (IOException e){
            e.printStackTrace();
        }

    }

}
