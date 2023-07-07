package sun.project.to_parking.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import sun.project.to_parking.pojo.Admin;

/**
 * @description 管理员数据库信息操作实体类
 *
 */
@Mapper
@Repository
public interface AdminMapper extends BaseMapper<Admin> {
}
