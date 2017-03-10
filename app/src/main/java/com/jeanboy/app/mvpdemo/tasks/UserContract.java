package com.jeanboy.app.mvpdemo.tasks;

import com.jeanboy.app.mvpdemo.base.BasePresenter;
import com.jeanboy.app.mvpdemo.base.BaseView;

/**
 * Created by jeanboy on 2017/3/10.
 */

public interface UserContract {

    interface View extends BaseView<Presenter> {

        void setLoadingIndicator(boolean active);
    }

    interface Presenter extends BasePresenter {

        void getUser(String userId);
    }
}
