package com.jeanboy.app.mvpdemo.tasks;

import android.support.annotation.NonNull;

import com.jeanboy.app.mvpdemo.cache.Injection;
import com.jeanboy.app.mvpdemo.tasks.domain.UseCase;
import com.jeanboy.app.mvpdemo.tasks.domain.usecase.UserGetTask;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by jeanboy on 2017/3/10.
 */

public class UserPresenter implements UserContract.Presenter {

    private final UserContract.View userView;

    private UserGetTask userGetTask = Injection.provideUserGetTask();

    public UserPresenter(@NonNull UserContract.View userView) {
        this.userView = checkNotNull(userView);
    }

    private boolean mFirstLoad = true;

    @Override
    public void start() {

        // TODO: 2017/3/10 do something...
        mFirstLoad = false;
    }

    @Override
    public void getUser(String userId) {
        userView.setLoadingIndicator(true);

        userGetTask.setUseCaseCallback(new UseCase.UseCaseCallback<UserGetTask.ResponseValue>() {
            @Override
            public void onSuccess(UserGetTask.ResponseValue response) {
                userView.setLoadingIndicator(false);
                userView.showUser(response.getUserModel());
            }

            @Override
            public void onError() {
                userView.setLoadingIndicator(false);
                userView.showGetUserError();
            }
        });
        userGetTask.run();
    }
}
