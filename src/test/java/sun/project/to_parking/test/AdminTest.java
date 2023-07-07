package sun.project.to_parking.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ObjectUtils;
import sun.project.to_parking.mapper.AdminMapper;
import sun.project.to_parking.pojo.Admin;
import sun.project.to_parking.service.AdminService;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AdminTest {
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private AdminService adminService;
    @Test
    public void testGetData(){
        List<Admin> adminList = adminMapper.selectList(null);
        System.out.println("查询到的管理员信息有：\n"+adminList);
    }
    @Test
    public void addAdmin(){
//        BCryptPasswordEncoder加密
        Admin admin=new Admin();
        admin.setAdmin("admin");
        //加密加盐
        String password= new BCryptPasswordEncoder().encode("123456");
        admin.setAdminPassword(password);
        admin.setRole("admin1");
        if(!ObjectUtils.isEmpty(adminService.getAdminByAdmin("admin"))){
            System.out.println("管理员已存在！");
            return;
        }
        adminMapper.insert(admin);
    }
    @Test
    public void testGetDataByElement(){
        Admin admin = adminService.getAdminByAdmin("admin");
        System.out.println(admin);
    }
    @Test
    public void testGetDataByElement1(){
        Admin admin = adminService.getAdminByAdmin("admin");
        adminService.loadUserByUsername("admin");
    }
}
