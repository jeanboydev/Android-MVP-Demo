package com.jeanboy.common.task;

/**
 * Created by Next on 2016/8/16.
 */
public interface BaseTask {


    void onAdded();

    void onRun();

    void onComplete();

    void onCancel();

}
