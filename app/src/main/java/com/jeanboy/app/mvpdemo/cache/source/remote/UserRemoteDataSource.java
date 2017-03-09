package com.jeanboy.app.mvpdemo.cache.source.remote;

import android.support.annotation.NonNull;

import com.jeanboy.app.mvpdemo.cache.database.model.UserModel;
import com.jeanboy.app.mvpdemo.cache.source.UserDataSource;
import com.jeanboy.app.mvpdemo.cache.source.callback.SourceCallback;

import java.util.List;

/**
 * Created by jeanboy on 2017/3/9.
 */

public class UserRemoteDataSource implements UserDataSource {


    private static UserRemoteDataSource INSTANCE;

    public static UserRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UserRemoteDataSource();
        }
        return INSTANCE;
    }

    @Override
    public void getUsers(@NonNull SourceCallback<List<UserModel>> callback) {
//        ApiManager.getInstance().userDao.getInfo()
    }

    @Override
    public void getUser(@NonNull Long userId, @NonNull SourceCallback<UserModel> callback) {
//        ApiManager.getInstance().userDao.getInfo()
    }

    @Override
    public void saveUser(@NonNull UserModel userModel) {

    }

    @Override
    public void refreshUsers() {

    }

    @Override
    public void deleteAllUser() {

    }

    @Override
    public void deleteUser(@NonNull UserModel userModel) {

    }
}
