package com.jeanboy.app.mvpdemo.net.mapper;

import android.support.annotation.NonNull;

import com.jeanboy.app.mvpdemo.cache.database.model.TokenModel;
import com.jeanboy.app.mvpdemo.net.entity.TokenEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by jeanboy on 2017/3/15.
 */

public class TokenModelDataMapper {

    public TokenModelDataMapper() {
    }

    public TokenModel transform(@NonNull TokenEntity tokenEntity) {
        checkNotNull(tokenEntity);
        TokenModel tokenModel=new TokenModel();
        tokenModel.setUserId(tokenEntity.getUser_id());
        tokenModel.setExpiresIn(tokenEntity.getExpires_in());
        tokenModel.setRefreshToken(tokenEntity.getRefresh_token());
        tokenModel.setAccessToken(tokenEntity.getAccess_token());
        tokenModel.setCreateTime(System.currentTimeMillis());
        return tokenModel;
    }

    public Collection<TokenModel> transfrom(Collection<TokenEntity> tokenEntityCollection) {
        Collection<TokenModel> tokenModelCollection;
        if (tokenEntityCollection != null && !tokenEntityCollection.isEmpty()) {
            tokenModelCollection = new ArrayList<>();
            for (TokenEntity tokenEntity : tokenEntityCollection) {
                tokenModelCollection.add(transform(tokenEntity));
            }
        } else {
            tokenModelCollection = Collections.emptyList();
        }
        return tokenModelCollection;
    }

}
