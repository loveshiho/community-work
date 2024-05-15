package com.akai.pojo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;

import java.io.Serializable;
@ExcelTarget("card")
public class Card implements Serializable {
    @Excel(name = "身份证号", orderNum = "7")
    private String id;
    @Excel(name = "家庭住址", orderNum = "8")
    private String address;

    public Card() {
    }

    public Card(String id, String address) {
        this.id = id;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
