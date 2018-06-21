package ocean.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

/**
 * 结果封装
 *
 * @author xieyi
 * @param <T>
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ServerResponse<T> implements Serializable {
    private static final long serialVersionUID = 1871532358419285431L;
    private Boolean status;
    private String msg;
    private T data;

    public ServerResponse() {
    }

    private ServerResponse(String msg, T data) {
        this.msg = msg;
        this.data = data;
    }

    public static ServerResponse failed(String msg){
        ServerResponse serverResponse=new ServerResponse();
        serverResponse.setStatus(false);
        serverResponse.setMsg(msg);
        return serverResponse;
    }

    public static <T> ServerResponse<T> ok(T data){
        ServerResponse serverResponse=new ServerResponse();
        serverResponse.setStatus(true);
        serverResponse.setData(data);
        return serverResponse;
    }

}

