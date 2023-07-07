package sun.project.to_parking.comment;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import sun.project.to_parking.enums.ResultCode;

import java.io.Serializable;

/**
 * 返回体
 */
@ApiModel(value = "响应信息实体类:Result" )
public class Result implements Serializable {
    @ApiModelProperty("状态码")
    private Integer status;
    @ApiModelProperty("请求信息")
    private String msg;
    @ApiModelProperty("返回数据对象")
    private Object data;

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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
    //有参
    public Result(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }
    //无参
    public Result() {
    }
    //返回无data返回数据的result
    public static Result responseResult(ResultCode resultCode){
        Result result=new Result();
        result.setMsg(resultCode.getMsg());
        result.setStatus(resultCode.getStatus());
        return result;
    }
    //返回有参result
    public static Result responseResult(ResultCode resultCode, Object data){
        Result result=new Result();
        result.setMsg(resultCode.getMsg());
        result.setStatus(resultCode.getStatus());
        result.setData(data);
        return result;
    }
}
