package com.jeanboy.app.api;

import android.text.TextUtils;

import com.jeanboy.app.api.file.FileApi;
import com.jeanboy.app.api.user.UserApi;
import com.jeanboy.manager.net.RequestCallback;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Next on 2016/7/4.
 */
public class ApiManager {

    public UserApi userApi = new UserApi();
    public FileApi fileApi = new FileApi();

    private static ApiManager instance = null;

    private ApiManager() {
    }

    public static ApiManager getInstance() {
        if (instance == null) {
            synchronized (ApiManager.class) {
                if (instance == null) {
                    instance = new ApiManager();
                }
            }
        }
        return instance;
    }


    public <T> void doBack(Call<T> call, final RequestCallback<T> callback) {
        call.enqueue(new retrofit2.Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                if (response.isSuccessful()) {
                    callback.success(response);
                } else {
                    String msg = null;
                    try {
                        msg = response.errorBody().string();// TODO:处理自定义错误信息
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

    public <T> void doSyncBack(Call<T> call, RequestCallback<T> callback) {
        try {
            Response<T> response = call.execute();
            if (response.isSuccessful()) {
                callback.success(response);
            } else {
                String msg = null;
                try {
                    msg = response.errorBody().string();// TODO:处理自定义错误信息
                } catch (IOException e) {
                    callback.error(e.getMessage());
                }
                if (TextUtils.isEmpty(msg)) {
                    msg = response.message();
                }
                callback.error(msg);
            }
        } catch (IOException e) {
            e.printStackTrace();
            callback.error(e.getMessage());
        }
    }
}
