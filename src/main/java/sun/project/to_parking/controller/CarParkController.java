package sun.project.to_parking.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.project.to_parking.comment.Result;
import sun.project.to_parking.enums.ResultCode;
import sun.project.to_parking.pojo.CarPark;
import sun.project.to_parking.pojo.Stall;
import sun.project.to_parking.service.CarParkService;
import sun.project.to_parking.vo.CarParkVO;

import java.util.List;

/**
 * 停车场信息控制类
 */
@RestController
@RequestMapping("carPark")
public class CarParkController {

    @Autowired
    private CarParkService carParkService;

    /**
     * 停车场信息添加
     */
    @ApiOperation("Add:添加停车场信息")
    @PostMapping("/addCarPark")
    public Result addCarPark(@RequestBody CarParkVO carParkVO){
        CarPark carPark = carParkService.addCarPark(carParkVO);
        return Result.responseResult(ResultCode.SUCCESS,carPark);
    }

    /**
     * @description 获取所有停车场信息
     * @return
     */
    @ApiOperation("Get:获取所有停车场信息")
    @GetMapping("/getAllCarPark")
    public Result getAllCarPark(){
        List<CarPark> carParkList=carParkService.getAllCarPark();
        return Result.responseResult(ResultCode.SUCCESS,carParkList);
    }

    /**
     * @description 根据carParkId获取停车场信息
     * @param carParkId
     */
    @ApiOperation("Get:根据stallId获取停车场信息")
    @GetMapping("/getCarParkById/{carParkId}")
    public Result getCarParkById(@PathVariable("carParkId")Long carParkId){
        CarPark carPark=carParkService.getCarParkByCarParkId(carParkId);
        return Result.responseResult(ResultCode.SUCCESS,carPark);
    }

    @ApiOperation("Get：模糊查询完成搜索功能")
    @GetMapping("/getLikeInfo/{content}")
    public Result getLikeInfo(@PathVariable(value = "content")String content){
        List<CarPark> carParkList=carParkService.getCarParkByContent(content);
        return Result.responseResult(ResultCode.SUCCESS,carParkList);
    }

}
