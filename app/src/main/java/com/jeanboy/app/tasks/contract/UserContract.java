package com.jeanboy.app.tasks.contract;

import com.jeanboy.app.base.BasePresenter;
import com.jeanboy.app.base.BaseView;

/**
 * Created by Next on 2016/7/4.
 */
public interface UserContract {

    interface View extends BaseView<Presenter> {

        void showDialog(String msg);
    }


    interface Presenter extends BasePresenter {

        void login(String username, String password);

        void getInfo(long id);
    }

}
