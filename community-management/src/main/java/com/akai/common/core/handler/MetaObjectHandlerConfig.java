package com.akai.common.core.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;
@Component
public class MetaObjectHandlerConfig implements MetaObjectHandler {
    // insert时要填充的字段
    @Override
    public void insertFill(MetaObject metaObject) {
        // 根据属性名称设置要填充的值
        this.strictInsertFill(metaObject, "createBy", String.class, "admin");
        this.strictInsertFill(metaObject,"updateBy",String.class,"admin");
        this.strictInsertFill(metaObject,"createTime", Date.class,new Date());
        this.strictInsertFill(metaObject,"updateTime", Date.class,new Date());
    }
    // update操作时要填充的字段
    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, "updateBy", String.class, "admin");
        this.strictUpdateFill(metaObject,"updateTime", Date.class,new Date());
    }
}
