package com.akai.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;

import java.io.Serializable;

@ExcelTarget("course")
public class Course implements Serializable {
    @Excel(name = "编号", orderNum = "1")
    private String cid;

    @Excel(name = "订单编号", orderNum = "2")
    private String orderno;

    @Excel(name = "课程名称", orderNum = "3")
    private String cname;

    @Excel(name = "简介", orderNum = "4")
    private String brief;

    @Excel(name = "价格", orderNum = "5")
    private double price;

    public Course() {
    }

    public Course(String cid, String orderno, String cname, String brief, double price) {
        this.cid = cid;
        this.orderno = orderno;
        this.cname = cname;
        this.brief = brief;
        this.price = price;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Course{" +
                "cid='" + cid + '\'' +
                ", orderno='" + orderno + '\'' +
                ", cname='" + cname + '\'' +
                ", brief='" + brief + '\'' +
                ", price=" + price +
                '}';
    }
}
