package com.jeanboy.app.model.repositories;

import com.jeanboy.app.model.bean.UserBean;
import com.jeanboy.app.model.sources.UserDataSource;
import com.jeanboy.app.model.sources.local.UserLocalDataSource;
import com.jeanboy.app.model.sources.remote.UserRemote;
import com.jeanboy.app.model.sources.remote.UserRemoteDataSource;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Next on 2016/7/5.
 */
public class UserRepository implements UserDataSource, UserRemote {

    private static UserRepository INSTANCE = null;

    private final UserRemoteDataSource mRemoteDataSource;

    private final UserLocalDataSource mLocalDataSource;

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
    public void logIn(String username, String password, GetBack<UserBean> callback) {
        mRemoteDataSource.logIn(username, password, callback);
    }

    @Override
    public void logOut(String username, String password, GetBack<UserBean> callback) {
        mRemoteDataSource.logOut(username, password, callback);
    }

    @Override
    public void getInfo(String id, GetBack<UserBean> callback) {
        if (mCachedMap != null && !mCacheIsDirty) {
            callback.success(mCachedMap.get(id));
            return;
        }
        if (mCacheIsDirty) {//本地数据已过时
            getFromRemote(id, callback);
        } else {//读取本地数据
            UserBean userBean = mLocalDataSource.get(id);
            if (userBean == null) {//读取失败，获取远程信息
                getFromRemote(id, callback);
            } else {
                refreshCache(userBean);
                callback.success(userBean);
            }
        }
    }

    @Override
    public void refresh() {
        mCacheIsDirty = true;
    }

    @Override
    public void delete(String id) {
        mLocalDataSource.delete(id);
    }


    public void getFromRemote(String id, final GetBack<UserBean> callback) {
        mRemoteDataSource.getInfo(id, new GetBack<UserBean>() {
            @Override
            public void success(UserBean userBean) {
                refreshCache(userBean);
                refreshLocalData(userBean);
                callback.success(userBean);
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


