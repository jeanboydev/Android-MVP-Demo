package com.jeanboy.app.mvpdemo.architecture;

/**
 * Created by jeanboy on 2017/3/9.
 */

public interface View<T> {

    void display(int action, T t);
}
