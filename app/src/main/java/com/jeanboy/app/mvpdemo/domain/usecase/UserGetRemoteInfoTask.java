package com.jeanboy.app.mvpdemo.domain.usecase;

import android.support.annotation.NonNull;

import com.jeanboy.app.mvpdemo.cache.database.model.UserModel;
import com.jeanboy.app.mvpdemo.cache.source.UserRepository;
import com.jeanboy.app.mvpdemo.domain.base.BaseUseCase;
import com.jeanboy.lib.common.manager.net.RequestCallback;

import retrofit2.Response;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by jeanboy on 2017/3/10.
 */

public class UserGetRemoteInfoTask extends BaseUseCase<UserGetRemoteInfoTask.RequestValues, UserGetRemoteInfoTask.ResponseValue> {

    private final UserRepository userRepository;

    public UserGetRemoteInfoTask(@NonNull UserRepository userRepository) {
        this.userRepository = checkNotNull(userRepository);
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        String userId = requestValues.getUserId();
        String accessToken = requestValues.getAccessToken();
        userRepository.getInfo(accessToken, userId, new RequestCallback<UserModel>() {
            @Override
            public void success(Response<UserModel> response) {
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
        private String userId;
        private String accessToken;

        public RequestValues(String userId, String accessToken) {
            this.userId = userId;
            this.accessToken = accessToken;
        }

        public String getUserId() {
            return userId;
        }

        public String getAccessToken() {
            return accessToken;
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
