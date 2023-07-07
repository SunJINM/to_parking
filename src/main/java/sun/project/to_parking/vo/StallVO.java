package sun.project.to_parking.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description 用户信息接收
 */
@ApiModel("用户信息接收传输类")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StallVO {
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
    @ApiModelProperty("停车位照片链接")
    private String url;
}
