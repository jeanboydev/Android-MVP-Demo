package com.jeanboy.app.mvpdemo.cache.source;

import android.support.annotation.NonNull;

import com.jeanboy.app.mvpdemo.cache.database.model.TokenModel;
import com.jeanboy.app.mvpdemo.cache.source.base.BaseRepository;
import com.jeanboy.app.mvpdemo.cache.source.callback.SourceCallback;
import com.jeanboy.app.mvpdemo.cache.source.local.TokenLocalDataSource;
import com.jeanboy.app.mvpdemo.cache.source.remote.TokenRemoteDataSource;
import com.jeanboy.lib.common.manager.net.RequestCallback;
import com.jeanboy.lib.common.manager.net.StatusCode;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by jeanboy on 2017/3/13.
 */

public class TokenRepository implements BaseRepository, TokenDataSource.Local, TokenDataSource.Remote {

    private static TokenRepository INSTANCE = null;

    Map<String, TokenModel> mCacheMap;

    boolean mCacheIsDirty = false;

    private final static int RETRY_TIMES = 3;//刷新token重试次数

    private int retryErrorCount = 0;//刷新token失败次数

    private final TokenLocalDataSource tokenLocalDataSource;
    private final TokenRemoteDataSource tokenRemoteDataSource;

    public TokenRepository(@NonNull TokenLocalDataSource tokenLocalDataSource,
                           @NonNull TokenRemoteDataSource tokenRemoteDataSource) {
        this.tokenLocalDataSource = checkNotNull(tokenLocalDataSource);
        this.tokenRemoteDataSource = checkNotNull(tokenRemoteDataSource);
    }

