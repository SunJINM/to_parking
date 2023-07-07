package sun.project.to_parking.vo;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description 判断是否登录成功
 */
@ApiModel("判断是否登录成功")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VerifyLoginVO {
    private Boolean verify;
    private Long userId;
}
