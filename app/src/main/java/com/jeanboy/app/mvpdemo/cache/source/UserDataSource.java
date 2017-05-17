package com.jeanboy.app.mvpdemo.cache.source;

import com.jeanboy.app.mvpdemo.cache.database.model.UserModel;
import com.jeanboy.app.mvpdemo.cache.source.base.BaseLocalDataSource;
import com.jeanboy.app.mvpdemo.net.entity.UserEntity;
import com.jeanboy.lib.common.manager.net.RequestCallback;
import com.jeanboy.lib.common.manager.net.ResponseData;

import java.io.File;

import retrofit2.Call;

/**
 * Created by jeanboy on 2017/3/9.
 */

public class UserDataSource {


    public interface Local extends BaseLocalDataSource<UserModel> {

    }

    public interface Remote {

        Call<UserEntity> getInfo(String token, String id, RequestCallback<ResponseData<UserEntity>> callback);

        Call<UserEntity> updateInfo(String token, String id, UserEntity user, RequestCallback<ResponseData<UserEntity>> callback);

        Call<String> uploadAvatar(String token, String id, File file, RequestCallback<ResponseData<String>> callback);
    }
}
