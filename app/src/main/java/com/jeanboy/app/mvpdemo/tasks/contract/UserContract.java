package com.jeanboy.app.mvpdemo.tasks.contract;

import com.jeanboy.app.mvpdemo.base.BaseView;
import com.jeanboy.app.mvpdemo.model.sources.remote.UserRemote;

/**
 * Created by Next on 2016/7/4.
 */
public interface UserContract {

    interface View extends BaseView {

        void showDialog(String msg);

    }


    interface Presenter extends UserRemote {

    }

}
