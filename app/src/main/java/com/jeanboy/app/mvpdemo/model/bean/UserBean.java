package com.jeanboy.app.mvpdemo.model.bean;

/**
 * Created by Next on 2016/7/4.
 */
public class UserBean  {

    private long id;
    private String username;
    private String password;
    private String nickname;
    private FileBean avatar;

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public FileBean getAvatar() {
        return avatar;
    }

    public void setAvatar(FileBean avatar) {
        this.avatar = avatar;
    }
}
