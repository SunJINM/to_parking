package sun.project.to_parking.pojo;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.org.apache.xpath.internal.operations.Bool;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @description 预约信息处理实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("预约信息处理实体类")
@TableName("t_stall_use")
public class StallUse {
    @ApiModelProperty("主键-策略：雪花算法")
    @TableId(type = IdType.ASSIGN_ID)
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
    @ApiModelProperty("判断是否已经开始使用")
    private Boolean isStartUse;
    @ApiModelProperty("开始使用时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date useStartTime;
    @ApiModelProperty("判断是否已经开始使用")
    private Boolean isEndUse;
    @ApiModelProperty("结束使用时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date useEndTime;
    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(value = "gmt_create",fill = FieldFill.INSERT)//数据插入时候自动填充
    private Date gmtCreate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")//返回数据给前端的
    @TableField(fill = FieldFill.INSERT_UPDATE)//数据插入和更新的时候自动填充
    @ApiModelProperty("更新时间")
    private Date gmtModified;
}
