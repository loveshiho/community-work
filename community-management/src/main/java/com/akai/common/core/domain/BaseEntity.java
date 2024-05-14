package com.akai.common.core.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;
// 抽取公有属性
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 搜索值
     *
     * @TableField(exist = false)注解加载bean属性上，表示当前属性不是数据库的字段，
     * 但在项目中必须使用，这样在新增等使用 bean的时候，mybatis-plus就会忽略这个，不会报错
     */
    @TableField(exist = false)
    private String searchValue;
    /* 请求参数,封装前端发来的数据 */
    @TableField(exist = false)
    private Map<String, Object> params;
    /**
     * 创建者
     * fill 在需要被填充的字段上使用注解，声明什么时候要被填充
     * FieldFill.INSERT 只在插入时填充
     * FieldFill.INSERT_UPDATE 插入和更新时都填充
     */
    @TableField(fill = FieldFill.INSERT)
    private String createBy;
    /* 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    /* 更新者 */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateBy;
    /* 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    /* 备注 */
    private String remark;

    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
