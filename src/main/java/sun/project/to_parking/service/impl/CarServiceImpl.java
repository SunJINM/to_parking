package sun.project.to_parking.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.project.to_parking.service.CarService;
import sun.project.to_parking.mapper.CarMapper;
import sun.project.to_parking.pojo.Car;

@Service
@Transactional(rollbackFor = Exception.class)
public class CarServiceImpl implements CarService {

    @Autowired
    private CarMapper carMapper;

    /**
     * @description 根据Id更新数据
     * @param car
     * @return
     */
    @Override
    public Boolean UpdateCarById(Car car) {
        Car car1 = new Car();
        car1.setCarId(car.getCarId());
        car1.setName(car.getName());
        car1.setState(car.getState());
        car1.setStartDate(car.getStartDate());
        car1.setEndDate(car.getEndDate());

        return carMapper.updateById(car1)>0;
    }
}
