package kansha.entitys;

import kansha.emuns.CommonEmun;
import lombok.Data;

/**
 * @program: ks-parent
 * @description:
 * @author: pzy
 * @create: 2019-08-02 13:33
 **/
@Data
public class ResultEntity {

    public ResultEntity(){
        this.status = true;//请求成功
        this.code = CommonEmun.FAIL.getCode();//方法返回失败
        this.description = CommonEmun.FAIL.getDesc();//请求说明
    }
    /**
     * 服务名称
     */
    private String serviceName ;

    /**
     * API请求的地址
     * 例如: /usc-zuul/base/user
     */
    private String path;

    /**
     * 请求状态
     */
    private Boolean status;

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 说明
     */
    private String description;

    /**
     * 返回的数据
     */
    private Object data;

    }