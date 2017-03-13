package com.jeanboy.app.mvpdemo.cache.source.remote;

import com.jeanboy.app.mvpdemo.api.impl.TokenDaoImpl;
import com.jeanboy.app.mvpdemo.cache.database.model.TokenModel;
import com.jeanboy.app.mvpdemo.cache.source.TokenDataSource;
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
    public Call<TokenModel> getToken(String username, String password, RequestCallback<TokenModel> callback) {
        return tokenDao.getToken(username, password, callback);
    }

    @Override
    public Call<TokenModel> refreshToken(String refreshToken, RequestCallback<TokenModel> callback) {
        return tokenDao.refreshToken(refreshToken, callback);
    }
}
