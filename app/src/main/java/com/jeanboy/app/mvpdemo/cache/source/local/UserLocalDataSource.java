package com.jeanboy.app.mvpdemo.cache.source.local;

import com.jeanboy.app.mvpdemo.cache.database.model.UserModel;
import com.jeanboy.app.mvpdemo.cache.source.UserDataSource;
import com.jeanboy.app.mvpdemo.cache.source.callback.SourceCallback;
import com.jeanboy.lib.common.manager.database.DBManager;

import java.util.List;

/**
 * Created by jeanboy on 2017/3/9.
 */

public class UserLocalDataSource implements UserDataSource.Local {


    private static UserLocalDataSource INSTANCE;

    public static UserLocalDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UserLocalDataSource();
        }
        return INSTANCE;
    }

    @Override
    public void save(UserModel userModel) {
        DBManager.getInstance().save(userModel, null);
    }

    @Override
    public void get(Long id, final SourceCallback<UserModel> callback) {
        DBManager.getInstance().getById(UserModel.class, id, new DBManager.Callback<UserModel>() {
            @Override
            public void onFinish(UserModel userModel) {
                if (userModel == null) {
                    callback.onDataNotAvailable();
                    return;
                }
                callback.onLoaded(userModel);
            }
        });
    }

    @Override
    public void getAll(final SourceCallback<List<UserModel>> callback) {
        DBManager.getInstance().getAll(UserModel.class, new DBManager.Callback<List<UserModel>>() {
            @Override
            public void onFinish(List<UserModel> userModels) {
                if (userModels == null) {
                    callback.onDataNotAvailable();
                    return;
                }
                callback.onLoaded(userModels);
            }
        });
    }

    @Override
    public void delete(UserModel userModel) {
        DBManager.getInstance().delete(userModel, null);
    }

    @Override
    public void deleteAll() {
        DBManager.getInstance().deleteAll(UserModel.class, null);
    }
}
