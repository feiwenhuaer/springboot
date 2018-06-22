package ocean.qiNiu;

/**
 * 七牛云相关参数
 * @author xieyi
 */
public class QiNiuInfo {

    /**
     * 七牛云相关,貌似只有华东的可以正常使用
     */
    public static final String QINIU_IMAGE_URL = "http://onmpi5j6x.bkt.clouddn.com/";
    public static final String QINIU_ACCESS_KEY = "nLcpksJBqg-gIBw3HQoKOoR_ukbDweh8H1NBW5_O";
    public static final String QINIU_SECRET_KEY = "BoFZPtCQepVsMhHxBiiFfi9vW-22pg4-QlOOkfJH";
    public static final String QINIU_BUCKET_NAME = "exciteding";
    /**
     *  发送邮件的邮箱，要与df.properties中的一致
     */
    public static final String MAIL_FROM = "18271672219@163.com";
    /**
     * 域名
     */
    public static final String DOMAIN_NAME = "http://localhost:8080/xieyi/";
    /**
     *  三种操作
     */
    public static final int OPERATION_CLICK_LIKE = 1;
    public static final int OPERATION_REPLY = 2;
    public static final int OPERATION_COMMENT = 3;

}