    public static TokenRepository getInstance(TokenLocalDataSource tokenLocalDataSource,
                                              TokenRemoteDataSource tokenRemoteDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new TokenRepository(tokenLocalDataSource, tokenRemoteDataSource);
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
     * 将token保存到数据库中
     *
     * @param tokenModel
     */
    @Override
    public void save(@NonNull TokenModel tokenModel) {
        checkNotNull(tokenModel);
        tokenLocalDataSource.save(tokenModel);
    }

    /**
     * 根据id获取数据库中缓存的token
     *
     * @param id
     * @param callback
     */
    @Override
    public void get(@NonNull Long id, @NonNull SourceCallback<TokenModel> callback) {
        checkNotNull(id);
        checkNotNull(callback);
        tokenLocalDataSource.get(id, callback);
    }

    /**
     * 获取数据库中缓存的token
     *
     * @param callback
     */
    @Override
    public void getAll(@NonNull SourceCallback<List<TokenModel>> callback) {
        checkNotNull(callback);
        tokenLocalDataSource.getAll(callback);
    }

    /**
     * 删除数据库缓存的token
     *
     * @param tokenModel
     */
    @Override
    public void delete(@NonNull TokenModel tokenModel) {
        checkNotNull(tokenModel);
        tokenLocalDataSource.delete(tokenModel);
    }

    /**
     * 删除数据库缓存的所有token
     */
    @Override
    public void deleteAll() {
        tokenLocalDataSource.deleteAll();
    }

    /**
     * 根据username获取数据库中缓存的token
     *
     * @param username
     * @param callback
     */
    @Override
    public void getToken(String username, SourceCallback<TokenModel> callback) {
        tokenLocalDataSource.getToken(username, callback);
    }

    /**
     * 从服务器获取token
     *
     * @param username
     * @param password
     * @param callback
     * @return
     */
    @Override
    public Call<TokenModel> getToken(@NonNull final String username, @NonNull final String password,
                                     @NonNull final RequestCallback<TokenModel> callback) {
        checkNotNull(username);
        checkNotNull(password);
        checkNotNull(callback);
        return tokenRemoteDataSource.getToken(username, password, callback);
    }

    @Override
    public Call<TokenModel> refreshToken(@NonNull String refreshToken, @NonNull RequestCallback<TokenModel> callback) {
        checkNotNull(refreshToken);
        checkNotNull(callback);
        return tokenRemoteDataSource.refreshToken(refreshToken, callback);
    }

    /**
     * 获取token，失效尝试自动刷新
     *
     * @param username
     * @param callback
     */
    public void getTokenAutoRefresh(@NonNull final String username, @NonNull final SourceCallback<TokenModel> callback) {

        checkNotNull(username);
        checkNotNull(callback);

        TokenModel memoryCache = getFromMemory(username);
        if (memoryCache != null && !mCacheIsDirty) {//获取内存中缓存
            if (checkAccessTokenIfInvalid(memoryCache)) {//access_token已失效，尝试刷新
                refreshToken(memoryCache.getRefreshToken(), callback);
            } else {
                callback.onLoaded(memoryCache);
            }
            return;
        }


        getToken(username, new SourceCallback<TokenModel>() {
            @Override
            public void onLoaded(TokenModel tokenModel) {
                if (tokenModel == null) {//数据库中不存在
                    callback.onDataNotAvailable();
                    return;
                }

                if (mCacheIsDirty || checkAccessTokenIfInvalid(tokenModel)) {//已标记本地数据过时，或者失效，尝试刷新
                    retryErrorCount = 0;
                    refreshToken(tokenModel.getRefreshToken(), callback);
                } else {
                    refreshCache(tokenModel);
                    callback.onLoaded(tokenModel);
                }
            }

            @Override
            public void onDataNotAvailable() {//数据库中不存在
                callback.onDataNotAvailable();
            }
        });


    }

    /**
     * access_token失效时，尝试自动刷新access_token
     *
     * @param refreshToken
     * @param callback
     * @return
     */
    private Call<TokenModel> refreshToken(@NonNull final String refreshToken,
                                          @NonNull final SourceCallback<TokenModel> callback) {
        checkNotNull(refreshToken);
        checkNotNull(callback);

        return refreshToken(refreshToken, new RequestCallback<TokenModel>() {
            @Override
            public void success(Response<TokenModel> response) {
                TokenModel tokenModel = response.body();
                retryErrorCount = 0;
                refreshCache(tokenModel);//刷新内存中缓存
                refreshLocalDataSource(tokenModel);//刷新数据库缓存
                callback.onLoaded(tokenModel);
            }

            @Override
            public void error(int code, String msg) {
                if (StatusCode.CODE_UNAUTHORIZED == code) {//403表示refresh_token已失效
                    retryErrorCount = 0;
                    callback.onDataNotAvailable();
                    return;
                }

                if (++retryErrorCount <= RETRY_TIMES) {//刷新access_token，重试三次
                    refreshToken(refreshToken, callback);
                } else {
                    retryErrorCount = 0;
                    callback.onDataNotAvailable();
                }
            }
        });
    }

    /**
     * 刷新内存中缓存
     *
     * @param tokenModel
     */
    private void refreshCache(TokenModel tokenModel) {
        if (mCacheMap == null) {
            mCacheMap = new LinkedHashMap<>();
        }
        mCacheMap.clear();
        mCacheMap.put(tokenModel.getUsername(), tokenModel);
        mCacheIsDirty = false;
    }

    private void refreshLocalDataSource(TokenModel tokenModel) {
        deleteAll();//删除数据库中缓存
        save(tokenModel);//保存到数据库中
    }

    /**
     * 获取内存中缓存
     *
     * @param key
     * @return
     */
    private TokenModel getFromMemory(String key) {
        if (mCacheMap == null) {
            mCacheMap = new LinkedHashMap<>();
        }
        return mCacheMap.get(key);
    }


    /**
     * 检查本地access_token是否已失效
     *
     * @param tokenModel
     * @return true 失效, false 未失效
     */
    private boolean checkAccessTokenIfInvalid(TokenModel tokenModel) {
        long tokenInvalidTime = tokenModel.getExpiresIn() + tokenModel.getCreateTime();
        return System.currentTimeMillis() >= tokenInvalidTime;
    }


}
