package com.jeanboy.app.tasks.presenter;

import android.support.annotation.NonNull;

import com.jeanboy.app.model.bean.UserBean;
import com.jeanboy.app.model.repositories.UserRepository;
import com.jeanboy.app.tasks.contract.UserContract;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Next on 2016/7/4.
 */
public class UserPresenter implements UserContract.Presenter {

    private UserRepository mUserRepository;

    private final UserContract.View mUserView;


    private boolean mFirstLoad = true;

    public UserPresenter(@NonNull UserContract.View userView) {
        mUserView = checkNotNull(userView, "userView cannot be null!");
        mUserRepository = UserRepository.getInstance();
    }


    @Override
    public void logIn(String username, String password, final GetBack<UserBean> callback) {
        mUserView.setLoadingIndicator(true);
        mUserRepository.logIn(username, password, new GetBack<UserBean>() {
            @Override
            public void success(UserBean userBean) {
                mUserRepository.refresh();//用户登陆，需要重新刷新缓存信息
                mUserView.setLoadingIndicator(false);
                callback.success(userBean);
            }

            @Override
            public void error(String msg) {
                mUserView.setLoadingIndicator(false);
                mUserView.toast(msg);
                callback.error(msg);
            }
        });
    }

    @Override
    public void logOut(String username, String password, final GetBack<UserBean> callback) {
        mUserView.setLoadingIndicator(true);
        mUserRepository.logOut(username, password, new GetBack<UserBean>() {
            @Override
            public void success(UserBean userBean) {
                mUserView.setLoadingIndicator(false);
                callback.success(userBean);
            }

            @Override
            public void error(String msg) {
                mUserView.setLoadingIndicator(false);
                mUserView.toast(msg);
                callback.error(msg);
            }
        });
    }

    @Override
    public void getInfo(String id, final GetBack<UserBean> callback) {
        mUserView.setLoadingIndicator(true);
        mUserRepository.getInfo(id, new GetBack<UserBean>() {
            @Override
            public void success(UserBean userBean) {
                mUserView.setLoadingIndicator(false);
                callback.success(userBean);
            }

            @Override
            public void error(String msg) {
                mUserView.setLoadingIndicator(false);
                mUserView.toast(msg);
                callback.error(msg);
            }
        });
    }
}