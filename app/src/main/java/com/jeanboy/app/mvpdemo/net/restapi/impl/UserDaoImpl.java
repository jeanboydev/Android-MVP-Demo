package com.jeanboy.app.mvpdemo.net.restapi.impl;

import com.jeanboy.app.mvpdemo.cache.source.UserDataSource;
import com.jeanboy.app.mvpdemo.component.handler.OkHttpHandler;
import com.jeanboy.app.mvpdemo.config.ApiConfig;
import com.jeanboy.app.mvpdemo.net.entity.UserEntity;
import com.jeanboy.app.mvpdemo.net.restapi.dao.UserDao;
import com.jeanboy.lib.common.manager.net.NetManager;
import com.jeanboy.lib.common.manager.net.RequestCallback;

import java.io.File;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;

/**
 * Created by jeanboy on 2017/2/10.
 */

public class UserDaoImpl implements UserDataSource.Remote {

    private UserDao userDao = (UserDao) NetManager.getInstance().getNetHandler().create(UserDao.class);

    @Override
    public Call<UserEntity> getInfo(String token, String id, RequestCallback<OkHttpHandler.ResponseData> callback) {
        Call<UserEntity> call = userDao.getInfo(token, id);
        NetManager.getInstance().getNetHandler().doBack(new OkHttpHandler.RequestParams(call), callback);
        return call;
    }

    @Override
    public Call<UserEntity> updateInfo(String token, String id, UserEntity user, RequestCallback<OkHttpHandler.ResponseData> callback) {
        //        Map<String, Object> params = new HashMap<>();
//        params.put("grantType", "phone_pass");
//        params.put("phone", username);
//        params.put("password", password);
//        RequestBody body = RequestBody.create(ApiConfig.TYPE_JSON, JSON.toJSONString(params));
        Call<UserEntity> call = userDao.updateInfo("Basic " + token, id, ApiConfig.TYPE_CLIENT, user);
        NetManager.getInstance().getNetHandler().doBack(new OkHttpHandler.RequestParams(call), callback);
        return call;
    }

    @Override
    public Call<String> uploadAvatar(String token, String id, File file, RequestCallback<OkHttpHandler.ResponseData> callback) {
        // create RequestBody instance from file
        RequestBody requestFile = RequestBody.create(ApiConfig.TYPE_DATA, file);

        // MultipartBody.Part is used to send also the actual file name
        MultipartBody.Part body = MultipartBody.Part.createFormData("picture", file.getName(), requestFile);

        // add another part within the multipart request
        String descriptionString = "hello, this is description speaking";
        RequestBody description = RequestBody.create(ApiConfig.TYPE_DATA, descriptionString);

        Call<String> call = userDao.uploadAvatar("Basic " + token, id, description, body);
        NetManager.getInstance().getNetHandler().doBack(new OkHttpHandler.RequestParams(call), callback);
        return call;
    }
}
