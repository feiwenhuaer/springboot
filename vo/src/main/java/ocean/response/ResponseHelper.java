package ocean.response;

/**
 * @author xieyi
 */
public class ResponseHelper {

    public static <T> T or500(ServerResponse<T> resp) {
        if (resp.getStatus()) {
            return resp.getData();
        } else {
            throw new RuntimeException(resp.getMsg());
        }
    }
    public static <T, D extends T> ServerResponse<T> ok(D data) {
        ServerResponse<T> resp = new ServerResponse();
        resp.setData(data);
        return resp;
    }
}
