package com.jeanboy.app.mvpdemo.cache.source.remote;

import com.jeanboy.app.mvpdemo.api.impl.UserDaoImpl;
import com.jeanboy.app.mvpdemo.cache.database.model.UserModel;
import com.jeanboy.app.mvpdemo.cache.source.UserDataSource;
import com.jeanboy.lib.common.manager.net.RequestCallback;

import java.io.File;

import retrofit2.Call;

/**
 * Created by jeanboy on 2017/3/9.
 */

public class UserRemoteDataSource implements UserDataSource.Remote {

    private UserDaoImpl userDao = new UserDaoImpl();

    private static UserRemoteDataSource INSTANCE;

    public static UserRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UserRemoteDataSource();
        }
        return INSTANCE;
    }


    @Override
    public Call<UserModel> getInfo(String token, String id, RequestCallback<UserModel> callback) {
        return userDao.getInfo(token, id, callback);
    }

    @Override
    public Call<UserModel> updateInfo(String token, String id, UserModel user, RequestCallback<UserModel> callback) {
        return userDao.updateInfo(token, id, user, callback);
    }

    @Override
    public Call<String> uploadAvatar(String token, String id, File file, RequestCallback<String> callback) {
        return userDao.uploadAvatar(token, id, file, callback);
    }
}
