package sun.project.to_parking.service;

import org.springframework.stereotype.Service;
import sun.project.to_parking.pojo.Stall;
import sun.project.to_parking.vo.StallVO;

import java.util.List;

/**
 * @description 停车位信息控制数据库操作类
 */
@Service
public interface StallService {
    /**
     * 添加停车位信息
     * @param stallVO 停车位信息接收实体类
     * @return 添加是否成功
     */
    Stall addStall(StallVO stallVO);

    /**
     * 根据停车场ID获取所有停车位信息
     * @return 所有停车位信息
     */
    List<Stall> getAllStallByCarParkId(Long carParkId);
    /**
     * 获取所有停车位信息
     * @return 所有停车位信息
     */


    List<Stall> getAllStall();

    /**
     * 根据stallId获取停车位信息
     * @param stallId 停车位Id
     * @return 停车位信息
     */
    Stall getStallByStallId(Long stallId);

    /**
     * 根据Id修改用户信息
     * @param stall
     * @return
     */
    Boolean modifyStallInfoById(Stall stall);
    /**
     * 模糊查询获取停车位的信息
     */
    List<Stall> getStallByContent(String content);
    /**
     * 修改车位为可预约状态
     *
     */
    Boolean modifyStallState(Long stallId);
}
