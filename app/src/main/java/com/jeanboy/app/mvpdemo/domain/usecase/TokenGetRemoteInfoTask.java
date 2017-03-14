package com.jeanboy.app.mvpdemo.domain.usecase;

import android.support.annotation.NonNull;

import com.jeanboy.app.mvpdemo.cache.database.model.TokenModel;
import com.jeanboy.app.mvpdemo.cache.source.TokenRepository;
import com.jeanboy.app.mvpdemo.domain.base.BaseUseCase;
import com.jeanboy.lib.common.manager.net.RequestCallback;

import retrofit2.Response;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by jeanboy on 2017/3/14.
 */

public class TokenGetRemoteInfoTask extends BaseUseCase<TokenGetRemoteInfoTask.RequestValues, TokenGetRemoteInfoTask.ResponseValue> {

    private final TokenRepository tokenRepository;

    public TokenGetRemoteInfoTask(@NonNull TokenRepository tokenRepository) {
        this.tokenRepository = checkNotNull(tokenRepository);
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        tokenRepository.getToken(requestValues.getUsername(), requestValues.getPassword(),
                new RequestCallback<TokenModel>() {
                    @Override
                    public void success(Response<TokenModel> response) {
                        ResponseValue responseValue = new ResponseValue(response.body());
                        getUseCaseCallback().onSuccess(responseValue);
                    }

                    @Override
                    public void error(int code, String msg) {
                        getUseCaseCallback().onError();
                    }
                });
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
