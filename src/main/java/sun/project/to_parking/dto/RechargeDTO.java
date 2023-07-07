package sun.project.to_parking.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description 余额充值信息实体类
 */
@ApiModel("余额充值实体类")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RechargeDTO {
    @ApiModelProperty("用户标识-主键Id")
    private Long userId;
    @ApiModelProperty("充值的金额")
    private double money;
}
