package com.jeanboy.app.mvpdemo.net.entity;

/**
 * Created by jeanboy on 2017/3/15.
 */

public class UserEntity {

    private Long userId;
    private String userName;
    private String userNick;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserNick() {
        return userNick;
    }

    public void setUserNick(String userNick) {
        this.userNick = userNick;
    }
}
