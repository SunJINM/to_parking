package sun.project.to_parking.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description 添加用户
 *
 */
@ApiModel("添加用户实体类")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
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
}
