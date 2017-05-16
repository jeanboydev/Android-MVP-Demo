package com.jeanboy.app.mvpdemo.component.handler;

import android.text.TextUtils;

import com.jeanboy.app.mvpdemo.BuildConfig;
import com.jeanboy.app.mvpdemo.component.handler.converters.FastJsonConverterFactory;
import com.jeanboy.app.mvpdemo.config.AppConfig;
import com.jeanboy.lib.common.manager.net.NetHandler;
import com.jeanboy.lib.common.manager.net.RequestCallback;
import com.jeanboy.lib.common.manager.net.StatusCode;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by jeanboy on 2017/2/10.
 */

public class OkHttpHandler extends NetHandler<OkHttpHandler.RequestParams, OkHttpHandler.ResponseData> {

    private static Retrofit mRetrofit;
    private static OkHttpClient mOkHttpClient;
    private final static int CONNECT_TIMEOUT = 30;

    @Override
    public String getBaseUrl() {
        return AppConfig.SERVER_HOST;
    }

    @Override
    public <T> T create(Class<T> clazz) {
        return getRetrofit(getBaseUrl()).create(clazz);
    }

    @Override
    public void doBack(RequestParams requestValues, final RequestCallback<OkHttpHandler.ResponseData> callback) {
        requestValues.getParams().enqueue(new retrofit2.Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                if (response.isSuccessful()) {
                    ResponseData responseData = new ResponseData(response);
                    callback.onSuccess(responseData);
                } else {
                    String msg = null;
                    try {
                        msg = response.errorBody().string();
                    } catch (IOException e) {
                        callback.onError(response.code(), e.getMessage());
                    }
                    if (TextUtils.isEmpty(msg)) {
                        msg = response.message();
                    }
                    callback.onError(response.code(), msg);
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                callback.onError(StatusCode.CODE_UNKNOWN_EXCEPTION, t.getMessage());
            }
        });
    }

    @Override
    public void doSyncBack(RequestParams requestValues, RequestCallback<OkHttpHandler.ResponseData> callback) {
        try {
            Response response = requestValues.getParams().execute();
            if (response.isSuccessful()) {
                ResponseData responseData = new ResponseData(response);
                callback.onSuccess(responseData);
            } else {
                String msg = null;
                try {
                    msg = response.errorBody().string();
                } catch (IOException e) {
                    callback.onError(response.code(), e.getMessage());
                }
                if (TextUtils.isEmpty(msg)) {
                    msg = response.message();
                }
                callback.onError(response.code(), msg);
            }
        } catch (IOException e) {
            e.printStackTrace();
            callback.onError(StatusCode.CODE_UNKNOWN_EXCEPTION, e.getMessage());
        }
    }

    public static final class RequestParams implements NetHandler.RequestParams {

        private Call call;

        public RequestParams(Call call) {
            this.call = call;
        }

        public Call getParams() {
            return call;
        }
    }

    public static final class ResponseData implements NetHandler.ResponseData {

        private Response response;

        public ResponseData(Response response) {
            this.response = response;
        }

        public Response getData() {
            return response;
        }
    }

    /*------------------------配置环境------------------------------*/

    private Retrofit getRetrofit(String baseUrl) {
        if (mRetrofit == null) {
            if (mOkHttpClient == null) {
                mOkHttpClient = getOkHttpClient();
            }
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(FastJsonConverterFactory.create())
                    .client(mOkHttpClient)
                    .build();
        }
        return mRetrofit;
    }

    private OkHttpClient getOkHttpClient() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(BuildConfig.DEBUG ?
                HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
        if (mOkHttpClient == null) {
            mOkHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(loggingInterceptor)
                    .retryOnConnectionFailure(true)
                    .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                    .build();
        }
        return mOkHttpClient;
    }
}
