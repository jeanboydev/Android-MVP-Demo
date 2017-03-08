package com.jeanboy.app.mvpdemo.component.task;

import com.jeanboy.app.mvpdemo.model.bean.FileBean;

/**
 * Created by Next on 2016/8/17.
 */
public class TestRunning {


    public static void test() {
        FileBean fileBean = new FileBean();

        UploadTask uploadTask = new UploadTask(fileBean);
        TaskManager.getInstance().add(uploadTask);


        uploadTask.setTaskListener(new TaskListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onUpdate() {

            }

            @Override
            public void onComplete() {

            }
        });

        uploadTask.cancel();


    }
}
