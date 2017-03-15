package com.jeanboy.app.mvpdemo.net.mapper;

import android.support.annotation.NonNull;

import com.jeanboy.app.mvpdemo.cache.database.model.UserModel;
import com.jeanboy.app.mvpdemo.net.entity.UserEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by jeanboy on 2017/3/15.
 */

public class UserModelDataMapper {

    public UserModelDataMapper() {
    }

    public UserModel transform(@NonNull UserEntity userEntity) {
        checkNotNull(userEntity);
        UserModel userModel = new UserModel();
        userModel.setId(userEntity.getUserId());
        userModel.setUserName(userEntity.getUserName());
        userModel.setUserNick(userEntity.getUserNick());
        userModel.setCreateTime(System.currentTimeMillis());
        return userModel;
    }

    public Collection<UserModel> transfrom(Collection<UserEntity> userEntityCollection) {
        Collection<UserModel> userModelsCollection;
        if (userEntityCollection != null && !userEntityCollection.isEmpty()) {
            userModelsCollection = new ArrayList<>();
            for (UserEntity userEntity : userEntityCollection) {
                userModelsCollection.add(transform(userEntity));
            }
        } else {
            userModelsCollection = Collections.emptyList();
        }
        return userModelsCollection;
    }

}
