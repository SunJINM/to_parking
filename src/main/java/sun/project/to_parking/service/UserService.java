package sun.project.to_parking.service;

import org.springframework.stereotype.Service;
import sun.project.to_parking.dto.RechargeDTO;
import sun.project.to_parking.dto.UserDTO;
import sun.project.to_parking.dto.UserToModifyInfoDTO;
import sun.project.to_parking.pojo.User;

import java.util.List;
import java.util.Map;

/**
 * @description 用户数据操作业务层
 */
@Service
public interface UserService {
    /**
     * 添加用户数据
     * @param userDTO 用户返回数据实体类
     * @return 用户信息
     */
    User addUser(UserDTO userDTO);

    /**
     * 获取所有注册用户-注册顺序新-旧
     * @return 获取所有注册用户
     */
    List<User> getAllUser();
    /**
     * 根据某字段获取用户信息
     * @param phone 用户手机号
     * @return 用户信息实体类
     */
    User getOneUserByPhone(String phone);
    /**
     * 根据某用户ID获取用户信息
     * @param userId 用户ID
     * @return 用户信息实体类
     */
    User getOneUserById(Long userId);
    /**
     * 根据用户手机号和用户登录密码获取用户信息
     * @param phone 用户手机号
     * @param password 用户登录密码
     * @return 用户信息实体类
     */
    User getOneUserByPhoneAndPassword(String phone,String password);

    /**
     * 查询符合条件的用户
     * @param map 查询条件-AND
     * @return 查询符合条件的用户信息
     */
    List<User> getUserByMap(Map<String,Object>map);
    /**
     * 修改用户密码
     * @param phone 用户手机号
     * @param password 用户登录密码
     * @return 判断是否修改成功
     */
    Boolean modifyUserPassword(String phone,String password);

    /**
     * 修改用户的个人信息
     * @param userToModifyInfoDTO 修改的用户信息
     * @return 判断是否修改成功
     */
    Boolean modifyUserInfo(UserToModifyInfoDTO userToModifyInfoDTO);

    /**
     * 余额充值
     * @param rechargeDTO 余额充值信息实体类
     * @return 判断是否充值成功
     */
    Boolean rechargeBalance(RechargeDTO rechargeDTO);
    /**
     * 根据Id获取用户信息
     */
    User getUserById(Long userId);
}
