package com.jeanboy.app.mvpdemo.cache.source;

import android.support.annotation.NonNull;

import com.jeanboy.app.mvpdemo.cache.database.model.UserModel;
import com.jeanboy.app.mvpdemo.cache.source.callback.SourceCallback;

import java.util.List;

/**
 * Created by jeanboy on 2017/3/9.
 */

public interface UserDataSource {

    void getUsers(@NonNull SourceCallback<List<UserModel>> callback);

    void getUser(@NonNull Long userId, @NonNull SourceCallback<UserModel> callback);

    void saveUser(@NonNull UserModel userModel);

    void refreshUsers();

    void deleteAllUser();

    void deleteUser(@NonNull UserModel userModel);
}
