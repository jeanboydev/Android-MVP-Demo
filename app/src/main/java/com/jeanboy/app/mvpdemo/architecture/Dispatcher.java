package com.jeanboy.app.mvpdemo.architecture;

/**
 * Created by jeanboy on 2017/3/9.
 */

public class Dispatcher {

    private View view;

    public void register(View view) {
        this.view = view;
    }

    public void unregister(View view) {
        this.view = null;
    }

    public void dispatch(final int action, Object... data) {

    }
}
