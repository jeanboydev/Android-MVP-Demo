package com.jeanboy.app.mvpdemo.component.task;

/**
 * Created by Next on 2016/8/16.
 */
public abstract class Task implements BaseTask, Runnable {

    private boolean cancel = false;

    private TaskListener taskListener;

    public void setTaskListener(TaskListener taskListener) {
        this.taskListener = taskListener;
    }


    public boolean isCancel() {
        return cancel;
    }

    public Task() {
        onAdded();
    }

    /**
     * 取消任务
     */
    public void cancel() {
        cancel = true;
        onCancel();
    }

    @Override
    public void run() {
        if (cancel) return;
        if(taskListener!=null){
            taskListener.onStart();
        }
        onRun();
        if(taskListener!=null){
            taskListener.onComplete();
        }
        onComplete();
    }

}
