package sun.project.to_parking.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;


/**
 * @description 自动填充创建时间和修改时间，阿里巴巴开发手册：所有的数据库表：gmt_create、gmt_modified几乎所有的表都要配置上！而且需要自动化！
 */

/**
 * MetaObjectHandler接口是mybatisPlus为我们提供的的一个扩展接口，
 * 我们可以利用这个接口在我们插入或者更新数据的时候，为一些字段指定默认值。
 * 实现这个需求的方法不止一种，在sql层面也可以做到，在建表的时候也可以指定默认值。
 */
@Slf4j
@Component  //将这个Bean交给IoC处理
public class MyMetaObjectHandler implements MetaObjectHandler {
    /**
     * 插入时的填充策略
     *
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill ......");
        this.setFieldValByName("gmtCreate", new Date(), metaObject);
        this.setFieldValByName("gmtModified", new Date(), metaObject);
    }


    /**
     * 更新时的填充策略
     *
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill ......");
        this.setFieldValByName("gmtModified", new Date(), metaObject);
    }
}
