package com.jeanboy.app.mvpdemo.cache;

import com.jeanboy.app.mvpdemo.cache.source.UserRepository;
import com.jeanboy.app.mvpdemo.cache.source.local.UserLocalDataSource;
import com.jeanboy.app.mvpdemo.cache.source.remote.UserRemoteDataSource;
import com.jeanboy.app.mvpdemo.tasks.domain.usecase.UserGetTask;

/**
 * Created by jeanboy on 2017/3/10.
 */

public class Injection {

    public static UserRepository provideUserRepository() {
        return UserRepository.getInstance(UserRemoteDataSource.getInstance(), UserLocalDataSource.getInstance());
    }

    public static UserGetTask provideUserGetTask() {
        return new UserGetTask(provideUserRepository());
    }
}
