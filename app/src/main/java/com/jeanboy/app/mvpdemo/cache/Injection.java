package com.jeanboy.app.mvpdemo.cache;

import com.jeanboy.app.mvpdemo.cache.source.TokenRepository;
import com.jeanboy.app.mvpdemo.cache.source.UserRepository;
import com.jeanboy.app.mvpdemo.cache.source.local.TokenLocalDataSource;
import com.jeanboy.app.mvpdemo.cache.source.local.UserLocalDataSource;
import com.jeanboy.app.mvpdemo.cache.source.remote.TokenRemoteDataSource;
import com.jeanboy.app.mvpdemo.cache.source.remote.UserRemoteDataSource;
import com.jeanboy.app.mvpdemo.domain.usecase.TokenGetCacheInfoTask;
import com.jeanboy.app.mvpdemo.domain.usecase.TokenGetRemoteInfoTask;
import com.jeanboy.app.mvpdemo.domain.usecase.UserGetCacheInfoTask;
import com.jeanboy.app.mvpdemo.domain.usecase.UserGetRemoteInfoTask;

/**
 * Created by jeanboy on 2017/3/10.
 */

public class Injection {

    public static UserRepository provideUserRepository() {
        return UserRepository.getInstance(UserLocalDataSource.getInstance(), UserRemoteDataSource.getInstance());
    }

    public static TokenRepository provideTokenRepository() {
        return TokenRepository.getInstance(TokenLocalDataSource.getInstance(), TokenRemoteDataSource.getInstance());
    }

    public static UserGetRemoteInfoTask provideUserGetRemoteInfoTask() {
        return new UserGetRemoteInfoTask(provideUserRepository());
    }

    public static UserGetCacheInfoTask provideUserGetCacheInfoTask() {
        return new UserGetCacheInfoTask(provideUserRepository());
    }

    public static TokenGetRemoteInfoTask provideTokenGetRemoteInfoTask() {
        return new TokenGetRemoteInfoTask(provideTokenRepository());
    }

    public static TokenGetCacheInfoTask provideTokenGetCacheInfoTask() {
        return new TokenGetCacheInfoTask(provideTokenRepository());
    }
}
