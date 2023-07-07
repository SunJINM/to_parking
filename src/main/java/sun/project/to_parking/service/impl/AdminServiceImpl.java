package sun.project.to_parking.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import sun.project.to_parking.mapper.AdminMapper;
import sun.project.to_parking.pojo.Admin;
import sun.project.to_parking.service.AdminService;

/**
 * @description 管理员权限业务操作实现类
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminMapper adminMapper;
    /**
     * 定义此实现类:
     *实现该接口中的loadUserByUsername方法，该方法的参数就是用户登录时输入的用户名，
     * 通过用户名去数据库中查找用户，如果没有查找到用户，就抛出一个用户不存在的异常，通过
     * 查找到了用户，就继续查找该用户所具有的角色信息，并将获取到的user对象返回，再由系统
     * 提供的DAOAuthenticationProvider类去比对密码是否正确
     */
    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        Admin admin=this.getAdminByAdmin(name);
        if(ObjectUtils.isEmpty(admin)){
            throw new UsernameNotFoundException("账户不存在!");
        }
        Admin getAdmin=adminMapper.selectById(admin.getId());
        UserDetails userDetails=User.withUsername(getAdmin.getAdmin())
                .password(getAdmin.getAdminPassword())
                .authorities(getAdmin.getRole())
                .build();
        System.out.println("admin:\n"+getAdmin);
        System.out.println("userDetails:\n"+userDetails.getUsername());
        System.out.println("roles:\n"+userDetails.getAuthorities());
        System.out.println("locked:\n"+userDetails.isAccountNonLocked());
        System.out.println("enabled:\n"+userDetails.isEnabled());
        System.out.println("userDetails:\n"+userDetails.getUsername());
        return userDetails;
    }


    @Override
    public Admin getAdminByAdmin(String admin) {
        QueryWrapper<Admin> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("admin",admin);
        return adminMapper.selectOne(queryWrapper);
    }
}
