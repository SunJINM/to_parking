package sun.project.to_parking.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import sun.project.to_parking.dto.*;
import sun.project.to_parking.enums.ResultCode;
import sun.project.to_parking.pojo.User;
import sun.project.to_parking.vo.VerifyLoginVO;
import sun.project.to_parking.vo.VerifyVO;
import sun.project.to_parking.comment.Result;
import sun.project.to_parking.dto.*;
import sun.project.to_parking.service.UserService;

import java.util.List;

/**
 * @description 用户业务处理控制层
 */
@Api(tags = "用户业务处理控制层")
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * @description 获取所有注册用户信息
     */
    @ApiOperation(value = "Get:获取所有注册用户信息")
    @GetMapping("/getAllUser")
    public Result getAllUser(){
        log.info("获取所有注册用户信息");
        List<User> allUser = userService.getAllUser();
        return Result.responseResult(ResultCode.SUCCESS,allUser);
    }
    /**
     * @description 用户注册
     */
    @ApiOperation("Add:用户注册")
    @PostMapping("/register")
    public Result register(@RequestBody UserDTO userDTO){
        log.info("添加用户");
        //首先查询用户是否已经注册
        User queryUserByPhone=userService.getOneUserByPhone(userDTO.getPhone());
        if(ObjectUtils.isEmpty(queryUserByPhone)){
            User addUser = userService.addUser(userDTO);
            log.info("添加成功！");
            return Result.responseResult(ResultCode.SUCCESS,addUser);
        }else {
            log.info("添加失败！用户已经存在！");
          return Result.responseResult(ResultCode.USER_HAS_EXISTED);
        }
    }
    /**
     * @description 用户登录
     */
    @ApiOperation("Login:用户登录")
    @PostMapping("/login")
    public Result login(@RequestBody UserToLoginDTO userToLoginDTO){
        log.info("用户登录功能");
        User getUser=userService.getOneUserByPhoneAndPassword(userToLoginDTO.getPhone(),userToLoginDTO.getPassword());
        if(!ObjectUtils.isEmpty(getUser)){
            return Result.responseResult(ResultCode.SUCCESS,new VerifyLoginVO(true,getUser.getUserId()));
        }else {
            log.info("用户登录失败！");
            return Result.responseResult(ResultCode.USER_LOGIN_ERROR,new VerifyVO(false));
        }
    }
    /**
     * @description 修改密码
     */
    @ApiOperation("Modify:修改用户密码！")
    @PostMapping("/modifyPassword")
    public Result modifyPassword(@RequestBody UserToModifyPasswordDTO userToModifyPasswordDTO){
        log.info("修改用户密码");
        //判断用户是否已经注册
        User getUser=userService.getOneUserByPhone(userToModifyPasswordDTO.getPhone());
        if(ObjectUtils.isEmpty(getUser)){
            return Result.responseResult(ResultCode.USER_LOGIN_ERROR,new VerifyVO(false));
        }else {
            boolean checkModify=userService.modifyUserPassword(userToModifyPasswordDTO.getPhone(),userToModifyPasswordDTO.getPassword());
            log.info("密码修改成功！");
            return Result.responseResult(ResultCode.SUCCESS,new VerifyVO(checkModify));
        }
    }
    /**
     * @description 修改个人信息
     *
     */
    @ApiOperation("Modify:修改个人信息")
    @PostMapping("modifyPersonalInfo")
    public Result modifyPersonalInfo(@RequestBody UserToModifyInfoDTO userToModifyInfoDTO){
        log.info("修改个人信息");
        //首先判断修改的后的手机号是否也已经被注册
        User getUser=userService.getOneUserById(userToModifyInfoDTO.getUserId());
        if(ObjectUtils.isEmpty(getUser)){
            log.info("修改失败！手机号已被注册！");
            return Result.responseResult(ResultCode.USER_LOGIN_ERROR,new VerifyVO(false));
        }else {
            boolean checkModify=userService.modifyUserInfo(userToModifyInfoDTO);
            log.info("修改成功！");
            return Result.responseResult(ResultCode.SUCCESS,new VerifyVO(checkModify));
        }

    }

    /**
     * @description 余额充值
     */
    @ApiOperation("Modify:余额充值")
    @PostMapping("/rechargeBalance")
    public Result rechargeBalance(@RequestBody RechargeDTO rechargeDTO){
        log.info("余额充值！");
        Boolean checkRechargeBalance=userService.rechargeBalance(rechargeDTO);
        if(checkRechargeBalance){
            log.info("充值成功！");
            return Result.responseResult(ResultCode.SUCCESS);
        }else {
            return Result.responseResult(ResultCode.USER_LOGIN_ERROR);
        }
    }
    /**
     * @description 根据userId获取用户信息
     *
     */
    @ApiOperation("Get:根据userId获取用户信息")
    @GetMapping("/getInfoByUserId/{userId}")
    public Result getInfoByUserId(@PathVariable(value = "userId")Long userId){
        User user=userService.getOneUserById(userId);
        return Result.responseResult(ResultCode.SUCCESS,user);
    }

}
