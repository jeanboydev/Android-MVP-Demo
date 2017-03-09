package com.jeanboy.app.mvpdemo.architecture.repositories;

import com.jeanboy.app.mvpdemo.api.ApiManager;
import com.jeanboy.app.mvpdemo.model.bean.UserBean;
import com.jeanboy.lib.common.manager.database.DBManager;
import com.jeanboy.lib.common.manager.net.RequestCallback;

import java.util.LinkedHashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by jeanboy on 2017/3/9.
 */

public class UserRepository {

    private static UserRepository INSTANCE = null;

    boolean mCacheIsDirty = false;

    Map<String, UserBean> mCachedMap;


    public static UserRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UserRepository();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }


    public Call<UserBean> getInfo(String id, RequestCallback<UserBean> callback) {
        if (mCachedMap != null && !mCacheIsDirty) {
            callback.success(Response.success(mCachedMap.get(id)));
            return null;
        }

        if (mCacheIsDirty) {
            return getFromRemote(id, callback);
        } else {
            getFromLocal(id, callback);
        }
        return null;
    }

    private void getFromLocal(final String id, final RequestCallback<UserBean> callback) {
        DBManager.getInstance().getById(UserBean.class, Long.valueOf(id), new DBManager.Callback<UserBean>() {
            @Override
            public void onFinish(UserBean userBean) {
                if (userBean == null) {
                    getFromRemote(id, callback);
                } else {
                    refreshCache(userBean);
                    callback.success(Response.success(mCachedMap.get(id)));
                }
            }

        });
    }

    private Call<UserBean> getFromRemote(String id, RequestCallback<UserBean> callback) {
        return ApiManager.getInstance().userDao.getInfo(id, callback);
    }

    public void refresh() {
        mCacheIsDirty = true;
    }

    private void refreshCache(UserBean userBean) {
        if (mCachedMap == null) {
            mCachedMap = new LinkedHashMap<>();
        }
        mCachedMap.clear();
        mCachedMap.put(String.valueOf(userBean.getId()), userBean);
        mCacheIsDirty = false;
    }
}
