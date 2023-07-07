package sun.project.to_parking.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sun.project.to_parking.service.StallUseService;
import sun.project.to_parking.vo.StallUseMainVO;

/**
 * @author tian
 * @date 2022.6.16
 * @description 与用户预约停车位信息处理测试类
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class StallUseText {
    @Autowired
    private StallUseService stallUseService;
    /**
     * 添加测试数据
     */
    @Test
    public void textAddStallInfo(){
        Long userId= Long.valueOf("1533336131880849409");
        Long stallId= Long.valueOf("1536614373282762754");
        StallUseMainVO stallUseMainVO =new StallUseMainVO();
        stallUseMainVO.setStallId(stallId);
        stallUseMainVO.setUserId(userId);
        stallUseService.addStallUseInfo(stallUseMainVO);
    }
}
