package com.jeanboy.app.mvpdemo.tasks;

import com.jeanboy.app.mvpdemo.base.BasePresenter;
import com.jeanboy.app.mvpdemo.base.BaseView;
import com.jeanboy.app.mvpdemo.cache.database.model.UserModel;

/**
 * Created by jeanboy on 2017/3/10.
 */

public interface UserContract {

    interface View extends BaseView<Presenter> {

        void setLoadingIndicator(boolean active);

        void showGetUserError();

        void showUser(UserModel userModel);
    }

    interface Presenter extends BasePresenter {

        void getUser(String userId);
    }
}
