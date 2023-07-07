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
 * @description 停车位状态信息实体类
 */


@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("车位状态信息实体类")
@TableName("t_car")
public class Car {

    @ApiModelProperty("主键")
    @TableId(value = "car_id",type = IdType.ASSIGN_ID)
    private long carId;
    @ApiModelProperty("车位名")
    private String name;
    @ApiModelProperty("车位状态")
    private String state;
    @ApiModelProperty("开始停车时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date startDate;
    @ApiModelProperty("结束停车时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endDate;
    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(value = "gmt_create",fill = FieldFill.INSERT)//数据插入时候自动填充
    private Date gmtCreate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")//返回数据给前端的
    @TableField(fill = FieldFill.INSERT_UPDATE)//数据插入和更新的时候自动填充
    @ApiModelProperty("更新时间")
    private Date gmtModified;

}
