package com.jeanboy.app.mvpdemo.cache.source.remote;

import com.jeanboy.app.mvpdemo.cache.source.UserDataSource;
import com.jeanboy.app.mvpdemo.component.handler.OkHttpHandler;
import com.jeanboy.app.mvpdemo.net.entity.UserEntity;
import com.jeanboy.app.mvpdemo.net.restapi.impl.UserDaoImpl;
import com.jeanboy.lib.common.manager.net.NetHandler;
import com.jeanboy.lib.common.manager.net.RequestCallback;
import com.jeanboy.lib.common.manager.net.ResponseData;

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
    public Call<UserEntity> getInfo(String token, String id, RequestCallback<ResponseData<UserEntity>> callback) {
        return userDao.getInfo(token, id, callback);
    }

    @Override
    public Call<UserEntity> updateInfo(String token, String id, UserEntity user, RequestCallback<ResponseData<UserEntity>> callback) {
        return userDao.updateInfo(token, id, user, callback);
    }

    @Override
    public Call<String> uploadAvatar(String token, String id, File file, RequestCallback<ResponseData<String>> callback) {
        return userDao.uploadAvatar(token, id, file, callback);
    }
}
