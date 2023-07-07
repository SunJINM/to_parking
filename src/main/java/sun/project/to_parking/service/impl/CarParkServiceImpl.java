package sun.project.to_parking.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.project.to_parking.mapper.CarParkMapper;
import sun.project.to_parking.pojo.CarPark;
import sun.project.to_parking.pojo.Stall;
import sun.project.to_parking.service.CarParkService;
import sun.project.to_parking.vo.CarParkVO;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class CarParkServiceImpl implements CarParkService {

    @Autowired
    private CarParkMapper carParkMapper;

    @Override
    public CarPark addCarPark(CarParkVO carParkVO) {

        CarPark carPark = new CarPark();
        carPark.setCarParkId(carParkVO.getCarParkId());
        carPark.setCarParkName(carParkVO.getCarParkName());
        carPark.setProvince(carParkVO.getProvince());
        carPark.setAddress(carParkVO.getAddress());
        carPark.setCity(carParkVO.getCity());
        carPark.setLatitude(carParkVO.getLatitude());
        carPark.setLongitude(carParkVO.getLongitude());

        carParkMapper.insert(carPark);
        return carPark;
    }

    @Override
    public List<CarPark> getAllCarPark() {
        QueryWrapper<CarPark> queryWrapper=new QueryWrapper<>();
        //降序获取停车位信息
        queryWrapper.orderByDesc("car_park_id");
        return carParkMapper.selectList(queryWrapper);
    }

    @Override
    public CarPark getCarParkByCarParkId(Long carParkId) {
        return carParkMapper.selectById(carParkId);
    }

    @Override
    public List<CarPark> getCarParkByContent(String content) {
        QueryWrapper<CarPark> queryWrapper=new QueryWrapper<>();
        queryWrapper.like("address",content);
        queryWrapper.like("carParkName", content);
        return carParkMapper.selectList(queryWrapper);
    }
}
