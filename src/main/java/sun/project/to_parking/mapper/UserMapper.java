package sun.project.to_parking.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import sun.project.to_parking.pojo.User;

/**
 * @description 用户数据库操作层
 */
@Repository
public interface UserMapper extends BaseMapper<User> {
}
