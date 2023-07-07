package sun.project.to_parking.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 用户获取订单返回实体类信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StallUserInfoVO {
    @ApiModelProperty("预约编号")
    private Long Id;
    @ApiModelProperty("用户标识Id")
    private Long userId;
    @ApiModelProperty("车位标识Id")
    private Long stallId;
    @ApiModelProperty("判断是否已经预约")
    private Boolean isBook;
    @ApiModelProperty("获取预约时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date bookTime;
    @ApiModelProperty("判断是否取消预约")
    private Boolean cancelBook;
    @ApiModelProperty("取消预约时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date cancelBookTime;
    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gmtCreate;
    @ApiModelProperty("停车位照片信息")
    private String url;
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
}
