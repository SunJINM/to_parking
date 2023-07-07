package sun.project.to_parking.pojo;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.omg.CORBA.IDLType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.annotation.security.DenyAll;
import javax.swing.*;
import java.util.*;

/**
 * @description 管理员实体类
 *
 */
@ApiModel("管理员实体类")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "t_admin")
public class Admin implements UserDetails {
    @ApiModelProperty("主键-雪花算法策略")
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    @ApiModelProperty("管理员账号")
    private String admin;
    @ApiModelProperty("密码")
    private String adminPassword;
    @ApiModelProperty("角色")
    private String role;
    @ApiModelProperty("当前用户是否未锁定")
    private Boolean locked;
    @ApiModelProperty("当前账户是否可用")
    private Boolean checkEnabled;
    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(value = "gmt_create",fill = FieldFill.INSERT)//数据插入时候自动填充
    private Date gmtCreate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")//返回数据给前端的
    @TableField(fill = FieldFill.INSERT_UPDATE)//数据插入和更新的时候自动填充
    @ApiModelProperty("更新时间")
    private Date gmtModified;


    /**
     * 用户实体类需要实现UserDetails的接口，实现的7个方法如下：
     * 1.getAuthorities   获取当前用户对象所具有的角色信息
     * 2.getPassword() 获取当前用户对象的密码
     * 3.getUsername() 获取当前用户对象的用户名
     * 4.getAccountNonExpired() 当前用户是否未过期
     * 5.getAccountNonLocked()当前用户是否未锁定
     * 6.isCredentialsNonExpired() 当前账户密码是否未过期
     * 7.isEnable 当前账户是否可用
     */
    /*
    注意：
    用户根据实际情况设置以下方法的返回值
     */
    /**
     * getAuthorities   获取当前用户对象所具有的角色信息
     * @return
     */

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities=new ArrayList<>();
        //本系统只有一个角色
        authorities.add(new SimpleGrantedAuthority(role));
        return authorities;
    }

    @Override
    public String getUsername() {
        return admin;
    }

    @Override
    public String getPassword() {
        return adminPassword;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return checkEnabled;
    }
}
