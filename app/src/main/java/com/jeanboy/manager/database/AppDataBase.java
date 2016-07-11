package com.jeanboy.manager.database;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by Next on 2016/7/11.
 */
@Database(name = AppDataBase.NAME, version = AppDataBase.VERSION)
public class AppDataBase {

    //数据库名称
    public static final String NAME = "demo_db";
    //数据库版本号
    public static final int VERSION = 1;
}
