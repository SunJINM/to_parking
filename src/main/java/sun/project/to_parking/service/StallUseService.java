package sun.project.to_parking.service;

import org.springframework.stereotype.Service;
import sun.project.to_parking.pojo.StallUse;
import sun.project.to_parking.vo.StallUseMainVO;
import sun.project.to_parking.vo.StallUserInfoVO;

import java.util.List;

/**
 * @description 用户预约停车位信息处理实体类
 */
@Service
public interface StallUseService {
    /**
     * 添加预约用户信息
     */
    StallUse addStallUseInfo(StallUseMainVO stallUseMainVO);
    /**
     * 查询预约状态且未取消的用户
     */
    StallUse getStallByBookAndCancel(Long userId);
    /**
     * 获取用户预约的信息，根据时间获取
     *
     */
    List<StallUserInfoVO> getUserStallUse(Long userId);
    /**
     * 获取用户预约的信息，根据时间获取
     *
     */
    StallUserInfoVO getUserStallUseById(Long Id);
    /**
     * 修改状态信息
     * @param userId 用户标识
     * @param stallId 车位标识
     */
    Boolean modifyInfoByUserIDStallIDBookState(Long userId,
                                               Long stallId);
    /**
     * 获取用户预约停车位的次数
     */
    Integer getStallBookNumber(Long userId);
}
