package sun.project.to_parking.vo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;

/**
 * @description 判断对错实体类
 *
 */
@ApiModel("判断对错实体类")
public class VerifyVO {
    private Boolean verify;

    public VerifyVO() {
    }

    public VerifyVO(Boolean verify) {

        this.verify = verify;
    }

    public Boolean getVerify() {
        return verify;
    }

    public void setVerify(Boolean verify) {
        this.verify = verify;
    }
}
