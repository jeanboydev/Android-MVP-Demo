package com.jeanboy.app.mvpdemo.cache.source;

import android.support.annotation.NonNull;

import com.jeanboy.app.mvpdemo.cache.database.model.UserModel;
import com.jeanboy.app.mvpdemo.cache.source.base.BaseRepository;
import com.jeanboy.app.mvpdemo.cache.source.callback.SourceCallback;
import com.jeanboy.app.mvpdemo.cache.source.local.UserLocalDataSource;
import com.jeanboy.app.mvpdemo.cache.source.remote.UserRemoteDataSource;
import com.jeanboy.lib.common.manager.net.RequestCallback;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by jeanboy on 2017/3/9.
 */

public class UserRepository implements BaseRepository, UserDataSource.Local, UserDataSource.Remote {

    private static UserRepository INSTANCE = null;

    Map<String, UserModel> mCacheMap;

    boolean mCacheIsDirty = false;

    private final UserLocalDataSource userLocalDataSource;
    private final UserRemoteDataSource userRemoteDataSource;

    public UserRepository(@NonNull UserLocalDataSource userLocalDataSource,
                          @NonNull UserRemoteDataSource userRemoteDataSource) {
        this.userLocalDataSource = checkNotNull(userLocalDataSource);
        this.userRemoteDataSource = checkNotNull(userRemoteDataSource);
    }

    public static UserRepository getInstance(UserLocalDataSource userLocalDataSource,
                                             UserRemoteDataSource userRemoteDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new UserRepository(userLocalDataSource, userRemoteDataSource);
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    /**
     * 标识本地缓存已失效
     */
    @Override
    public void refresh() {
        mCacheIsDirty = true;
    }

    @Override
    public void save(@NonNull UserModel userModel) {
        checkNotNull(userModel);
        userLocalDataSource.save(userModel);
    }

    @Override
    public void get(@NonNull final Long id, @NonNull final SourceCallback<UserModel> callback) {
        checkNotNull(id);
        checkNotNull(callback);

        UserModel userModel = getFromMemory(String.valueOf(id));
        //内存缓存没有失效，优先从内存取出
        if (userModel != null && !mCacheIsDirty) {
            callback.onLoaded(userModel);
            return;
        }

        if (mCacheIsDirty) {//本地失效，从服务器获取
            getUserFromRemote(id, callback);
        } else {//本地未失效，从数据库读取
            userLocalDataSource.get(id, new SourceCallback<UserModel>() {
                @Override
                public void onLoaded(UserModel userModel) {
                    refreshCache(userModel);
                    callback.onLoaded(userModel);
                }

                @Override
                public void onDataNotAvailable() {//数据库没有数据，从服务器获取
                    getUserFromRemote(id, callback);
                }
            });
        }
    }

    @Override
    public void getAll(@NonNull SourceCallback<List<UserModel>> callback) {
        checkNotNull(callback);
        userLocalDataSource.getAll(callback);
    }

    @Override
    public void delete(@NonNull UserModel userModel) {
        checkNotNull(userModel);
        userLocalDataSource.delete(userModel);
    }

    @Override
    public void deleteAll() {
        userLocalDataSource.deleteAll();
    }

    @Override
    public Call<UserModel> getInfo(@NonNull String token, @NonNull String id,
                                   @NonNull RequestCallback<UserModel> callback) {
        checkNotNull(token);
        checkNotNull(id);
        checkNotNull(callback);
        return userRemoteDataSource.getInfo(token, id, callback);
    }

    @Override
    public Call<UserModel> updateInfo(@NonNull String token, @NonNull String id, UserModel user,
                                      @NonNull RequestCallback<UserModel> callback) {
        checkNotNull(token);
        checkNotNull(id);
        checkNotNull(user);
        checkNotNull(callback);
        return userRemoteDataSource.updateInfo(token, id, user, callback);
    }

    @Override
    public Call<String> uploadAvatar(@NonNull String token, @NonNull String id, @NonNull File file,
                                     @NonNull RequestCallback<String> callback) {
        checkNotNull(token);
        checkNotNull(id);
        checkNotNull(file);
        checkNotNull(callback);
        return userRemoteDataSource.uploadAvatar(token, id, file, callback);
    }


    private void getUserFromRemote(Long id, @NonNull final SourceCallback<UserModel> callback) {
        checkNotNull(callback);

        String token = "";// TODO: 2017/3/13 获取token
        getInfo(token, String.valueOf(id), new RequestCallback<UserModel>() {
            @Override
            public void success(Response<UserModel> response) {
                UserModel userModel = response.body();
                refreshCache(userModel);
                refreshLocalDataSource(userModel);
                callback.onLoaded(userModel);
            }

            @Override
            public void error(int code, String msg) {
                callback.onDataNotAvailable();
            }
        });
    }

    private void refreshCache(UserModel userModel) {
        if (mCacheMap == null) {
            mCacheMap = new LinkedHashMap<>();
        }
        mCacheMap.clear();
        mCacheMap.put(String.valueOf(userModel.getId()), userModel);
        mCacheIsDirty = false;
    }

    private void refreshLocalDataSource(UserModel userModel) {
        userLocalDataSource.deleteAll();
        userLocalDataSource.save(userModel);
    }

    private UserModel getFromMemory(String key) {
        if (mCacheMap == null) {
            mCacheMap = new LinkedHashMap<>();
        }
        return mCacheMap.get(key);
    }
}
