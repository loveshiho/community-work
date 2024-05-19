package com.akai.pojo;

import java.io.Serializable;

public class LoginUserVo implements Serializable {
    private String userName;
    private String password;
    private String code;
    private String uuid;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public LoginUserVo() {
    }

    public LoginUserVo(String userName, String password, String code, String uuid) {
        this.userName = userName;
        this.password = password;
        this.code = code;
        this.uuid = uuid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "LoginUserVo{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", code='" + code + '\'' +
                ", uuid='" + uuid + '\'' +
                '}';
    }
}
