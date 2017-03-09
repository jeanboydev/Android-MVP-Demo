package com.jeanboy.app.mvpdemo.api;

import com.jeanboy.app.mvpdemo.api.impl.UserDaoImpl;

/**
 * Created by jeanboy on 2017/2/12.
 */

public class ApiManager {

    public UserDaoImpl userDao = new UserDaoImpl();

    private static class SingletonHolder {
        public static ApiManager instance = new ApiManager();
    }

    public static ApiManager getInstance() {
        return SingletonHolder.instance;
    }

    private ApiManager() {
    }
}
