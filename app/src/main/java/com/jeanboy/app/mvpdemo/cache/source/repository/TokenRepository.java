package com.jeanboy.app.mvpdemo.cache.source.repository;

import android.support.annotation.NonNull;

import com.jeanboy.app.mvpdemo.cache.database.model.TokenModel;
import com.jeanboy.app.mvpdemo.cache.source.TokenDataSource;
import com.jeanboy.app.mvpdemo.cache.source.base.BaseRepository;
import com.jeanboy.app.mvpdemo.cache.source.callback.SourceCallback;
import com.jeanboy.app.mvpdemo.cache.source.local.TokenLocalDataSource;
import com.jeanboy.app.mvpdemo.cache.source.remote.TokenRemoteDataSource;
import com.jeanboy.app.mvpdemo.component.handler.OkHttpHandler;
import com.jeanboy.app.mvpdemo.net.entity.TokenEntity;
import com.jeanboy.app.mvpdemo.net.mapper.TokenModelDataMapper;
import com.jeanboy.lib.common.manager.net.RequestCallback;
import com.jeanboy.lib.common.manager.net.ResponseData;
import com.jeanboy.lib.common.manager.net.StatusCode;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;

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
     * 根据userId获取数据库中缓存的token
     *
     * @param userId
     * @param callback
     */
    @Override
    public void getToken(Long userId, SourceCallback<TokenModel> callback) {
        tokenLocalDataSource.getToken(userId, callback);
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
    public Call<TokenEntity> getToken(String username, String password, RequestCallback<ResponseData<TokenEntity>> callback) {
        checkNotNull(username);
        checkNotNull(password);
        checkNotNull(callback);
        return tokenRemoteDataSource.getToken(username, password, callback);
    }

    /**
     * 使用refresh_token从服务器重新获取access_token
     *
     * @param refreshToken
     * @param callback
     * @return
     */
    @Override
    public Call<TokenEntity> refreshToken(String refreshToken, RequestCallback<ResponseData<TokenEntity>> callback) {
        checkNotNull(refreshToken);
        checkNotNull(callback);
        return tokenRemoteDataSource.refreshToken(refreshToken, callback);
    }

    /**
     * 获取token，失效尝试自动刷新
     *
     * @param userId
     * @param callback
     */
    public void getTokenAutoRefresh(@NonNull Long userId, @NonNull final SourceCallback<TokenModel> callback) {

        checkNotNull(userId);
        checkNotNull(callback);

        TokenModel memoryCache = getFromMemory(userId);
        if (memoryCache != null && !mCacheIsDirty) {//获取内存中缓存
            if (checkAccessTokenIfInvalid(memoryCache)) {//access_token已失效，尝试刷新
                refreshAccessToken(memoryCache.getRefreshToken(), callback);
            } else {
                callback.onLoaded(memoryCache);
            }
            return;
        }

        //读取数据库缓存
        getToken(userId, new SourceCallback<TokenModel>() {
            @Override
            public void onLoaded(TokenModel tokenModel) {
                if (tokenModel == null) {//数据库中不存在
                    callback.onDataNotAvailable();
                    return;
                }

                if (mCacheIsDirty || checkAccessTokenIfInvalid(tokenModel)) {//已标记本地数据过时，或者失效，尝试刷新
                    retryErrorCount = 0;
                    refreshAccessToken(tokenModel.getRefreshToken(), callback);
                } else {
                    refreshMemoryCache(tokenModel);
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
    private Call<TokenEntity> refreshAccessToken(@NonNull final String refreshToken,
                                                 @NonNull final SourceCallback<TokenModel> callback) {
        checkNotNull(refreshToken);
        checkNotNull(callback);

        //使用refresh_token获取access_token
        return refreshToken(refreshToken, new RequestCallback<ResponseData<TokenEntity>>() {
            @Override
            public void onSuccess(ResponseData<TokenEntity> response) {
                TokenEntity tokenEntity = response.getBody();
                TokenModel tokenModel = new TokenModelDataMapper().transform(tokenEntity);
                retryErrorCount = 0;
                refreshMemoryCache(tokenModel);//刷新内存中缓存
                refreshLocalDataSource(tokenModel);//刷新数据库缓存
                callback.onLoaded(tokenModel);
            }

            @Override
            public void onError(int code, String msg) {
                if (StatusCode.CODE_UNAUTHORIZED == code) {//403表示refresh_token已失效
                    retryErrorCount = 0;
                    callback.onDataNotAvailable();
                    return;
                }

                if (++retryErrorCount <= RETRY_TIMES) {//刷新access_token，重试三次
                    refreshAccessToken(refreshToken, callback);
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
    private void refreshMemoryCache(TokenModel tokenModel) {
        if (mCacheMap == null) {
            mCacheMap = new LinkedHashMap<>();
        }
        mCacheMap.clear();
        mCacheMap.put(String.valueOf(tokenModel.getUserId()), tokenModel);
        mCacheIsDirty = false;
    }

    private void refreshLocalDataSource(TokenModel tokenModel) {
        deleteAll();//删除数据库中缓存
        save(tokenModel);//保存到数据库中
    }

    /**
     * 获取内存中缓存
     *
     * @param userId
     * @return
     */
    private TokenModel getFromMemory(Long userId) {
        if (mCacheMap == null) {
            mCacheMap = new LinkedHashMap<>();
        }
        return mCacheMap.get(String.valueOf(userId));
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
