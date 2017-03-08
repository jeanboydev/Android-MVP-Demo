package com.jeanboy.app.mvpdemo.model.sources;

/**
 * Created by Next on 2016/7/5.
 */
public interface UserDataSource {

    void refresh();

    void delete(String id);

}
