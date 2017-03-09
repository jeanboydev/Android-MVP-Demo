package com.jeanboy.app.mvpdemo.component.handler;

import android.content.Context;

import com.jeanboy.app.mvpdemo.config.AppConfig;
import com.jeanboy.app.mvpdemo.cache.database.dao.DaoMaster;
import com.jeanboy.app.mvpdemo.cache.database.dao.DaoSession;
import com.jeanboy.lib.common.manager.database.DBHandler;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * Created by jeanboy on 2016/12/14.
 */

public class GreenDaoHandler implements DBHandler {

    private Context context;
    private DaoSession daoSession;

    private DaoMaster getDaoMaster(Context context) {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, AppConfig.DB_NAME, null);
        return new DaoMaster(helper.getWritableDatabase());
    }

    private DaoSession getDaoSession(Context context) {
        if (daoSession == null) {
            daoSession = getDaoMaster(context).newSession();
        }
        return daoSession;
    }

    @Override
    public void init(Context context) {
        this.context = context.getApplicationContext();
    }

    @Override
    public <T> long save(T t) {
        return getDaoSession(context).insert(t);
    }

    @Override
    public <T> long update(T t) {
        return getDaoSession(context).insertOrReplace(t);
    }

    @Override
    public <T> void delete(T t) {
        getDaoSession(context).delete(t);
    }

    @Override
    public <T> void deleteAll(Class<T> clazz) {
        getDaoSession(context).deleteAll(clazz);
    }

    @Override
    public <T> T getById(Class<T> clazz, Long id) {
        return getDaoSession(context).load(clazz, id);
    }

    @Override
    public <T> List<T> getAll(Class<T> clazz) {
        return getDaoSession(context).loadAll(clazz);
    }

    @Override
    public <T> List<T> query(Class<T> clazz, String where, String[] params) {
        return getDaoSession(context).queryRaw(clazz, where, params);
    }


    public <T> QueryBuilder<T> queryBuilder(Class<T> clazz) {
        return getDaoSession(context).queryBuilder(clazz);
    }
}
