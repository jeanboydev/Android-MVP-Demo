package com.jeanboy.app.api;

import android.text.TextUtils;

import com.jeanboy.app.api.impl.UserImpl;
import com.jeanboy.manager.net.RequestCallback;
import com.jeanboy.manager.net.NetManager;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Next on 2016/7/4.
 */
public class ApiManager {

    public UserImpl userApi;

    private static ApiManager instance;

    private ApiManager() {
        userApi = NetManager.getInstance().create(UserImpl.class);
    }

    public static ApiManager getInstance() {
        if (instance == null) {
            instance = new ApiManager();
        }
        return instance;
    }


    public <T> void doBack(Call<T> call, final RequestCallback<T> callback) {
        call.enqueue(new retrofit2.Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                if (response.code() >= 200 && response.code() < 300) {
                    callback.success(response);
                } else {
                    String msg = null;
                    try {
                        msg = response.errorBody().string();// TODO: 2016/7/13 处理自定义错误信息
                    } catch (IOException e) {
                        callback.error(e.getMessage());
                    }
                    if (TextUtils.isEmpty(msg)) {
                        msg = response.message();
                    }
                    callback.error(msg);
                }

            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                callback.error(t.getMessage());
            }
        });
    }
}
