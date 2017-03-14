package com.jeanboy.app.mvpdemo.domain.module.login;

import com.jeanboy.app.mvpdemo.cache.database.model.UserModel;
import com.jeanboy.app.mvpdemo.domain.base.BasePresenter;
import com.jeanboy.app.mvpdemo.domain.base.BaseView;

/**
 * Created by jeanboy on 2017/3/14.
 */

public class LoginContract {

    public interface View extends BaseView {

        void onLoginSuccessful(UserModel userModel);

        void onLoginError();

        void showUserCache(UserModel userModel);

    }

    interface Presenter extends BasePresenter<View> {

        void toLogin(String username, String password);

        void getUserCache();
    }
}
