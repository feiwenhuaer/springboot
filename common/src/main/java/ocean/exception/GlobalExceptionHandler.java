package ocean.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
/**
 * 系统异常处理，比如：404,500
 * @return
 * @throws Exception
 * @author xieyi
 */

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler{

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseForError defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        ResponseForError r = new ResponseForError();
        r.setMsg(e.getMessage());
        if (e instanceof org.springframework.web.servlet.NoHandlerFoundException) {
            r.setCode(404);
        } else {
            r.setCode(500);
        }
        return r;
    }

}
