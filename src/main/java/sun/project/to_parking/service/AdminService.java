package sun.project.to_parking.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import sun.project.to_parking.pojo.Admin;

/**
 * @description 管理员权限业务处理实现类
 *
 */
@Service
public interface AdminService extends UserDetailsService {
    /**
     * 根据管理员名获取管理员
     */
    Admin getAdminByAdmin(String name);
}
