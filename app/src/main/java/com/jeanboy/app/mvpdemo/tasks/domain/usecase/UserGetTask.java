package com.jeanboy.app.mvpdemo.tasks.domain.usecase;

import android.support.annotation.NonNull;

import com.jeanboy.app.mvpdemo.cache.database.model.UserModel;
import com.jeanboy.app.mvpdemo.cache.source.UserRepository;
import com.jeanboy.app.mvpdemo.cache.source.callback.SourceCallback;
import com.jeanboy.app.mvpdemo.tasks.domain.UseCase;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by jeanboy on 2017/3/10.
 */

public class UserGetTask extends UseCase<UserGetTask.RequestValues, UserGetTask.ResponseValue> {

    private final UserRepository userRepository;

    public UserGetTask(@NonNull UserRepository userRepository) {
        this.userRepository = checkNotNull(userRepository);
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        String userId = requestValues.getUserId();

        userRepository.getUser(Long.valueOf(userId), new SourceCallback<UserModel>() {
            @Override
            public void onLoaded(UserModel userModel) {
                ResponseValue responseValue = new ResponseValue(userModel);
                getUseCaseCallback().onSuccess(responseValue);
            }

            @Override
            public void onDataNotAvailable() {
                getUseCaseCallback().onError();
            }
        });
    }

    public static final class RequestValues implements UseCase.RequestValues {
        private String userId;

        public RequestValues(String userId) {
            this.userId = userId;
        }

        public String getUserId() {
            return userId;
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue {
        private UserModel userModel;

        public ResponseValue(UserModel userModel) {
            this.userModel = userModel;
        }

        public UserModel getUserModel() {
            return userModel;
        }
    }
}
