package com.jeanboy.app.tasks.contract;

import com.jeanboy.app.base.BaseView;
import com.jeanboy.app.model.sources.remote.UserRemote;

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
