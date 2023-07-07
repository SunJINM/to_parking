package sun.project.to_parking.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description 用户修改密码实体类
 *
 */
@ApiModel("用户修改密码实体类")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserToModifyPasswordDTO {
    private String phone;
    private String password;
}
