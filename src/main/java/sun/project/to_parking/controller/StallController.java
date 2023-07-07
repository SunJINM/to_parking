package sun.project.to_parking.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.project.to_parking.enums.ResultCode;
import sun.project.to_parking.pojo.Stall;
import sun.project.to_parking.service.StallService;
import sun.project.to_parking.vo.StallVO;
import sun.project.to_parking.comment.Result;

import java.util.List;

/**
 * @description 停车位信息控制类
 */
@RestController
@RequestMapping("stall")
@Api(tags = "停车位信息控制类")
public class StallController {
    @Autowired
    private StallService stallService;
    /**
     * @param stallVO 添加停车位信息实体类
     * @return
     */
    @ApiOperation("Add:添加停车位信息")
    @PostMapping("/addStall")
    public Result addStall(@RequestBody StallVO stallVO){
        Stall stall=stallService.addStall(stallVO);
        return Result.responseResult(ResultCode.SUCCESS,stall);
    }

    /**
     * @description 根据停车场ID获取所有停车位信息
     * @return
     */
    @ApiOperation("Get:根据停车场ID获取所有停车位信息")
    @GetMapping("/getAllStallByCarParkId/{carParkId}")
    public Result getAllStallByCarParkId(@PathVariable("carParkId")Long carParkId){
        List<Stall> stallList = stallService.getAllStallByCarParkId(carParkId);
        return Result.responseResult(ResultCode.SUCCESS, stallList);
    }
    /**
     * @description 获取所有停车位信息
     * @return
     */
    @ApiOperation("Get:获取所有停车位信息")
    @GetMapping("/getAllStall")
    public Result getAllStall(){
        List<Stall> stallList=stallService.getAllStall();
        return Result.responseResult(ResultCode.SUCCESS,stallList);
    }

    /**
     * @description 根据stallId获取停车位信息
     * @param stallId
     * @return
     */
    @ApiOperation("Get:根据stallId获取停车位信息")
    @GetMapping("/getStallById/{stallId}")
    public Result getStallById(@PathVariable("stallId")Long stallId){
        Stall stall=stallService.getStallByStallId(stallId);
        return Result.responseResult(ResultCode.SUCCESS,stall);
    }

    /**
     * @description 模糊查询完成搜索功能
     * @param content
     * @return
     */
    @ApiOperation("Get：模糊查询完成搜索功能")
    @GetMapping("/getLikeInfo/{content}")
    public Result getLikeInfo(@PathVariable(value = "content")String content){
        List<Stall> stallList=stallService.getStallByContent(content);
        return Result.responseResult(ResultCode.SUCCESS,stallList);
    }

}
