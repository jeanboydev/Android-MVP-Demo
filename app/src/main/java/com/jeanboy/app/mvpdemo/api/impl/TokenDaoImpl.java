package com.jeanboy.app.mvpdemo.api.impl;

import com.jeanboy.app.mvpdemo.api.dao.TokenDao;
import com.jeanboy.app.mvpdemo.cache.database.model.TokenModel;
import com.jeanboy.app.mvpdemo.cache.source.TokenDataSource;
import com.jeanboy.app.mvpdemo.config.ApiConfig;
import com.jeanboy.lib.common.manager.net.NetManager;
import com.jeanboy.lib.common.manager.net.RequestCallback;

import retrofit2.Call;

/**
 * Created by jeanboy on 2017/3/13.
 */

public class TokenDaoImpl implements TokenDataSource.Remote {

    private TokenDao tokenDao = NetManager.getInstance().create(TokenDao.class);

    @Override
    public Call<TokenModel> getToken(String username, String password, RequestCallback<TokenModel> callback) {
        Call<TokenModel> call = tokenDao.getToken(ApiConfig.TYPE_CLIENT, username, password);
        NetManager.getInstance().doBack(call, callback);
        return call;
    }

    @Override
    public Call<TokenModel> refreshToken(String refreshToken, RequestCallback<TokenModel> callback) {
        Call<TokenModel> call = tokenDao.refreshToken(ApiConfig.TYPE_CLIENT, refreshToken);
        NetManager.getInstance().doBack(call, callback);
        return call;
    }
}
