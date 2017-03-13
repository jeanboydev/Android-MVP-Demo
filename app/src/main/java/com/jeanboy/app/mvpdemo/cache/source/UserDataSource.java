package com.jeanboy.app.mvpdemo.cache.source;

import com.jeanboy.app.mvpdemo.cache.database.model.UserModel;
import com.jeanboy.app.mvpdemo.cache.source.base.BaseLocalDataSource;
import com.jeanboy.lib.common.manager.net.RequestCallback;

import java.io.File;

import retrofit2.Call;

/**
 * Created by jeanboy on 2017/3/9.
 */

public class UserDataSource {


    public interface Local extends BaseLocalDataSource<UserModel> {

    }

    public interface Remote {

        Call<UserModel> getInfo(String token, String id, RequestCallback<UserModel> callback);

        Call<UserModel> updateInfo(String token, String id, UserModel user, RequestCallback<UserModel> callback);

        Call<String> uploadAvatar(String token, String id, File file, RequestCallback<String> callback);
    }
}
