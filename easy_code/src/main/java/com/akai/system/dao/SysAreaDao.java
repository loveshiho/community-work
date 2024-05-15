package com.akai.system.dao;

import com.akai.system.entity.SysArea;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * 区域表(SysArea)表数据库访问层
 *
 * @author makejava
 * @since 2024-05-15 09:20:49
 */
public interface SysAreaDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysArea queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param sysArea 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<SysArea> queryAllByLimit(SysArea sysArea, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param sysArea 查询条件
     * @return 总行数
     */
    long count(SysArea sysArea);

    /**
     * 新增数据
     *
     * @param sysArea 实例对象
     * @return 影响行数
     */
    int insert(SysArea sysArea);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysArea> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<SysArea> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysArea> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<SysArea> entities);

    /**
     * 修改数据
     *
     * @param sysArea 实例对象
     * @return 影响行数
     */
    int update(SysArea sysArea);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}

