package com.jeanboy.app.model.repositories;

import com.jeanboy.app.model.bean.TokenBean;
import com.jeanboy.app.model.bean.UserBean;
import com.jeanboy.app.model.sources.UserDataSource;
import com.jeanboy.app.model.sources.local.UserLocalDataSource;
import com.jeanboy.app.model.sources.remote.UserRemote;
import com.jeanboy.app.model.sources.remote.UserRemoteDataSource;
import com.jeanboy.manager.net.RequestCallback;

import java.util.LinkedHashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Repository管理缓存的逻辑
 *
 * Created by Next on 2016/7/5.
 */
public class UserRepository implements UserDataSource, UserRemote {

    private static UserRepository INSTANCE = null;

    private final UserRemoteDataSource mRemoteDataSource;//网络数据操作

    private final UserLocalDataSource mLocalDataSource;//缓存操作

    boolean mCacheIsDirty = false;//是否需要重新获取数据

    Map<String, UserBean> mCachedMap;//数据缓存

    public UserRepository() {
        mRemoteDataSource = UserRemoteDataSource.getInstance();
        mLocalDataSource = UserLocalDataSource.getInstance();
    }

    public static UserRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UserRepository();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }


    @Override
    public Call<TokenBean> logIn(String username, String password, RequestCallback<TokenBean> callback) {
        return mRemoteDataSource.logIn(username, password, callback);
    }

    @Override
    public Call<UserBean> logOut(String username, String password, RequestCallback<UserBean> callback) {
        return mRemoteDataSource.logOut(username, password, callback);
    }

    @Override
    public Call<UserBean> getInfo(String id, RequestCallback<UserBean> callback) {
        if (mCachedMap != null && !mCacheIsDirty) {
            callback.success(Response.success(mCachedMap.get(id)));
            return null;
        }
        if (mCacheIsDirty) {//本地数据已过时
            getFromRemote(id, callback);
        } else {//读取本地数据
            UserBean userBean = mLocalDataSource.get(id);
            if (userBean == null) {//读取失败，获取远程信息
                return getFromRemote(id, callback);
            } else {
                refreshCache(userBean);
                callback.success(Response.success(mCachedMap.get(id)));
            }
        }
        return null;
    }

    @Override
    public void refresh() {
        mCacheIsDirty = true;
    }

    @Override
    public void delete(String id) {
        mLocalDataSource.delete(id);
    }


    public Call<UserBean> getFromRemote(String id, final RequestCallback<UserBean> callback) {
        return mRemoteDataSource.getInfo(id, new RequestCallback<UserBean>() {
            @Override
            public void success(Response<UserBean> response) {
                refreshCache(response.body());
                refreshLocalData(response.body());
                callback.success(response);
            }

            @Override
            public void error(String msg) {
                callback.error(msg);
            }
        });
    }

    /**
     * 刷新缓存的数据
     *
     * @param userBean
     */
    private void refreshCache(UserBean userBean) {
        if (mCachedMap == null) {
            mCachedMap = new LinkedHashMap<>();
        }
        mCachedMap.clear();
        mCachedMap.put(String.valueOf(userBean.getId()), userBean);
        mCacheIsDirty = false;
    }

    /**
     * 刷新本地数据
     *
     * @param userBean
     */
    private void refreshLocalData(UserBean userBean) {
        mLocalDataSource.clear();
        mLocalDataSource.save(userBean);
    }


}


