package sun.project.to_parking.service;

import org.springframework.stereotype.Service;
import sun.project.to_parking.pojo.CarPark;
import sun.project.to_parking.pojo.Stall;
import sun.project.to_parking.vo.CarParkVO;

import java.util.List;

@Service
public interface CarParkService {

    /**
     * 添加停车场信息
     * @return
     */
    CarPark addCarPark(CarParkVO carParkVO);

    /**
     * 获取所有停车场信息
     * @return 所有停车场信息
     */

    List<CarPark> getAllCarPark();

    /**
     * 根据carParkId获取停车位信息
     * @param carParkId 停车场Id
     * @return 停车场信息
     */
    CarPark  getCarParkByCarParkId(Long carParkId);

    /**
     * 模糊查询获取停车位的信息
     */
    List<CarPark> getCarParkByContent(String content);
}
