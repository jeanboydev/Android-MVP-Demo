package com.jeanboy.app.mvpdemo.cache.database.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by jeanboy on 2017/3/10.
 */
@Entity
public class TokenModel {

    @Id(autoincrement = true)
    private Long id;
    private Long userId;
    private Long expiresIn;
    private String refreshToken;
    private String accessToken;
    private Long createTime;

    public TokenModel() {
    }

    @Generated(hash = 1245094608)
    public TokenModel(Long id, Long userId, Long expiresIn, String refreshToken,
            String accessToken, Long createTime) {
        this.id = id;
        this.userId = userId;
        this.expiresIn = expiresIn;
        this.refreshToken = refreshToken;
        this.accessToken = accessToken;
        this.createTime = createTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Long expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
}
