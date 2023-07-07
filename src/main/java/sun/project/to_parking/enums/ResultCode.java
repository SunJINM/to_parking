package sun.project.to_parking.enums;

/**
 *结果的枚举类
 */
public enum ResultCode {
    /*成功的状态码200*/
    SUCCESS(200,"请求成功"),
    FAIL(400,"请求失败"),
    /*参数错误：1001-1999*/
    PARAM_IS_BLANK(1001,"参数或内容为空"),
    PARAM_IS_INVALID(1002,"参数无效"),
    PARAM_TYPE_BIND_ERROR(1003,"参数类型或格式错误"),
    PARAM_NOT_COMPLETE(1004,"参数缺失"),
    PARAM_NOT_INSERT(1005,"插入失败"),
    /*用户请求为空*/
    USER_IS_BLANK(2005,"请求用户参数为空"),
    USER_NOT_EXIST(2006,"用户不存在"),
    USER_HAS_EXISTED(2007,"用户已存在"),
    USER_LOGIN_ERROR(2008,"账户不存在或密码错误"),
    USER_REGULAR_ERROR(2008,"账户或密码不规范！"),
    /*验证码*/
    CODE_IS_ERROR(2009,"验证码错误"),
    CODE_IS_NOT_GET(2010,"验证码获取失败，邮箱不存在或邮箱不合法"),
    CODE_IS_EXPIRE(2011,"验证码已过期或还未获取，请获取验证码"),
    /*停车位信息*/
    USER_STALL_IS_BOOKED(2100,"用户还有预约车位状态在进行中！"),
    ;
    //状态码
    private Integer status;
    //请求状态信息
    private String msg;

    //创建构造函数
    ResultCode(Integer status, String msg) {
        this.status=status;
        this.msg=msg;
    }
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
