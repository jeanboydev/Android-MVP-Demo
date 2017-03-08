package com.jeanboy.app.mvpdemo.api.impl;

import com.alibaba.fastjson.JSON;
import com.jeanboy.lib.common.manager.net.NetManager;
import com.jeanboy.app.mvpdemo.api.ApiConfig;
import com.jeanboy.lib.common.manager.net.RequestCallback;
import com.jeanboy.app.mvpdemo.api.dao.UserDao;

import java.util.HashMap;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;

/**
 * Created by jeanboy on 2017/2/10.
 */

public class UserDaoImpl {

    private UserDao userDao = NetManager.getInstance().create(UserDao.class);

    public Call<String> getInfo(String id, RequestCallback<String> callback) {
        Call<String> call = userDao.getInfo(id);
        NetManager.getInstance().doBack(call, callback);
        return call;
    }

    public Call<String> logIn(String username, String password, RequestCallback<String> callback) {
        String client = "android";
        Map<String, Object> params = new HashMap<>();
        params.put("grantType", "phone_pass");
        params.put("phone", username);
        params.put("password", password);
        RequestBody body = RequestBody.create(ApiConfig.TYPE_JSON, JSON.toJSONString(params));
        Call<String> call = userDao.logIn(client, body);
        NetManager.getInstance().doBack(call, callback);
        return call;
    }

    public Call<String> logOut(String username, String password, RequestCallback<String> callback) {
        Call<String> call = userDao.logOut(username, password);
        NetManager.getInstance().doBack(userDao.logOut(username, password), callback);
        return call;
    }
}
