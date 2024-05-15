package com.akai.pojo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelEntity;
import cn.afterturn.easypoi.excel.annotation.ExcelIgnore;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@ExcelTarget("users")
public class User implements Serializable {
    @ExcelIgnore
    @Excel(name = "编号", orderNum = "1")
    // orderNum默认为0
    private Integer id;
    @Excel(name = "姓名", orderNum = "2")
    private String name;
    @Excel(name = "年龄", orderNum = "3", suffix = "岁")
    private Integer age;
    @Excel(name = "生日", orderNum = "4", format = "yyyy年MM月dd日", width = 20)
    private Date birthday;
    @Excel(name = "状态", orderNum = "5", replace = {"激活_1", "未激活_0"})
    private String status;
    // @Excel(name = "爱好", orderNum = "6", width = 20)
    private List<String> hobby;
    @Excel(name = "爱好", orderNum = "6", width = 20)
    private String hobbyStr;
    @ExcelEntity(name = "card")
    private Card card;
    @Excel(name = "头像", type = 2, orderNum = "0", height = 40, width = 20)
    private String photo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getHobby() {
        return hobby;
    }

    public void setHobby(List<String> hobby) {
        this.hobby = hobby;
    }

    public String getHobbyStr() {
        StringBuilder sb = new StringBuilder();
        this.hobby.forEach(s -> sb.append(s).append("、"));
        return sb.deleteCharAt(sb.length() - 1).toString();
    }

    public void setHobbyStr(String hobbyStr) {
        this.hobbyStr = hobbyStr;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public Card getCard() {
        return card;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
