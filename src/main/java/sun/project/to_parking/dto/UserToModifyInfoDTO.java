package sun.project.to_parking.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description 用户修改信息实体类
 *
 */
@ApiModel("用户修改信息实体类")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserToModifyInfoDTO {
    @ApiModelProperty("主键-雪花算法-修改某用户信息的凭证")
    private Long userId;
    @ApiModelProperty("昵称")
    private String nickname;
    @ApiModelProperty("用户手机号")
    private String phone;
    @ApiModelProperty("性别")
    private String gender;
    @ApiModelProperty("年龄")
    private Integer age;
    @ApiModelProperty("地址")
    private String address;
}
