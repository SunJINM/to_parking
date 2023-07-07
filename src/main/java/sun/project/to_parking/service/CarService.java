package sun.project.to_parking.service;

import org.springframework.stereotype.Service;
import sun.project.to_parking.pojo.Car;

@Service
public interface CarService {
    Boolean UpdateCarById(Car car);
}
