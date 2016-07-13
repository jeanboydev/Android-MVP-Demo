package com.jeanboy.app.model.sources.remote;

import com.jeanboy.app.model.bean.UserBean;
import com.jeanboy.manager.net.RequestCallback;

import retrofit2.Call;


/**
 * Created by Next on 2016/7/7.
 */
public interface UserRemote {

    Call<UserBean> logIn(String username, String password, RequestCallback<UserBean> callback);

    Call<UserBean> logOut(String username, String password, RequestCallback<UserBean> callback);

    Call<UserBean> getInfo(String id, RequestCallback<UserBean> callback);
}
