package com.jeanboy.manager.database;

import com.raizlabs.android.dbflow.sql.language.OrderBy;
import com.raizlabs.android.dbflow.sql.language.SQLCondition;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.sql.language.Select;
import com.raizlabs.android.dbflow.structure.Model;

import java.util.List;

/**
 * 参见https://github.com/Raizlabs/DBFlow/blob/master/usage2/SQLiteWrapperLanguage.md
 * Created by Next on 2016/7/4.
 */
public class DataManager {

    private static DataManager instance;

    public static DataManager getInstance() {
        if (instance == null) {
            synchronized (DataManager.class) {
                if (instance == null) {
                    instance = new DataManager();
                }
            }
        }
        return instance;
    }

    /**
     * @param table
     * @param where T_Table.*.eq("xx")
     * @param <T>
     * @return
     */
    public <T extends Model> T get(Class<T> table, SQLCondition... where) {
        return new Select().from(table).where(where).querySingle();
    }

    public <T extends Model> List<T> getList(Class<T> table, OrderBy orderBy, SQLCondition... where) {
        return new Select().from(table).where(where).orderBy(orderBy).queryList();
    }

    public <T extends Model> void save(T t) {
        t.save();
    }

    public <T extends Model> void update(T t) {
        t.update();
    }

    public <T extends Model> void delete(T t) {
        t.delete();
    }

    public <T extends Model> void delete(Class<T> table, SQLCondition... where) {
        SQLite.delete(table).where(where).execute();
    }
}
