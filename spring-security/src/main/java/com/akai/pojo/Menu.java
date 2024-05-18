package com.akai.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.Date;

@TableName("sys_menu")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Menu implements Serializable {
    @TableId
    private Long menuId;

    // 菜单名
    private String menuName;

    // 路由地址
    private String path;

    // 组件路径
    private String component;

    // 菜单状态 (0 显示, 1隐藏)
    private String visible;

    // 菜单状态 (0 正常, 1 停用)
    private String status;

    // 权限标识
    private String perms;

    // 菜单图标
    private String icon;

    private String createBy;

    private String updateBy;

    private Date updateTime;

    private Date createTime;

    private String remark;

    public Menu() {
    }

    public Menu(Long menuId, String menuName, String path, String component, String visible, String status, String perms, String icon, String createBy, String updateBy, Date updateTime, Date createTime, String remark) {
        this.menuId = menuId;
        this.menuName = menuName;
        this.path = path;
        this.component = component;
        this.visible = visible;
        this.status = status;
        this.perms = perms;
        this.icon = icon;
        this.createBy = createBy;
        this.updateBy = updateBy;
        this.updateTime = updateTime;
        this.createTime = createTime;
        this.remark = remark;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getVisible() {
        return visible;
    }

    public void setVisible(String visible) {
        this.visible = visible;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPerms() {
        return perms;
    }

    public void setPerms(String perms) {
        this.perms = perms;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "menuId=" + menuId +
                ", menuName='" + menuName + '\'' +
                ", path='" + path + '\'' +
                ", component='" + component + '\'' +
                ", visible='" + visible + '\'' +
                ", status='" + status + '\'' +
                ", perms='" + perms + '\'' +
                ", icon='" + icon + '\'' +
                ", createBy='" + createBy + '\'' +
                ", updateBy='" + updateBy + '\'' +
                ", updateTime=" + updateTime +
                ", createTime=" + createTime +
                ", remark='" + remark + '\'' +
                '}';
    }
}
