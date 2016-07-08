package com.jeanboy.app.model.sources.remote;

import com.jeanboy.app.model.bean.UserBean;

/**
 * User有关的Api的具体实现
 * <p>
 * Created by Next on 2016/7/5.
 */
public class UserRemoteDataSource implements UserRemote {

    private static UserRemoteDataSource INSTANCE;

    public static UserRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UserRemoteDataSource();
        }
        return INSTANCE;
    }


    @Override
    public void logIn(String username, String password, GetBack<UserBean> callback) {

    }

    @Override
    public void logOut(String username, String password, GetBack<UserBean> callback) {

    }

    @Override
    public void getInfo(String id, GetBack<UserBean> callback) {

    }
}
