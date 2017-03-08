package com.jeanboy.app.mvpdemo.component.task;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Next on 2016/8/16.
 */
public class TaskManager {

    private ExecutorService tasks = Executors.newFixedThreadPool(3);

    private static TaskManager instance;

    private TaskManager() {
    }

    public static TaskManager getInstance() {
        if (instance == null) {
            synchronized (TaskManager.class) {
                if (instance == null) {
                    instance = new TaskManager();
                }
            }
        }
        return instance;
    }

    public void add(Task task) {
        tasks.execute(task);
    }

}
