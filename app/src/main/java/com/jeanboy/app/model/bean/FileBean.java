package com.jeanboy.app.model.bean;

import com.jeanboy.manager.database.AppDataBase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ModelContainer;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by Next on 2016/7/14.
 */
@ModelContainer
@Table(database= AppDataBase.class)
public class FileBean extends BaseModel{
    @Column
    private long id;
    @Column
    private String url;
    @Column
    private String path;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
