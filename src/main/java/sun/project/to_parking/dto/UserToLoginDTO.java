package sun.project.to_parking.dto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description 用户登录信息接收实体类
 *
 */
@ApiModel("用户登录信息接收实体类")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserToLoginDTO {
    @ApiModelProperty("用户手机号")
    private String phone;
    @ApiModelProperty("用户登录密码")
    private String password;
}
