package com.jeanboy.app.mvpdemo.config;

import okhttp3.MediaType;

/**
 * Created by Next on 2016/7/12.
 */
public class ApiConfig {

    public static final MediaType TYPE_JSON = MediaType.parse("application/json");
    public static final MediaType TYPE_DATA = MediaType.parse("multipart/form-data");

    public static final String TYPE_CLIENT = "android";

    public static final String PATH_USERS = "users";
    public static final String PATH_TOKENS = "tokens";


}
