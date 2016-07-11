package com.jeanboy.app.api;

import com.jeanboy.app.model.bean.UserBean;

import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Next on 2016/7/11.
 */
public interface UserApi {

    @GET("/user/{id}")
    UserBean getInfo(@Path("id") String id);
}
