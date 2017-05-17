package com.jeanboy.app.mvpdemo.net.restapi.impl;

import com.jeanboy.app.mvpdemo.cache.source.TokenDataSource;
import com.jeanboy.app.mvpdemo.component.handler.OkHttpHandler;
import com.jeanboy.app.mvpdemo.component.handler.OkHttpManager;
import com.jeanboy.app.mvpdemo.config.ApiConfig;
import com.jeanboy.app.mvpdemo.net.entity.TokenEntity;
import com.jeanboy.app.mvpdemo.net.restapi.dao.TokenDao;
import com.jeanboy.lib.common.manager.net.NetManager;
import com.jeanboy.lib.common.manager.net.RequestCallback;
import com.jeanboy.lib.common.manager.net.RequestParams;
import com.jeanboy.lib.common.manager.net.ResponseData;

import retrofit2.Call;

/**
 * Created by jeanboy on 2017/3/13.
 */

public class TokenDaoImpl implements TokenDataSource.Remote {

    private OkHttpHandler<TokenEntity> handler = new OkHttpHandler<>();
    private TokenDao tokenDao = OkHttpManager.getInstance().create(TokenDao.class);

    @Override
    public Call<TokenEntity> getToken(String username, String password, RequestCallback<ResponseData<TokenEntity>> callback) {
        Call<TokenEntity> call = tokenDao.getToken(ApiConfig.TYPE_CLIENT, username, password);
        NetManager.getInstance().doBack(handler, new RequestParams<>(call), callback);
        return call;
    }

    @Override
    public Call<TokenEntity> refreshToken(String refreshToken, RequestCallback<ResponseData<TokenEntity>> callback) {
        Call<TokenEntity> call = tokenDao.refreshToken(ApiConfig.TYPE_CLIENT, refreshToken);
        NetManager.getInstance().doBack(handler, new RequestParams<>(call), callback);
        return call;
    }
}
