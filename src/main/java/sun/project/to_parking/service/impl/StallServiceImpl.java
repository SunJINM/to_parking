package sun.project.to_parking.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.project.to_parking.service.StallService;
import sun.project.to_parking.vo.StallVO;
import sun.project.to_parking.mapper.StallMapper;
import sun.project.to_parking.pojo.Stall;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class StallServiceImpl implements StallService {
    @Autowired
    private StallMapper stallMapper;

    @Override
    public Stall addStall(StallVO stallVO) {
        Stall stall=new Stall();
        stall.setCarParkId(stallVO.getCarParkId());
        stall.setPrice(stallVO.getPrice());
        stall.setCategory(stallVO.getCategory());
        stall.setAddress(stallVO.getAddress());
        stall.setCity(stallVO.getCity());
        stall.setProvince(stallVO.getProvince());
        stall.setUrl(stallVO.getUrl());
        stall.setLatitude(stallVO.getLatitude());
        stall.setLongitude(stallVO.getLongitude());
        stallMapper.insert(stall);
        return stall;
    }

    @Override
    public List<Stall> getAllStallByCarParkId(Long carParkId) {
        QueryWrapper<Stall> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("car_park_id", carParkId);
        return stallMapper.selectList(queryWrapper);
    }

    @Override
    public List<Stall> getAllStall() {
        QueryWrapper<Stall> queryWrapper=new QueryWrapper<>();
        //降序获取停车位信息
        queryWrapper.orderByDesc("stall_id");
        return stallMapper.selectList(queryWrapper);
    }

    @Override
    public Stall getStallByStallId(Long stallId) {
        return stallMapper.selectById(stallId);
    }

    @Override
    public Boolean modifyStallInfoById(Stall stall) {
        return stallMapper.updateById(stall)>0;
    }

    @Override
    public List<Stall> getStallByContent(String content) {
        QueryWrapper<Stall> queryWrapper=new QueryWrapper<>();
        queryWrapper.like("address",content);
        queryWrapper.like("category", content);
        return stallMapper.selectList(queryWrapper);
    }

    @Override
    public Boolean modifyStallState(Long stallId) {
        UpdateWrapper<Stall> updateWrapper=new UpdateWrapper();
        updateWrapper.eq("stall_id",stallId)
                .set("is_book",false);
        return stallMapper.update(null,updateWrapper)>0;
    }
}
