package ocean.exception;
import lombok.Data;
/**
 * @author xieyi
 */

@Data
public class ResponseForError {
    private Integer code;
    private String msg;

    public ResponseForError() {
    }

    public ResponseForError(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
