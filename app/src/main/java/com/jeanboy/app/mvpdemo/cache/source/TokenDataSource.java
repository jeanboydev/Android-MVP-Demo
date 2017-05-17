package com.jeanboy.app.mvpdemo.cache.source;

import com.jeanboy.app.mvpdemo.cache.database.model.TokenModel;
import com.jeanboy.app.mvpdemo.cache.source.base.BaseLocalDataSource;
import com.jeanboy.app.mvpdemo.cache.source.callback.SourceCallback;
import com.jeanboy.app.mvpdemo.net.entity.TokenEntity;
import com.jeanboy.lib.common.manager.net.RequestCallback;
import com.jeanboy.lib.common.manager.net.ResponseData;

import retrofit2.Call;

/**
 * Created by jeanboy on 2017/3/13.
 */

public class TokenDataSource {

    public interface Local extends BaseLocalDataSource<TokenModel> {

        void getToken(Long userId, SourceCallback<TokenModel> callback);
    }

    public interface Remote {

        Call<TokenEntity> getToken(String username, String password, RequestCallback<ResponseData<TokenEntity>> callback);

        Call<TokenEntity> refreshToken(String refreshToken, RequestCallback<ResponseData<TokenEntity>> callback);
    }
}
