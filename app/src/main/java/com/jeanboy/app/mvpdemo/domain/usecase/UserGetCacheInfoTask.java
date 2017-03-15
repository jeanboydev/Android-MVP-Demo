package com.jeanboy.app.mvpdemo.domain.usecase;

import android.support.annotation.NonNull;

import com.jeanboy.app.mvpdemo.cache.database.model.UserModel;
import com.jeanboy.app.mvpdemo.cache.source.repository.UserRepository;
import com.jeanboy.app.mvpdemo.cache.source.callback.SourceCallback;
import com.jeanboy.app.mvpdemo.domain.base.BaseUseCase;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by jeanboy on 2017/3/14.
 */

public class UserGetCacheInfoTask extends BaseUseCase<UserGetCacheInfoTask.RequestValues, UserGetCacheInfoTask.ResponseValue> {

    private final UserRepository userRepository;

    public UserGetCacheInfoTask(@NonNull UserRepository userRepository) {
        this.userRepository = checkNotNull(userRepository);
    }


    @Override
    protected void executeUseCase(RequestValues requestValues) {
        userRepository.get(requestValues.getUserId(), new SourceCallback<UserModel>() {
            @Override
            public void onLoaded(UserModel userModel) {
                getUseCaseCallback().onSuccess(new ResponseValue(userModel));
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
        private Long userId;

        public RequestValues(Long userId) {
            this.userId = userId;
        }

        public Long getUserId() {
            return userId;
        }
    }

    public static final class ResponseValue implements BaseUseCase.ResponseValue {
        private UserModel userModel;

        public ResponseValue(UserModel userModel) {
            this.userModel = userModel;
        }

        public UserModel getUserModel() {
            return userModel;
        }
    }
}
