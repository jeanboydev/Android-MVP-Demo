package com.jeanboy.app.model.sources.remote;

import com.jeanboy.app.api.ApiManager;
import com.jeanboy.app.model.bean.UserBean;
import com.jeanboy.manager.net.RequestCallback;

import retrofit2.Call;

/**
 * User有关的Api的具体实现
 * <p/>
 * Created by Next on 2016/7/5.
 */
public class UserRemoteDataSource implements UserRemote {

    private static UserRemoteDataSource INSTANCE;

    private UserRemoteDataSource() {
    }

    public static UserRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UserRemoteDataSource();
        }
        return INSTANCE;
    }


    @Override
    public Call<UserBean> logIn(String username, String password, final RequestCallback<UserBean> callback) {
        Call<UserBean> call = ApiManager.getInstance().userApi.logIn(username, password);
        ApiManager.getInstance().doBack(call, callback);
        return call;
    }

    @Override
    public Call<UserBean> logOut(String username, String password, final RequestCallback<UserBean> callback) {
        Call<UserBean> call = ApiManager.getInstance().userApi.logOut(username, password);
        ApiManager.getInstance().doBack(call, callback);
        return call;
    }

    @Override
    public Call<UserBean> getInfo(String id, final RequestCallback<UserBean> callback) {
        Call<UserBean> call = ApiManager.getInstance().userApi.getInfo(id);
        ApiManager.getInstance().doBack(call, callback);
        return call;
    }


}
