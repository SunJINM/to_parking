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
 * @description 停车位信息实体类
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("停车位信息实体类")
@TableName("t_stall")
public class Stall {
    @ApiModelProperty("主键-雪花算法")
    @TableId(value = "stall_id",type=IdType.ASSIGN_ID)
    private Long stallId;
    @ApiModelProperty("停车场ID")
    private Long carParkId;
    @ApiModelProperty("类型")
    private String category;
    @ApiModelProperty("费用")
    private Integer price;
    @ApiModelProperty("省份")
    private String province;
    @ApiModelProperty("城市")
    private String city;
    @ApiModelProperty("地址")
    private String address;
    @ApiModelProperty("经度")
    private String longitude;
    @ApiModelProperty("纬度")
    private String latitude;
    @ApiModelProperty("是否预约")
    private Boolean isBook;
    @ApiModelProperty("是否已经使用中")
    private Boolean isUse;
    @ApiModelProperty("停车位照片信息")
    private String url;
    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(value = "gmt_create",fill = FieldFill.INSERT)//数据插入时候自动填充
    private Date gmtCreate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")//返回数据给前端的
    @TableField(fill = FieldFill.INSERT_UPDATE)//数据插入和更新的时候自动填充
    @ApiModelProperty("更新时间")
    private Date gmtModified;
}
