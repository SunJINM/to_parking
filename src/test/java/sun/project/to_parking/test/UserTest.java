package sun.project.to_parking.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sun.project.to_parking.dto.RechargeDTO;
import sun.project.to_parking.dto.UserDTO;
import sun.project.to_parking.mapper.UserMapper;
import sun.project.to_parking.pojo.User;
import sun.project.to_parking.service.UserService;

import java.util.List;

/**
 * 用户数据测试类
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserTest {
    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;
    /**
     * 查询数据库
     *
     */
    @Test
    public void testQueryUser(){
        List<User> userList = userMapper.selectList(null);
        System.out.println("已注册的用户有："+userList);
    }

    /**
     * 添加用户
     */
    @Test
    public void testAddUser(){
        UserDTO userDTO=new UserDTO();
        userDTO.setNickname("A06");
        userDTO.setPhone("12345678906");
        userDTO.setPassword("123456mm");
        userDTO.setAddress("广东省广州市");
        userDTO.setGender("男");
        userDTO.setAge(21);
        User user = userService.addUser(userDTO);
        System.out.println("用户数据添加成功：\n"+user);
    }
    /**
     * 余额充值
     */
    @Test
    public void testAddBalance(){
        RechargeDTO rechargeDTO=new RechargeDTO();
        rechargeDTO.setMoney(100);
        rechargeDTO.setUserId(Long.valueOf("1531864152339054594"));
        userService.rechargeBalance(rechargeDTO);
    }
}
