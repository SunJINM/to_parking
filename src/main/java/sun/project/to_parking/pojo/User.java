package sun.project.to_parking.pojo;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @description 用户信息实体类
 */
@ApiModel("用户信息实体类")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_user")
public class User {
    @ApiModelProperty("主键-雪花算法")
    @TableId(value = "user_id",type = IdType.ASSIGN_ID)
    private Long userId;
    @ApiModelProperty("昵称")
    private String nickname;
    @ApiModelProperty("用户手机号")
    private String phone;
    @ApiModelProperty("用户密码")
    private String password;
    @ApiModelProperty("性别")
    private String gender;
    @ApiModelProperty("年龄")
    private Integer age;
    @ApiModelProperty("地址")
    private String address;
    @ApiModelProperty("余额")
    private Double balance;
    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(value = "gmt_create",fill = FieldFill.INSERT)//数据插入时候自动填充
    private Date gmtCreate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")//返回数据给前端的
    @TableField(fill = FieldFill.INSERT_UPDATE)//数据插入和更新的时候自动填充
    @ApiModelProperty("更新时间")
    private Date gmtModified;
}
