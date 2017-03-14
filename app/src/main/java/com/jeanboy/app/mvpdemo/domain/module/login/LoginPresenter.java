package com.jeanboy.app.mvpdemo.domain.module.login;

import com.jeanboy.app.mvpdemo.cache.Injection;
import com.jeanboy.app.mvpdemo.domain.base.BaseUseCase;
import com.jeanboy.app.mvpdemo.domain.usecase.TokenGetCacheInfoTask;
import com.jeanboy.app.mvpdemo.domain.usecase.TokenGetRemoteInfoTask;
import com.jeanboy.app.mvpdemo.domain.usecase.UserGetCacheInfoTask;
import com.jeanboy.app.mvpdemo.domain.usecase.UserGetRemoteInfoTask;

/**
 * Created by jeanboy on 2017/3/14.
 */

public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.View view;

    private TokenGetRemoteInfoTask tokenGetRemoteInfoTask = Injection.provideTokenGetRemoteInfoTask();
    private TokenGetCacheInfoTask tokenGetCacheInfoTask = Injection.provideTokenGetCacheInfoTask();

    private UserGetRemoteInfoTask userGetRemoteInfoTask = Injection.provideUserGetRemoteInfoTask();
    private UserGetCacheInfoTask userGetCacheInfoTask = Injection.provideUserGetCacheInfoTask();

    @Override
    public void attachView(LoginContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    @Override
    public void toLogin(String username, String password) {
        if (view == null) return;
        view.showProgress("正在登录，请稍候...");
        tokenGetRemoteInfoTask.setRequestValues(new TokenGetRemoteInfoTask.RequestValues(username, password));
        tokenGetRemoteInfoTask.setUseCaseCallback(new BaseUseCase.UseCaseCallback<TokenGetRemoteInfoTask.ResponseValue>() {
            @Override
            public void onSuccess(TokenGetRemoteInfoTask.ResponseValue response) {
                userGetRemoteInfoTask.setUseCaseCallback(new BaseUseCase.UseCaseCallback<UserGetRemoteInfoTask.ResponseValue>() {
                    @Override
                    public void onSuccess(UserGetRemoteInfoTask.ResponseValue response) {
                        view.dismissProgress();
                        view.onLoginSuccessful(response.getUserModel());
                    }

                    @Override
                    public void onError() {
                        view.dismissProgress();
                        view.showMessage("登陆失败，请重试！");
                        view.onLoginError();
                    }
                });
                userGetRemoteInfoTask.run();
            }

            @Override
            public void onError() {
                view.dismissProgress();
                view.showMessage("登陆失败，请重试！");
                view.onLoginError();
            }
        });
        tokenGetRemoteInfoTask.run();
    }

    @Override
    public void getUserCache() {
        if (view == null) return;

        tokenGetCacheInfoTask.setRequestValues(new TokenGetCacheInfoTask.RequestValues());
        tokenGetCacheInfoTask.setUseCaseCallback(new BaseUseCase.UseCaseCallback<TokenGetCacheInfoTask.ResponseValue>() {
            @Override
            public void onSuccess(TokenGetCacheInfoTask.ResponseValue response) {
                userGetCacheInfoTask.setRequestValues(new UserGetCacheInfoTask.RequestValues(response.getTokenModel().getUserId()));
                userGetCacheInfoTask.setUseCaseCallback(new BaseUseCase.UseCaseCallback<UserGetCacheInfoTask.ResponseValue>() {
                    @Override
                    public void onSuccess(UserGetCacheInfoTask.ResponseValue response) {
                        view.showUserCache(response.getUserModel());
                    }

                    @Override
                    public void onError() {

                    }
                });
                userGetCacheInfoTask.run();
            }

            @Override
            public void onError() {

            }
        });
        tokenGetCacheInfoTask.run();
    }
}
