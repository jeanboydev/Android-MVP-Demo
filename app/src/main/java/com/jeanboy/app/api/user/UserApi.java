package com.jeanboy.app.api.user;

import com.alibaba.fastjson.JSON;
import com.jeanboy.app.api.BaseApi;
import com.jeanboy.app.model.bean.TokenBean;
import com.jeanboy.app.model.bean.UserBean;
import com.jeanboy.manager.net.NetManager;

import java.util.HashMap;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;

/**
 * Created by Next on 2016/7/11.
 */
public class UserApi extends BaseApi {

    private UserDao userDao = NetManager.getInstance().create(UserDao.class);

    public Call<UserBean> getInfo(String id) {
        return userDao.getInfo(id);
    }

    public Call<TokenBean> logIn(String username, String password) {
        String client = "android";
        Map<String, Object> params = new HashMap<>();
        params.put("grantType", "phone_pass");
        params.put("phone", username);
        params.put("password", password);
        RequestBody body = RequestBody.create(defaultType, JSON.toJSONString(params));
        return userDao.logIn(client, body);
    }

    public Call<UserBean> logOut(String username, String password) {
        return userDao.logOut(username, password);
    }

}
