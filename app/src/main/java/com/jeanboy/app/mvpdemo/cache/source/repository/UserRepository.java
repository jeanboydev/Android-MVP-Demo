package com.jeanboy.app.mvpdemo.cache.source.repository;

import android.support.annotation.NonNull;

import com.jeanboy.app.mvpdemo.cache.database.model.UserModel;
import com.jeanboy.app.mvpdemo.cache.source.UserDataSource;
import com.jeanboy.app.mvpdemo.cache.source.base.BaseRepository;
import com.jeanboy.app.mvpdemo.cache.source.callback.SourceCallback;
import com.jeanboy.app.mvpdemo.cache.source.local.UserLocalDataSource;
import com.jeanboy.app.mvpdemo.cache.source.remote.UserRemoteDataSource;
import com.jeanboy.app.mvpdemo.net.entity.UserEntity;
import com.jeanboy.app.mvpdemo.net.mapper.UserModelDataMapper;
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

    /**
     * 保存到数据库
     *
     * @param userModel
     */
    @Override
    public void save(@NonNull UserModel userModel) {
        checkNotNull(userModel);
        userLocalDataSource.save(userModel);
    }

    /**
     * 获取本地缓存
     *
     * @param id
     * @param callback
     */
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

        userLocalDataSource.get(id, new SourceCallback<UserModel>() {
            @Override
            public void onLoaded(UserModel userModel) {
                refreshMemoryCache(userModel);
                callback.onLoaded(userModel);
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });
    }

    /**
     * 获取数据库中所有缓存
     *
     * @param callback
     */
    @Override
    public void getAll(@NonNull SourceCallback<List<UserModel>> callback) {
        checkNotNull(callback);
        userLocalDataSource.getAll(callback);
    }

    /**
     * 删除数据库中的缓存
     *
     * @param userModel
     */
    @Override
    public void delete(@NonNull UserModel userModel) {
        checkNotNull(userModel);
        userLocalDataSource.delete(userModel);
    }

    /**
     * 删除数据库缓存的所有user
     */
    @Override
    public void deleteAll() {
        userLocalDataSource.deleteAll();
    }

    /**
     * 从服务器获取user info
     *
     * @param token
     * @param id
     * @param callback
     * @return
     */
    @Override
    public Call<UserEntity> getInfo(@NonNull String token, @NonNull String id,
                                    @NonNull final RequestCallback<UserEntity> callback) {
        checkNotNull(token);
        checkNotNull(id);
        checkNotNull(callback);

        return userRemoteDataSource.getInfo(token, id, new RequestCallback<UserEntity>() {
            @Override
            public void success(Response<UserEntity> response) {
                UserEntity userEntity = response.body();
                UserModel userModel = new UserModelDataMapper().transform(userEntity);
                refreshMemoryCache(userModel);
                refreshLocalDataSource(userModel);
                callback.success(response);
            }

            @Override
            public void error(int code, String msg) {
                callback.error(code, msg);
            }
        });
    }

    /**
     * 更新user info
     *
     * @param token
     * @param id
     * @param user
     * @param callback
     * @return
     */
    @Override
    public Call<UserEntity> updateInfo(@NonNull String token, @NonNull String id, UserEntity user,
                                      @NonNull RequestCallback<UserEntity> callback) {
        checkNotNull(token);
        checkNotNull(id);
        checkNotNull(user);
        checkNotNull(callback);
        return userRemoteDataSource.updateInfo(token, id, user, callback);
    }

    /**
     * 上传user 头像
     *
     * @param token
     * @param id
     * @param file
     * @param callback
     * @return
     */
    @Override
    public Call<String> uploadAvatar(@NonNull String token, @NonNull String id, @NonNull File file,
                                     @NonNull RequestCallback<String> callback) {
        checkNotNull(token);
        checkNotNull(id);
        checkNotNull(file);
        checkNotNull(callback);
        return userRemoteDataSource.uploadAvatar(token, id, file, callback);
    }

    private void refreshMemoryCache(UserModel userModel) {
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
