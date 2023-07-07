package sun.project.to_parking.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import sun.project.to_parking.pojo.User;
import sun.project.to_parking.dto.RechargeDTO;
import sun.project.to_parking.dto.UserDTO;
import sun.project.to_parking.dto.UserToModifyInfoDTO;
import sun.project.to_parking.mapper.UserMapper;
import sun.project.to_parking.service.UserService;

import java.util.List;
import java.util.Map;

@Service("userService")
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User addUser(UserDTO userDTO) {
        User user=new User();
        user.setNickname(userDTO.getNickname());
        user.setPhone(userDTO.getPhone());
        user.setPassword(userDTO.getPassword());
        user.setAddress(userDTO.getAddress());
        user.setGender(userDTO.getGender());
        user.setAge(userDTO.getAge());
        userMapper.insert(user);
        return user;
    }

    @Override
    public List<User> getAllUser() {
        //按照降序排列
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.orderByDesc("user_id");
        return userMapper.selectList(queryWrapper);
    }

    @Override
    public User getOneUserByPhone(String phone) {
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("phone",phone);
        return userMapper.selectOne(queryWrapper);
    }

    @Override
    public User getOneUserById(Long userId) {
        return userMapper.selectById(userId);
    }

    @Override
    public User getOneUserByPhoneAndPassword(String phone, String password) {
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("phone",phone)
                .eq("password",password);
        return userMapper.selectOne(queryWrapper);
    }

    @Override
    public List<User> getUserByMap(Map<String, Object> map) {
        return userMapper.selectByMap(map);
    }

    @Override
    public Boolean modifyUserPassword(String phone, String password) {
        UpdateWrapper<User> updateWrapper=new UpdateWrapper<>();
        updateWrapper.eq("phone",phone)
                .set("password",password);
        return userMapper.update(null,updateWrapper)>0;
    }

    @Override
    public Boolean modifyUserInfo(UserToModifyInfoDTO userToModifyInfoDTO) {
        User user=new User();
        user.setUserId(userToModifyInfoDTO.getUserId());
        user.setNickname(userToModifyInfoDTO.getNickname());
        user.setPhone(userToModifyInfoDTO.getPhone());
        user.setAddress(userToModifyInfoDTO.getAddress());
        user.setAge(userToModifyInfoDTO.getAge());
        user.setGender(userToModifyInfoDTO.getGender());
        return userMapper.updateById(user)>0;
    }

    @Override
    public Boolean rechargeBalance(RechargeDTO rechargeDTO) {
        log.info("余额充值中……");
        User getUser=userMapper.selectById(rechargeDTO.getUserId());
        if(ObjectUtils.isEmpty(getUser)){
            return false;
        }
        //充值后的金额
        double rechargeBalance=getUser.getBalance()+rechargeDTO.getMoney();
        User user=new User();
        user.setUserId(rechargeDTO.getUserId());
        user.setBalance(rechargeBalance);
        return userMapper.updateById(user)>0;
    }

    @Override
    public User getUserById(Long userId) {
        return userMapper.selectById(userId);
    }
}
