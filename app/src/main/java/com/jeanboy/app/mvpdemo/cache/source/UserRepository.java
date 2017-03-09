package com.jeanboy.app.mvpdemo.cache.source;

import android.support.annotation.NonNull;

import com.jeanboy.app.mvpdemo.cache.database.model.UserModel;
import com.jeanboy.app.mvpdemo.cache.source.callback.SourceCallback;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by jeanboy on 2017/3/9.
 */

public class UserRepository implements UserDataSource {

    private static UserRepository INSTANCE = null;

    Map<String, UserModel> mCachedUsers;

    boolean mCacheIsDirty = false;

    private final UserDataSource userLocalDataSource;
    private final UserDataSource userRemoteDataSource;

    public UserRepository(@NonNull UserDataSource userLocalDataSource,
                          @NonNull UserDataSource userRemoteDataSource) {
        this.userLocalDataSource = checkNotNull(userLocalDataSource);
        this.userRemoteDataSource = checkNotNull(userRemoteDataSource);
    }

    public UserRepository getInstance(UserDataSource userLocalDataSource,
                                      UserDataSource userRemoteDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new UserRepository(userLocalDataSource, userRemoteDataSource);
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    @Override
    public void getUsers(@NonNull final SourceCallback<List<UserModel>> callback) {
        checkNotNull(callback);

//        //内存缓存没有失效，优先从内存取出
//        if (mCachedUsers != null && !mCacheIsDirty) {
//            callback.onLoaded(new ArrayList<>(mCachedUsers.values()));
//            return;
//        }
//
//        if (mCacheIsDirty) {//本地失效，从服务器获取
//            getUsersFromRemote(callback);
//        } else {//本地未失效，从数据库读取
//            userLocalDataSource.getUsers(new SourceCallback<List<UserModel>>() {
//                @Override
//                public void onLoaded(List<UserModel> userModels) {
//                    refreshCache(userModels);
//                    callback.onLoaded(new ArrayList<>(mCachedUsers.values()));
//                }
//
//                @Override
//                public void onDataNotAvailable() {//数据库没有数据，从服务器获取
//                    getUsersFromRemote(callback);
//                }
//            });
//        }
    }

    @Override
    public void getUser(@NonNull final Long userId, @NonNull final SourceCallback<UserModel> callback) {
        checkNotNull(userId);
        checkNotNull(callback);
        //内存缓存没有失效，优先从内存取出
        if (mCachedUsers != null && !mCacheIsDirty) {
            callback.onLoaded(mCachedUsers.get(String.valueOf(userId)));
            return;
        }

        if (mCacheIsDirty) {//本地失效，从服务器获取
            getUserFromRemote(userId, callback);
        } else {//本地未失效，从数据库读取
            userLocalDataSource.getUser(userId, new SourceCallback<UserModel>() {
                @Override
                public void onLoaded(UserModel userModel) {
                    refreshCache(userModel);
                    callback.onLoaded(userModel);
                }

                @Override
                public void onDataNotAvailable() {//数据库没有数据，从服务器获取
                    getUserFromRemote(userId, callback);
                }
            });
        }
    }

    @Override
    public void saveUser(@NonNull UserModel userModel) {
        checkNotNull(userModel);
        userRemoteDataSource.saveUser(userModel);
        userLocalDataSource.saveUser(userModel);

        if (mCachedUsers == null) {
            mCachedUsers = new LinkedHashMap<>();
        }
        mCachedUsers.put(String.valueOf(userModel.getId()), userModel);
    }

    @Override
    public void refreshUsers() {
        mCacheIsDirty = true;
    }

    @Override
    public void deleteAllUser() {
        userRemoteDataSource.deleteAllUser();
        userLocalDataSource.deleteAllUser();

        if (mCachedUsers == null) {
            mCachedUsers = new LinkedHashMap<>();
        }
        mCachedUsers.clear();
    }

    @Override
    public void deleteUser(@NonNull UserModel userModel) {
        checkNotNull(userModel);
        userRemoteDataSource.deleteUser(userModel);
        userLocalDataSource.deleteUser(userModel);

        mCachedUsers.remove(String.valueOf(userModel.getId()));
    }

    private void getUserFromRemote(@NonNull Long userId, @NonNull final SourceCallback<UserModel> callback) {
        userRemoteDataSource.getUser(userId, new SourceCallback<UserModel>() {
            @Override
            public void onLoaded(UserModel userModel) {
                refreshCache(userModel);
                refreshLocalDataSource(userModel);
                callback.onLoaded(userModel);
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });
    }

    private void refreshCache(UserModel userModel) {
        if (mCachedUsers == null) {
            mCachedUsers = new LinkedHashMap<>();
        }
        mCachedUsers.clear();
        mCachedUsers.put(String.valueOf(userModel.getId()), userModel);
        mCacheIsDirty = false;
    }

    private void refreshLocalDataSource(UserModel userModel) {
        userLocalDataSource.deleteAllUser();
        userLocalDataSource.saveUser(userModel);
    }


}
