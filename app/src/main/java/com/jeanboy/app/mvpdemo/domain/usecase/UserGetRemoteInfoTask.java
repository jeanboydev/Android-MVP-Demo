package com.jeanboy.app.mvpdemo.domain.usecase;

import android.support.annotation.NonNull;

import com.jeanboy.app.mvpdemo.cache.database.model.UserModel;
import com.jeanboy.app.mvpdemo.cache.source.repository.UserRepository;
import com.jeanboy.app.mvpdemo.component.handler.OkHttpHandler;
import com.jeanboy.app.mvpdemo.domain.base.BaseUseCase;
import com.jeanboy.app.mvpdemo.net.entity.UserEntity;
import com.jeanboy.app.mvpdemo.net.mapper.UserModelDataMapper;
import com.jeanboy.lib.common.manager.net.RequestCallback;

import retrofit2.Call;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by jeanboy on 2017/3/10.
 */

public class UserGetRemoteInfoTask extends BaseUseCase<UserGetRemoteInfoTask.RequestValues, UserGetRemoteInfoTask.ResponseValue> {

    private final UserRepository userRepository;

    private Call<UserEntity> call;

    public UserGetRemoteInfoTask(@NonNull UserRepository userRepository) {
        this.userRepository = checkNotNull(userRepository);
    }

    @Override
    protected void executeUseCase(final RequestValues requestValues) {
        String userId = requestValues.getUserId();
        String accessToken = requestValues.getAccessToken();
        call = userRepository.getInfo(accessToken, userId, new RequestCallback<OkHttpHandler.ResponseData>() {
            @Override
            public void onSuccess(OkHttpHandler.ResponseData response) {
                UserEntity userEntity = (UserEntity) response.getData().body();
                UserModel userModel = new UserModelDataMapper().transform(userEntity);
                ResponseValue responseValue = new ResponseValue(userModel);
                getUseCaseCallback().onSuccess(responseValue);
            }

            @Override
            public void onError(int code, String msg) {
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
