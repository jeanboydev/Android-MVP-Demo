package com.jeanboy.app.mvpdemo.api.dao;

import com.jeanboy.app.mvpdemo.cache.database.model.TokenModel;
import com.jeanboy.app.mvpdemo.config.ApiConfig;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by jeanboy on 2017/3/13.
 */

public interface TokenDao {

    /**
     * http://www.xxx.com?client=android
     * body:
     *      username:xxx
     *      password:xxx
     *
     * @param client
     * @param username
     * @param password
     * @return
     */
    @FormUrlEncoded
    @POST(ApiConfig.PATH_TOKENS)
    Call<TokenModel> getToken(@Query("client") String client, @Field("username") String username, @Field("password") String password);


    /**
     * http://www.xxx.com?client=android
     * body:
     *      refresh_token:xxx
     *
     * @param client
     * @param refreshToken
     * @return
     */
    @FormUrlEncoded
    @POST(ApiConfig.PATH_TOKENS)
    Call<TokenModel> refreshToken(@Query("client") String client, @Field("refresh_token") String refreshToken);
}
