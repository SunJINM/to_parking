package sun.project.to_parking.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.project.to_parking.mapper.StallMapper;
import sun.project.to_parking.mapper.StallUseMapper;
import sun.project.to_parking.pojo.Stall;
import sun.project.to_parking.pojo.StallUse;
import sun.project.to_parking.vo.StallUseMainVO;
import sun.project.to_parking.vo.StallUserInfoVO;
import sun.project.to_parking.service.StallUseService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @description 用户处理停车位信息业务实体类
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class StallUseServiceImpl implements StallUseService {
    @Autowired
    private StallUseMapper stallUseMapper;
    @Autowired
    private StallMapper stallMapper;

    
    @Override
    public StallUse addStallUseInfo(StallUseMainVO stallUseMainVO) {
        StallUse stallUse=new StallUse();
        //获取用户信息
        stallUse.setUserId(stallUseMainVO.getUserId());
        stallUse.setStallId(stallUseMainVO.getStallId());

        //获取预约时间
        stallUse.setIsBook(true);
        stallUse.setBookTime(new Date());
        stallUseMapper.insert(stallUse);
        return stallUse;
    }

    @Override
    public StallUse getStallByBookAndCancel(Long userId) {
        //获取预约的用户且在预约状态的用户信息
        QueryWrapper<StallUse> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("user_id",userId)
                .eq("is_book",true)
                .eq("cancel_book",false);
        return stallUseMapper.selectOne(queryWrapper);
    }

    @Override
    public List<StallUserInfoVO> getUserStallUse(Long userId) {
        QueryWrapper<StallUse> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("user_id",userId).orderByDesc("id");
        //获取用户预约的信息
        List<StallUse> stallUseList=stallUseMapper.selectList(queryWrapper);
        System.out.println("获取每一条信息");
        System.out.println("获取每一条信息"+stallUseList.size());
        List<StallUserInfoVO> stallUserInfoVOList=new ArrayList<>();
        for(StallUse stallUse : stallUseList){
            Long stallId=stallUse.getStallId();
            System.out.println("获取该条信息"+stallId);
            QueryWrapper<Stall> queryWrapper1=new QueryWrapper<>();
            queryWrapper1.eq("stall_id",stallId);
            Stall stall=stallMapper.selectOne(queryWrapper1);
            System.out.println("获取该条信息stall:"+stall);
            StallUserInfoVO userInfoVO=new StallUserInfoVO();
            userInfoVO.setCategory(stall.getCategory());
            userInfoVO.setUrl(stall.getUrl());
            userInfoVO.setAddress(stall.getAddress());
            userInfoVO.setProvince(stall.getProvince());
            userInfoVO.setCity(stall.getCity());
            userInfoVO.setPrice(stall.getPrice());
            userInfoVO.setGmtCreate(stallUse.getGmtCreate());
            userInfoVO.setLatitude(stall.getLatitude());
            userInfoVO.setLongitude(stall.getLongitude());
            userInfoVO.setUserId(stallUse.getUserId());
            userInfoVO.setStallId(stallUse.getStallId());
            userInfoVO.setIsBook(stallUse.getIsBook());
            userInfoVO.setBookTime(stallUse.getBookTime());
            userInfoVO.setCancelBook(stallUse.getCancelBook());
            userInfoVO.setCancelBookTime(stallUse.getCancelBookTime());
            userInfoVO.setId(stallUse.getId());
            stallUserInfoVOList.add(userInfoVO);
        }
        return stallUserInfoVOList;
    }

    @Override
    public StallUserInfoVO getUserStallUseById(Long id) {
        StallUse stallUse=stallUseMapper.selectById(id);
        Stall stall=stallMapper.selectById(stallUse.getStallId());
        StallUserInfoVO userInfoVO=new StallUserInfoVO();
        userInfoVO.setCategory(stall.getCategory());
        userInfoVO.setUrl(stall.getUrl());
        userInfoVO.setAddress(stall.getAddress());
        userInfoVO.setProvince(stall.getProvince());
        userInfoVO.setCity(stall.getCity());
        userInfoVO.setPrice(stall.getPrice());
        userInfoVO.setGmtCreate(stallUse.getGmtCreate());
        userInfoVO.setLatitude(stall.getLatitude());
        userInfoVO.setLongitude(stall.getLongitude());
        userInfoVO.setUserId(stallUse.getUserId());
        userInfoVO.setStallId(stallUse.getStallId());
        userInfoVO.setIsBook(stallUse.getIsBook());
        userInfoVO.setBookTime(stallUse.getBookTime());
        userInfoVO.setCancelBook(stallUse.getCancelBook());
        userInfoVO.setCancelBookTime(stallUse.getCancelBookTime());
        userInfoVO.setId(stallUse.getId());
        return userInfoVO;
    }

    @Override
    public Boolean modifyInfoByUserIDStallIDBookState(Long userId, Long stallId) {
        UpdateWrapper<StallUse> useUpdateWrapper=new UpdateWrapper<>();
        useUpdateWrapper.set("cancel_book",true)
                .set("cancel_book_time",new Date())
                .eq("user_id",userId)
                .eq("stall_id",stallId)
                .eq("is_book",true)
                .eq("cancel_book",false);
        return stallUseMapper.update(null,useUpdateWrapper)>0;
    }

    @Override
    public Integer getStallBookNumber(Long userId) {
        QueryWrapper<StallUse> queryWrapper =new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        return stallUseMapper.selectCount(queryWrapper);
    }
}
