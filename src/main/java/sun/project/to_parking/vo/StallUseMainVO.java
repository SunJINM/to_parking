package sun.project.to_parking.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description 停车位预约信息系统初始添加实体类
 *
 */
@ApiModel("停车位预约信息系统初始添加实体类")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StallUseMainVO {
    @ApiModelProperty("用户标识Id")
    private Long userId;
    @ApiModelProperty("车位标识Id")
    private Long stallId;
}
