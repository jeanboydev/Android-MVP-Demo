package com.jeanboy.app.mvpdemo.cache.source.local;

import com.jeanboy.app.mvpdemo.cache.database.model.TokenModel;
import com.jeanboy.app.mvpdemo.cache.source.TokenDataSource;
import com.jeanboy.app.mvpdemo.cache.source.callback.SourceCallback;
import com.jeanboy.lib.common.manager.database.DBManager;

import java.util.List;

/**
 * Created by jeanboy on 2017/3/13.
 */

public class TokenLocalDataSource implements TokenDataSource.Local {

    private static TokenLocalDataSource INSTANCE;

    public static TokenLocalDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TokenLocalDataSource();
        }
        return INSTANCE;
    }

    @Override
    public void save(TokenModel tokenModel) {
        DBManager.getInstance().save(tokenModel, null);
    }

    @Override
    public void get(Long id, final SourceCallback<TokenModel> callback) {
        DBManager.getInstance().getById(TokenModel.class, id, new DBManager.Callback<TokenModel>() {
            @Override
            public void onFinish(TokenModel tokenModel) {
                if (callback != null) {
                    if (tokenModel == null) {
                        callback.onDataNotAvailable();
                        return;
                    }
                    callback.onLoaded(tokenModel);
                }
            }
        });
    }

    @Override
    public void getAll(final SourceCallback<List<TokenModel>> callback) {
        DBManager.getInstance().getAll(TokenModel.class, new DBManager.Callback<List<TokenModel>>() {
            @Override
            public void onFinish(List<TokenModel> tokenModels) {
                if (callback != null) {
                    if (tokenModels == null) {
                        callback.onDataNotAvailable();
                        return;
                    }
                    callback.onLoaded(tokenModels);
                }
            }
        });
    }

    @Override
    public void delete(TokenModel tokenModel) {
        DBManager.getInstance().delete(tokenModel, null);
    }

    @Override
    public void deleteAll() {
        DBManager.getInstance().deleteAll(TokenModel.class, null);
    }

    @Override
    public void getToken(String username, final SourceCallback<TokenModel> callback) {
        String where = "where username == ?";
        DBManager.getInstance().query(TokenModel.class, where, new String[]{username}, new DBManager.Callback<List<TokenModel>>() {
            @Override
            public void onFinish(List<TokenModel> tokenModels) {
                if (callback != null) {
                    if (tokenModels != null && tokenModels.size() > 0) {
                        callback.onLoaded(tokenModels.get(0));
                    } else {
                        callback.onDataNotAvailable();
                    }
                }
            }
        });
    }
}
