package com.jeanboy.app.mvpdemo.net.restapi.impl;

import com.jeanboy.app.mvpdemo.cache.source.TokenDataSource;
import com.jeanboy.app.mvpdemo.config.ApiConfig;
import com.jeanboy.app.mvpdemo.net.entity.TokenEntity;
import com.jeanboy.app.mvpdemo.net.restapi.dao.TokenDao;
import com.jeanboy.lib.common.manager.net.NetManager;
import com.jeanboy.lib.common.manager.net.RequestCallback;

import retrofit2.Call;

/**
 * Created by jeanboy on 2017/3/13.
 */

public class TokenDaoImpl implements TokenDataSource.Remote {

    private TokenDao tokenDao = NetManager.getInstance().create(TokenDao.class);

    @Override
    public Call<TokenEntity> getToken(String username, String password, RequestCallback<TokenEntity> callback) {
        Call<TokenEntity> call = tokenDao.getToken(ApiConfig.TYPE_CLIENT, username, password);
        NetManager.getInstance().doBack(call, callback);
        return call;
    }

    @Override
    public Call<TokenEntity> refreshToken(String refreshToken, RequestCallback<TokenEntity> callback) {
        Call<TokenEntity> call = tokenDao.refreshToken(ApiConfig.TYPE_CLIENT, refreshToken);
        NetManager.getInstance().doBack(call, callback);
        return call;
    }
}
