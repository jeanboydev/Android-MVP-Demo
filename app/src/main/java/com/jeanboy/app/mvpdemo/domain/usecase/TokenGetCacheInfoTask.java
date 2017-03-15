package com.jeanboy.app.mvpdemo.domain.usecase;

import android.support.annotation.NonNull;

import com.jeanboy.app.mvpdemo.cache.database.model.TokenModel;
import com.jeanboy.app.mvpdemo.cache.source.repository.TokenRepository;
import com.jeanboy.app.mvpdemo.cache.source.callback.SourceCallback;
import com.jeanboy.app.mvpdemo.domain.base.BaseUseCase;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by jeanboy on 2017/3/14.
 */

public class TokenGetCacheInfoTask extends BaseUseCase<TokenGetCacheInfoTask.RequestValues, TokenGetCacheInfoTask.ResponseValue> {

    private final TokenRepository tokenRepository;

    public TokenGetCacheInfoTask(@NonNull TokenRepository tokenRepository) {
        this.tokenRepository = checkNotNull(tokenRepository);
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        tokenRepository.getAll(new SourceCallback<List<TokenModel>>() {
            @Override
            public void onLoaded(List<TokenModel> tokenModels) {
                if (tokenModels.isEmpty()) {
                    getUseCaseCallback().onError();
                    return;
                }
                getUseCaseCallback().onSuccess(new ResponseValue(tokenModels.get(0)));
            }

            @Override
            public void onDataNotAvailable() {
                getUseCaseCallback().onError();
            }
        });
    }

    @Override
    protected void cancelUseCase() {

    }

    public static final class RequestValues implements BaseUseCase.RequestValues {
        public RequestValues() {
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
