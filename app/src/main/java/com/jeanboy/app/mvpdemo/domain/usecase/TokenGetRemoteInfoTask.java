package com.jeanboy.app.mvpdemo.domain.usecase;

import android.support.annotation.NonNull;

import com.jeanboy.app.mvpdemo.cache.database.model.TokenModel;
import com.jeanboy.app.mvpdemo.cache.source.repository.TokenRepository;
import com.jeanboy.app.mvpdemo.domain.base.BaseUseCase;
import com.jeanboy.app.mvpdemo.net.entity.TokenEntity;
import com.jeanboy.app.mvpdemo.net.mapper.TokenModelDataMapper;
import com.jeanboy.lib.common.manager.net.RequestCallback;

import retrofit2.Call;
import retrofit2.Response;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by jeanboy on 2017/3/14.
 */

public class TokenGetRemoteInfoTask extends BaseUseCase<TokenGetRemoteInfoTask.RequestValues, TokenGetRemoteInfoTask.ResponseValue> {

    private final TokenRepository tokenRepository;

    private Call<TokenEntity> call;

    public TokenGetRemoteInfoTask(@NonNull TokenRepository tokenRepository) {
        this.tokenRepository = checkNotNull(tokenRepository);
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        call = tokenRepository.getToken(requestValues.getUsername(), requestValues.getPassword(),
                new RequestCallback<TokenEntity>() {
                    @Override
                    public void success(Response<TokenEntity> response) {
                        TokenEntity tokenEntity = response.body();
                        TokenModel tokenModel = new TokenModelDataMapper().transform(tokenEntity);
                        ResponseValue responseValue = new ResponseValue(tokenModel);
                        getUseCaseCallback().onSuccess(responseValue);
                    }

                    @Override
                    public void error(int code, String msg) {
                        getUseCaseCallback().onError();
                    }
                });
    }

    @Override
    protected void cancelUseCase() {
        if (call != null) {
            call.cancel();
        }
    }

    public static final class RequestValues implements BaseUseCase.RequestValues {
        private String username, password;

        public RequestValues(String username, String password) {
            this.username = username;
            this.password = password;
        }

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }
    }

    public static final class ResponseValue implements BaseUseCase.ResponseValue {
        private TokenModel tokenModel;

        public ResponseValue(TokenModel tokenModel) {
            this.tokenModel = tokenModel;
        }

        public TokenModel getTokenModel() {
            return tokenModel;
        }
    }

}
