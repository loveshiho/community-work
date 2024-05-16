package com.akai.pojo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;

import java.io.Serializable;
import java.util.Date;

@ExcelTarget("loginUser")
public class LoginUser implements Serializable {
    @Excel(name = "用户ID", orderNum = "1")
    private Integer id;
    @Excel(name = "昵称", orderNum = "2")
    private String username;
    @Excel(name = "密码", orderNum = "3")
    private String password;
    @Excel(name = "注册时间", orderNum = "4", format = "yyyy年MM月dd日")
    private Date createTime;
    @Excel(name = "状态", orderNum = "5")
    private String status;

    public LoginUser() {
    }

    public LoginUser(Integer id, String username, String password, Date createTime, String status) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.createTime = createTime;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "LoginUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", createTime=" + createTime +
                ", status='" + status + '\'' +
                '}';
    }
}
