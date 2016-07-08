package com.jeanboy.app.model.sources.remote;

import com.jeanboy.app.base.BaseRemote;
import com.jeanboy.app.model.bean.UserBean;

/**
 * Created by Next on 2016/7/7.
 */
public interface UserRemote extends BaseRemote {

    void logIn(String username, String password, GetBack<UserBean> callback);

    void logOut(String username, String password, GetBack<UserBean> callback);

    void getInfo(String id, GetBack<UserBean> callback);
}
