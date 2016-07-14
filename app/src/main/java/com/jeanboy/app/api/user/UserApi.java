package com.jeanboy.app.api.user;

import com.jeanboy.app.model.bean.UserBean;
import com.jeanboy.manager.net.NetManager;

import retrofit2.Call;

/**
 * Created by Next on 2016/7/11.
 */
public class UserApi {

    private UserDao userDao;

    public UserApi() {
        userDao = NetManager.getInstance().create(UserDao.class);
    }

    public Call<UserBean> getInfo(String id) {
        return userDao.getInfo(id);
    }

    public Call<UserBean> logIn(String username, String password) {
        String client = "android";
        return userDao.logIn(client, username, password);
    }

    Call<UserBean> logOut(String username, String password) {
        return userDao.logOut(username, password);
    }

}
