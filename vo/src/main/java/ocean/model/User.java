package ocean.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author
 * @date 2018-04-28 16:17:46
 * Created by
 */
@Data
@JsonInclude(value= JsonInclude.Include.NON_NULL)
public class User implements Serializable {

    private static final long serialVersionUID = -240952393538802950L;
    /**
     * id
     */
    private Integer id;

    /**
     * ${item.comment}
     */
    private Long phone;

    /**
     * ${item.comment}
     */
    private String email;

    /**
     * 类型
     */
    private String password;

    /**
     * ${item.comment}
     */
    private String name;

    /**
     * 用户类型(枚举类)
     */
    private Integer userType;

    /**
     * ${item.comment}
     */
    private Integer age;

    /**
     * ${item.comment}
     */
    private String job;

    /**
     * ${item.comment}
     */
    private String description;

    /**
     * ${item.comment}
     */
    private String headUrl;

    /**
     * ${item.comment}
     */
    private Date joinTime;

    /**
     * ${item.comment}
     */
    private Integer status;


}