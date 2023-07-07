package sun.project.to_parking;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("sun.project.to_parking.mapper")
@EnableTransactionManagement
public class ToParkingApplication {

    public static void main(String[] args) {
        SpringApplication.run(ToParkingApplication.class, args);
    }

}
