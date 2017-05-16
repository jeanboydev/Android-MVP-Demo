package com.jeanboy.lib.common.manager.net;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;

/**
 * Created by jeanboy on 2017/2/10.
 */

public class NetManager {

    private NetHandler netHandler;

    private static class SingletonHolder {
        @SuppressLint("StaticFieldLeak")
        private static NetManager instance = new NetManager();
    }

    public static NetManager getInstance() {
        return SingletonHolder.instance;
    }

    private NetManager() {
    }

    public void build(@NonNull NetHandler netHandler) {
        this.netHandler = netHandler;
    }

    public NetHandler getNetHandler() {
        return netHandler;
    }
}
