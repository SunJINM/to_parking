package sun.project.to_parking.controller;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import sun.project.to_parking.enums.ResultCode;
import sun.project.to_parking.service.StallService;
import sun.project.to_parking.vo.StallUseMainVO;
import sun.project.to_parking.vo.StallUserInfoVO;
import sun.project.to_parking.vo.VerifyVO;
import sun.project.to_parking.comment.Result;
import sun.project.to_parking.pojo.Stall;
import sun.project.to_parking.pojo.StallUse;
import sun.project.to_parking.pojo.User;
import sun.project.to_parking.service.StallUseService;
import sun.project.to_parking.service.UserService;

import java.util.List;

/**
 * @description 停车位预约信息处理控制类
 */
@RestController
@RequestMapping("/stallUse")
@Slf4j
public class StallUseController {
    @Autowired
    private StallService stallService;
    @Autowired
    private StallUseService stallUseService;
    @Autowired
    private UserService userService;

    /**
     * @description 添加停车位预约信息
     * @param stallUseMainVO 添加停车位预约信息实体类
     * @return 响应信息实体类
     */
    @ApiOperation("Add:用户预约，添加停车位预约信息")
    @PostMapping("/addStallUse")
    public Result addStallUse(@RequestBody StallUseMainVO stallUseMainVO){
        //判断用户是否已经注册
        User user=userService.getUserById(stallUseMainVO.getUserId());
        if(ObjectUtils.isEmpty(user)){
            //用户还未注册
            return Result.responseResult(ResultCode.USER_LOGIN_ERROR,new VerifyVO(false));
        }
        //判断停车位是否存在
        Stall getStall=stallService.getStallByStallId(stallUseMainVO.getStallId());
        if(ObjectUtils.isEmpty(getStall)){
            //停车位不存在
            return Result.responseResult(ResultCode.PARAM_IS_BLANK,new VerifyVO(false));
        }
        log.info("添加停车位预约信息");
        //判断用户是否已经预约过车位信息却未结束,通过是否预约及是否取消预约判断
        StallUse stallUse=stallUseService.getStallByBookAndCancel(Long.valueOf(stallUseMainVO.getUserId()));
        if(!ObjectUtils.isEmpty(stallUse)){
            return Result.responseResult(ResultCode.USER_STALL_IS_BOOKED,new VerifyVO(false));
        }

        //判断停车位是否可使用
        boolean isBook=stallService.getStallByStallId(stallUseMainVO.getStallId()).getIsBook();
        if(!isBook){
            //添加停车位
            StallUse addStallUseInfo=stallUseService.addStallUseInfo(stallUseMainVO);
            //改变停车位信息状态
            Stall stall=new Stall();
            stall.setStallId(stallUseMainVO.getStallId());
            stall.setIsBook(true);
            stallService.modifyStallInfoById(stall);
            return Result.responseResult(ResultCode.SUCCESS,new VerifyVO(true));
        }else {
            return Result.responseResult(ResultCode.FAIL,new VerifyVO(false));
        }
    }
    @ApiOperation("Get:获取用户预约的信息")
    @GetMapping("/getStallByUserId/{userId}")
    public Result getStallByUserId(@PathVariable("userId") Long userId){
        log.info("获取用户预约的信息");
        List<StallUserInfoVO> stallUseList=stallUseService.getUserStallUse(userId);
        return Result.responseResult(ResultCode.SUCCESS,stallUseList);
    }
    /**
     * @description 取消预约
     * @return
     */
    @ApiOperation("Modify:取消预约")
    @GetMapping("/cancelBook")
    public Result cancelBook(@RequestParam("userId")Long userId,@RequestParam("stallId") Long stallId){
        //通过isBook判断是否可以预约
        //我预约的停车位信息还原到未预约状态
        stallUseService.modifyInfoByUserIDStallIDBookState(userId,stallId);
        //修改车位状态信息为预约状态
        stallService.modifyStallState(stallId);
        return Result.responseResult(ResultCode.SUCCESS);
    }

    /**
     * @description 获取用户预约次数
     * @param userId 用户标识
     * @return
     */
    @ApiOperation("Get:获取用户预约次数")
    @GetMapping("/getUserBookNumber/{userId}")
    public Result getUserBookNumber(@PathVariable("userId") Long userId){
        int bookNumber=stallUseService.getStallBookNumber(userId);
        return Result.responseResult(ResultCode.SUCCESS,bookNumber);
    }
    @ApiOperation("Get:根据Id获取预约信息")
    @GetMapping("/getStallBookInfoById/{id}")
    public Result getStallBookInfoById(@PathVariable(value = "id")Long id){
        StallUserInfoVO stallUseById=stallUseService.getUserStallUseById(id);
        return Result.responseResult(ResultCode.SUCCESS,stallUseById);
    }

}
