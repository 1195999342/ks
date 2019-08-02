package kansha.emuns;

/**
 * @program: ks-parent
 * @description:
 * @author: pzy
 * @create: 2019-08-02 13:55
 **/
public enum  CommonEmun {
    FAIL(0, "交易失败"),
    SUCCESS(1, "交易成功"),
    PARAMS_ERROR(2, "参数错误"),
    SEAND_CODE_ERROR(3, "验证码发送失败"),
    NOT_DATA(99, "没有数据");

    private Integer code;

    private String desc;

    private CommonEmun(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }


    public Integer getCode() {
        return code;
    }


    public void setCode(Integer code) {
        this.code = code;
    }


    public String getDesc() {
        return desc;
    }


    public void setDesc(String desc) {
        this.desc = desc;
    }


    public static String getRetCodeDesc(Integer code) {
        for (CommonEmun c : CommonEmun.values()) {
            if (c.code.equals(code)) {
                return c.desc;
            }
        }
        return "";
    }
}