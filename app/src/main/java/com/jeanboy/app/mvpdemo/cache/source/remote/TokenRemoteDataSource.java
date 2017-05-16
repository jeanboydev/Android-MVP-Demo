package com.jeanboy.app.mvpdemo.cache.source.remote;

import com.jeanboy.app.mvpdemo.cache.source.TokenDataSource;
import com.jeanboy.app.mvpdemo.component.handler.OkHttpHandler;
import com.jeanboy.app.mvpdemo.net.entity.TokenEntity;
import com.jeanboy.app.mvpdemo.net.restapi.impl.TokenDaoImpl;
import com.jeanboy.lib.common.manager.net.RequestCallback;

import retrofit2.Call;

/**
 * Created by jeanboy on 2017/3/13.
 */

public class TokenRemoteDataSource implements TokenDataSource.Remote {

    private TokenDaoImpl tokenDao = new TokenDaoImpl();

    private static TokenRemoteDataSource INSTANCE;

    public static TokenRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TokenRemoteDataSource();
        }
        return INSTANCE;
    }

    @Override
    public Call<TokenEntity> getToken(String username, String password, RequestCallback<OkHttpHandler.ResponseData> callback) {
        return tokenDao.getToken(username, password, callback);
    }

    @Override
    public Call<TokenEntity> refreshToken(String refreshToken, RequestCallback<OkHttpHandler.ResponseData> callback) {
        return tokenDao.refreshToken(refreshToken, callback);
    }
}
