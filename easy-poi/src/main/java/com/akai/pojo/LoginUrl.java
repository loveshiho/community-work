package com.akai.pojo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;

import java.io.Serializable;
@ExcelTarget("loginUrl")
public class LoginUrl implements Serializable {
    @Excel(name = "用户ID", orderNum = "1")
    private Integer userId;
    @Excel(name = "请求类型", orderNum = "2")
    private String type;
    @Excel(name = "访问地址", orderNum = "3")
    private String url;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "LoginUrl{" +
                "userId=" + userId +
                ", type='" + type + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
