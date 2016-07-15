package com.jeanboy.app.model.bean;

import com.jeanboy.manager.database.AppDataBase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ForeignKey;
import com.raizlabs.android.dbflow.annotation.ModelContainer;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by Next on 2016/7/4.
 */
@ModelContainer
@Table(database = AppDataBase.class)
public class UserBean extends BaseModel {

    @PrimaryKey(autoincrement = false)
    private long id;
    @Column
    private String username;
    @Column
    private String password;
    @Column
    private String nickname;
    @ForeignKey(saveForeignKeyModel = true)
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
